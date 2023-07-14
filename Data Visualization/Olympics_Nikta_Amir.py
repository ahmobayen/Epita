from dash import Dash, html, dcc, Input, Output
import plotly.graph_objects as go
import pandas as pd

# Create Dash app
app = Dash(__name__)

# Read Input Data
df = pd.read_csv('./data/athlete_events.csv')

# Filter out rows with null values in Medal column
country_medals = df.groupby(['NOC', 'Medal'])['Medal'].count().unstack(fill_value=0)
country_medals['Total Medals'] = country_medals.sum(axis=1)
country_medals = country_medals.sort_values('Total Medals', ascending=False)

# Create the Choropleth chart using Plotly
choropleth_chart = go.Figure(data=go.Choropleth(
    locations=country_medals.index,
    z=country_medals['Total Medals'],
    text=country_medals.index,
    colorscale='YlOrRd',
    autocolorscale=False,
    reversescale=True,
    marker_line_color='darkgray',
    marker_line_width=0.5,
    colorbar_title='Number of Medals',
))

choropleth_chart.update_layout(
    title_text='Countries with the Most Medals',
    geo=dict(
        showframe=False,
        showcoastlines=False,
        projection_type='equirectangular',
    )
)

# Create the Line chart using Plotly
line_chart = go.Figure()

# Create the Pie chart using Plotly
pie_chart = go.Figure()

app.layout = html.Div(children=[
    html.H1(children='Amir Mobayen | Nikita Chistyakov'),

    html.Div(children='''
        Dash: A web application framework for your data.
    '''),

    dcc.Graph(
        id='choropleth-chart',
        figure=choropleth_chart
    ),

    dcc.Graph(
        id='line-chart',
        figure=line_chart
    ),

    dcc.Graph(
        id='pie-chart',
        figure=pie_chart
    )
])


@app.callback(
    Output('line-chart', 'figure'),
    Input('choropleth-chart', 'clickData')
)
def update_line_chart(click_data):
    line_chart = go.Figure()  # Reset the line chart

    if click_data is not None:
        selected_country = click_data['points'][0]['location']
        country_data = df[df['NOC'] == selected_country]

        gold_medals = (
            country_data[country_data['Medal'] == 'Gold']
            .groupby('Year')['Medal']
            .count()
            .cumsum()
        )

        silver_medals = (
            country_data[country_data['Medal'] == 'Silver']
            .groupby('Year')['Medal']
            .count()
            .cumsum()
        )

        bronze_medals = (
            country_data[country_data['Medal'] == 'Bronze']
            .groupby('Year')['Medal']
            .count()
            .cumsum()
        )

        line_chart.add_trace(go.Scatter(
            x=gold_medals.index,
            y=gold_medals.values,
            mode='lines+markers',
            name='Gold',
            marker=dict(size=8, color='gold'),
            hovertemplate='<b>Year</b>: %{x}<br><b>Cumulative Gold Medals</b>: %{y}',
        ))

        line_chart.add_trace(go.Scatter(
            x=silver_medals.index,
            y=silver_medals.values,
            mode='lines+markers',
            name='Silver',
            marker=dict(size=8, color='silver'),
            hovertemplate='<b>Year</b>: %{x}<br><b>Cumulative Silver Medals</b>: %{y}',
        ))

        line_chart.add_trace(go.Scatter(
            x=bronze_medals.index,
            y=bronze_medals.values,
            mode='lines+markers',
            name='Bronze',
            marker=dict(size=8, color='peru'),
            hovertemplate='<b>Year</b>: %{x}<br><b>Cumulative Bronze Medals</b>: %{y}',
        ))

        line_chart.update_layout(
            title=f'Cumulative Medals over the Years ({selected_country})',
            xaxis_title='Year',
            yaxis_title='Cumulative Medals',
            hovermode='closest',
            plot_bgcolor='rgba(0,0,0,0)'
        )

    return line_chart


@app.callback(
    Output('pie-chart', 'figure'),
    Input('choropleth-chart', 'clickData')
)
def update_pie_chart(click_data):
    pie_chart = go.Figure()  # Reset the pie chart

    if click_data is not None:
        selected_country = click_data['points'][0]['location']
        medal_details = country_medals.loc[selected_country, ['Gold', 'Silver', 'Bronze']]
        labels = ['Gold', 'Silver', 'Bronze']
        values = [medal_details['Gold'], medal_details['Silver'], medal_details['Bronze']]
        colors = ['gold', 'silver', 'peru']

        pie_chart.add_trace(go.Pie(
            labels=labels,
            values=values,
            marker=dict(colors=colors)
        ))

        pie_chart.update_layout(
            title=f'Medal Details for {selected_country}',
            showlegend=True
        )

    return pie_chart


# Execute Web Service
if __name__ == '__main__':
    app.run(debug=True)

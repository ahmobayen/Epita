from dash import Dash, html, dcc
import plotly.express as px
import pandas as pd
import plotly.graph_objects as go

app = Dash(__name__)

# Data Set Source: https://www.kaggle.com/datasets/leoth9/international-debt-statistics-last-5y

df = pd.read_csv('./data/ids_last_5y.csv')
# assume you have a "long-form" data frame
# see https://plotly.com/python/px-arguments/ for more options

# Extract the last five years from the dataset based on the available columns
last_five_years = df.columns[-5:]

# Calculate the total debt for each year
total_debt = df[last_five_years].sum()

# Create a DataFrame with the year and total debt
df_pie = pd.DataFrame({'Year': last_five_years, 'Total Debt': total_debt})

# Create the pie chart
pie_fig = px.pie(df_pie, values='Total Debt', names='Year', title='Total International Debt Statistics (Last 5 Years)')

# Create a Plotly chart
fig = px.choropleth(df, locations='Country Code', locationmode='ISO-3', color='2022',
                    title='World Map Graph', hover_name='Country Name',
                    color_continuous_scale='Blues')

fig.update_layout(
    title_text='Debt in 2022 By Countries',
    geo=dict(
        showframe=False,
        showcoastlines=False,
        projection_type='equirectangular'
    )
)


# Select the year for comparison
year = '2022'

# Filter the dataset for the selected year
df_year = df[['Country Name', year]].dropna()

# Sort the data by debt levels in descending order
df_year_sorted = df_year.sort_values(year, ascending=False)

# Create the bar chart
bar_fig = px.bar(df_year_sorted, x='Country Name', y=year, title=f'Debt Levels by Country - {year}')

# Customize the bar chart as needed
bar_fig.update_layout(xaxis_title='Country', yaxis_title='Debt Amount')

# Rotate the x-axis labels for better readability
bar_fig.update_layout(xaxis_tickangle=-45)


app.layout = html.Div(children=[
    html.H1(children='Amir Mobayen | Nikita Chistyakov'),

    html.Div(children='''
        Dash: A web application framework for your data.
    '''),

    dcc.Graph(
        id='worldmap-graph',
        figure=fig
    ),

    dcc.Graph(
        id='Total-Debt',
        figure=pie_fig
    ),

    dcc.Graph(
        id='bar_chart',
        figure=bar_fig
    ),
])

if __name__ == '__main__':
    app.run(debug=True)

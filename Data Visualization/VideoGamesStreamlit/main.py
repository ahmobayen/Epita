import streamlit as st
import pandas as pd
import plotly.express as px


st.set_page_config(
    page_title="Video games business through the ages",
    page_icon="💰",
    layout='wide',
    initial_sidebar_state="expanded"
)


if 'df' not in st.session_state:
    df = pd.read_csv("vgsales.csv")
    df = df.sort_values(['Year','Platform'])
    df = df.fillna(0)
    st.session_state.df = df
else:
    df=st.session_state.df





st.write("# VideoGames")
## filtered_df = dataframe_explorer(df)
## st.dataframe(filtered_df)

fig = px.sunburst(df, path=['Year','Platform'], values='Global_Sales' )
st.write("## VideoGames global sales per year, per platform")
fig

treemap1 = px.treemap(df, path=[px.Constant("all"), 'Year','Platform'], values='Global_Sales',
                            labels='Year',hover_name="Year")
treemap1

st.write("## VideoGames global sales per Publisher,Genre and Platform")
treemap2 = px.treemap(df, path=[px.Constant("all"), 'Publisher','Genre'], values='Global_Sales',
                            labels='Year',hover_name="Year")
treemap2

st.write("## VideoGames global sales by Genre and Year + platform")
treemap3 = px.treemap(df, path=[px.Constant("all"),'Genre','Year','Platform'], values='Global_Sales',
                            labels='Year',hover_name="Year")
treemap3

st.write("## VideoGames global sales by platform, year")
treemap4 = px.treemap(df, path=[px.Constant("all"),'Platform','Year'], values='Global_Sales',
                            labels='Year',hover_name="Year")
treemap4
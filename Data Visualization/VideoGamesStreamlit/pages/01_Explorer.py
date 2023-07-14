import streamlit as st
from streamlit_extras.dataframe_explorer import dataframe_explorer

df = st.session_state.df

df['Publisher'].replace(0,'N/A')
map_dict = {0: "NA"}
df["Publisher"] = df["Publisher"].map(map_dict)

filtered_df = dataframe_explorer(df)
st.dataframe(filtered_df)
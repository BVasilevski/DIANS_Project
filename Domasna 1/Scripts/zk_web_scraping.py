import pandas as pd
from bs4 import BeautifulSoup
import requests

urls = ['https://zk.mk/vinarii', 'https://zk.mk/vinarii?skip=20', 'https://zk.mk/vinarii?skip=40']
names = []
locations = []
towns = []
activities = []
working_hours = []
cards = []

# For each url append the card that contains the winery
for url in urls:
    snapshot = requests.get(url)
    html = snapshot.text
    bs = BeautifulSoup(html, 'html.parser')
    cards.append(bs.find_all('div', class_='result unsponsored'))

# Iterate through the cards from each page
for l in cards:
    # Iterate through each card from the page
    for card in l:
        names.append(card.select_one('h2', class_='fontot').text)
        locations.append(
            card.select_one('ul', class_='details').select_one('li', class_='h56146bf7196e9cd84da16cf2ccd2a8df').text)
        towns.append(card.select_one('ul', class_='details').find_all('li', class_='h4efd8293a5270db14490d2fba73e0d16')[
                         0].text.split()[-1])
        hours_card = card.select_one('ul', class_='details').find_all('li', class_='ha406382314ab32830b5be3ba354edd06')
        working_hours.append(hours_card[0].text) if len(hours_card) > 0 else working_hours.append(
            'N/A')
        activity_card = card.select_one('div', class_='shortdescription').find_all('p')[1].find_all('a')
        card_activities = []
        for element in activity_card:
            card_activities.append(element.text)
        activities.append(card_activities)

data = {"Име": names, "Локација": locations, "Град": towns, "Работно време": working_hours, "Дејност": activities}
df = pd.DataFrame(data)

# Save the scraped data
df.to_csv('data_zk.csv', encoding='utf-8', index=False)

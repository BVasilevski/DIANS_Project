import csv
import xml.etree.ElementTree as ET

tree = ET.parse('../Data/data_osm.xml')
root = tree.getroot()

wineries = []

# Iterate through items with tag 'way'
for way in root.findall(".//way"):
    winery_info = {}

    # Iterate through tags with tag 'tag'
    for tag in way.findall(".//tag"):
        k = tag.get("k")
        v = tag.get("v")
        winery_info[k] = v

    wineries.append(winery_info)

# Iterate through items with tag 'node'
for way in root.findall(".//node"):
    winery_info = {}

    # Iterate through tags with tag 'tag'
    for tag in way.findall(".//tag"):
        k = tag.get("k")
        v = tag.get("v")
        winery_info[k] = v

    wineries.append(winery_info)

translate_to_macedonian = {'wine': "винарија", 'N/A': 'N/A'}
output = [['Име', 'Локација', 'Град', 'Работно време', 'Дејност']]

for winery in wineries:
    row_data = []
    # Append winery if the winery has name else continue
    if winery.get("name") is not None:
        row_data.append(winery.get("name"))
        row_data.append(winery.get("addr:street", "N/A"))
        row_data.append(winery.get("addr:city", "N/A"))
        row_data.append(winery.get("opening_hours", "N/A"))
        row_data.append(translate_to_macedonian[winery.get("shop", "N/A")])
        output.append(row_data)

# Write the winery data to a csv file
with open('../Data//data_osm_extracted.csv', mode='w', newline='', encoding='utf-8') as file:
    writer = csv.writer(file)
    writer.writerows(output)

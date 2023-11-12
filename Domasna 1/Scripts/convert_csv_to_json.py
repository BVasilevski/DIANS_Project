import csv
import json


def csv_to_json(csv_file_path, json_file_path):
    # Read CSV file and convert to a list of dictionaries
    with open(csv_file_path, 'r', encoding='utf-8') as csv_file:
        csv_reader = csv.DictReader(csv_file)
        data = [row for row in csv_reader]

    # Write the list of dictionaries to a JSON file
    with open(json_file_path, 'w', encoding='utf-8') as json_file:
        json.dump(data, json_file, indent=2, ensure_ascii=False)


csv_file_path = '../Data/filtered_data.csv'
json_file_path = '../Data/data.json'

csv_to_json(csv_file_path, json_file_path)

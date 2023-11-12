import csv


# Function to remove the word 'Адреса' from the address column
def remove_address(input_line: list):
    new_line = []
    for element in input_line:
        if element.find('Адреса: ') != -1:
            element = element.replace('Адреса: ', '')
            new_line.append(element)
        else:
            new_line.append(element)

    return new_line


# Function to remove the parentheses from the 'Дејност' column
def remove_parentheses(input_line: list):
    new_line = []
    for element in input_line:
        if element.startswith('['):
            element = element.replace('[', '').replace(']', '').replace("'", '')
        new_line.append(element)

    return new_line


# Function to remove 'Работно време' from 'Работно време' column
def remove_working_hours(input_line: list):
    new_line = []
    for element in input_line:
        if element.find('Работно време: ') != -1:
            element = element.replace('Работно време: ', '')
            new_line.append(element)
        else:
            new_line.append(element)

    return new_line


# Function to change the working days from English to Macedonian
def change_working_hours_to_mk(input_line: list):
    new_line = []
    translation_dictionary = {'Mo': 'Пон', 'Tu': 'Вто', 'We': 'Сре', 'Th': 'Чет', 'Fr': 'Пет',
                              'Sa': 'Саб',
                              'Su': 'Нед'}
    for element in input_line:
        for translation in translation_dictionary:
            if element.find(translation) != -1:
                element = element.replace(translation, translation_dictionary[translation])
        new_line.append(element)
    return new_line


# Make the first letter to upper in the columns 'Локација' and 'Град'
# def to_uppercase(input_line: list):
#     new_line = []
#     for element in input_line:
#         if element.startswith('ул.') is False and element.startswith('с.') is False:
#             element = element.replace(element[0], str.upper(element[0]))
#         new_line.append(element)
#     return new_line


data = []
filters = [remove_address, remove_parentheses, remove_working_hours, change_working_hours_to_mk]

with open('../Data/data_zk.csv', mode='r', encoding='utf-8') as file:
    reader = csv.reader(file)
    for row in reader:
        # Use each filter on the data
        for filt in filters:
            row = filt(row)
        data.append(row)

with open('../Data/data_osm_extracted.csv', mode='r', encoding='utf-8') as file:
    reader = csv.reader(file)
    next(reader)
    for row in reader:
        # Use each filter on the data
        for filt in filters:
            row = filt(row)
        data.append(row)

# Write the data to a file
with open('../Data/filtered_data.csv', mode='w', encoding='utf-8', newline='') as file:
    writer = csv.writer(file)
    for row in data:
        writer.writerow(row)



import requests
import json

url = "https://naver.com"
headers = {
    'Authorization': 'Bearer asdfasdffsdfkjnaskdjfnkjnerjkqerqwrqwrqrw'

}
print(headers)
response = requests.get(url, headers=headers, verify=False)
print(response.text)

jsonObject = json.loads(response.text)
print(jsonObject.get('info').get('targets').get('default'))
data = 'hi'
response = requests.post(url, data=data,headers=headers, verify=False)


default_data = '192.168.0.1 \n 168.126.63.41\n'
default_data = jsonObject.get('info').get('targets').get('default')
parsing_data = default_data.replace('\n', ',')

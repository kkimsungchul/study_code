import requests
import json


# 함수 선언부 시작


def call_api_post(param_headers, param_url, param_payload):
    """
    POST API 호출 함수
    Parameters:
    param_headers (header) : 포함할 헤더값
    param_url (str): 호출할 URL
    param_payload (json): 호출시 넘길 JSON 데이터
    Returns:
    JSON: 응답 받은 제이슨 데이터
    """

    response = requests.post(param_url, json=param_payload, headers=param_headers)
    json_object = json.loads(response.text)
    return json_object


def call_api_get(param_headers, param_url):
    """
    GET API 호출 함수
    Parameters:
    param_headers (header) : 포함할 헤더값
    param_url (str): 호출할 URL
    Returns:
    JSON: 응답 받은 JSON 데이터
    """
    response = requests.get(param_url, headers=param_headers)
    json_object = json.loads(response.text)
    return json_object


def add_headers(param_header, *args):
    """
    여러 개의 헤더를 추가하는 함수
    Parameters:
    param_header (dict): 기존 헤더
    *args: (key, value) 튜플 형태로 헤더들을 받음
    Returns:
    dict: 값들이 추가된 새로운 헤더
    """
    updated_header = param_header.copy()  # 원본 헤더 복사

    for key, value in args:
        updated_header[key] = value

    return updated_header


def create_csv_file(file_name, file_content):
    with open(file_name, 'w', encoding='utf-8') as f:
        f.write("IP,HOSTNAME,OS,CVE list,Plugin ID,Vulerability name,Solution,Severity\n")
        f.close()
    for item in file_content:
        cve = ""
        ipv4 = item["asset"]["ipv4"]
        hostname = item["asset"]["hostname"]
        operating_system = ''.join(map(str, item["asset"]["operating_system"]))
        try:
            cve = ''.join(map(str, item["plugin"]["cve"]))
        except KeyError:
            print()

        bid = ''.join(map(str, item["plugin"]["bid"]))
        name = item["plugin"]["name"]
        # solution = item["plugin"]["solution"]
        solution = item["plugin"]["solution"].replace("\n", "")
        solution = "\"" + solution + "\""
        severity = item["severity"]

        with open(file_name, 'a', encoding='utf-8') as f:
            f.write(ipv4)
            f.write(",")
            f.write(hostname)
            f.write(",")
            f.write(operating_system)
            f.write(",")
            f.write(cve)
            f.write(",")
            f.write(bid)
            f.write(",")
            f.write(name)
            f.write(",")
            f.write(solution)
            f.write(",")
            f.write(severity)
            f.write("\n")
            f.close()


# 함수 선언부 종료


# 아래의 헤더는 수정하지 말것
headers = {
    "X-ApiKeys": "accessKey=e9941d722e1216fef861614427795986b04061014a681ede411c67e500390922;secretKey=52b1fb82121392dfd648720368698fe12a7454d406e51340876b103473a6a493"
}

# print("result_header", end='')
# print(result_header)
# print("headers", end='')
# print(headers)



# scan id 추출
# url ="https://cloud.tenable.com/scans"
# scan_id_header = add_headers(headers, ("accept", "application/json"))
# scan_result_data = call_api_get(scan_id_header, url)
# for item in scan_result_data["scans"]:
#     print(item["name"])
#     try:
#         print(item["uuid"])
#     except KeyError:
#         print()
#     print()



# export_uuid 추출
url = "https://cloud.tenable.com/vulns/export"
result_header = add_headers(headers, ("accept", "application/json"), ("content", "application/json"))
payload = {
    "filters": {
        "scan_uuid": "1c39c351-e51a-49bd-92a2-48cba766bc5e",
        "severity": ["critical", "high"]
    },
    "num_assets": 5000,
    "include_unlicensed": False
}
export_id = call_api_post(result_header, url, payload)
# print("export_id['export_uuid']", end='')
# print(export_id['export_uuid'])




# 원본 JSON 데이터 받기
url = "https://cloud.tenable.com/vulns/export/80147c34-91ed-4fdc-b21a-56363abdeb46/chunks/1"
result_header2 = add_headers(headers, ("application", "octet-stream"))
# print("result_header2", end='')
# print(result_header2)
result_data = call_api_get(result_header2, url)
result_json_string = json.dumps(result_data, indent=4)
print(result_json_string)
# 원본 JSON 데이터 파일 생성
with open('origin.json', 'w', encoding='utf-8') as f:
    f.write(result_json_string)
    f.close()


create_csv_file('file_name.csv', result_data)

# 필요한 데이터
#
# asset.ipv4 -> ip
# asset.hostname -> hostname
# operating_system -> OS
# plugin.cve
# plugin.bid
# plugin.name -> 취약점 명
# plugin.solution -> 조치방법
# severity
# print("result_data[0]")
# print(result_data[0]["asset"]["ipv4"])
# print(result_data[0]["asset"]["hostname"])
# print(result_data[0]["asset"]["operating_system"])
# print(result_data[0]["plugin"]["cve"])
# print(result_data[0]["plugin"]["bid"])
# print(result_data[0]["plugin"]["name"])
# print(result_data[0]["severity"])
# print()

# json 데이터 추출

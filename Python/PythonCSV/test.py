csv_file_path = "temp.csv"
ipData = []
cData=[]
yesData = []
noData =[]
with open(csv_file_path, "r") as f:
    for line in f:
        if '19506' in line:
            ipData.append(line[4])

with open(csv_file_path, "r") as f:
    for line in f:
        if 'Credentialed checks' in line:
            cData.append(line)

for i in range(len(cData)):
    if "no" in cData[i]:
        noData.append(ipData[i] +","+cData[i])
    else:
        yesData.append(ipData[i] +","+cData[i])

for i in range(len(noData)):
    print(noData[i])

for i in range(len(yesData)):
    print(yesData[i])

    #     data += line
    # data = data.replace("\n", "")
    # arrData = data.split(",")
    # for selectData in arrData:
    #     if "172" in selectData:
    #         print(selectData, end=" ")
    #     if "Credentialed checks" in selectData:
    #         print(selectData)
    #

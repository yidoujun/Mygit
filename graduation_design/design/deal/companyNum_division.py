import pandas as pd
import numpy as np


def companyNum_standard(avg_companyNum):
    if avg_companyNum <= 50:
        return "小于50人"
    elif 50 < avg_companyNum < 150:
        return "50-150人"
    elif 150 <= avg_companyNum < 500:
        return "150-500人"
    elif 500 <= avg_companyNum < 2000:
        return "500-2000人"
    else:
        return "2000人以上"
    pass


def deal_companyNum(data):

    companyNum_standard_list = []
    for i in range(data.shape[0]):
        companyNum = data.loc[i]
        if len(companyNum.split('-')) > 1:
            companyNum_low = int(companyNum.split('-')[0])
            companyNum_high = int(companyNum.split('-')[1])
            avg_companyNum  = int((companyNum_low + companyNum_high ) / 2)
            companyNum_standard_list.append(companyNum_standard(avg_companyNum))
        else:
            companyNum_standard_list.append(companyNum_standard(int(companyNum)))

    return companyNum_standard_list


if __name__ == '__main__':
    # 读取company_num.csv文件
    data = pd.read_csv(r'./file/company_num.csv')
    # 替换"人", "以上", "少于"，将其转换为字符串类型
    data = data["company_num"].replace('["人", "以上", "少于"]','', regex=True)
    new_companyNum = deal_companyNum(data)
    companyNum_standard = pd.DataFrame(new_companyNum, columns=['companyNum_standard'])
    # 将data的Series转换为DataFrame类型
    data = {"company_num":data.values}
    data = pd.DataFrame(data)
    # 合并
    data["companyNum_standard"] = companyNum_standard
    print(data)
    pass


















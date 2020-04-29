import pandas as pd

dongbeifile = r'dongbei_result.csv'
huabeifile = r'huabei_result.csv'
huadongfile = r'huadong_result.csv'
huananfile = r'huanan_result.csv'
huazhongfile = r'huazhong_result.csv'
xibeifile = r'xibei_result.csv'
xinanfile = r'xinan_result.csv'


dongbei_data = pd.read_csv(dongbeifile)
# 删除多余列
def del_column(dongbei_data, huabei_data, huadong_data, huanan_data, huazhong_data, xinan_data, xibei_data):
    dongbei_data.drop('Unnamed: 0', axis=1, inplace=True)
    huabei_data.drop('Unnamed: 0', axis=1, inplace=True)
    huadong_data.drop('Unnamed: 0', axis=1, inplace=True)
    huanan_data.drop('Unnamed: 0', axis=1, inplace=True)
    huazhong_data.drop('Unnamed: 0', axis=1, inplace=True)
    xinan_data.drop('Unnamed: 0', axis=1, inplace=True)
    xibei_data.drop('Unnamed: 0', axis=1, inplace=True)
    pass

if __name__ == '__main__':

    # dongbei_data = pd.read_csv(dongbeifile)
    # huabei_data = pd.read_csv(huabeifile)
    # huadong_data = pd.read_csv(huadongfile)
    # huanan_data = pd.read_csv(huananfile)
    # huazhong_data = pd.read_csv(huazhongfile)
    # xinan_data = pd.read_csv(xinanfile)
    # xibei_data = pd.read_csv(xibeifile)
    #
    # del_column(dongbei_data, huabei_data, huadong_data, huanan_data, huazhong_data, xinan_data, xibei_data)
    #
    # data = dongbei_data.merge(huabei_data, how="outer")
    # data = data.merge(huadong_data, how="outer")
    # data = data.merge(huanan_data, how="outer")
    # data = data.merge(huazhong_data, how="outer")
    # data = data.merge(xibei_data, how="outer")
    # data = data.merge(xinan_data, how="outer")
    # # print(data)
    #
    # CN_city_location = data
    # CN_city_location.to_csv("china_city_location.csv", encoding='utf-8')

    CN_data = pd.read_csv("china_city_location.csv", encoding='utf-8')
    print(CN_data)

    pass


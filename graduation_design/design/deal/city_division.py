import pandas as pd
# import time
'''
城市划分
    
城市划分：一线城市、新一线城市、二线城市、三线城市、四线城市、五线城市
    
@author:易都军
@date:
'''

def get_city_level(city_data, city_level_data):
    for i in range(city_level_data.shape[0]):
        temp_city = city_level_data.loc[i]["city"]
        if len(city_data.split("-")) > 1:
            temp_city_data = city_data.split("-")[0]
            if temp_city_data in temp_city:
                return city_level_data.loc[i]["city_level"]
        else:
            if city_data in temp_city:
                return city_level_data.loc[i]["city_level"]


# 读取城市-等级表，用于判断标准
city_level_data = pd.read_excel(r"./file/city_level.xlsx")

# 读取爬取数据
data = pd.read_csv("../read/data_58job.csv")
# 单独取出job_address数据
job_address_data = pd.DataFrame(data.job_address)

# print(time.strftime('%Y-%m-%d %H:%M:%S'), time.localtime(time.time()))

city_level_list = []
for i in range(job_address_data.shape[0]):
    city_data = job_address_data.loc[i]["job_address"]
    city_leve = get_city_level(city_data, city_level_data)
    city_level_list.append(city_leve)
    pass

job_address_data["city_level"] = pd.DataFrame(city_level_list)
print(job_address_data)
# print(time.strftime('%Y-%m-%d %H:%M:%S'), time.localtime(time.time()))












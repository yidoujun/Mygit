import pandas as pd
import time
'''
区位划分：

    东北地区、华北地区、华东地区、华中地区、华南地区、西南地区、西北地区
'''
def get_city_location(city_data, city_location_data):

    for i in range(city_location_data.shape[0]):
        # 来自标准
        temp_city = city_location_data.loc[i]["city"]
        if len(city_data.split("-")) > 1:
            # 来自爬取的数据
            temp_city_date = city_data.split("-")[0]
            if temp_city_date in temp_city:
                return city_location_data.loc[i]["location"]
        else:
            if city_data in temp_city:
                return city_location_data.loc[i]["location"]
        pass
    pass


# 读取城市-区位表，用于判断标准
city_location_data = pd.read_csv(r'./file/china_city_location.csv')
# print(city_location_data)

# 读取爬取数据
data = pd.read_csv("../read/data_58job.csv")
# 单独取出job_address数据
job_address_data = pd.DataFrame(data.job_address)
# print(job_address_data)

print(time.strftime('%Y-%m-%d %H:%M:%S'), time.localtime(time.time()))
location_list = []
for i in range(job_address_data.shape[0]):
    city_data = job_address_data.loc[i]["job_address"]
    location = get_city_location(city_data, city_location_data)
    location_list.append(location)
    pass

# print(get_city_location("深圳-南山区", city_location_data))

job_address_data["location"] = pd.DataFrame(location_list)
print(job_address_data)
print(time.strftime('%Y-%m-%d %H:%M:%S'), time.localtime(time.time()))










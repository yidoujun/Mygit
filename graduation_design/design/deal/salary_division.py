import pandas as pd



def deal_salary(data, salary_interval_standard):
    '''
    处理薪资，使其区间化
    :param data:  原始数据
    :return:      区间化薪资
    '''
    if len(data.split('-')) > 1:
        salary_low = round(float(data.split("-")[0][:-1]))
        salary_high = round(float(data.split("-")[1][:-1]))
        average_salary = round((salary_low + salary_high) / 2)
        for i in range(salary_interval_standard.shape[0]):
            salary_standard = salary_interval_standard.loc[i]['salary_interval_standard']
            # 在[0,40)区间
            if len(salary_standard.split(',')) > 1:
                salary_standard_low = int(salary_standard.split(',')[0][1:])
                salary_standard_high = int(salary_standard.split(',')[1][:-1])
                if average_salary >= salary_standard_low and average_salary < salary_standard_high:
                    return salary_standard, average_salary

                pass
            # 40及以上区间
            else:
                salary_standard_low = int(salary_standard.split('及')[0])
                if average_salary >= salary_standard_low:
                    return salary_standard, average_salary
        pass
    else:
        salary = round(float(data[:-1]))
        for i in range(salary_interval_standard.shape[0]):
            salary_standard = salary_interval_standard.loc[i]['salary_interval_standard']
            # 在[0,40)区间
            if len(salary_standard.split(',')) > 1:
                salary_standard_low = int(salary_standard.split(',')[0][1:])
                salary_standard_high = int(salary_standard.split(',')[1][:-1])
                if salary >= salary_standard_low and salary < salary_standard_high:
                    return salary_standard, salary
                pass
            # 40及以上区间
            else:
                salary_standard_low = int(salary_standard.split('及')[0])
                if salary >= salary_standard_low:
                    return  salary_standard, salary
        pass
    pass

# 读取数据
inputfile = r'./file/job_salary.csv'
salary_data = pd.read_csv(inputfile)
# 去掉Nan值
salary_data.dropna(axis=0, inplace=True)
# 重设索引
salary_data.reset_index(inplace=True)
# 删除index列
salary_data.drop("index",axis=1, inplace=True)
# print(salary_data)

# 读取薪资区间标准
salary_interval_standard = pd.read_csv(r'./file/salary_interval_standard.csv')
salary_interval_list = []
salary_average_list = []
for data in salary_data['job_salary']:
    salary_interval, salary_average = deal_salary(data, salary_interval_standard)
    salary_interval_list.append(salary_interval)
    salary_average_list.append(salary_average)

print(salary_average_list)

salary_data['salary_interval'] = pd.DataFrame(salary_interval_list)
salary_data['salary_average'] = pd.DataFrame(salary_average_list)







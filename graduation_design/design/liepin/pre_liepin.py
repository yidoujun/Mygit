import pandas as pd
import numpy as np


pd.set_option('display.max_columns', 1000)
pd.set_option('display.width', 1000)
pd.set_option('display.max_colwidth', 1000)

def city_division(data):
    '''
        处理城市划分
        :param data:    招聘数据
        :return:        返回城市划分完成的数据
        '''
    # 读取城市--等级表，用于判断标准
    city_level_data = pd.read_excel(r"../deal/file/city_level.xlsx")
    city_level_list = []
    for i in range(data.shape[0]):
        city_data = data.loc[i]["job_address"]
        city_leve = get_city_level(city_data, city_level_data)
        city_level_list.append(city_leve)
        pass
    data['city_level'] = pd.DataFrame(city_level_list)
    return data
    pass
def get_city_level(city_data, city_level_data):
    '''
    城市划分实现方法：
        一线/新一线/二线/三线/四线/五线/
    :param city_data:
    :param city_level_data:
    :return:
    '''
    for i in range(city_level_data.shape[0]):
        temp_city = city_level_data.loc[i]["city"]
        if len(city_data.split("-")) > 1:
            temp_city_data = city_data.split("-")[0]
            if temp_city_data in temp_city:
                return city_level_data.loc[i]["city_level"]
        else:
            if city_data in temp_city:
                return city_level_data.loc[i]["city_level"]


def location_division(data):
    '''
    处理区位划分
    :param data:    招聘数据
    :return:        返回区位划分完成的数据
    '''
    # 读取城市-区位表，用于判断标准
    city_location_data = pd.read_csv(r'../deal/file/china_city_location.csv')
    location_list = []
    for i in range(data.shape[0]):
        city_data = data.loc[i]["job_address"]
        location = get_city_location(city_data, city_location_data)
        location_list.append(location)
    data['location'] = pd.DataFrame(location_list)
    return data
def get_city_location(city_data, city_location_data):
    '''
    区位划分实现方法：
        东北地区、华北地区、华东地区、华中地区、华南地区、西南地区、西北地区
    :param city_data:
    :param city_location_data:
    :return:
    '''
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


def companyNum_division(data):
    '''
    公司规模划分
    :param data:
    :return:
    '''
    companyNum = data['company_num'].replace('["人", "以上", "少于"]', '', regex=True)
    new_companyNum = deal_companyNum(companyNum)
    companyNum_standard = pd.DataFrame(new_companyNum, columns=['companyNum_standard'])
    data['companyNum_standard'] = companyNum_standard
    return data
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
        companyNum = data.iloc[i]
        if len(companyNum.split('-')) > 1:
            companyNum_low = int(companyNum.split('-')[0])
            companyNum_high = int(companyNum.split('-')[1])
            avg_companyNum  = int((companyNum_low + companyNum_high ) / 2)
            companyNum_standard_list.append(companyNum_standard(avg_companyNum))
        else:
            companyNum_standard_list.append(companyNum_standard(int(companyNum)))
    return companyNum_standard_list


def education_division(data):
    '''
    学历划分
    :param data:
    :return:
    '''
    jobEducation = data["job_education"].replace('无学历要求', '学历不限', regex=True)
    jobEducation = jobEducation.replace('["及以上", "统招"]', '', regex=True)
    jobEducation = pd.DataFrame({"job_education": jobEducation.values})
    data['education'] = jobEducation
    return data
    pass


def jobYear_division(data):
    ''''
    工作年限划分
    '''
    jobYear_list = []
    jobYear = data['job_year'].replace('年以上', "", regex=True)

    for i in range(len(jobYear)):
        job_year = jobYear.iloc[i]
        if '不限' in job_year:
            jobYear_list.append("不限")
        else:
            jobYear_list.append(jobYear_standard(int(job_year)))
    jobYear = pd.DataFrame(jobYear_list, columns=['jobYear'])
    data['jobYear'] = jobYear
    return data
def jobYear_standard(jobYear):
    if jobYear <= 1:
        return '1年以下'
    elif 1 < jobYear < 3:
        return '1-3年'
    elif 3 <= jobYear < 5:
        return '3-5年'
    elif 5 <= jobYear < 10:
        return '5-10年'
    else:
        return '10年以上'
    pass



def salary_divsion(data):
    '''
    处理薪资，进行区间划分，并计算平均值
    :param data:
    :return:
    '''
    salary_list = []
    salarys = data['job_salary'].values
    i = 0
    for salary in salarys:
        if "面议" == salary:
            data.loc[i, 'job_salary'] = None
        else:
            data.loc[i, 'job_salary'] = salary.split('·')[0]
        print(data.loc[i]['job_salary'])
        i += 1
    # 区间化并获取平均值
    salary_interval_standard = pd.read_csv(r'../deal/file/salary_interval_standard.csv')
    salary_interval_list = []
    salary_average_list = []
    for money in data['job_salary']:
        salary_interval, salary_average = deal_salary(money, salary_interval_standard)
        salary_interval_list.append(salary_interval)
        salary_average_list.append(salary_average)
        pass
    data['salary_interval'] = pd.DataFrame(salary_interval_list)
    data['salary_average'] = pd.DataFrame(salary_average_list)
    return data
    pass

def deal_salary(salary, salary_interval_standard):
    if None == salary:
        return salary, salary
    else:
        if len(salary.split('-')) > 1:
            salary_low = round(float(salary.split('-')[0]))
            salary_high = round(float(salary.split('-')[1].replace('k', '')))
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
            salary = round(float(salary.replace('k', '')))
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
                        return salary_standard, salary
            pass
    pass


# 处理薪资空缺
def dealSalaryIsNan(data):

    # 取出含有nan的所有行
    salaryNanT = data[data.isnull().T.any().T]
    salaryNan = salaryNanT.copy()
    for i in range(salaryNan.shape[0]):
        salaryAverage = salaryNan.iloc[i]['salary_average']
        jobYear = salaryNan.iloc[i]['jobYear']
        if (pd.isnull(salaryAverage)):
            if (jobYear == '不限' or jobYear == '3-5年'):
                salaryNan.iloc[i, 3] = '20-25k'
                salaryNan.iloc[i, 15] = '[20-25)'
                salaryNan.iloc[i, 16] = 20.0
            elif (jobYear == '5-10年' or jobYear == '10年以上'):
                salaryNan.iloc[i, 3] = '25-30k'
                salaryNan.iloc[i, 15] = '[25-30)'
                salaryNan.iloc[i, 16] = 25.0
            else:
                salaryNan.iloc[i, 3] = '15-20k'
                salaryNan.iloc[i, 15] = '[15-20)'
                salaryNan.iloc[i, 16] = 18.0
    salaryNan.reset_index(drop=True, inplace=True)
    return salaryNan
    pass


# 行业分类
def industry_division(data):
    # 读取分组聚合好的行业划分
    industry_data = pd.read_excel(r'liepin_industry.xlsx')

    companyBusiness_list = []
    for i in range(data.shape[0]):
        companyBusiness = data.iloc[i]['company_business']
        for j in range(industry_data.shape[0]):
            industryBusiness = industry_data.iloc[j]['company_business']
            if (companyBusiness == industryBusiness):
                companyBusiness_list.append(industry_data.iloc[j]['industry'])

    data['company_industry'] = pd.DataFrame(companyBusiness_list)
    return data

if __name__ == '__main__':

    # # 1、读入数据
    # data = pd.read_csv(r'../read/data_liepin.csv')
    # # 2、删除异常值
    # # 删除重复数组
    # data = data.drop_duplicates()
    # # 删除含有空的行
    # data.drop(labels=["id"], axis=1, inplace=True)
    # data.dropna(subset=['job_position', 'job_address', 'job_salary', 'job_year', 'job_education', 'company_num',
    #                     'company_business', 'job_detail'], inplace=True)
    # # 删除岗位名称未包含"数据"二字的行
    # data = data[data.job_position.str.contains("数据")]
    # # 删除公司规模为未知的数据和删除不包括"人"的数据
    # data = data[data.company_num.str.contains('人')]
    # data = data[~data['company_num'].isin(['辽宁省大连市中山区人民路24号平安大厦六楼'])]
    # data = data[~data["job_education"].str.contains('中专')]
    # # print(data)
    # # 删除面议的
    # # data = data[~data['job_salary'].str.contains('面议')]
    # # 3、重设索引
    # data = data.reset_index(drop=True)
    # # 4、重新保存数据
    # data.to_csv(r'new_liepin_data.csv')

    # 读取简单处理过的数据
    # new_data = pd.read_csv(r'new_liepin_data.csv')

    # 5、地理位置划分
    # 城市划分
    # new_data = city_division(new_data)
    # # 按照区位划分
    # new_data = location_division(new_data)
    #
    # # 6、按照公司规模划分
    # new_data = companyNum_division(new_data)
    #
    # # 7、按照学历划分
    # new_data = education_division(new_data)
    #
    # # 8、按照工作年限划分
    # new_data = jobYear_division(new_data)
    #
    # # 8、按照薪资划分
    # new_data = salary_divsion(new_data)
    # new_data.to_csv(r'new_liepin_data.csv', index=False)
    # new_data.to_excel(r'new_liepin_data.xlsx')

    # 处理薪资空缺的值
    # data = pd.read_excel(r'new_liepin_data.xlsx', index_col=0)
    # data.dropna(subset=['location'], inplace=True)
    # data.reset_index(drop=True, inplace=True)
    # salary = dealSalaryIsNan(data)
    # salary.to_excel(r'new_liepin_data2.xlsx')
    # # # 把原来薪资为空的行删除
    # data.dropna(subset=['salary_average'], inplace=True)
    # data.reset_index(drop=True, inplace=True)
    # data.to_excel(r'new_liepin_data1.xlsx')
    #手动合并一下new_liepin_data1.xlsx和new_liepin_data2.xlsx文件到new_liepin_data1.xlsx文件

    # 企业行业分类
    # data = pd.read_excel(r'new_liepin_data1.xlsx')
    # data = industry_division(data)
    # data.to_excel(r'new_liepin_data3.xlsx')
    data = pd.read_excel(r'new_liepin_data3.xlsx', index_col=0)
    data = data.drop_duplicates()

    print(data.info())
    pass
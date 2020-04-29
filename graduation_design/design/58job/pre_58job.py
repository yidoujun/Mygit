import pandas as pd
pd.set_option('display.max_columns', 1000)
pd.set_option('display.width', 1000)
pd.set_option('display.max_colwidth', 1000)

'''
前程无忧招聘信息预处理

1、删除一些异常数据
2、学历划分
3、工作年限划分
4、公司规模划分
5、城市划分
6、区位划分
7、薪资划分
8、行业划分
'''

def delete_jobYeal(data):
    '''
    删除脏数据
    :param data:原始数据
    :return:
    '''
    _list = ['高中', '中专', '大专', '本科', '硕士', '博士', '招']
    for i in _list:
        data = data[~data["job_year"].str.contains(i)]
    return data
def delete_jobEducation(data):
    '''
    删除脏数据
    :param data:
    :return:
    '''
    _list = ['初中', '中技', '高中', '中专', '招']
    for i in _list:
        data = data[~data["job_education"].str.contains(i)]
    return data
    pass

def deal_unnormal(data):
    '''
    处理异常值
    :param data:
    :return:
    '''
    # 2、删除异常数据
    # 删除id、发布日期、企业性质、招聘人数和职业类型列
    data.drop(labels=["id", "job_pub", "job_number", "company_property", "job_type"], axis=1, inplace=True)
    # 删除重复数组
    data = data.drop_duplicates()
    # 删除含有空的行
    data = data.dropna(axis=0)
    # 删除岗位名称未包含"数据"二字的行
    data = data[data.job_position.str.contains("数据")]
    # 删除公司规模为未知的数据和删除不包括"人"的数据
    data = data.drop(data[data.company_num == '未知'].index)
    data = data[data.company_num.str.contains('人')]
    # 删除工作年限里面的脏数据
    data = delete_jobYeal(data)
    # 删除工作学历里面的脏数据
    data = delete_jobEducation(data)
    return data

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
    companyNum = data['company_num'].replace('["人", "以上", "少于"]','', regex=True)
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
    jobEducation = pd.DataFrame({"job_education": jobEducation.values})
    data['education'] = jobEducation
    return data

def jobYear_division(data):
    '''
    工作年限划分
    :param data:
    :return:
    '''
    jobYear_list = deal_jobYear(data)
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

def deal_jobYear(data):
    '''
    工作年限划分
    :param data:
    :return:
    '''
    jobYear_list = []
    for i in range(data.shape[0]):
        jobYear = data.loc[i]["job_year"]
        # 处理"在校生/应届生"
        if "经验" not in jobYear:
            jobYear_list.append('应届毕业生')
            pass
        else:
            # 处理"x-x年经验"
            if len(jobYear.split('-')) > 1:
                jobYear = jobYear.replace("年经验", '')
                jobYear_low = int(jobYear.split('-')[0])
                jobYear_high = int(jobYear.split('-')[1])
                avg_jobYear = round(((jobYear_low + jobYear_high) / 2))
                jobYear_list.append(jobYear_standard(avg_jobYear))
                pass
            # 处理'x年经验'
            elif len(jobYear.split('年')) > 1:
                jobYear = int(jobYear.split('年')[0])
                jobYear_list.append(jobYear_standard(jobYear))
            # 处理'无需经验'
            else:
                jobYear_list.append('不限')
    return jobYear_list

def salary_divsion(data):
    '''
    薪资区间化、数值化并取平均值
    :param data:
    :return:
    '''
    # 删除面议数据
    data = data[~data["job_salary"].str.contains('面议')]
    print()
    # 重设索引
    data = data.reset_index(drop=True)
    data_money = data.job_salary.values
    i = 0
    for money in data_money:
        data.loc[i, 'job_salary'] = conversion_salary(money)
        i += 1
    # 读取薪资区间标准
    salary_interval_standard = pd.read_csv(r'../deal/file/salary_interval_standard.csv')
    salary_interval_list = []
    salary_average_list = []
    for money in data['job_salary']:
        salary_interval, salary_average = deal_salary(money, salary_interval_standard)
        salary_interval_list.append(salary_interval)
        salary_average_list.append(salary_average)

    data['salary_interval'] = pd.DataFrame(salary_interval_list)
    data['salary_average'] = pd.DataFrame(salary_average_list)

    # 薪资均值
    # mean_salary = int(data['salary_average'].mean())
    # 均值所在薪资范围
    # mean_interval = get_mean_interval(mean_salary, salary_interval_standard)
    return data


def conversion_salary(money):
    '''
    转化薪资，最后格式'xxxxK-xxxxK'
    :param money:
    :return:
    '''
    k = money.split("-")
    # 处理薪水格式为：xx元/天或者xx元/小时
    if len(k) == 1:
        min_money = k[0].split("元")[0]
        symbol = k[0].split("/")[1]
        if symbol == '天':
            min_money = round((int(min_money) * 30 / 1000), 2)
            salary_money = str(min_money) + 'k'
            return salary_money
        elif symbol == "小时":
            min_money = round((int(min_money) * 8 * 30 / 1000), 2)
            salary_money = str(min_money) + 'k'
            return salary_money

    # 处理薪水格式为：xx-xx万/月或者xx-xx万/年或者xx-xx千/月
    else:
        first_symbol = k[1].split("/")[0][-1]
        second_symbol = k[1].split("/")[1]
        # 处理单位为万/月
        if first_symbol == '万' and second_symbol == '月':
            min_money = float(k[0]) * 10
            max_money = float(k[1].split('万')[0]) * 10
            salary_money = str(min_money) + 'k-' + str(max_money) + 'k'
            return salary_money

        elif first_symbol == '万' and second_symbol == '年':
            min_money = float(k[0]) * 10 / 12
            max_money = float(k[1].split('万')[0]) * 10 / 12
            salary_money = str(round(min_money, 2)) + 'k-' + str(round(max_money, 2)) + 'k'
            return salary_money

        else:
            min_money = float(k[0])
            max_money = float(k[1].split('千')[0])
            salary_money = str(min_money) + 'k-' + str(max_money) + 'k'
            return salary_money
    pass

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

def get_mean_interval(mean_salary, salary_interval_standard):
    '''
    根据均值得到薪资区间
    :param mean_salary: 均值
    :param salary_interval_standard: 薪资区间标准
    :return:
    '''
    for i in range(salary_interval_standard.shape[0]):
        salary_standard = salary_interval_standard.loc[i]['salary_interval_standard']
        # 在[0,40)区间
        if len(salary_standard.split(',')) > 1:
            salary_standard_low = int(salary_standard.split(',')[0][1:])
            salary_standard_high = int(salary_standard.split(',')[1][:-1])
            if mean_salary >= salary_standard_low and mean_salary < salary_standard_high:
                return salary_standard
            pass
        # 40及以上区间
        else:
            salary_standard_low = int(salary_standard.split('及')[0])
            if mean_salary >= salary_standard_low:
                return salary_standard


def industry_division(data):
    # 读取分组聚合好的行业划分
    industry_data = pd.read_excel(r'58job_industry.xlsx')

    companyBusiness_list = []
    for i in range(data.shape[0]):
        companyBusiness = data.iloc[i]['company_business']
        for j in range(industry_data.shape[0]):
            industryBusiness = industry_data.iloc[j]['company_business']
            if (companyBusiness == industryBusiness):
                companyBusiness_list.append(industry_data.iloc[j]['industry'])

    data['company_industry'] = pd.DataFrame(companyBusiness_list)
    return data

    pass

if __name__ == '__main__':
    # 1、读取数据
    # data = pd.read_csv("../read/data_58job.csv")
    # # 2、删除异常数据
    # data = deal_unnormal(data)
    # # 3、重设索引
    # data = data.reset_index(drop=True)
    #
    # # 重新保存数据
    # data.to_csv(r'new_58job_data.csv')
    #
    # # 读取处理后的数据
    # new_data = pd.read_csv(r'new_58job_data.csv')
    # # # 4、地理位置划分
    # # # 按照城市划分
    # new_data = city_division(new_data)
    # # # 按照区位划分
    # new_data = location_division(new_data)
    # # # 5、按照公司规模划分
    # new_data = companyNum_division(new_data)
    # # # 6、按照学历划分
    # new_data = education_division(new_data)
    # # # 7、按照工作年限划分
    # new_data = jobYear_division(new_data)
    # # 8、按照薪资划分
    # new_data = salary_divsion(new_data)


    # new_data.to_excel(r'new_58job_data_.xls', encoding='uft-8_sig')
    # new_data.to_excel(r'new_58job_data.xlsx')

    # 按照行业划分
    # data = pd.read_excel(r'new_58job_data.xlsx')
    # data = industry_division(data)
    # data.to_excel(r'new_58job_data1.xlsx')

    # 删除位置为空的行
    # data = pd.read_excel(r'new_58job_data1.xlsx', index_col=0)
    # data.dropna(subset=['location'], inplace=True)
    # data.reset_index(drop=True, inplace=True)
    # data.to_excel(r'new_58job_data1.xlsx')

    pass

















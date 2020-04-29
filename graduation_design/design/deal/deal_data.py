import numpy as np
import pandas as pd



pd.set_option('display.max_columns', 1000)
pd.set_option('display.width', 1000)
pd.set_option('display.max_colwidth', 1000)

# 处理薪水
def conversion_salary(money):
    if money == '面议':
        salary_money = None
        return salary_money

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



if __name__ == '__main__':
    # 读取数据
    data = pd.read_csv("../read/data_58job.csv")

    # 删除id、发布日期、企业性质、招聘人数和职业类型列
    data.drop(labels=["id", "job_pub", "job_number",  "company_property", "job_type"], axis=1, inplace=True)

    # 删除重复数组
    data = data.drop_duplicates()

    # 删除含有空的行
    data = data.dropna(axis=0)

    # 删除岗位名称未包含"数据"二字的行
    data = data[data.job_position.str.contains("数据")]

    # 删除公司规模为未知的数据和删除不包括"人"的数据
    data = data.drop(data[data.company_num == '未知'].index)
    data = data[data.company_num.str.contains('人')]

    # 重设索引
    data = data.reset_index(drop=True)
    # print(data.info())
    # print(data)

    # 处理薪水
    # data_money = data.job_salary.values
    # i = 0
    # for money in data_money:
    #     data.loc[i, 'job_salary'] = salary_util(money)
    #     i += 1
    #     pass
    # # 将其保存在job_salary.csv里面
    # data.job_salary.to_csv(r"./file/job_salary.csv")

    # 将公司规模保存到file/company_num.csv里面
    # data.company_num.to_csv(r"./file/company_num.csv")

    # 将工作年限和学历要求保存到year_education.csv里面
    # job_year = pd.DataFrame({"job_year": data["job_year"].values})
    # job_education = pd.DataFrame({"job_education": data["job_education"].values})
    # job_year["job_education"] = job_education
    # job_year.to_csv(r'./file/year_education.csv')
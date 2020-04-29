import pandas as pd
from matplotlib import pyplot as plt
from matplotlib import font_manager as fm
from matplotlib import cm
import matplotlib as mpl
import seaborn as sns
import collections
import numpy as np

# 设置字体
font = {'family': 'MicroSoft YaHei',
        'weight': 'bold',
        'size': 25
        }
mpl.rc("font", **font)

# 条形图-----学历与职位数量
def educationAndJobNum(data):

    # 按照学历分组
    education = data.groupby('education')['education'].count()
    # x轴
    x_index = education.index.tolist()
    # y轴
    y_value = list(education)

    plt.style.use('ggplot')
    plt.figure(figsize=(10, 8), dpi=80)
    rect = plt.bar(x=range(len(x_index))       # 指定条形图x轴的刻度值
            , height=y_value
            , tick_label=x_index
            , color=['darkorange', 'navajowhite', 'blanchedalmond', 'papayawhip', 'moccasin']
            )

    for x, y in enumerate(y_value):
        plt.text(x, y+0.1, '%s' %round(y,1), ha='center')
    # # 设置图形大小
    # plt.figure(figsize=(20, 15), dpi=80)
    # # 绘制条形图
    # plt.bar(range(len(x_index)), y_value, color=['darkorange', 'navajowhite', 'blanchedalmond', 'papayawhip', 'moccasin'])
    # # 设置x轴步长和轴点信息
    # plt.xticks(range(len(x_index)), x_index)
    # # 给条形图添加数据标注
    # for x, y in enumerate(y_value):
    #     plt.text(x-0.4, y+50, '%s' % y)
    plt.ylabel("职位数量")
    plt.xlabel("学历")
    plt.title("学历与职位数量关系图")
    plt.legend(rect, x_index)
    plt.show()

    pass

# 饼图-----各城市职位数量分布图
def cityAndJobNum_pie(data):
    # 取出job_address列数据
    address = data['job_address']
    # 定义一个列表，存放处理后的数据
    addresslist = []
    for address_value in address:
        if (len(address_value.split('-')) > 1):
            addresslist.append(address_value.split('-')[0])
        else:
            addresslist.append(address_value)
    # 将list转为str
    addresslist_str = " ".join(addresslist)
    # 统计每个城市出现的次数
    cityNum = collections.Counter(addresslist_str.split(" "))
    df_cityNum = pd.DataFrame(cityNum.items())
    # 修改列索引名
    df_cityNum.rename(columns={0: 'cityName', 1: 'cityNum'}, inplace=True)
    # 排序（降序）
    df_cityNum.sort_values(by='cityNum', ascending=False, inplace=True)
    # 重设索引
    df_cityNum.reset_index(drop=True, inplace=True)
    df_cityNum = df_cityNum[df_cityNum['cityNum'] > 50]
    print(df_cityNum)
    labels = df_cityNum['cityName'].values
    sizes = df_cityNum['cityNum'].values
    print(labels)
    print(sizes)
    plt.style.use('ggplot')
    # 设置绘图区域大小
    fig, axes = plt.subplots(figsize=(10, 6), ncols=2)
    ax1, ax2 = axes.ravel()
    colors = cm.rainbow(np.arange(len(sizes))/len(sizes))
    patches, texts, autotexts = ax1.pie(sizes, labels=labels, autopct='%1.0f%%',
                                        shadow=False, startangle=170, colors=colors)
    ax1.axis('equal')

    # 重新设置字体大小
    proptease = fm.FontProperties()
    proptease.set_size('xx-small')
    plt.setp(autotexts, fontproperties=proptease)
    plt.setp(texts, fontproperties=proptease)

    ax1.set_title('各城市职位数量分别饼图', loc='center')

    # 只显示图例
    ax2.axis('off')
    ax2.legend(patches, labels, loc='center left')
    plt.tight_layout()
    # plt.savefig('city-pie.png')
    plt.show()

# 条形图-----各城市职位数量分布图
def cityAndJobNum_bar(data):
    # 取出job_address列数据
    address = data['job_address']
    # 定义一个列表，存放处理后的数据
    addresslist = []
    for address_value in address:
        if (len(address_value.split('-')) > 1):
            addresslist.append(address_value.split('-')[0])
        else:
            addresslist.append(address_value)
    # 将list转为str
    addresslist_str = " ".join(addresslist)
    # 统计每个城市出现的次数
    cityNum = collections.Counter(addresslist_str.split(" "))
    df_cityNum = pd.DataFrame(cityNum.items())
    # 修改列索引名
    df_cityNum.rename(columns={0: 'cityName', 1: 'cityNum'}, inplace=True)
    # 排序（降序）
    df_cityNum.sort_values(by='cityNum', ascending=False, inplace=True)
    # 重设索引
    df_cityNum.reset_index(drop=True, inplace=True)
    df_cityNum = df_cityNum[df_cityNum['cityNum'] >= 50]
    labels = df_cityNum['cityName'].values
    sizes = df_cityNum['cityNum'].values

    # plt.style.use('ggplot')
    plt.figure(figsize=(20, 18), dpi=80)
    colors = cm.rainbow(np.arange(len(sizes))/len(sizes))
    rect = plt.bar(x=range(len(labels))  # 指定条形图x轴的刻度值
                   , height=sizes
                   , tick_label=labels
                   , color=colors
                   )
    for x, y in enumerate(sizes):
        plt.text(x, y + 0.1, '%s' % round(y, 1), ha='center')
    plt.ylabel("职位数量")
    plt.xlabel("城市")
    plt.title("城市与职位数量分布图")
    plt.legend(rect, labels)
    plt.show()
    pass

# 条形图-----各区位职位数量分布图
def locationAndJobNum_bar(data):
    # 按照区位分组
    location = data.groupby('location')['location'].count()
    # x轴
    x_index = location.index.tolist()
    # y轴
    y_value = list(location)

    plt.style.use('ggplot')
    plt.figure(figsize=(10, 8), dpi=80)
    colors = cm.rainbow(np.arange(len(y_value)) / len(y_value))
    rect = plt.bar(x=range(len(x_index))  # 指定条形图x轴的刻度值
                   , height=y_value
                   , tick_label=x_index
                   , color=colors
                   )

    for x, y in enumerate(y_value):
        plt.text(x, y + 0.1, '%s' % round(y, 1), ha='center')
    plt.ylabel("职位数量")
    plt.xlabel("区位")
    plt.title("区位与职位数量关系图")
    plt.legend(rect, x_index)
    plt.show()
    pass

# 条形图-----X线职位数量分布图
def cityLevelAndJobNum_bar(data):
    # 按照X线分组
    city_level = data.groupby('city_level')['city_level'].count()
    # x轴
    x_index = city_level.index.tolist()
    # y轴
    y_value = list(city_level)

    plt.style.use('ggplot')
    plt.figure(figsize=(10, 8), dpi=80)
    colors = cm.rainbow(np.arange(len(y_value)) / len(y_value))
    rect = plt.bar(x=range(len(x_index))  # 指定条形图x轴的刻度值
                   , height=y_value
                   , tick_label=x_index
                   , color=colors
                   )

    for x, y in enumerate(y_value):
        plt.text(x, y + 0.1, '%s' % round(y, 1), ha='center')
    plt.ylabel("职位数量")
    plt.xlabel("X线")
    plt.title("X线与职位数量关系图")
    plt.legend(rect, x_index)
    plt.show()
    pass

# 条形图-----工作年限与职位数量分布图
def jobYearAndJobNum_bar(data):
    # 按照工作年限分组
    jobYear = data.groupby('jobYear')['jobYear'].count()
    # x轴
    x_index = jobYear.index.tolist()
    # y轴
    y_value = list(jobYear)

    plt.style.use('ggplot')
    plt.figure(figsize=(10, 8), dpi=80)
    colors = cm.rainbow(np.arange(len(y_value)) / len(y_value))
    rect = plt.bar(x=range(len(x_index))  # 指定条形图x轴的刻度值
                   , height=y_value
                   , tick_label=x_index
                   , color=colors
                   )

    for x, y in enumerate(y_value):
        plt.text(x, y + 0.1, '%s' % round(y, 1), ha='center')
    plt.ylabel("职位数量")
    plt.xlabel("工作年限")
    plt.title("工作年限与职位数量关系图")
    plt.legend(rect, x_index)
    plt.show()
    pass

# 条形图-----薪资区间与职位数量分布图
def salaryAndJobNum_bar(data):
    # 按照薪资区间分组
    salaryInterval = data.groupby('salary_interval')['salary_interval'].count()
    # x轴
    x_index = salaryInterval.index.tolist()
    # y轴
    y_value = list(salaryInterval)

    plt.style.use('ggplot')
    plt.figure(figsize=(10, 8), dpi=80)
    colors = cm.rainbow(np.arange(len(y_value))/len(y_value))
    rect = plt.bar(x=range(len(x_index))  # 指定条形图x轴的刻度值
                   , height=y_value
                   , tick_label=x_index
                   , color=colors
                   )

    for x, y in enumerate(y_value):
        plt.text(x, y + 0.1, '%s' % round(y, 1), ha='center')
    plt.ylabel("职位数量")
    plt.xlabel("薪资区间")
    plt.title("薪资区间与职位数量关系图")
    plt.legend(rect, x_index)
    plt.show()
    pass

# 条形图-----学历与平均薪资分布图
def educationAndAvgSalary_barh(data):
    # 计算学历平均薪资
    df_data = data[['education', 'salary_average']].groupby('education').apply(lambda x: (round(x.mean(), 3) * 1000).astype(np.int64))
    df_data.reset_index(inplace=True)
    # 计算所有工资平均值
    avgSalary = (round(data['salary_average'].mean(), 3) * 1000).astype(np.int64)
    # 将平均薪资添加进去
    df_data = df_data.append({'education': '平均薪资', 'salary_average': avgSalary}, ignore_index=True)
    # 排序（降序）
    df_data.sort_values(by='salary_average', ascending=True, inplace=True)
    # 重设索引
    df_data.reset_index(drop=True, inplace=True)
    x_index = df_data['salary_average'].values
    y_value = df_data['education'].values

    plt.style.use('ggplot')
    plt.figure(figsize=(10, 8), dpi=80)
    colors = cm.rainbow(np.arange(len(y_value)) / len(y_value))
    rect = plt.barh(y=range(len(y_value))  # 指定条形图x轴的刻度值
                   , width=x_index
                   , tick_label=y_value
                   , color=colors
                   )

    for y, x in enumerate(x_index):
        plt.text(x+0.1, y, '%s' % round(x, 1), ha='center')
    plt.ylabel("学历")
    plt.xlabel("平均薪资")
    plt.title("学历与平均薪资分布图")
    plt.legend(rect, y_value)
    plt.show()
    pass

# 条形图-----工作经验与平均薪资分布图
def jobYearAndAvgJobNum_barh(data):
    # 计算工作经验平均薪资
    df_data = data[['jobYear', 'salary_average']].groupby('jobYear').apply(
        lambda x: (round(x.mean(), 3) * 1000).astype(np.int64))
    df_data.reset_index(inplace=True)
    # 计算所有工资平均值
    avgSalary = (round(data['salary_average'].mean(), 3) * 1000).astype(np.int64)
    # 将平均薪资添加进去
    df_data = df_data.append({'jobYear': '平均薪资', 'salary_average': avgSalary}, ignore_index=True)
    # 排序（降序）
    df_data.sort_values(by='salary_average', ascending=True, inplace=True)
    # 重设索引
    df_data.reset_index(drop=True, inplace=True)
    x_index = df_data['salary_average'].values
    y_value = df_data['jobYear'].values

    plt.style.use('ggplot')
    plt.figure(figsize=(10, 8), dpi=80)
    colors = cm.rainbow(np.arange(len(y_value)) / len(y_value))
    rect = plt.barh(y=range(len(y_value))  # 指定条形图x轴的刻度值
                    , width=x_index
                    , tick_label=y_value
                    , color=colors
                    )

    for y, x in enumerate(x_index):
        plt.text(x + 0.1, y, '%s' % round(x, 1), ha='center')
    plt.ylabel("工作经验")
    plt.xlabel("平均薪资")
    plt.title("工作经验与平均薪资分布图")
    plt.legend(rect, y_value)
    plt.show()
    pass

# 条形图-----城市与平均薪资分布图
def cityAndAvgSalary_barh(data):
    # 取出job_address列数据
    address = data['job_address']
    # 定义一个列表，存放处理后的数据
    addresslist = []
    for address_value in address:
        addresslist.append(address_value.split('-')[0])
    # 将处理后的工作城市保存到data里面
    data['city'] = pd.DataFrame(addresslist)

    city_date = data[['city', 'salary_average']].groupby('city').count()
    city_date.reset_index(inplace=True)
    city_date.rename(columns={'salary_average': 'cityNum'}, inplace=True)
    # 排序（降序）
    city_date.sort_values(by='cityNum', ascending=False, inplace=True)
    city_date.reset_index(inplace=True, drop=True)
    # 得到城市出现数量大于10的城市列表
    cityNamelist = city_date[city_date['cityNum'] > 10]['city'].values

    # 保留是cityNamelist的数据
    data = data[data.city.isin(cityNamelist)]
    # 计算城市中平均薪资
    df_data = data[['city', 'salary_average']].groupby('city').apply(
        lambda x: (round(x.mean(), 3) * 1000).astype(np.int64))
    df_data.reset_index(inplace=True)
    # 排序（降序）
    df_data.sort_values(by='salary_average', ascending=True, inplace=True)
    # 重设索引
    df_data.reset_index(drop=True, inplace=True)
    x_index = df_data['salary_average'].values
    y_value = df_data['city'].values
    plt.style.use('ggplot')
    plt.figure(figsize=(15, 10), dpi=80)
    colors = cm.rainbow(np.arange(len(y_value)) / len(y_value))
    rect = plt.barh(y=range(len(y_value))  # 指定条形图x轴的刻度值
                    , width=x_index
                    , tick_label=y_value
                    , color=colors
                    )

    for y, x in enumerate(x_index):
        plt.text(x + 0.1, y, '%s' % round(x, 1), ha='center')
    plt.ylabel("工作经验")
    plt.xlabel("平均薪资")
    plt.title("工作经验与平均薪资分布图")
    plt.legend(rect, y_value)
    plt.show()

    pass

# 条形图-----区位与平均薪资分布图
def locationAndAvgSalary_bar(data):

    # 计算区位平均薪资
    df_data = data[['location', 'salary_average']].groupby('location').apply(
        lambda x: (round(x.mean(), 3) * 1000).astype(np.int64))
    df_data.reset_index(inplace=True)
    # 排序（降序）
    df_data.sort_values(by='salary_average', ascending=True, inplace=True)
    # 重设索引
    df_data.reset_index(drop=True, inplace=True)
    x_index = df_data['location'].values
    y_value= df_data['salary_average'].values

    plt.style.use('ggplot')
    plt.figure(figsize=(10, 8), dpi=80)
    colors = cm.rainbow(np.arange(len(y_value)) / len(y_value))
    rect = plt.bar(x=range(len(x_index))  # 指定条形图x轴的刻度值
                    , height=y_value
                    , tick_label=x_index
                    , color=colors
                    )

    for x, y in enumerate(y_value):
        plt.text(x, y + 0.1, '%s' % round(y, 1), ha='center')
    plt.ylabel("区位")
    plt.xlabel("平均薪资")
    plt.title("区位与平均薪资分布图")
    plt.legend(rect, x_index)
    plt.show()
    pass

# 条形图-----X线城市与平均薪资分布图
def cityLevelAndAvgSalary_bar(data):
    # 计算X线城市平均薪资
    df_data = data[['city_level', 'salary_average']].groupby('city_level').apply(
        lambda x: (round(x.mean(), 3) * 1000).astype(np.int64))
    df_data.reset_index(inplace=True)
    # 排序（降序）
    df_data.sort_values(by='salary_average', ascending=True, inplace=True)
    # 重设索引
    df_data.reset_index(drop=True, inplace=True)
    x_index = df_data['city_level'].values
    y_value = df_data['salary_average'].values

    plt.style.use('ggplot')
    plt.figure(figsize=(10, 8), dpi=80)
    colors = cm.rainbow(np.arange(len(y_value)) / len(y_value))
    rect = plt.bar(x=range(len(x_index))  # 指定条形图x轴的刻度值
                   , height=y_value
                   , tick_label=x_index
                   , color=colors
                   )

    for x, y in enumerate(y_value):
        plt.text(x, y + 0.1, '%s' % round(y, 1), ha='center')
    plt.ylabel("X线城市")
    plt.xlabel("平均薪资")
    plt.title("X线城市与平均薪资分布图")
    plt.legend(rect, x_index)
    plt.show()
    pass

# 各学历下不同工作经验的平均薪资分布图
def education_jobYearAndAvgSalary_bar(data):
    # 数据处理
    # df_data = pd.DataFrame(data.groupby(by=['jobYear', 'education']).aggregate(np.mean).loc[:, 'salary_average'])
    temp_data = pd.DataFrame(data.groupby(by=['jobYear', 'education']).aggregate({'salary_average': np.mean}))
    temp_data = temp_data.reset_index()
    df_data = temp_data.groupby(['jobYear', 'education']).apply(lambda x: (round(x.mean(), 3) * 1000).astype(np.int64))
    df_data = df_data.reset_index()
    print(df_data)
    # 画图
    plt.style.use('ggplot')
    # plt.figure(figsize=(16, 10), dpi=80)
    # # 应届毕业生
    # x_Freshgraduates = df_data[df_data['jobYear'] == '应届毕业生']['education'].values
    # y_Freshgraduates = df_data[df_data['jobYear'] == '应届毕业生']['salary_average'].values
    # print(x_Freshgraduates)
    # print(y_Freshgraduates)
    # colors = cm.rainbow(np.arange(len(y_Freshgraduates)) / len(y_Freshgraduates))
    # plt.bar(x=range(len(x_Freshgraduates))  # 指定条形图x轴的刻度值
    #                , height=y_Freshgraduates
    #                , tick_label=x_Freshgraduates
    #                , color=colors
    #                )
    #
    # for x, y in enumerate(y_Freshgraduates):
    #     plt.text(x, y + 0.1, '%s' % round(y, 1), ha='center')
    #     pass
    #
    #
    # # 1-3年工作经验
    # x_jobYear13 = df_data[df_data['jobYear'] == '1-3年']['education'].values
    # y_jobYear13 = df_data[df_data['jobYear'] == '1-3年']['salary_average'].values
    # print(x_jobYear13)
    # print(y_jobYear13)
    # colors = cm.rainbow(np.arange(len(y_jobYear13)) / len(y_jobYear13))
    # plt.bar(x=range(len(x_jobYear13))  # 指定条形图x轴的刻度值
    #                 , height=y_jobYear13
    #                 , tick_label=x_jobYear13
    #                 , color=colors
    #                )
    #
    # for x13, y13 in enumerate(y_jobYear13):
    #     plt.text(x13, y13 + 0.1, '%s' % round(y13, 1), ha='center')
    #
    #
    # plt.show()

    pass

def industryAndAvgSalary(data):

    # count_data = data[['company_industry', 'salary_average']].groupby(by=['company_industry']).count()
    # count_data.reset_index(inplace=True)
    # count_data.rename(columns={'salary_average': 'industryNum'}, inplace=True)
    # count_data.sort_values(by='industryNum', ascending=False, inplace=True)
    # count_data.reset_index(inplace=True, drop=True)

    # # 计算各行各业平均薪资
    df_data = data[['company_industry', 'salary_average']].groupby('company_industry').apply(
        lambda x: (round(x.mean(), 3) * 1000).astype(np.int64))
    df_data.reset_index(inplace=True)
    # ddf_data = df_data.copy()
    # print(ddf_data)
    # for i in range(ddf_data.shape[0]):
    #     com_industry = ddf_data.loc[i]['company_industry']
    #     for j in range(count_data.shape[0]):
    #         cou_industry = count_data.loc[i]['company_industry']
    #         if (com_industry == cou_industry):
    #             df_data.append({''})

    # 计算所有工资平均值
    avgSalary = (round(data['salary_average'].mean(), 3) * 1000).astype(np.int64)
    # 将平均薪资添加进去
    # df_data = df_data.append({'company_industry': '平均薪资', 'salary_average': avgSalary}, ignore_index=True)
    # 排序（降序）
    df_data.sort_values(by='salary_average', ascending=True, inplace=True)
    # 重设索引
    df_data.reset_index(drop=True, inplace=True)
    x_index = df_data['salary_average'].values
    y_value = df_data['company_industry'].values

    plt.style.use('ggplot')
    plt.figure(figsize=(10, 8), dpi=80)
    colors = cm.rainbow(np.arange(len(y_value)) / len(y_value))
    rect = plt.barh(y=range(len(y_value))  # 指定条形图x轴的刻度值
                    , width=x_index
                    , tick_label=y_value
                    , color=colors
                    )

    for y, x in enumerate(x_index):
        plt.text(x + 0.1, y, '%s' % round(x, 1), ha='center')
    plt.ylabel("公司所属行业")
    plt.xlabel("平均薪资")
    plt.title("公司所属行业与平均薪资分布图")
    plt.legend(rect, y_value)
    plt.show()
    pass


if __name__ == '__main__':

    data = pd.read_excel(r'data.xlsx', index_col=0)

    # 条形图-----学历与职位数量
    # educationAndJobNum(data)

    # 饼图-----各城市职位数量分布图
    # cityAndJobNum_pie(data)

    # 条形图-----各城市职位数量分布图
    # cityAndJobNum_bar(data)

    # 条形图-----各区位职位数量分布图
    locationAndJobNum_bar(data)

    # 条形图-----X线职位数量分布图
    # cityLevelAndJobNum_bar(data)

    # 条形图-----工作年限与职位数量分布图
    # jobYearAndJobNum_bar(data)

    # 条形图-----薪资区间与职位数量分布图
    # salaryAndJobNum_bar(data)

    # 条形图-----学历与平均薪资分布图
    # educationAndAvgSalary_barh(data)

    # 条形图-----工作经验与平均薪资分布图
    # jobYearAndAvgSalary_barh(data)

    # 条形图-----城市与平均薪资分布图
    # cityAndAvgSalary_barh(data)

    # 条形图-----区位与平均薪资分布图
    # locationAndAvgSalary_bar(data)

    # 条形图-----X线城市与平均薪资分布图
    # cityLevelAndAvgSalary_bar(data)

    # 各学历下不同工作经验的平均薪资分布图
    # education_jobYearAndAvgSalary_bar(data)

    # 各行业中平均薪资分布图
    # industryAndAvgSalary(data)


    pass
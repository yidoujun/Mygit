import pandas as pd

'''
工作年限和工作学历划分

工作年限：不限/应届生/1年以下/1-3年/3-5年/5-10年/10年以上
工作学历：学历不限/大专/本科/硕士/博士

@author:易都军
@date:2020/3/21
'''
pd.set_option('display.max_columns', 1000)
pd.set_option('display.width', 1000)
pd.set_option('display.max_colwidth', 1000)



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
    _list = ['初中', '高中', '中专', '招',]
    for i in _list:
        data = data[~data["job_year"].str.contains(i)]
    return data
    pass

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




if __name__ == '__main__':
    # 读取数据
    data = pd.read_csv(r'./file/year_education.csv')
    # 删除工作年限里面的脏数据
    data = delete_jobYeal(data)
    # 删除工作学历里面的脏数据
    data = delete_jobEducation(data)
    # 重设索引
    data = data.reset_index(drop=True)
    # 工作年限划分
    jobYear_list = deal_jobYear(data)
    jobYear = pd.DataFrame(jobYear_list, columns=['job_year'])
    # 工作学历划分
    jobEducation = data["job_education"].replace('无学历要求', '学历不限', regex=True)
    jobEducation = pd.DataFrame({"job_education":jobEducation.values})

    # 合并
    jobYear['job_education'] =  jobEducation


    # 查询本科
    # print(jobYear[jobYear["job_education"] == '本科'].count())
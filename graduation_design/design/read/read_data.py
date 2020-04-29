import pandas  as  pd
import csv
import os
import pymysql

'''
将数据从数据库中导入到csv文件中
'''

# 连接数据库，读取数据
def JoinDB():
    conn = pymysql.connect(host='localhost', user='ydj', passwd='ydjws', db='test_internet_recruitment', port=3306, charset='utf8')
    ##创建游标
    cur = conn.cursor()
    ##执行sql语句
    sql = "SELECT * FROM job_info_liepin"
    count=cur.execute(sql)
    print('总共有%s条数据' %count)
    # 搜取所有结果
    results = cur.fetchall()
    # 获取表的数据结构字段
    fields = cur.description
    return list(results), list(fields)

S=JoinDB()
results=S[0]
fields=S[1]

# 写入文件
def writer_file(results, fields):
    # 查看文件大小
    file_size = os.path.getsize('./data_liepin.csv')
    if file_size == 0:
        # 表头
        name=[]
        results_list=[]
        for  i  in  range(len(fields)):
            name.append(fields[i][0])
        for  i  in  range(len(results)):
            results_list.append(results[i])
        # 建立DataFrame对象
        file_test = pd.DataFrame(columns=name, data=results_list)
        # 数据写入,不要索引
        file_test.to_csv('./data_liepin.csv', encoding='utf-8', index=False)
    else:
        with  open('./data_liepin.csv', 'a+', newline='') as  file_test:
            # 追加到文件后面
            writer = csv.writer(file_test)
            # 写文件
            writer.writerows(results)


if __name__ == '__main__':
    writer_file(results,fields)
# -*- coding: utf-8 -*-

import pandas as pd
import jieba
import jieba.analyse
import re
import collections
import time

pd.set_option('display.max_columns', 1000)
pd.set_option('display.width', 1000)
pd.set_option('display.max_colwidth', 1000)

# 停用词列表
def stopwordslist(filepath):
    stopwords = open(filepath, encoding='utf-8').read()
    stopwords = jieba.cut(stopwords, cut_all=False)
    # 注意这里是'\n '而不是'\n'
    stopwords2 = "\n ".join(stopwords)
    return stopwords2

# 文本去噪--去除一些标点符号、数字等等
def textDenoising(sentence):
    # 正则规则
    r1 = '[\s+.!\/_,$%^*(+\"\')]+|[:：+——()?【】“”！，。？、~@#￥%……&*（）]+'
    # r2 = '[^\u4e00-\u9fa5]'
    # 删除标点符号
    temp = re.sub(r1, '', sentence)
    # 删除英文和数字
    # temp = re.sub(r2, '', temp)
    return temp


# 移除分词中的停用词
def movestopwords(sentence):
    # 加载停用词的路径
    stopwords = stopwordslist(inputStopWordsFilePath)
    outstr = ''
    for word in sentence.split("\n"):
        if word not in stopwords:
            if word != '\t' and '\n':
                outstr += word
    return outstr

# 进行文本处理
def dealText(data):
    text_list = []
    for i in range(data.shape[0]):
        # 取岗位详情（一行一行的取，是str类型）
        jobDetailText = data.loc[i]['job_detail']
        # 去除符号
        jobDetailText = textDenoising(jobDetailText)
        # 分词
        jobDetailText = jieba.cut(jobDetailText, cut_all=False, HMM=False)
        # 注意这里是'\n '而不是'\n'
        spaceText = "\n ".join(jobDetailText)
        jobDetailContent = movestopwords(spaceText)
        text_list.append(jobDetailContent)
    return text_list


if __name__ == '__main__':


    inputStopWordsFilePath = r'./stopwords.txt'

    #开始时间
    print(time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time())))
    jieba.load_userdict(r'./userdice.txt')

    # 读取文件
    allData = pd.read_excel(r'./data.xlsx', encoding='utf-8', index_col=0)
    listText = dealText(allData)
    listStr = "".join(listText)
    # 统计词频
    wordFrequency = listStr.split(" ")
    q = collections.Counter(wordFrequency)
    dataq = pd.DataFrame(q.items())
    dataq.to_excel("industry.xlsx", header=False, encoding='utf-8')
    #结束时间
    print(time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time())))   # print(listStr)
    pass
# -*- coding: utf-8 -*-

import pandas as pd
import jieba

# 停用词列表
def stopwordslist(filepath):
    stopwords = open(filepath, encoding='utf-8').read()
    stopwords = jieba.cut(stopwords, cut_all=True)
    # 注意这里是'\n '而不是'\n'
    stopwords2 = "\n ".join(stopwords)
    return stopwords2

def movestopwords(sentence):
    # 加载停用词的路径
    stopwords = stopwordslist(inputStopWordsFilePath)
    outstr = ''
    for word in sentence.split("\n"):
        # print(word)
        if word not in stopwords:
            if word != '\t' and '\n':
                outstr += word
    return outstr


if __name__ == '__main__':

    inputStopWordsFilePath = r'./file/stopwords.txt'
    inputPath = r'./file/test.txt'

    # 读取文件
    text = open(inputPath, encoding='utf-8').read()
    # 分割语句，形成词汇
    wordlist_text = jieba.cut(text, cut_all=True)
    # 注意这里是'\n '而不是'\n'
    wl_space_split = "\n ".join(wordlist_text)
    # str类型
    listcontent = movestopwords(wl_space_split)
    print(listcontent)


import jieba #导入结巴分词，需要自行下载安装
import pandas as pd


def deal_city(data):

    city_list = []
    for word in  data.split("\n"):
        city_list.append(word)
    return city_list

def pre_deal_city(inputfile, outputfile):

    # 读取文件
    text = open(inputfile, encoding='utf-8').read()

    # 分割语句，形成词汇
    wordlist_text = jieba.cut(text, cut_all=True)
    # 注意这里是'\n '而不是'\n'
    wordlist_text_split = "\n ".join(wordlist_text)
    city_list = deal_city(wordlist_text_split)
    data = pd.DataFrame(city_list)
    data.to_csv(outputfile, index=False, header=False, encoding='utf-8')

def mapper_city(outputfile, location):
    data = pd.read_csv(outputfile)
    location_list = []
    for i in range(data.shape[0]):
        location_list.append(location)
    data['location'] = pd.DataFrame(location_list)
    print(data)
    data.to_csv(outputfile)
    pass

if __name__ == '__main__':

    # inputfile = r'xibei.txt'
    outputfile = r'xinan_result.csv'
    #
    # loction = "西北地区"

    # 简单处理
    # pre_deal_city(inputfile, outputfile)

    # 读取处理后的文件，为其加上区位
    # mapper_city(outputfile, loction)

    data = pd.read_csv(outputfile)
    print(data)

    pass
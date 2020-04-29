from jieba import analyse
import jieba

str1 = '数据采集项目经理 - PM of Domestic & Centralized DC'

str2 = '27564- 体育游戏数据分析（深圳）'

rst1 = jieba.analyse.extract_tags(str2, topK=20, withWeight=True)

print(rst1)


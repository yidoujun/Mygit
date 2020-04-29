from time import time
import random
from random import sample
import hashlib
import math
import requests

action = math.floor(1e5 * random.random())

# 生成时间戳
tim = round(time())

# 生成5个随机大写字母
randstr = ''.join(sample([chr(i) for i in range(65, 91)], 5))


value = str(action) + str(tim) + randstr

def hex5(value):
    manipulator = hashlib.md5()
    manipulator.update(value.encode("UTF-8"))
    return manipulator.hexdigest()

hexs = hex5(value)

def uri():
    args = '?actions={}&tim={}&randstr={}&sign={}'.format(action, tim, randstr, hexs)
    return args

url = 'http://www.porters.vip/verif/sign/fet' + uri()


r = requests.get(url)

print(r.status_code, r.text)



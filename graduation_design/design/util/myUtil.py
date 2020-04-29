# MyUtil
class MyUtil:
    def salary_util(self, money):
        if money == '面议':
            salary_money = ''
            return salary_money

        k = money.split("-")
        # 处理薪水格式为：xx元/天或者xx元/小时
        if len(k) == 1:
            min_money = k[0].split("元")[0]
            symbol = k[0].split("/")[1]
            if symbol == '天':
                min_money = int(min_money) * 30
                salary_money = str(min_money) + 'k'
                return salary_money
            elif symbol == "小时":
                min_money = int(min_money) * 8 * 30
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
                salary_money = str(min_money) + '-' + str(max_money) + 'k'
                return salary_money

            elif first_symbol == '万' and second_symbol == '年':
                min_money = float(k[0]) * 10 / 12
                max_money = float(k[1].split('万')[0]) * 10 / 12
                salary_money = str(round(min_money, 2)) + '-' + str(round(max_money, 2)) + 'k'
                return salary_money

            else:
                min_money = float(k[0])
                max_money = float(k[1].split('千')[0])
                salary_money = str(min_money) + '-' + str(max_money) + 'k'
                return salary_money
        pass

    def test(self):
        print("xxxxx")
        pass

    pass

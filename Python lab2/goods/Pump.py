from goods.Good import *
from enums.GoodType import *
from enums.GoodName import *


class Pump(Good):
    goods_type = GoodType.FORANY
    goods_name = GoodName.PUMP

    def __init__(self, diametr, name, price, material, weight, manufacturer, color, amount):
        self.diametr = diametr
        Good.__init__(self, name, price, material, weight, manufacturer, color, amount)

    def __str__(self):
        return "Name: " + str(self.name) + " price: " + str(self.price) + " diametr:" + str(self.diametr)

from goods.Good import *
from enums.GoodType import *
from enums.GoodName import *


class Stick(Good):
    goods_type = GoodType.FORANY
    goods_name = GoodName.STICK

    def __init__(self, length, name, price, material, weight, manufacturer, color, amount):
        self.length = length
        Good.__init__(self, name, price, material, weight, manufacturer, color, amount)

    def __str__(self):
        return "Name: " + str(self.name) + " price: " + str(self.price) + " length:" + str(self.length)
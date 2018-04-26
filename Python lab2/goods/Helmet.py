from goods.Good import *
from enums.GoodType import *
from enums.GoodName import *


class Helmet(Good):
    goods_type = GoodType.FORANY
    goods_name = GoodName.SKATES

    def __init__(self, sizeOfHead, name, price, material, weight, manufacturer, color, amount):
        self.sizeofhead = sizeOfHead
        Good.__init__(self, name, price, material, weight, manufacturer, color, amount)

    def __str__(self):
        return "Name : " + str(self.name) + " price: " + str(self.price) + " the size of head is " + str(self.sizeofhead)
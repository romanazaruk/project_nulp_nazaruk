from goods.Good import *
from enums.GoodType import *
from enums.GoodName import *


class Skates(Good):
    goods_type = GoodType.FORANY
    goods_name = GoodName.SKATES

    def __init__(self, sizeOfLeg, name, price, material, weight, manufacturer, color, amount):
        self.sizeofleg = sizeOfLeg
        Good.__init__(self, name, price, material, weight, manufacturer, color, amount)

    def __str__(self):
        return "Name: " + str(self.name) + " price: " + str(self.price) + " the size of leg is " + str(self.sizeofleg)
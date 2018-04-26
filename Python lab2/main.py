from goods.Helmet import *
from goods.Pump import *
from HockeyClub import *
from goods.Skates import *
from goods.Stick import *


if __name__ == '__main__':
    hockeyclub = HockeyClub()

    helmet = Helmet("S", "Bruce", 50.5, "metal", 2.5, "NIKE", "White", 2)
    pump = Pump(34, "Kolo", 5.2, "steel", 2.3, "NIKE", "Black", 50)
    skates = Skates(44, "Ignat", 34.0, "metal", 5.1, "NIKE", "White", 15)
    stick = Stick(80, "Ostap", 54.9, "wood", 6.4, "Nike", "GREY", 47)

    hockeyclub.goods = [helmet, pump, skates, stick]
    print("Previous list:")
    hockeyclub.print_list()

    hockeyclub.sort_by_price()
    print("Sorted list")
    hockeyclub.print_list()

    pass
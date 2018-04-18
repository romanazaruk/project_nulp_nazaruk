class Hotel:
    __name = "no name"
    __stars, __area, __rooms, __floors = 0, 0, 0, 0
    __total_area = 0

    def __init__(self, name="no name", stars=0, area=0, rooms=0, floors=0):
        self.__name = name
        self.__area = area
        self.__rooms = rooms
        self.__floors = floors
        self.__stars= stars
        Hotel.__total_area += self.__area

    def to_string(self):
        print("Name: " + self.__name + ", stars: " + str(self.__stars)
              + ", area: " + str(self.__area) + ", rooms: "
              + str(self.__rooms) + ", floors: " + str(self.__floors))

    def print_sum(self):
        print("The area of the hotel " + self.__name + " is " + str(self.__area))

    @staticmethod
    def print_static_sum():
        print("The total area of all hotels is  " + str(Hotel.__total_area))


if __name__ == "__main__":
    hotel = Hotel()
    grand = Hotel("Grand Hotel ", 5, 200, 10, 2)
    eurohotel = Hotel("EUROHOTEL", 4, 140, 100, 5)

    grand.to_string()
    eurohotel.to_string()

    Hotel.print_static_sum()
    grand.print_sum()
    eurohotel.print_sum()
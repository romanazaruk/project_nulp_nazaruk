class Insect:

    def __init__(self, name, speed, weight):
        self.name = name
        self.speed = speed
        self.weight = weight

    def __repr__(self):
        return str(self.name)+" "+str(self.speed)+" "+str(self.weight)
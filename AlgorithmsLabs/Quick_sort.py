class QuickSort:
    list_to_sort = []
    exchanges = 0
    comp = 0

    def __init__(self, list):
        self.list_to_sort = list

    def quick_sort(self):

        def partition(begin, end):
            if end - begin < 1:
                return
            left = begin + 1
            right = end
            pivot = self.list_to_sort[begin].weight
            while True:
                while self.list_to_sort[left].weight > pivot and left < end:
                    left += 1
                    self.comp += 1

                while not self.list_to_sort[right].weight > pivot and right > begin:
                    right -= 1
                    self.comp += 1

                if left >= right:
                    break

                self.list_to_sort[left], self.list_to_sort[right] = self.list_to_sort[right], self.list_to_sort[left]
                self.exchanges += 1
            self.list_to_sort[begin], self.list_to_sort[right] = self.list_to_sort[right], self.list_to_sort[begin]
            self.exchanges += 1
            partition(begin, right - 1)
            partition(right + 1, end)

        partition(0, len(self.list_to_sort) - 1)
        print("Comparisons: " + str(self.comp))
        print("Swaps: " + str(self.exchanges))

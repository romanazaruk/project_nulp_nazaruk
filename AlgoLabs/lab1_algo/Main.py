import time

from Insect import Insect

from Quick_sort import QuickSort

def Selection_sort(list):
    N = len(list)
    for i in range(0, N - 1):
        min_index = i
        for j in range(i + 1, N):
            if list[j].speed > list[min_index].speed:
                min_index = j
        list = swap(list, i, min_index)
    return list


def swap(list, i, min_index):
    tmp = list[i]
    list[i] = list[min_index]
    list[min_index] = tmp
    return list

if __name__ == "__main__":
    insect_list = []
    insect_name = 0
    insect_speed = 1
    insect_weight = 2
    file = open('data.txt')
    for line in file:
        values = line.split(',')
        insect = Insect
        insect = Insect(values[insect_name], int(values[insect_speed]), int(values[insect_weight][:-1]))
        insect_list.append(insect)

    print("SELECTION SORT")
    start_time = time.clock()
    Selection_sort(insect_list)
    print("Time: " + str(time.clock() - start_time))
    for insect in insect_list:
        print(insect)

    print("QUICK SORT")
    quick_sort = QuickSort(insect_list)
    start_time = time.clock()
    quick_sort.quick_sort()
    print("Time: " + str(time.clock() - start_time))
    for insect in insect_list:
         print(insect)




list = [(1, 2), (3, 5), (5, 8), (6, 7), (9, 12)]

def algorithm(list):

    first = 0
    second = 1
    listOfNumbers = list.__len__()

    # sorting list
    for n in range(1, listOfNumbers):
        indx = n - 1
        while (indx > -1) and list[indx + 1] < list[indx]:
            list[indx + 1], list[indx] = list[indx], list[indx + 1]
            indx -= 1

    i = 0
    while i < listOfNumbers - 1:
        j = i + 1
        while j < listOfNumbers:
            if list[i][second] > list[j][second]:
                list.pop(j)
                listOfNumbers -= 1
            else:
                if list[i][second] < list[j][first]:
                    j += 1
                    continue
                else:
                    new_element = (list[i][first], list[j][second])
                    list[i] = new_element
                    list.pop(j)
                    listOfNumbers -= 1
        i += 1
    return list

import unittest
import Main


class Test(unittest.TestCase):

    def test_algorithm(self):
        test_lists = [
            [(1, 2), (3, 5), (5, 8), (6, 7), (9, 12)]
        ]
        test_results = [
            [(1, 2), (3, 8), (9, 12)]
        ]

        for i in range(test_lists.__len__()):
            self.assertEqual(Main.algorithm(test_lists[i]), test_results[i])

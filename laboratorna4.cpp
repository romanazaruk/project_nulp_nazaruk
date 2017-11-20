// laboratorna4.cpp : Defines the entry point for the console application.
//


#include "stdafx.h"
#include <iostream>

using namespace std;

const short N = 5;

class Matrix
{
private:
	int matrix[N][N];
	int i, j;
	int * products;
public:
	void InputMatrix();
	void WriteMatrix();
	void SortMatrix();
	void Averages();

};

void Matrix::InputMatrix()
{
	cout<< "Write matrix:"<< endl;

	for (i = 0; i < N; i++)
	{
		for (j = 0; j < N; j++)
		{
			cout<< '[' << i + 1 << "][" << j + 1 << "] = ";
			cin >> matrix[i][j];
		}
	}
}

void Matrix::WriteMatrix()
{
	for (i = 0; i < N; i++, cout << endl)
	{
		for (j = 0; j < N; j++)
		{
			printf("%4d ", matrix[i][j]);
		}
	}
}

void Matrix::SortMatrix()
{
	int i, k, j, c, z;
	//compare each element with other
	for (k = 0; k < N; k++)
	{
		for (c = 0; c < N; c++)
		{
			for (j = 0; j < N; j++)
			{
				for (i = 0; i < N; i++)
				{
					//search the least element and stand it on start of array
					if (matrix[k][c] < matrix[i][j])
					{
						z = matrix[k][c];
						matrix[k][c] = matrix[i][j];
						matrix[i][j] = z;
					}
				}
			}
		}
	}
}
void Matrix::Averages()
{
	double sum = 0;
	double ser;
	int product = 0;
	for (i = 0; i < N - 1; i++)
	{
		product = 1;
		for (j = i + 1; j < N; j++)
		{

			product *= matrix[i][j];

		}
		//products[i] = product;
		
		cout << "Averages: " << product << endl << endl;
		sum += product;

	}
	ser = sum / 4;
	cout << "Arithmetic mean:" << ser << endl;
}

int main(void) {
	Matrix M;

	M.InputMatrix();
	cout << "\n Inputed matrix:" << endl;
	M.WriteMatrix();
	M.SortMatrix();
	cout << "\n Sorted matrix:" << endl;
	M.WriteMatrix();
	M.Averages();

	system("PAUSE");
	return 0;
}



// lab22.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<conio.h>
#include<math.h>
#include<stdio.h>


int main(void) {

	double BeginOfRange, i, EndOfRange, step, result, pohubka, dodanok;
	int k;//x=i

	printf("Begin of range: ");
	scanf_s("%lf", &BeginOfRange);

	printf("End of range: ");
	scanf_s("%lf", &EndOfRange);

	printf("State step: ");
	scanf_s("%lf", &step);

	printf("Write pohubka: ");
	scanf_s("%lf", &pohubka);

	for (i = BeginOfRange; i >= BeginOfRange && i <= EndOfRange; i += step) {
		result = 0;
		//dodanok = 0;
		k = 0;
		 
		if (i == 0) {
			result = 0;
			goto m;
		}
		
		do {		
			dodanok = (pow((-1), k) * pow(i, (2 * k + 3))) / ((2 * k + 1)*(2 * k + 3));
			result =result+ dodanok;
			k++;
		} while (dodanok >= pohubka);


		m:printf("i = %lf result = %lf\n", i, result);
	}
	_getche();
	return 0;
}


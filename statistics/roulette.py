#!/usr/bin/python

from random import randint

def draw( number ):
	total = 0;

	for x in range (0,number):
		iteration = randint(1,37)
		if iteration == 1:
			total = total + 35;
		else:
				total = total -1
		print total
	return

draw (100)

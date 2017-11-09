# Ruikang Tao
# rtao6@ucsc.edu
# programming assignment 7
# this program generates a series of functions that operate vector calculations.

"""
This module provides functions to perform standard vector operations. Vectors
are represented as lists of numbers (floats or ints). Functions that take two
vector arguments may give arbitrary output if the vectors are not compatible,
i.e. of the same dimension.
"""

import random
import math

def add(u, v):
	"""
	Return the vector sum u+v.
	"""
	vec = []
	for i in range(len(u)):
		vec.append(u[i]+v[i])
	return vec
# end add()

def negate(u):
	"""
	Return the negative -u of the vector u.
	"""
	vec = []
	for i in range(len(u)):
		vec.append(-u[i])
	return vec
# end negate()   

def sub(u, v):
	"""
	Return the vector difference u-v.
	"""
	return add(u,negate(v))
# end sub()

def scalarMult(c, u):
	"""
	Return the scalar product cu of the number c by the vector u.
	"""
	vec = []
	for i in range(len(u)):
		vec.append(c*u[i])
	return vec
# end scalarMult()

def zip(u, v):
	"""
	Return the component-wise product of u with v.
	"""
	vec = []
	for i in range(len(u)):
		vec.append(u[i]*v[i])
	return vec
# end zip()

def dot(u, v):
	"""
	Return the dot product of u with v.
	"""
	return sum(zip(u,v))
# end dot()

def length(u):
	"""
	Return the (geometric) length of the vector u.
	"""
	total = 0
	for i in range(len(u)):
		total += (u[i])**2
	#newSum = total**0.5
	return total**(0.5)
# end length()
   
def unit(v):
	"""
	Return a unit (geometric length 1) vector in the direction of v.
	"""
	vec = []
	for i in range(len(v)):
		vec.append(v[i]/length(v))
	return vec

# end unit()

def angle(u, v):
   """
   Return the angle (in degrees) between vectors u and v.
   """
   return math.degrees(math.acos(dot(unit(u),unit(v))))
# end angle()

def randVector(n, a, b):
	"""
	Return a vector of dimension n whose components are random floats
 	in the range [a, b).
 	"""
	vec = []
	for i in range(n):
		vec.append(random.uniform(a,b))
	return vec
# end randomVector()
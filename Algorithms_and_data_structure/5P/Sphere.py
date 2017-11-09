#Ruikang Tao
#rtao6@ucsc.edu
#programming assignment 1
#this program takes in a number and uses it as the given radius
#to calculate the volume and the surface area of a sphere.


from math import pi as p

r = float(input("Enter the radius of the sphere: "))
volume = 4/3*p*r**3
SurfA = 4*p*r**2

print("The volume is: "+str(volume))
print("The surface area is: "+str(SurfA))
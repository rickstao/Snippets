#Ruikang Tao
#rtao6@ucsc.edu
#programming assignment 2
#this program takes in an odd number as the points of the star
#and uses turtle function to draw it on the screen.

import turtle

vertex = int(input("Enter an odd integer greater than or equal to 3: "))
angle = 180 - float(180/vertex)

wn = turtle.Screen() # Set up the window and its attributes
wn.bgcolor("white")
wn.title(str(vertex)+"-pointed star")

tess = turtle.Turtle() # Create tess and set some attributes
tess.color("blue", "green")
tess.pensize(2)
tess.speed(0)
num = 0;

tess.begin_fill()
tess.down()
tess.goto(150,0) #Set the begin point
for num in range(0,vertex): #for loop draws the star
	tess.fillcolor("green")
	tess.right(angle)
	tess.forward(300)
	tess.dot(10,"red")

	num = num + 1
tess.up()
tess.end_fill()
tess.hideturtle()
wn.mainloop()

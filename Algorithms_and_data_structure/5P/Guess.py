#Ruikang Tao
#rtao6@ucsc.edu
#programming assignment 3
#this program creates a guessing game that interacts with the users
#the game gives the user three guesses to guess a random number generated 
#from 1 to 10

import random

number = random.randint(1,10)

def printLow():
	print("Your guess is too low.")

def printHigh():
	print("Your guess is too high.")

print ("I'm thinking of an integer in the range 1 to 10. You have three guesses.")

for times in ["first", "second", "third"]:
	userInput = int(input("Enter your " + times + " guess: "))
	if userInput == number:
		print("You win!")
		break
	elif userInput < number:
		printLow()
		if times == "third":
			print("You lose. The number was", number)
	elif userInput > number:
		printHigh()
		if times == "third":
			print("You lose. The number was", number)
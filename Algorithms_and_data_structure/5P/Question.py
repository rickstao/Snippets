#Ruikang Tao
#rtao6@ucsc.edu
#programming assignment 5
#this program generates a game that plays the other side of the guessing game
#implemented in pa3

#takes in user input
print("Enter two numbers, low then high.")
low = int(input("low = "))
high = int(input("high = "))
print("")

#while loop makes sure user input is correct
while low >= high:
	print("Please enter the smaller followed by the larger number.")
	low = int(input("low = "))
	high = int(input("high = "))
	print("")

#gives instruction
print("Think of a number in the range", low, "to", high, ".")
print("")
L = list(range(low,high+1))
left = 0
right = len(L)-1
count = 0
run = True

#while loop tests if input is valid
#if so, execute the following
#else, break while loop
while run:
	#binary search guesses number
	guess = (left + right)//2
	print("Is your number Less than, Greater than, or Equal to", L[guess], "?")
	userInput = str(input("Type 'L', 'G' or 'E': "))
	print("")
	askInput = True
	while askInput:
		if userInput == 'L' or userInput == 'l':
			right = guess - 1
			count += 1
			askInput = False
		elif userInput == 'g' or userInput == 'G':
			left = guess + 1
			count += 1
			askInput = False
		elif userInput == 'e' or userInput == 'E':
			count += 1
			askInput = False
			if count == 1:
				print("Your number is", L[guess], ". I found it in", count, "guesse.")
				print("")
				run = False
			else:
				print("Your number is", L[guess], ". I found it in", count, "guesses.")
				print("")
				run = False
		else:
			userInput = str(input("Please type 'L', 'G' or 'E': "))
			print("")
	if left > right:
		print("Your answers have not been consistent.")
		print("")
		break
	if left == right:
		if count == 1:
			print("Your number is", L[left], ". I found it in", count, "guesse.")
			print("")
		else:
			print("Your number is", L[left], ". I found it in", count, "guesses.")
			print("")
		break
#Ruikang Tao
#rtao6@ucsc.edu
#programming assignment 4
#this program generates a prime bumber list which length is determined
#by user's discretion and prints out the list in an increment of 10

L = [2]
print("")
userInput = int(input("Enter the number of Primes to compute: "))
print("")
count = 0
n = 3
while count < userInput:
	for p in L:
		if n < p**2:
			L.append(n)
			n += 1
			count += 1
			break
		if n%p == 0:
			n += 1
			break
print("The first", userInput, "primes are:")
for i in range(1, count+1):
	print(L[i-1], end =" ")
	if i % 10 == 0:
		print("")
print("")
print("")
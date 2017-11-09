#Ruikang Tao
#rtao6@ucsc.edu
#programming assignment 6
#this program generates a dice probability calculator. It will calculate
#the sum, the frequency, the relative frequency, and the experimental
#probability

import random

#takes in valid user input
diceNum = int(input("Enter the number of dice: "))
while diceNum < 1:
   print("The number of dice must be at least 1")
   diceNum = int(input("Please enter the number of dice: "))

print()
diceSide = int(input("Enter the number of sides on each die: "))
while diceSide < 2:
   print("The number of sides on each die must be at least 2")
   diceSide = int(input("Please enter the number sides on each die: "))

print()
numberOfTrials = int(input("Enter the number of trials to perform: "))
while numberOfTrials < 1:
   print("The number of trials must be at least 1")
   numberOfTrials = int(input("Please enter the number of trials to perform: "))

print()
rng = random.Random(237) # Create a random number generator

def throwDice():
   """
   throws dices and returns the result in a tuple
   """
   sums = [rng.randrange(1,diceSide+1) for i in range(diceNum)]
   L = tuple(sums)
   return L
# end throwDice()


#-- main ----------------------------------------------------------------------   


# perform simulation, record and print frequencies
frequency = (diceNum*diceSide+1)*[0]  # same as [0,0,0,0,0,0,0,0,0,0,0,0,0]
for i in range(numberOfTrials):
   t = throwDice()
   frequency[sum(t)] += 1;
# end for

# calculate relative frequencies, probabilities and errors
relativeFrequency = [0]*diceNum
probability = [0]*diceNum
exProb = [0]*diceNum
error = [0,0]
for i in range(diceNum, len(frequency)):
   relativeFrequency.append(frequency[i]/numberOfTrials)
   probability.append(min(i-1,13-i)/36)
   exProb.append(relativeFrequency[i]*100)

print()

#prints out the results
f1 = "{0:<9}{1:<14}{2:<23}{3:<24}"
f2 = 70*"-"
f3 = "{0:>4}{1:>11}{2:>18.5f}{3:>21.2f} %"
print(f1.format("Sum","Frequency","Relative Frequency","Experimental Probability"))
print(f2)
for i in range(diceNum, len(frequency)):
   print(f3.format(i, frequency[i], relativeFrequency[i], exProb[i]))
#end for
print()
/*
  This is a simple number guessing game. The computer tries to guess a number 
  between 1 and 100. The user provides hints by indicating that the current guess
  was too small, too big, or the correct guess.
  Author: Charlie McDowell
 */
#include <stdio.h>

#define LINE_MAX 100
#define GOT_IT 1
#define TOO_BIG 2
#define TOO_SMALL 3
#define MAX_NUM 100

int nextGuess(int min, int max);

int main() {
  int response;
  int numGuesses = 1;
  int min = 1;
  int max = MAX_NUM;
  int guess = nextGuess(min, max);
  printf("Think of a number between %d and %d.\n", min, max);
  printf("I'll try and guess it in 8 or fewer guesses.\n");

  while (min < max ) {
    // make a guess and get the user response
    response = makeAGuess(numGuesses, guess);

    // decide what to do based upon the user response
    if (response == GOT_IT) {
      min = max = guess;
    }
    else if (response == TOO_BIG) {
      max = guess -1;
      guess = nextGuess(min, max);
      numGuesses++;
    }
    else if (response == TOO_SMALL) {
      min = guess+1;
      guess = nextGuess(min,max);
      numGuesses++;
    }
    else {
      printf("That was not a valid response.\n");
    }
  }
  if (response != 1) {
    printf("The number you were thinking of was %d\n", guess);
    numGuesses++;
  }
  printf("I did it in %d guesses.\n", numGuesses);
}

/*
  Prompt the user with the current guess and ask for their response.
  The response should be an integer value that is one of GOT_IT, TOO_BIG, or
  TOO_SMALL (constants).
 */
int makeAGuess(int numGuesses, int guess) {
  int response;

  printf("Guess %d: Is it %d?\n", numGuesses, guess);
  printf("Enter %d if I guessed correctly.\n", GOT_IT);
  printf("Enter %d if my guess was too big.\nOtherwise enter %d.\n",
	 TOO_BIG, TOO_SMALL);
  scanf("%d",&response);
  return response;
}

  
/*
  Determine the next guess given the current min and max values for the possible
  range of the  unknown value. The optimal strategy is to guess right in the 
  middle of the current range, thus cutting the range in half on each guess.
 */
int nextGuess(int min, int max) {
    int diff;
    diff = max - min;
    return min + (diff+1)/2;
}

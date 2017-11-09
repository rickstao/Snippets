#include<stdio.h>
#include<stdlib.h>
int count = 0;  // number of lines
int counta= 0; //number of the type of characters
int countd= 0;
int countp= 0;
int countw= 0;
void extract_chars(char* s, char* a, char* d, char* p, char* w){
	int i, j;
	count++;
    counta=0;
    countd=0;
    countp=0;
    countw=0;

    memset(&a[0], 0, sizeof(a));
    memset(&d[0], 0, sizeof(d));
    memset(&p[0], 0, sizeof(p));
    memset(&w[0], 0, sizeof(w));

    for(i=0; *(s+i)!='\0';i++){ // iterate until the currenty address points to null
        
        if(isalpha(*(s+i))){    // check alphabet
            *(a+counta)=*(s+i);
            counta++;
        }
        if(isdigit(*(s+i))){  // check digit
            *(d+countd)=*(s+i);
            countd++;
        }
        if(ispunct(*(s+i))){  // check punctuation
            *(p+countp)=*(s+i);
            countp++;
        }
        if((*(s+i)==' ')||(*(s+i)== '\n')||(*(s+i)=='\t')){  // check white space etc..
            *(w+countw)=*(s+i);
            countw++;
        }
    }
	
}
/* check if malloc valid
if( p==NULL ){
   fprintf(stderr, "malloc failed\n");
   exit(EXIT_FAILURE);
}
*/



int main(int argc, char* argv[]){
	FILE* in;  /* file handle for input */  
    FILE* out; /* file handle for output */
    //char word[256]; /* char array to store words from input file */
	//int i,n;
	char *ch1, *ch2, *ch3, *ch4;
	char *A;
	int len = 256;
	//int count = 0;

   /* check command line for correct number of arguments */
	if( argc != 3 ){
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		exit(EXIT_FAILURE);
	}

   /* open input file for reading */
	in = fopen(argv[1], "r");
	if( in==NULL ){
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}

   /* open output file for writing */
	out = fopen(argv[2], "w");
	if( out==NULL ){
		printf("Unable to write to file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}

	A = malloc(len*sizeof(*A));
	ch1 = malloc(len*sizeof(*ch1));
	ch2 = malloc(len*sizeof(*ch2));
	ch3 = malloc(len*sizeof(*ch3));
	ch4 = malloc(len*sizeof(*ch4));
   /* read words from input file, print on separate lines to output file*/
	while( fgets(A, len*sizeof(char), in)){
		//count++;
		//extract_chars(char* s, char* a, char* d, char* p, char* w);
		
		extract_chars(A, ch1, ch2, ch3, ch4);
		fprintf(out, "Line %d contains:\n", count);
		fprintf(out, "%d alphabetic characters: %s \n", counta, ch1);
		fprintf(out, "%d numeric characters: %s \n", countd, ch2);
		fprintf(out, "%d punctuation characters: %s \n", countp, ch3);
		fprintf(out, "%d whitespace characters: %s \n", countw, ch4);

	}
	free(A);
	free(ch1);
	free(ch2);
	free(ch3);
	free(ch4);
   /* close input and output files */
	fclose(in);
	fclose(out);

	return(EXIT_SUCCESS);
}
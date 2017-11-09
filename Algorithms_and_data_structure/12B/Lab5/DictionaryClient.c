//-----------------------------------------------------------------------------
// DictionaryClient.c
// Test client for the Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"
#define MAX_LEN 160

int main(int argc, char* argv[]){
	Dictionary Dict = newDictionary();
	FILE *in, *out;
	char *token;
	char line[MAX_LEN];
	/* check command line for correct number of arguments */
	if( argc != 3 ){
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	/* open input file for reading */
	in = fopen(argv[1], "r");
	out = fopen(argv[2], "w");
	if( in==NULL ){
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}

	int num, found;
	//while loop reads input file
	while (fgets(line, MAX_LEN, in) != NULL){
		token = strtok(line, " ");
		if (token[0] == 'i'){
			token = strtok(NULL, " ");
			num = atoi(token);
			DictInsert(num, Dict);
			fprintf(out, "inserted %d\n", num);
		}else if (token[0] == 'f'){
			token = strtok(NULL, " ");
			num = atoi(token);
			found = DictFind(num, Dict);
			if(found == 0){
				fprintf(out, "%d not present\n", num);
			}else{
				fprintf(out, "%d present \n", num);
			}
		}else if (token[0] == 'd'){
			token = strtok(NULL, " ");
			num = atoi(token);
			DictDelete(num, Dict);
			fprintf(out, "deleted %d\n", num);
		}else if (token[0] == 'p'){
			printDictionary(out,Dict);
		}
	}

	//close reader
	fclose(in);
	fclose(out);
	freeDictionary(&Dict);
	return(EXIT_SUCCESS);
}
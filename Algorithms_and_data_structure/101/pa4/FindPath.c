// Ruikang Tao
// rtao6
// PA4
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Graph.h"

#define MAX_LEN 160

int main(int argc, char* argv[]){
	// checks correct argument number
	if(argc != 3){
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		exit (1);
	}

	// open files for reading and writing
	FILE *in, *out;
	in = fopen(argv[1], "r");
	out = fopen(argv[2], "w");
	if (in == NULL) {
		printf("Unable to open file %s for reading.\n", argv[1]);
		exit(1);
	} if (out == NULL) {
		printf("Unable to open file %s for writing.\n", argv[2]);
		exit(1);
	}


	char line[MAX_LEN];
	int order;
	// assigns order
	fgets(line, MAX_LEN, in);
	sscanf(line, "%d", &order);

	Graph G = newGraph(order);

	// reads in numbers to assign edges
	int x,y;
	while(fgets(line,MAX_LEN, in) != NULL){
		sscanf(line, "%d %d", &x, &y);
		if(x == 0 && y == 0){
			break;
		} else{
			addEdge(G, x, y);
		}
	}

	printGraph(out, G);
	// analyze input and prints out shortest distance
	List L = newList();
	int source, destination;
	while(fgets(line, MAX_LEN, in) != NULL){
		sscanf(line, "%d %d", &source, &destination);
		if(source == 0 && destination == 0){
			break;
		} else{
			BFS(G, source);
			getPath(L, G, destination);
			if(length(L) == 0){
				fprintf(out, "\nThe distance from %d to %d is infinity\n", source, destination);
				fprintf(out, "No %d-%d path exists", source, destination);
			} else{
				fprintf(out, "\nThe distance from %d to %d is %d\n", source, destination, getDist(G, destination));
				fprintf(out, "A shortest %d-%d path is: ", source, destination);
				printList(out, L);
			}
		}
		clear(L);
	}

	// close files and clean up
	freeGraph(&G);
	freeList(&L);
	fclose(in);
	fclose(out);

	return(0);

}
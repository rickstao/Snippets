// Ruikang Tao
// rtao6
// PA5

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include"Graph.h"
#define MAX_LEN 160

int main(int argc, char *argv[]){
	// checks correct argument number
	if (argc != 3) {
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		exit (1);
	}
	// open files for reading and writing
	FILE *in, *out;
	in = fopen(argv[1], "r");
	out = fopen(argv[2], "w");
	if(in == NULL){
		printf("Unable to open file %s for reading.\n", argv[1]);
		exit(1);
	}
	if (out == NULL) {
		printf("Unable to open file %s for writing.\n", argv[2]);
		exit(1);
	}

	// read in order number
	char line[MAX_LEN];
	int order = 0;
	fgets(line, MAX_LEN, in);
	sscanf(line, "%d", &order);

	// add edges to the new graph G
	Graph G = newGraph(order);
	while(fgets(line, MAX_LEN, in)){
		int x,y;
		sscanf(line, "%d %d", &x, &y);
		if(x ==0 && y == 0){
			break;
		} else{
			addArc(G, x, y);
		}
	}

	// prints the original graph G
	fprintf(out, "Adjacency list representation of G: \n");
	printGraph(out, G);
	fprintf(out, "\n");

	// new List to store order
	List L = newList();
	for(int i=1; i<=order; i++){
		append(L, i);
	}

	// new graph that is a transpose of G
	Graph T = transpose(G);
	DFS(G,L);
	DFS(T,L);
	
	// get the SCC count
	int SCCcount = 0;
	for(moveFront(L); index(L) != -1; moveNext(L)){
		if(getParent(T, get(L)) == NIL){
			SCCcount++;
		}
	}

	// initialize a new List to store SCCs
	int count = 0;
	List* Q = calloc(SCCcount+1, sizeof(List));
	for(moveFront(L); index(L) != -1; moveNext(L)){
		if(getParent(T, get(L)) == NIL){
			count++;
			Q[count] = newList();
		}
		append(Q[count], get(L));
	}

	// print strongly connected components into out file
	fprintf(out, "G contains %d strongly connected components: \n", SCCcount);
	for(int i=1; i<SCCcount+1; i++){
		fprintf(out, "Component %d: ", i);
		printList(out, Q[(SCCcount+1)-i]);
		fprintf(out, "\n");
	}

	// clean up
	freeGraph(&G);
	freeGraph(&T);
	for(int i=1; i<SCCcount+1; i++){
		freeList(&(Q[i]));
	}
	freeList(&L);
	free(Q);
	// close files
	fclose(in);
	fclose(out);
}
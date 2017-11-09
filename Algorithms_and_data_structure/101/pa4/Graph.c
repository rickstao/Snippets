// Ruikang Tao
// rtao6
// PA4
#include <stdio.h>
#include <stdlib.h>

#include"Graph.h"

#define WHITE 'w'
#define GREY 'g'
#define BLACK 'b'

/*** structs ***/
typedef struct GraphObj{
	List* neighbors;
	char* color;
	int* distance;
	int* parent;
	int source;
	int order;
	int size;
}GraphObj;

typedef GraphObj* Graph;


/*** Constructors-Destructors ***/

// newGraph()
// returns a new Graph with proper allocated memory and initial values
Graph newGraph(int n){
	Graph G = malloc(sizeof(GraphObj));
	G->neighbors = calloc(n+1, sizeof(List));
	G->color = calloc(n+1, sizeof(char));
	G->distance = calloc(n+1, sizeof(int));
	G->parent = calloc(n+1, sizeof(int));
	G->source = NIL;
	G->order = n;
	G->size = 0;
	for(int i=1; i<=n; i++){
		G->neighbors[i] = newList();
		G->color[i] = WHITE;
		G->distance[i] = INF;
		G->parent[i] = NIL;
	}
	return G;
}

// freeGraph()
// frees all memory allocated
void freeGraph(Graph *pG){
	Graph G = *pG;
	for(int i=1; i<=getOrder(*pG); i++){
		freeList(&(G->neighbors[i]));
	}
	free(G->neighbors);
	//G->neighbors = NULL;
	free(G->distance);
	//G->distance = NULL;
	free(G->color);
	//G->color = NULL;
	free(G->parent);
	//G->parent = NULL;
	free(*pG);
	*pG = NULL;
}


/*** Access functions ***/

// getOrder()
// returns the order of the Graph
int getOrder(Graph G){
	if(G == NULL) {
		printf("Graph Error: getOrder() called on NULL Graph reference\n");
		exit(1);
	}
	return G->order;
}

// getSize()
// returns the size of the Graph
int getSize(Graph G){
	if(G == NULL) {
		printf("Graph Error: getSize() called on NULL Graph reference\n");
		exit(1);
	}
	return G->size;
}

// getSource()
// returns the source of the Graph
int getSource(Graph G){
	if(G == NULL) {
		printf("Graph Error: getParent() called on NULL Graph reference\n");
		exit(1);
	}
	return G->source;
}

// getParent()
// returns the parent of u
int getParent(Graph G, int u){
	if(G == NULL){
		printf("Graph Error: getParent() called on NULL Graph reference\n");
		exit(1);
	}
	if(u < 1 || u > getOrder(G)){
		printf("Graph Error: getParent() called on invalid u value.\n");
		exit(1);
	}
	return G->parent[u];
}

// getDist()
// returns the distance from u to source
int getDist(Graph G, int u){
	if(G == NULL){
		printf("Graph Error: getDist() called on NULL Graph reference\n");
		exit(1);
	}
	if(u < 1 || u > getOrder(G)){
		printf("Graph Error: getDist() called on invalid u value.\n");
		exit(1);
	}
	return G->distance[u];
}

// getPath()
// appends vertices of a shortest path to G
void getPath(List L, Graph G, int u){
	if(getSource(G) == NIL){
		printf("Graph Error: getPath() called on invalid source.\n");
		exit(1);
	}
	if(u<1 || u>getOrder(G)) {
		printf("Graph Error: getPath() called with out of bound u value\n");
		exit(1);
	}
	int x = getSource(G);
	if(x == u){
		append(L,x);
	} else if(G->parent[u] == NIL){
		append(L,NIL);
	} else{
		getPath(L, G, G->parent[u]);
		append(L,u);
	}
}


/*** Manipulation procedures ***/

// makeNull()
// clear all neighbors of Graph
void makeNull(Graph G){
	for(int i=1; i<=getOrder(G); ++i){
		clear(G->neighbors[i]);
	}
	G->size = 0;
}

// addEdge()
// inserts an undirectioinal edge to the Graph joining u and v
void addEdge(Graph G, int u, int v){
	if(u<1 || u>getOrder(G)){
		printf("Graph Error: addEdge() called on invalid u value.");
		exit(1);
	}
	if(v<1 || v>getOrder(G)){
		printf("Graph Error: addEdge() called on invalid v value.");
		exit(1);
	}
	addArc(G, u, v);
	addArc(G, v, u);
	G->size--;   // to accomodate for two increments on each addArc call
}

// addArc()
// inserts a directed edge to the Graph joining u and v
void addArc(Graph G, int u, int v){
	int temp;
	List Q = G->neighbors[u];
	if(u<1 || u>getOrder(G)){
		printf("Graph Error: addEdge() called on invalid u value.");
		exit(EXIT_FAILURE);
	}
	if(v<1 || v>getOrder(G)){
		printf("Graph Error: addEdge() called on invalid v value.");
		exit(EXIT_FAILURE);
	}
	if(length(Q) == 0){
		append(Q, v);
	} else{
		moveFront(Q);
		while(index(Q) >= 0){
			temp = get(Q);
			if(v < temp){
				insertBefore(Q,v);
				moveBack(Q);
			}
			moveNext(Q);
		}
		if(v > temp && index(Q) == -1){
			append(Q,v);
		}
	}
	G->size++;
}


// BFS()
// BFS search algorithm given source assigns fields
void BFS(Graph G, int s){
	for(int i=1; i<getOrder(G)+1; i++){
		G->color[i] = WHITE;
		G->distance[i] = INF;
		G->parent[i] = NIL;
	}
	G->source = s;
	G->color[s] = GREY;
	G->distance[s] = 0;
	G->parent[s] = NIL;
	List Q = newList();
	append(Q,s);
	//moveFront(Q);
	int y;
	while(length(Q) > 0){
		int x = front(Q);
		deleteFront(Q);
		List list = G->neighbors[x];
		for(moveFront(list); index(list)!=-1; moveNext(list)){
			y = get(list);
			if(G->color[y] == WHITE){
				G->color[y] = GREY;
				G->distance[y] = G->distance[x]+1;
				G->parent[y] = x;
				append(Q,y);
			}
		}
		G->color[x] = BLACK;
	}
	freeList(&Q);
}

// printGraph()
// prints the vertices and their neighbors
void printGraph(FILE* out, Graph G){
	if(G == NULL || out == NULL){
		exit(1);
	}
	for(int i=1; i<=getOrder(G); i++){
		fprintf(out, "%d: ", i);
		printList(out, G->neighbors[i]);
		fprintf(out, "\n");
	}
}
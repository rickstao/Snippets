// Ruikang Tao
// rtao6
// PA5

#include <stdio.h>
#include <stdlib.h>

#include"Graph.h"

#define WHITE 'w'
#define GREY 'g'
#define BLACK 'b'

int time;

/*** structs ***/
typedef struct GraphObj{
	List* neighbors;
	char* color;
	int* discover;
	int* parent;
	int* finish;
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
	G->discover = calloc(n+1, sizeof(int));
	G->parent = calloc(n+1, sizeof(int));
	G->finish = calloc(n+1, sizeof(int));
	G->order = n;
	G->size = 0;
	for(int i=1; i<=n; i++){
		G->neighbors[i] = newList();
		G->color[i] = WHITE;
		G->discover[i] = UNDEF;
		G->parent[i] = NIL;
		G->finish[i] = UNDEF;
	}
	return G;
}

// freeGraph()
// frees all memory allocated
void freeGraph(Graph *pG){
	for(int i=1; i<=getOrder(*pG); i++){
		freeList(&(*pG)->neighbors[i]);
	}
	free((*pG)->neighbors);
	//G->neighbors = NULL;
	free((*pG)->discover);
	//G->distance = NULL;
	free((*pG)->color);
	free((*pG)->finish);
	//G->color = NULL;
	free((*pG)->parent);
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

// getDiscover()
// returns the discover of the Graph
int getDiscover(Graph G, int u){
	if(G == NULL) {
		printf("Graph Error: getDiscover() called on NULL Graph reference\n");
		exit(1);
	}
	return G->discover[u];
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

// getFinish()
// returns the finish from u to source
int getFinish(Graph G, int u){
	if(G == NULL){
		printf("Graph Error: getFinish() called on NULL Graph reference\n");
		exit(1);
	}
	if(u < 1 || u > getOrder(G)){
		printf("Graph Error: getFinish() called on invalid u value.\n");
		exit(1);
	}
	return G->finish[u];
}


/*** Manipulation procedures ***/

// addEdge()
// inserts an undirectioinal edge to the Graph joining u and v
void addEdge(Graph G, int u, int v){
	if(u<1 || u>getOrder(G)){
		printf("Graph Error: addEdge() called on invalid u value.\n");
		exit(1);
	}
	if(v<1 || v>getOrder(G)){
		printf("Graph Error: addEdge() called on invalid v value.\n");
		exit(1);
	}
	addArc(G, u, v);
	addArc(G, v, u);
	G->size--;
}

// addArc()
// inserts a directed edge to the Graph joining u and v
void addArc(Graph G, int u, int v){
	int temp;
	if(u<1 || u>getOrder(G)){
		printf("Graph Error: addEdge() called on invalid u value.\n");
		exit(1);
	}
	if(v<1 || v>getOrder(G)){
		printf("Graph Error: addEdge() called on invalid v value.\n");
		exit(1);
	}
	if(length(G->neighbors[u]) == 0){
		append(G->neighbors[u], v);
	} else{
		moveFront(G->neighbors[u]);
		while(index(G->neighbors[u]) >= 0){
			temp = get(G->neighbors[u]);
			if(v < temp){
				insertBefore(G->neighbors[u],v);
				moveBack(G->neighbors[u]);
			}
			moveNext(G->neighbors[u]);
		}
		if(v > temp && index(G->neighbors[u]) == -1){
			append(G->neighbors[u],v);
		}
	}
	G->size++;
}

// DFS()
// performs DFS algorithm to find SCC
void DFS(Graph G, List S){

    if(getOrder(G) != length(S)){
        printf("Graph Error: DFS() called on invalid graph order number.\n");
        exit(1);
    }
    time=1;
    List L = copyList(S);
    for(int i=1; i<=length(S); i++){
        G->color[i]=WHITE;
        G->parent[i]=NIL;
    }
    for(moveFront(L); index(L) != -1; moveNext(L)){
        int x = get(L);
        if(G->color[x] == WHITE){
            Visit(G,x,S);
        }
    }
    freeList(&L);
}


/*** Other operations ***/

// Visit()
// helper function for DFS
void Visit(Graph G, int x, List S){
    G->color[x] = GREY;
    G->discover[x] = time++;
    for(moveFront(G->neighbors[x]); index(G->neighbors[x]) != -1; moveNext(G->neighbors[x])){
        int y = get(G->neighbors[x]);
        if(G->color[y] == WHITE){
            G->parent[y]=x;
            Visit(G,y,S);
        }
    }
    G->color[x]=BLACK;
    G->finish[x]=time++;
    prepend(S,x);
    deleteBack(S);
}

// transpose()
// returns the transpose of target graph
Graph transpose(Graph G){
	if (G == NULL) {
		printf("Error: transpose() called on NULL Graph pointer.\n");
		exit(1);
	}
	Graph T = newGraph(getOrder(G));
	for(int i = 1; i <= getOrder(G); i++){
		moveFront(G->neighbors[i]);
		while(index(G->neighbors[i]) != -1){
			addArc(T, get(G->neighbors[i]), i);
			moveNext(G->neighbors[i]);
		}
	}
	return T;
}

// copyGraph()
// makes a copy of the target graph
Graph copyGraph(Graph G){
	if (G == NULL) {
		printf("Error: copyGraph() called on NULL Graph pointer.\n");
		exit(1);
	}
	Graph copy = newGraph(getOrder(G));
	for(int i = 1; i <= getOrder(G); i++){
		moveFront(G->neighbors[i]);
		while(index(G->neighbors[i]) != -1){
			addArc(copy, i, get(G->neighbors[i]));
			moveNext(G->neighbors[i]);
		}
	}
	return copy;
}

// printGraph()
// prints the vertices and their neighbors
void printGraph(FILE* out, Graph G){
	if(G == NULL || out == NULL){
		printf("Error: printGraph() called on NULL Graph pointer.\n");
		exit(1);
	}
	for(int i=1; i<=getOrder(G); i++){
		fprintf(out, "%d: ", i);
		printList(out, G->neighbors[i]);
		fprintf(out, "\n");
	}
}
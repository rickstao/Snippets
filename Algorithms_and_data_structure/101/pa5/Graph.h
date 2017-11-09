// Ruikang Tao
// rtao6
// PA5
#ifndef _GRAPH_H_INCLUDE_
#define _GRAPH_H_INCLUDE_

#include<stdio.h>
#include"List.h"
#define UNDEF -1
#define NIL 0

typedef struct  GraphObj* Graph;

/*** Constructors-Destructors ***/
Graph newGraph(int n);
void freeGraph(Graph* pG);
/*** Access functions ***/
int getOrder(Graph G);
int getSize(Graph G);
int getDiscover(Graph G, int u);
int getParent(Graph G, int u);
int getFinish(Graph G, int u);
/*** Manipulation procedures ***/
void addEdge(Graph G, int u, int v);
void addArc(Graph G, int u, int v);
void DFS(Graph G, List S);
/*** Other operations ***/
void Visit(Graph G, int x, List S);
Graph transpose(Graph G);
Graph copyGraph(Graph G);
void printGraph(FILE* out, Graph G);
#endif
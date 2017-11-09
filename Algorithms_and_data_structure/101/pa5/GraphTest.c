// Ruikang Tao
// rtao6
// PA5

#include <stdio.h>
#include <stdlib.h>
#include"Graph.h"

int main (int argc, char **argv) {
   int i; 
   int order=10;
   
   Graph G = newGraph(n);
   Graph T = NULL;
   Graph C = NULL;
   // add edges
   addArc(G, 1, 3);
   addArc(G, 2, 9);
   addArc(G, 2, 6);
   addArc(G, 2, 8);
   addArc(G, 3, 4);
   addArc(G, 3, 5);
   addArc(G, 4, 7);
   addArc(G, 4, 8);
   addArc(G, 5, 8);
   addArc(G, 6, 7);
   addArc(G, 7, 9);
   addArc(G, 8, 4);
   addArc(G, 8, 7);
   addArc(G, 9, 10);
   addArc(G, 10, 4);
   printGraph(stdout, G);
   fprintf(stdout, "\n");
   List L = newList();
   for(i=1; i<=order; i++){
      append(L, i);
   } 

   DFS(G, L);
   // test DFS output
   fprintf(stdout, "x:  d  f  p\n");
   for(i=1; i<=order; i++){
      fprintf(stdout, "%d: %2d %2d %2d\n", i, getDiscover(G,i), getFinish(G,i), getParent(G,i));
   }
   fprintf(stdout, "\n");
   printList(stdout, L);
   fprintf(stdout, "\n");

   // test transpose and copy output
   T = transpose(G);
   C = copyGraph(G);
   printGraph(stdout, C);
   fprintf(stdout, "\n");
   DFS(T, L);
   printGraph(stdout, T);
   fprintf(stdout, "\n");

   // free
   freeList(&S);
   freeGraph(&G);
   return(0);
}
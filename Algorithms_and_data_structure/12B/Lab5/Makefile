BASE_SOURCES   = Dictionary.c IntegerLinkedList.c
BASE_OBJECTS   = Dictionary.o IntegerLinkedList.o
HEADERS        = Dictionary.h IntegerLinkedList.h
COMPILE        = gcc -g -c -std=c99 -Wall
LINK           = gcc -o
REMOVE         = rm -f
MEMCHECK       = valgrind --leak-check=full


DictionaryClient : DictionaryClient.o $(BASE_OBJECTS)
	$(LINK) DictionaryClient DictionaryClient.o $(BASE_OBJECTS)

DictionaryClient.o : DictionaryClient.c $(HEADERS)
	$(COMPILE) DictionaryClient.c

$(BASE_OBJECTS) : $(BASE_SOURCES) $(HEADERS)
	$(COMPILE) $(BASE_SOURCES)

clean :
	$(REMOVE) DictionaryClient Dictionary.o DictionaryClient.o IntegerLinkedList.o $(BASE_OBJECTS)

check : DictionaryClient
	$(MEMCHECK) DictionaryClient input output

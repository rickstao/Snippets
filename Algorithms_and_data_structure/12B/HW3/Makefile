JAVASRC =Chessmoves.java 
SOURCES =README Makefile $(JAVASRC)
MAINCLASS = Chessmoves 
CLASSES = Chessmoves.class Bishop.class ChessPiece.class King.class Queen.class Knight.class Node.class Rook.class Utilities.class Pawn.class
JARFILE = Chessmoves.jar 

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS)> Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JARFILE)
	javac -Xlint $(JAVASRC)
clean:
	rm $(CLASSES) $(JARFILE)


JAVASRC    = Chessboard.java bishop.java Chesspiece.java queen.java rook.java pawn.java Linkedlist.java king.java knight.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = Chessboard
CLASSES    = Chessboard.class bishop.class Chesspiece.class queen.class rook.class pawn.class Linkedlist.class king.class knight.class 
JARFILE    = Chessboard.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)
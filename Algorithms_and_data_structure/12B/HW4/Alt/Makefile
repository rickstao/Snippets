JAVASRC =Priority.java
SOURCES =README Makefile $(JAVASRC)
MAINCLASS = Priority 
CLASSES = Priority.class
JARFILE = Priority.jar 

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS)> Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JARFILE)
	javac -Xlint $(JAVASRC)
clean:
	rm $(CLASSES) $(JARFILE)


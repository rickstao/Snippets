JAVASRC = Bard.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = Bard
CLASSES = Bard.class Bard$$1.class
JARFILE = Bard.jar 

all: $(JARFILE)

$(JARFILE): $(subst $$,\$$,$(CLASSES))
	echo Main-class: $(MAINCLASS)> Manifest
	jar cvfm $(JARFILE) Manifest $(subst $$,\$$,$(CLASSES))
	rm Manifest

$(subst $$,\$$,$(CLASSES)): $(JARFILE)
	javac -Xlint $(JAVASRC)
clean:
	rm $(subst $$,\$$,$(CLASSES)) $(JARFILE)


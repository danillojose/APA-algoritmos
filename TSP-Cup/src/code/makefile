JFLAGS = -g
JC = javac
FLG = *.java

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java $(FLG)

CLASSES = \
	TSP.java

default: classes			#make

classes: $(CLASSES:.java=.class)

run:
	java -cp ../ code.TSP	#java -cp <classpath> <main class>

clean:
	$(RM) *.class			#make clean

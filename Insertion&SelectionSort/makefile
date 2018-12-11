JFLAGS = -g
JC = javac
FLG = *.java

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java $(FLG)

CLASSES = \
	Principal.java \
	InsertionSort.java \
	SelectionSort.java \
	Common.java

default: classes			#make

classes: $(CLASSES:.java=.class)

run:
	java -cp ../ src.Principal	#java -cp <classpath> <main class>

clean:
	$(RM) *.class			#make clean

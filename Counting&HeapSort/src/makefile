JFLAGS = -g
JC = javac
FLG = *.java

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java $(FLG)

CLASSES = \
	CountingSort.java \
	HeapSort.java

default: classes			#make

classes: $(CLASSES:.java=.class)

run:
	java -cp . Principal	#java -cp <classpath> <main class>

clean:
	$(RM) *.class			#make clean

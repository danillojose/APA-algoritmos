JFLAGS = -g
JC = javac
FLG = *.java

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java $(FLG)

CLASSES = \
	Mochila_inteira.java

default: classes			#make

classes: $(CLASSES:.java=.class)

run:
	java -cp ../ code.Mochila_inteira	#java -cp <classpath> <main class>

clean:
	$(RM) *.class			#make clean

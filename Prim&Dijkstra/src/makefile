JFLAGS = -g
JC = javac
FLG = *.java

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java $(FLG)

CLASSES = \
	PRIM2.java

default: classes			#make

classes: $(CLASSES:.java=.class)

run:
	java -cp . PRIM2	#java -cp <classpath> <main class>

clean:
	$(RM) *.class			#make clean

# define a makefile variable for the java compiler
#
JCC = javac

# define a makefile variable for compilation flags
# the -g flag compiles with debugging information
#
JFLAGS = -g

# typing 'make' will invoke the first target entry in the makefile 
# (the default one in this case)
#
default: p2.class p1.class

# this target entry builds the p1 class
# the Average.class file is dependent on the p1.java file
# and the rule associated with this entry gives the command to create it
#
p2.class: p2.java
	$(JCC) $(JFLAGS) p2.java
p1.class: p1.java
	$(JCC) $(JFLAGS) p1.java

# To start over from scratch, type 'make clean'.  
# Removes all .class files, so that the next make rebuilds them
#
clean:
	$(RM) *.class

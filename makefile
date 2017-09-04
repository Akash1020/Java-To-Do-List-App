#~ Makefile

make: 
	javac Email.java
	
run:
	java Email toDo.txt

clean: 
	rm *.class
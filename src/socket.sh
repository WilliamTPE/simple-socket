#!/bin/bash
echo "Please initiate server first then go to another machine launch client."
echo "Mode: "
echo " 1 -> Server"
echo " 2 -> Client"
read VAR

if [[ $VAR == 1 ]]; then
	echo -e "\nCleaning previous .class files..."
	make clean
	echo -e "\nCompiling java files..."
	make
	echo -e "\nInitiating Server..."
	java p2
elif [[ $VAR == 2 ]]; then
	echo -e "\nLaunching Client..."
	java p1
else
	echo "Invalid input"
fi

echo -e "\nChecking difference..."
diff f1.txt f2.txt
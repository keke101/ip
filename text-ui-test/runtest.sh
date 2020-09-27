#!/usr/bin/env bash

#Clear previously compiled binaries
if [ -e "../bin" ]
then
  rm -rf ../bin/*
fi

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

#Copy test_duke.txt for testing
cp ./data/test_duke.txt ./data/duke.txt

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src -Xlint:none -d ../bin ../src/main/java/duke/*.java ../src/main/java/duke/task/*.java ../src/main/java/duke/exception/*.java ../src/main/java/duke/command/*.java ../src/main/java/duke/parser/*.java ../src/main/java/duke/storage/*.java ../src/main/java/duke/ui/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT

java -classpath ../bin duke.Duke < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

#Restore original duke.txt
rm ./data/duke.txt

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT

if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
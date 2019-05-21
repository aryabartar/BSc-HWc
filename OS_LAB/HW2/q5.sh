#!/bin/bash

# read -p "Input file address: " FILE_ADDRESS
FILE_ADDRESS=./ali.java

if ! [ -f $FILE_ADDRESS ]; then
    echo "This is not file address!"
    exit 2
fi

if ! [[ $FILE_ADDRESS == *.java || $FILE_ADDRESS == *.js ]]
then
    echo "This file type is not supported!"
fi

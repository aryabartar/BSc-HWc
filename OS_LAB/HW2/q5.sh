#!/bin/bash

# read -p "Input file address: " FILE_ADDRESS
FILE_ADDRESS=./ali.js

if ! [ -f $FILE_ADDRESS ]; then
    echo "This is not file address!"
    exit 2
fi

if ! [[ $FILE_ADDRESS == *.java || $FILE_ADDRESS == *.js ]]
then
    echo "This file type is not supported!"
fi

declare -i line_number=0
while read -r line; do
    let ++line_number
    if [[ $FILE_ADDRESS == *.java && ($line == "system.out.println" || $line == "system.out.print") ]]; then 
        sed -i "${line_number}d" $FILE_ADDRESS
        let --line_number
    elif [[ $FILE_ADDRESS == *.js && ($line == "console.log") ]]; then
        sed -i "${line_number}d" $FILE_ADDRESS
        let --line_number
    fi
done < $FILE_ADDRESS

exit 0

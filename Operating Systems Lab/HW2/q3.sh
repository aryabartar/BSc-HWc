#!/bin/bash

INPUT1=1
INPUT2=$INPUT1+1
INPUT3=$INPUT2+1
read -p "Input: " INPUT1 INPUT2 INPUT3

MAX=$INPUT1
if [ $INPUT2 -gt $INPUT1 ]; then
    MAX=$INPUT2
fi 
if [ $INPUT3 -gt $INPUT2 ]; then
    MAX=$INPUT3
fi

echo "Maximum input is: $MAX"
#!/bin/bash

read -p "Input: " INPUT1 INPUT2
if [ $INPUT1 -gt 10 ]; then
    echo "Value is greater than 10!"
elif [ $INPUT1 -gt $INPUT2 ]; then
    echo "First number is greater than second one."
else
    echo "Second number is greater!"
fi
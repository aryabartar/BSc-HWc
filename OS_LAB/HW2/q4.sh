#!/bin/bash
read -p "Input a number: " INPUT_NUM
sum=0

array=($(echo "$INPUT_NUM" | grep -o . ))
for num in "${array[@]}"; do
    sum=$(($sum+$num))
done

echo "Sum is $sum"
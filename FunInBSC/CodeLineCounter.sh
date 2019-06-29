#!/bin/bash 
# Used for Unfair DataBase project! More than 1300 SQL code lines for just 2 points! :((
cd $1
TOTAL_SUM=0
for FILE in `ls`; do
    if [ -f $FILE ]; then
        COUNT=`wc -l $FILE`
        LCOUNT="$(cut -d' ' -f1 <<<"$COUNT")"
        ((TOTALSUM=TOTALSUM+$LCOUNT))
    fi
done;
echo $TOTALSUM
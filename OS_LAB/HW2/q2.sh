X=0
while [ $X -lt 10 ]
do 
    echo "0 \c"
    Y=$(($X-1))
    X=$(($X+1))
    while [ $Y -ge 0 ]; do
        echo "$(($X - $Y - 1)) \c"        
        Y=$(($Y-1))
    done
    echo
done
#!/usr/bin/zsh -i
IFS='
'
HISTORY_COUNTER=5
for i in ‍‍‍`history | cut -c 8- | tac`; do
    A="$(cut -d' ' -f1 <<<"$i")"
    A_LOCATION=`which $A`
    A_SUPERDIR="$(cut -d'/' -f2 <<<"$A_LOCATION")"
    
    if [ "$A_SUPERDIR" = 'bin' ]; then
        ((HISTORY_COUNTER--))
        echo $i
        if [ $HISTORY_COUNTER -eq 0 ]; then
            break
        fi
    fi
done
#include <iostream>
#include <cstring>

using namespace std  ;

int main() {

    const int aMaxSize = 70 ;
    char a[aMaxSize] ;
    int whiteCount = 1 ;

    cin.get (a ,80) ;

    if ( strtok (a , " ") == NULL)
        cout << "No word !" ;

    else {

        while (strtok (NULL , " ") != NULL){
            whiteCount++ ;
        }

    cout << whiteCount << " words!" ;

    }

    return 0 ;
}

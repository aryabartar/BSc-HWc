#include <iostream>
#include <cstring>
using namespace std  ;

int main() {

    const int cSize = 30 ;
    char c [cSize] ;
    int len ;
    char temp ;

    cin >> c ;

    len = strlen(c) ;

    temp = c[0] ;
    c[0] = c[len-1] ;
    c[len-1] = temp ;


    if (len%2 == 0){

        for (int i1 = len - 1 ; i1 >= len/2 ; i1-- )
            c[i1+2] = c[i1] ;

        c[len/2] ='E' ;
        c[len/2+1] = 'V' ;

        for (int i2 = 0 ; i2 < len+2 ; i2++)
            cout << c [i2] ;
    }

    else {
        cout << c ;
    }

    return 0 ;
}

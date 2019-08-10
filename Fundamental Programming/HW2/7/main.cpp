#include <iostream>

using namespace std ;


int main () {

    int a = 1 , c , p = 3 , w = 0  , temp = 0  , max ;
    double b ;
    int t ;

    while ( p < 1000) {
//improvement
        while (a <= p/3) {

        b = (float) (p*p - 2*p*a) / (2*p - 2*a) ;
        t = b ;
        if (t == b && b > 0 && b > a)
            w++ ;
        a++ ;
        }
        if (w>temp){
            max = p ;
            temp = w ;

        }
        p++ ;
        a = 1 ;
        w = 0 ;

    }
    cout << max ;


    return 0 ;
}




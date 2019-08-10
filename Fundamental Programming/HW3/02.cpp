#include <iostream>

using namespace std  ;

int main() {

    const int N = 50 ;
    float sum = 1 , under = 1 ;

    for ( int i = 2 ; i < N ; i++ ) {
        sum += under ;
        under /= i ;
    }
    cout << " e = " << sum ;

    return 0 ;
}



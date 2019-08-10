#include <iostream>
#include <cmath>

using namespace std ;

int main () {

    double  s1 ,
            s2 ,
            temp ;

    cout << "First side :  " ;
    cin >> s1 ;

    cout << "Second side :  " ;
    cin >> s2 ;

    cout << "Area:  " << s1 * s2 << endl ;

    cout << "Perimiter:  " << (s1 + s2) * 2 << endl ;

    temp = s1 * s1 + s2 * s2 ;
    cout << "Diognal:   " << pow (temp , .5 );

    return 0 ; 
}

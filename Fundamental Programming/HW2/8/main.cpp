#include <iostream>

using namespace std ;

/* a - adade mesale 1 : 8 , 13 , -4

adade mesale 2 : -5 , 17 , 47 */


int main () {

    int n1 , n2 , n3 , a1 , a2 , a3  ;

    cout << "Numbers in squares :"<< endl ;
    cin >> n1 >> n2 >> n3 ;

    a3 = (n2 + n3 - n1) / 2  ;
    a2 = a3 + n1 - n3 ;
    a1 = n3 - a3 ;

    cout << "Numbers in circles :" << a1 << " " << a2 << "  " << a3 ;


}

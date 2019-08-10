#include <iostream>

using namespace std ;


int main () {

    string str = "True" ;
    int n , i = 2 ;

    cout << "Number : " ;
    cin >> n ;

    while (i<= n/2) {

        if (n%i == 0 ){
            str = "False" ;
            break ;
        }

        i++ ;
    }

    if (n == 1)
        str = "False" ;

    cout << "Is prime : " << str ;

    return 0 ; 
}

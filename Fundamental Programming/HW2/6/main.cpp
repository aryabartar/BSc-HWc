#include <iostream>

using namespace std ;


int main () {

    int n , i = 1 , sum = 0 , l = 0 ;

    cout << "Perfect number : " ;
    cin >> n ;

     while (i <= n/2) {

        if (n%i == 0)
            sum += i ;
        i++ ;

     }

     i = 1 ;

     if (sum == n) {

        cout << n << " = " ;

        while (i <= n/2){

            if (n%i == 0) {

                if (l == 0) {

                    cout << i ;
                    l = 2 ;
                }

            else
                cout << " + " << i ;

            }

            i++ ;
        }

     }
        else
        cout << "error" ;

        return 0 ;
}

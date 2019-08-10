#include <iostream>
using namespace std  ;

bool isPalindorme (int) ;

int main() {

    int num ;
    bool check ;

    cin >> num ;
    check = isPalindorme(num) ;

    if (check == true)
        cout << "The number is palindorme " ;
    else
        cout << "the number isn't palindorme " ;

    return 0 ;

}

bool isPalindorme (int n) {

    bool flag = false ;

    if (n%10 == n/10000 && (n%100)/10 == (n/1000)%10 )
        flag = true ;

    return flag ;

}

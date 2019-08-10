#include<iostream>
using namespace std;

int saveCounter = 0 ;
int least_factor (int) ;
int all_factors (int , int[]) ;

int main(){

    const int SAVESIZE = 100 ;
    int n , save2Counter = 0 , temp , save1 [SAVESIZE] = {0} , save2 [SAVESIZE] = {0} ;
    bool found = false , isSorted = false ;

    cin >> n ;
    all_factors(n , save1) ;

    for (int i1 = 0 ; i1 < 100 && save1[i1] != 0 ; i1++ )
        cout << save1[i1] << endl ;
    cout << "\n\n\n" ;

    for (int i1 = 0 ; i1 < SAVESIZE ; i1++ ) {
        found = false ;
        for (int i2 = 0 ; i2 < SAVESIZE ; i2++ )
            if (save2 [i2] == save1 [i1])
                found = true ;

        if (found == false)
            save2 [save2Counter++] = save1 [i1] ;
    }

    {
    int i2 ;

    for ( int i1 = 0 ; i1 < save2Counter - 1  && isSorted == false ; i1++ ) {
        isSorted = true ;
        for (i2 = 0 ; i2 < save2Counter - 1 ; i2++ ) {
            if (save2[i2] > save2[i2+1]) {
                temp = save2[i2] ;
                save2[i2] = save2[i2+1] ;
                save2[i2+1] = temp ;
                isSorted = false ;
            }
        }
    }
    }
    cout << "factors : " ;
    for (int i1 = 0 ; i1 < save2Counter ; i1++ )
        cout << "<" << save2[i1] << "> " ;


}

int all_factors (int n , int save[]) {

    if (n != least_factor(n)) {
        all_factors(n / least_factor(n) , save) ;
    }


    save[saveCounter++] = least_factor(n) ;

}

int least_factor (int n) {

    int save ;
    for (int i1 = n ; i1 > 1 ; i1-- )
        if (!(n%i1))
            save = i1 ;
    return save ;

}



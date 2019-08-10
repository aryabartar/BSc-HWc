#include <iostream>
#include <cstring>
using namespace std  ;

void aie (char [] , int * , int * , int * , int * , int *) ;
void Sort (char []) ;
int main() {

    const int strSize = 30 ;
    char str [strSize] ;
    int  A=0 , I=0 , E=0 , O=0 , U=0 ;

    cin >> str ;

    aie (str , &A , &I , &E , &O , &U ) ;

    cout << "A" << "  " << A << endl ;
    cout << "I" << "  " << I << endl ;
    cout << "E" << "  " << E << endl ;
    cout << "O" << "  " << O << endl ;
    cout << "U" << "  " << U << "\n\n" ;

    cout << str << " changes to : " ;

    Sort (str) ;

    cout << str << endl ;

    return 0 ;
}

void aie ( char c[] , int *Aptr , int *Iptr , int *Eptr , int *Optr , int *Uptr ){

    int len = strlen(c) ;

    for (int i1 = 0 ; i1 < len ; i1++ ) {
        switch (c[i1]) {

        case 'A' :
        case 'a' :
            (*Aptr)++ ;
            break ;

        case 'I' :
        case 'i' :
            (*Iptr)++ ;
            break ;

        case 'E' :
        case 'e' :
            (*Eptr)++ ;
            break ;

        case 'O' :
        case 'o' :
            (*Optr)++ ;
            break ;

        case 'U' :
        case 'u' :
            (*Uptr)++ ;
            break ;

        }
    }
}

void Sort (char c[]){

    int len = strlen(c) ;
    bool sorted = false ;
    char temp ;

    for (int i1 = 0 ; i1 < len - 1 && !sorted ; i1++) {
        sorted = true ;

        for (int i2 = 0 ; i2 < len - i1 - 1 ; i2++ )

            if (c[i2] > c[i2+1]) {
                temp = c[i2] ;
                c[i2] = c[i2+1] ;
                c[i2+1] = temp ;
                sorted = false ;
            }
    }

}









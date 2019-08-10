#include <iostream>

using namespace std  ;

void pi1 (int) ;
void pi2 (int) ;

int main() {

    int n ;
    cin >> n ;
    pi2(n) ;
    cout << "\nAnother form !\n\n" ;
    pi1(n) ;
    return 0 ;
}

void pi1 (int n) {

    double sum = 1 , temp , neg = -1 ;

    for (int i1 = 1 ; i1 < n+1 ; i1++ ) {

        cout << i1 << "    " << sum * 4 << endl ;
        temp = neg * ((double)1 / (2*i1 + 1)) ;
        neg *= -1 ;
        sum += temp ;

    }
}
void pi2 (int n) {

    int upsum = 1 , downsum = 1 , up1 = 1 , down1 = 1 , up2 = -1 , down2 = 3 , neg = -1 ;

    cout << 1 << "    "  << 4 << endl ;
    for (int i1 = 2 ; i1 < n+1 ; i1++) {

        upsum = down2 * up1 + up2 * down1 ;
        downsum = down1 * down2 ;

        cout << i1 << "    " << upsum * 4 << "\\" << downsum << endl ;

        up1 = upsum ;
        up2 *= -1 ;
        down1 = downsum ;
        down2 = 2*i1 + 1 ;
    }

}




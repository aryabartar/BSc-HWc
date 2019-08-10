#include <iostream>
#include <cmath>

using namespace std ;

int main () {

    int Num , Mon , Day ;

    cout << "Number : " ;
    cin >> Num ;
    cout << endl ;

    // 6*31 = 186
    if (Num <= 186) {

        Mon = (Num - 1) / 31 + 1 ;
        Day = Num  -  (((Num-1) / 31)* 31) ;
    }

    else {
        Mon = ((Num - 187) / 30 )  +  7 ;
        Day = (Num - 186) - (((Num-187) / 30) * 30 )  ;

    }

    cout << "Day:  " << Day << endl ;
    cout << "Month: " << Mon << endl ;

    if (Mon <= 3 )
        cout << "Season: Spring" ;
    else if (Mon<7 && Mon > 3)
        cout << "Season: Summer" ;
    else if (Mon<10 && Mon >6)
        cout << "Season: Fall" ;
    else if (Mon>9)
        cout << "Season: Winter" ;

    return 0 ;
}

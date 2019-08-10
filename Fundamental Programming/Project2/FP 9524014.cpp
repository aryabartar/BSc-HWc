#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std  ;

void printPad (int[][10] , const int) ;
void setGame (int [][10] , const int , int &) ;
void secondPad (char [][10]) ;
void shoot (char [][10] , int [][10] , int , int , int &) ;
void machineSet (int [][10] , const int , int &) ;
void machineTotShoot (char [][10] , int [][10] , int &) ;
int player1Size = 0 , player2Size = 0 ;
int shoot1Size = 0 , shoot2Size = 0 ;


int main() {

    const int ARRAYSIZE = 10 ;
    int a1[ARRAYSIZE][ARRAYSIZE] = {0} ;
    int a2[ARRAYSIZE][ARRAYSIZE] = {0} ;
    char b1[ARRAYSIZE][ARRAYSIZE] = {} ;
    char b2[ARRAYSIZE][ARRAYSIZE] = {} ;
    int i , j ;
    int oneOrTwo;
    bool ended = false ;

    cout << "Input 1 for one player and 2 for two player game :  " ;
    cin >> oneOrTwo ;

    while (oneOrTwo != 1 && oneOrTwo != 2){

        cout << "\nJust input 1 or 2 ! : " ;
        cin >> oneOrTwo ;

    }

    cout << "\n\n" ;
    cout << "\\tozihate baazi : \ndar marhaleye avval entekhab konid baazi chand nafare bashad.\n ";
    cout << "gabl az shorooe bazi bayad tank hayetan ra set konid , adade 0 neshan dahandeye jaye khaali\n" ;
    cout << "va adade 1 neshan dahandeye tank haye shoma ast , toole tank ha az 2 ta 5 moteghyyer ast va shoma 5" ;
    cout << "tank darid pas az shorooe bazi safheye khali baraye shoma namayesh dade mishavad ke agar shellik \n" ;
    cout << "konid va be hadaf esabat konad X va dar gheyre in soorat O namayesh dade mishavad \n" ;
    cout << "kasi ke zood tar tank haye harif ra monhadem konad barande ast !\n\n" ;

    if (oneOrTwo == 1){

        cout << "Set your tanks . \n" ;
        printPad(a1 , ARRAYSIZE) ;
        setGame(a1 , ARRAYSIZE , player1Size) ;
        cout << "\n\n\n" ;
        machineSet (a2 , ARRAYSIZE , player2Size) ;
        cout << "\nComputer set it's tanks ! \n" ;

        while (ended == false) {

            cout << "\nYour turn .\n" ;
            secondPad (b2) ;
            cout << "\nInput i : " ;
            cin >> i ;
            cout << "\nInput j : " ;
            cin >> j ;

            while (i > 9 || i < 0 || j > 9 || j < 0 || b2[i][j] == 'X' || b2[i][j] == 'O'){

                cout << "\nInput digits between 0 and 9 or inter another location .\n" ;
                cout << "\nInput i : " ;
                cin >> i ;
                cout << "\nInput j : " ;
                cin >> j ;

            }

            shoot (b2 , a2 , i , j , shoot2Size) ;
            secondPad(b2) ;

            //computer turn

            cout << "\nCoumputer turn .\n" ;
            machineTotShoot(b1 , a1 , shoot1Size) ;
            secondPad (b1) ;

            if (shoot1Size == player1Size || shoot2Size == player2Size)
                ended = true ;

        }

        if (shoot1Size == player1Size) cout << "\n\n\n\n******Player 2 WINS!!*******" ;
        else cout << "\n\n\n\n******Player 1 WINS!!******" ;

    }

    else {

        cout << "Player 1 set time ! \n" ;
        printPad(a1 , ARRAYSIZE) ;
        setGame(a1 , ARRAYSIZE , player1Size) ;
        cout << "\n\n\n\n\n\n\n\nPlayer 2 set time ! \n" ;
        printPad(a2 , ARRAYSIZE) ;
        setGame(a2 , ARRAYSIZE , player2Size) ;

        for (int i1 = 0 ; i1 < 10 ; i1++) cout << "\n" ;

        while (ended == false) {

            cout << "\nPlayer 1 turn . \n\nPlayer 2 pad :\n" ;
            secondPad(b2) ;
            cout << "\nInput i and j for shoot ." ;
            cout << "\nIpmort i : ";
            cin >> i ;
            cout << "\nInput j : ";
            cin >> j ;

            while (i > 9 || i < 0 || j > 9 || j < 0 || b2[i][j] == 'X' || b2[i][j] == 'O'){

                cout << "\nInput digits between 0 and 9 or inter another location .\n" ;
                cout << "\nInput i : " ;
                cin >> i ;
                cout << "\nInput j : " ;
                cin >> j ;

            }

            shoot (b2 , a2 , i , j , shoot2Size) ;
            secondPad (b2) ;

            //player 2 turn

            cout << "\nPlayer 2 turn . \n\nPlayer 1 pad :\n" ;
            secondPad(b1) ;
            cout << "\nInput i and j for shoot ." ;
            cout << "\nIpmort i : ";
            cin >> i ;
            cout << "\nInput j : ";
            cin >> j ;

            while (i > 9 || i < 0 || j > 9 || j < 0 || b1[i][j] == 'X' || b1[i][j] == 'O'){

                cout << "\nInput digits between 0 and 9 or inter another location .\n" ;
                cout << "\nInput i : " ;
                cin >> i ;
                cout << "\nInput j : " ;
                cin >> j ;
            }
            shoot (b1 , a1 , i , j , shoot1Size) ;
            secondPad (b1) ;

            if (shoot1Size == player1Size || shoot2Size == player2Size)
                ended = true ;

        }

        if (shoot1Size == player1Size) cout << "\n\n\n\n******Player 2 WINS!!*******" ;

        else if (shoot1Size == player1Size && shoot2Size == player2Size)
            cout << "\n\n\n\n******Player 1 and 2 WINS!!*******" ;

        else cout << "\n\n\n\n******Player 1 WINS!!******" ;



    }

    return 0 ;
}

void shoot (char c[][10] , int a[][10] , int i , int j , int &shoot) {

    if (a[i][j] == 1){ c[i][j] = 'X' ; shoot++ ; }
    else c[i][j] = 'O' ;

}

void machineSet(int a[][10] , const int ARRAYSIZE , int &Size) {

    int rand1 , rand2 , rand3 ;

    srand(time(0)) ;
    rand1 = rand()%7 ;
    rand2 = rand()%10 ;

    for (int i1 = rand1 ; i1 < rand1 + 3 ; i1++)
        a[rand2][i1] = 1 ;

    for (int i1 = rand1 ; i1 < rand1 + 2 ; i1++)
        a[i1][rand2] = 1 ;

    rand1 = rand()%4 ;
    rand2 = rand()%10 ;
    rand3 = rand()%4 ;

    for (int i1 =rand1  ; i1 < rand1+rand3+2 ; i1++)
        a[rand2][i1] = 1 ;

    for (int i1 =rand1  ; i1 < rand1+rand3+2 ; i1++)
        a[i1][rand2] = 1 ;

    rand1 = rand()%4+6 ;

    for (int i1 = rand1 ; i1 > rand1-rand3-2 ; i1--)
        a[rand2][i1] = 1 ;

    for ( int i1 = 0 ; i1 < ARRAYSIZE ; i1++ )

        for ( int i2 = 0 ; i2 < ARRAYSIZE ; i2++)

            if (a[i1][i2] == 1)
                Size++ ;

}

void secondPad (char a[][10]) {

    cout << "  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |" ;

    for (int i1 = 0 ; i1 < 10 ; i1++) {

        cout << "\n--|---+---+---+---+---+---+---+---+---+---+" ;
        cout << "\n" << i1 << " | " ;

        for (int i2 = 0 ; i2 < 10 ; i2++ ) {

            if (a[i1][i2] == 'X' || a[i1][i2] == 'O')

                cout << a[i1][i2] << " | " ;

            else cout << " " << " | " ;
        }
    }

    cout << "\n--|---+---+---+---+---+---+---+---+---+---+\n" ;

}

void setGame (int a1[][10] , const int ARRAYSIZE , int &playerSize) {

    int i1 , i2 , j1 , j2 ;
    int temp ;
    char c ;
    bool flag ;

    cout << "\nPlace your tanks by importing their first and last (size should be 2 to 5).\n " ;

    for (int k1 = 0 ; k1 < 5 ; k1++) {

        flag = false ;
        while (flag == false) {
            flag = true ;

            cout << "\nInput 'h' for horizontal or 'v' for vertical : " ;
            cin >> c ;

            while (c != 'h' && c != 'v') {

                cout << "\nJust input v or h ! :  " ;
                cin >> c ;
            }

            if (c == 'h') {

                cout << "\nYou have chosen horizontal .\n" ;
                cout << "\nInput j : " ;
                cin >> j1 ;
                cout << " to : " ;
                cin >> j2 ;
                cout << "\nInput i : " ;
                cin >> i1 ;

                if (j2 < j1) {
                    temp = j1 ;
                    j1 = j2 ;
                    j2 = temp ;
                }

                while (j2 - j1 > 4 || j2 - j1 < 1 || j1 > 9 || j1 < 0 || j2 > 9
                       || j2 < 0 || i1 > 9 || i1 < 0 ){

                    cout << "\nj2 - j1 should be between 2 and 5 and j1 , j2 , i1 between 0 and 9 .\n" ;
                    cout << "\nInput again ! \n" ;
                    cout << "\nInput j : " ;
                    cin >> j1 ;
                    cout << " to : " ;
                    cin >> j2 ;
                    cout << "\nInput i : " ;
                    cin >> i1 ;

                    if (j2 < j1) {

                        temp = j1 ;
                        j1 = j2 ;
                        j2 = temp ;

                    }
                }

                for (int k = j1 ; k < j2 + 1 ; k++){

                    if (a1[i1][k] ==1){

                        cout << "### You hit your tanks !###\n\n" ;
                        flag = false ;
                    }
                }

                for (int k = j1 ; k < j2 + 1 && flag == true ; k++)

                    a1[i1][k] = 1 ;

                printPad(a1 , ARRAYSIZE) ;

            }

            else {

                cout << "\nYou have chosen vertical .\n" ;
                cout << "\nInput i : " ;
                cin >> i1 ;
                cout << " to : " ;
                cin >> i2 ;
                cout << "\nInput j : " ;
                cin >> j1 ;

                if (i2 < i1) {

                    temp = i1 ;
                    i1 = i2 ;
                    i2 = temp ;

                }

                while (i2 - i1 < 1 || i2 - i1 > 4 || i2 < 0 || i2 > 9 || i1 < 0 || i1 > 9
                       || j1 > 9 || j1 < 0){

                    cout << "\ni2 - i1 should be between 2 and 5 and j1 , j2 , i1 between 0 and 9 .\n" ;
                    cout << "\nInput again ! \n" ;
                    cout << "\nInput i : " ;
                    cin >> i1 ;
                    cout << " to : " ;
                    cin >> i2 ;
                    cout << "\nInput j : " ;
                    cin >> j1 ;

                    if (i2 < i1) {

                        temp = i1 ;
                        i1 = i2 ;
                        i2 = temp ;

                    }

                }

                for (int k = i1 ; k < i2 + 1 ; k++)
                    if (a1[k][j1] == 1){
                        cout << "### You hit your tanks !###\n\n" ;
                        flag = false ;
                    }

                for (int k = i1 ; k < i2 + 1 && flag == true ; k++)
                    a1[k][j1] = 1 ;

                printPad(a1 , ARRAYSIZE) ;
            }

        }
    }

    cout << "\n\nSETTING HAS FINISHED!\n\n" ;

    printPad (a1 , ARRAYSIZE) ;

    for (int i1 = 0 ; i1 < ARRAYSIZE ; i1++ )
        for (int i2 = 0 ; i2 < ARRAYSIZE ; i2++ )
            if (a1[i1][i2] == 1) playerSize++ ;

}

void printPad (int array1[][10] , const int ARRAYSIZE) {

    cout << "  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |" ;

    for (int i1 = 0 ; i1 < ARRAYSIZE ; i1++) {

        cout << "\n--|---+---+---+---+---+---+---+---+---+---+" ;
        cout << "\n" << i1 << " | " ;

        for (int i2 = 0 ; i2 < ARRAYSIZE ; i2++ ) {

            cout << array1[i1][i2] << " | " ;
        }
    }

    cout << "\n--|---+---+---+---+---+---+---+---+---+---+\n" ;

}

void machineTotShoot(char b[][10] , int a[][10] , int &shoot){

    srand(time(0)) ;

    static int i = 2 , j = 2 , cc = 4 , miniFlag = 0  ;

    if ((a[i][j] == 1 || cc > -1) && miniFlag == 0) {

        if (a[i][j] == 1) {
            b[i][j] = 'X' ;
            shoot++ ;
            cc = 4 ; }

        else b[i][j] = 'O' ;


        if (cc == 4 && i > 0){
            i-- ;
            if (a[i][j] == 1) cc = 5 ; }

        if (cc == 4 && i < 1)
            cc-- ;

        if (cc == 3 && i < 9){
            i+= 2 ;
            if (a[i][j] == 1) cc = 5 ; }

        if (cc == 3 && i > 9)
            cc--  ;

        if (cc == 2 && j > 0){
            j-- ;
            i-- ;
            if (a[i][j] == 1) cc = 5 ;
        }

        if (cc == 2 && j < 1)
            cc-- ;

        if (cc == 1 && j < 9){
            j+= 2 ;
            if (a[i][j] == 1) cc = 5 ;
        }

        if (cc == 1 && j > 9)
            cc-- ;

        cc-- ;

        if (b[i][j] == 'O' || b[i][j] == 'X') {
            i = rand()%10 ;
            j = rand()%10 ;

        }

        if (i < 0 || j < 0 || i > 9 || j > 9 ){
            i = rand()%10 ;
            j = rand()%10 ;
        }

        if(a[i][j] == 1&&a[i][j-1] == 1 && b[i][j] == 'X'){
            i = rand()%10 ;
            j = rand()%10 ;

        }

    }


    else if (a[i][j] == 0 ){

        b[i][j] = 'O' ;
        i = rand()%10 ;
        j = rand()%10 ;

        while (b[i][j] == 'X' || b[i][j] == 'O'){
            i = rand()%10 ;
            j = rand()%10 ;

        }

    }



}

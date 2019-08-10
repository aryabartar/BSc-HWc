#include <iostream>
#include <cstdlib>
#include <ctime>
//WriTTeN BY AryA KhaligH
using namespace std ;

int cells [3][3];
int empty_x , empty_y ;
string command ;

int get_cell (int x , int y) {

    return cells[x][y];

}

void set_cell ( int x , int y , int value ) {

    cells [x][y] = value ;

}

void fill_cells() {

    int temp = 0 , chance , a , b  ;

    srand ( time(0) ) ;

    for ( int i = 0 ; i < 3 ; i++ )

        for ( int j = 0 ; j < 3 ; j++ )

            set_cell ( i , j , temp++ ) ;

    for ( int k = 0 ; k < 12 ; k++ ) {

        chance = rand () ;

        for ( int i1 = 0 ; i1 < 3 ; i1++ )

            for ( int j1 = 0 ; j1 < 3 ; j1++ )

                if ( cells [i1][j1] == rand ()%9 ){

                    temp = cells [i1][j1] ;
                    a = chance % 3 ;
                    b = chance % 3 ;
                    cells [i1][j1] = cells [a][b] ;
                    cells [a][b] = temp ;

                }
    }

}

int get_inversions() {

    int inversions = 0 , sum = 0 , a , b ;

    for ( int i = 0 ; i < 3 ; i++ )

        for ( int j = 0 ; j < 3 ; j++ )

            if ( cells [i][j] == 0 ){

                cells [i][j] = 100 ;
                a = i ;
                b = j ;

            }

    for ( int i = 0 ; i < 3 ; i++ ) {

        for ( int j = 0 ; j < 3 ; j++ ) {

            if ( cells [i][j] < 10 ) {

                for ( int i1 = i ; i <= i1 && i1 < 3 ; i1++ ) {

                    for ( int j1 = j ; j <= j1 && j1 < 3 ; j1++ ) {

                        if ( cells [i1][j1] < cells [i][j] ){

                            sum++ ;

                        }
                    }
                }

                for ( int i2 = i + 1 ;  i2 < 3 ; i2++ ) {

                    for ( int j2 = j - 1 ; j2 < j && j2 > -1 ; j2-- )

                        if ( cells [i2][j2] < cells [i][j] ){

                            sum++ ;

                        }
                }
            }

            inversions += sum ;
            sum = 0 ;
        }
    }

    cells [a][b] = 0 ;
    return inversions;

}

bool is_solvable() {

    int temp ;
    bool solvable = false ;

    temp = get_inversions() ;

    if ( temp % 2 == 0 )
        solvable = true ;

    return solvable;

}

void init() {

    int &empty_x1 = empty_x , &empty_y1 = empty_y ;

    fill_cells () ;
    for ( int i = 0 ; i < 3 ; i++ )

        for ( int j = 0 ; j < 3 ; j++ )

            if (cells [i][j]==0){

                empty_x1 = i ;
                empty_y1 = j ;

            }

}

void print_puzzle() {

    cout << "\n    " << cells [0][0] << " | " << cells [0][1] << " | " << cells [0][2]
         << "\n    " << "---------\n"
         << "    " << cells [1][0] << " | " << cells [1][1] << " | " << cells [1][2]
         << "\n    " << "---------\n"
         << "    " << cells [2][0] << " | " << cells [2][1] << " | " << cells [2][2] << endl  ;

}


void do_command ( string command ) {

    int temp ;
    int &empty_x1 = empty_x , &empty_y1 = empty_y ;

    if ( command == "down" && empty_x1 != 2 ) {
        empty_x1++ ;
        temp = cells [empty_x1][empty_y1] ;
        cells [empty_x1][empty_y1] = cells [empty_x1 - 1 ][empty_y1] ;
        cells [empty_x1 -1][empty_y1] = temp ;
    }

    else if ( command == "down" && empty_x1 == 2 )
        cout << "Can't move down !\n " ;

    if ( command == "up" && empty_x1 != 0 ){
        empty_x1-- ;
        temp = cells [empty_x1][empty_y1] ;
        cells [empty_x1][empty_y1] = cells [empty_x1 + 1 ][empty_y1] ;
        cells [empty_x1 + 1][empty_y1] = temp ;

    }

    else if ( command == "up" && empty_x1 == 0 )
        cout << "Can't move up ! \n" ;


    if ( command == "left" && empty_y1 != 0 ){
        empty_y1-- ;
        temp = cells [empty_x1][empty_y1] ;
        cells [empty_x1][empty_y1] = cells [empty_x1][empty_y1 + 1] ;
        cells [empty_x1][empty_y1 + 1] = temp ;
    }
    else if (command == "left" && empty_y1 == 0)
        cout << "Can't move left !\n" ;


    if ( command == "right" && empty_y1 != 2 ) {
        empty_y1++ ;
        temp = cells [empty_x1][empty_y1] ;
        cells [empty_x1][empty_y1] = cells [empty_x1][empty_y1 - 1] ;
        cells [empty_x1][empty_y1 - 1 ] = temp ;
    }
    else if (command == "right" && empty_y1 == 2)
        cout << "Can't move right !\n" ;


}

string get_command() {

    string &command1 = command ;

    cout << "Enter the direction (right , left , up , down ) : " ;

    do {

        cin >> command1 ;
        if ( command1 != "right" && command1 != "left" && command1 != "up" && command1 != "down" )
            cout << "\n Enter the true directin  : " ;

    }

    while ( command1 != "right" && command1 != "left" && command1 != "up" && command1 != "down" ) ;

    return command;

}



bool is_solved() {

    bool solved = false ;

    if ( cells[0][0] == 1 && cells[0][1] == 2 && cells[0][2] == 3 &&
         cells[1][0] == 4 && cells[1][1] == 5 && cells[1][2] == 6 &&
         cells[2][0] == 7 && cells[2][1] == 8 && cells[2][2] == 0 )
        solved = true ;

    return solved;

}



int main() {

    bool return_solved , return_is_solvable   ;

    do{

        fill_cells() ;
        init() ;
        return_is_solvable = is_solvable () ;

    } while ( !return_is_solvable ) ;

    print_puzzle () ;

    do {

        get_command () ;
        do_command (command) ;
        cout << endl ;
        print_puzzle () ;
        cout << endl ;
        return_solved = is_solved() ;

    } while (!return_solved) ;

    cout <<"*********************************\n"<<
         "* Congratulations ! You won !!! * \n" <<
         "*********************************" ;

    return 0;

}

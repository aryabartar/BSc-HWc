#include<iostream>
using namespace std;

int moves = 0;
void hanoi(int m, char a, char b, char c){

  if(m == 1)
    cout << "Move disc " << m << " from " << a << " to " << c << endl;

  else{

    hanoi( m-1 , a , c , b );

    cout << "Move disc " << m << " from " << a << " to " << c << endl;

    hanoi( m-1 , b , a , c );
  }
    moves += 1;
}

int main(){

  int discs;

  cout << "Enter the number of discs: " << endl;
  cin >> discs;

  hanoi(discs, 'A', 'B', 'C');

  cout << "It took " << moves << " moves. " << endl;

}

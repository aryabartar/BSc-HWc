#include <iostream>
#include <cstring>
#include <fstream>
#include <sstream>

using namespace std;

int firstCount = 0;
bool done = false ;

void setTable (char c[200][200] , int size) {

    bool key = true;
    char tableName[30] , temp[30];
    int i1 = 1;

    ofstream tableFake;
    ofstream table;

    if (strcmp (c[0] , "CREATE"))
        key = false;

    if (strcmp (c[1] , "TABLE"))
        key = false;

    if (key) {

        done = true ;

        while (c[2][i1] != '\'') {
            tableName[i1 - 1] = c[2][i1];
            i1++;
        }
        tableName[i1-1] = '\0' ;

        tableName[i1 - 1] = '\0';
        tableFake.open (tableName);
        tableFake.close ();
        table.open (tableName);

        for (int i2 = 3 ; i2 <= size ; i2 += 2) {
            i1 = 2;

            for (int i3 = 0 ; i3 < 31 ; i3++)
                temp[i3] = '\0';

            if (c[i2][0] == '(') {

                while (c[i2][i1] != '\'') {
                    temp[i1 - 2] = c[i2][i1];
                    i1++;
                }

                temp[i1 - 2] = ' ';
            }

            else if (c[i2][0] == '\'') {

                while (c[i2][i1] != '\'') {
                    temp[i1 - 2] = c[i2][i1 - 1];
                    i1++;
                }

                temp[i1 - 2] = c[i2][i1 - 1];
                temp[i1 - 1] = ' ';
            }


            if (c[i2 + 1][0] == 'I')
                strcat (temp , "Int");

            else if (c[i2 + 1][0] == 'S')
                strcat (temp , "String");

            else if (c[i2 + 1][0] == 'F')
                strcat (temp , "Float");

            table << temp;
            table << " ";
            firstCount++;
        }
        table.close ();
    }


}
void insert (char c[200][200] , int size) {

    int len;
    char tempName[30] , temp[30];
    bool key = true;
    ofstream file;

    if (strcmp (c[0] , "INSERT"))
        key = false;

    if (strcmp (c[1] , "INTO"))
        key = false;

    int i1 = 1;

    if (key) {

        done = true ;

        while (c[2][i1] != '\'') {
            tempName[i1 - 1] = c[2][i1];
            i1++;
        }
        tempName[i1 - 1] = '\0';

        file.open (tempName , ios::app);
        file << "\n";

        for (int i2 = 4 ; i2 < size + 1 ; i2++) {

            i1 = 0;

            if (c[i2][0] == '(') {

                if (c[i2][1] != '\'') {

                    for (int i3 = 0 ; i3 < strlen (c[i2]) ; i3++) {
                        temp[i3] = c[i2][i3 + 1];
                    }

                    temp[strlen (temp) - 1] = '\0';
                }

                else {

                    temp[i1] = c[i2][i1 + 2];
                    i1++;
                    temp[i1] = c[i2][i1 + 2];
                    i1++;

                    while (c[i2][i1] != '\'') {
                        temp[i1] = c[i2][i1 + 2];
                        i1++;
                    }

                    temp[i1 - 2] = '\0';
                }
            }

            else if (c[i2][0] == '\'') {
                temp[i1] = c[i2][i1 + 1];
                i1++;

                while (c[i2][i1] != '\'') {
                    temp[i1] = c[i2][i1 + 1];
                    i1++;
                }

                temp[i1 - 1] = '\0';
            }

            else {
                len = strlen (c[i2]);
                strcpy (temp , c[i2]);
                temp[len - 1] = '\0';
            }

            file << temp << " ";
        }
        file.close ();
    }
}
void select (char c[200][200] , int size) {

    bool key = true;
    int from , columnNameSize = 0;
    char columnName[30][30] , tableName[30];
    int columnNum[30];
    string line1;
    string a;
    ifstream file1;
    bool whereExist = false;
    string array[30];
    int arrayCheck[50] = {0};

    if (strcmp (c[0] , "SELECT"))
        key = false;

    if (key && strcmp (c[1] , "*")) {

        done = true ;

        for (int i1 = 1 ; i1 < size + 1 ; i1++)
            if (!strcmp (c[i1] , "FROM"))
                from = i1;
        {
            int j1 ;

            for (int i1 = 1 ; i1 < from ; i1++) {

                for (j1 = 1 ; c[i1][j1] != '\'' ; j1++) {
                    columnName[i1 - 1][j1 - 1] = c[i1][j1];
                }
                columnName[i1 - 1][j1 - 1] = '\0' ;
                columnNameSize++;
            }
        }
        {
            int i1 ;
            for (i1 = 1 ; c[from + 1][i1] != '\'' ; i1++)
                tableName[i1 - 1] = c[from + 1][i1];
            tableName[i1-1] = '\0' ;
        }
        file1.open (tableName);
        getline (file1 , line1);

        for (int i1 = 0 ; i1 < columnNameSize + 1 ; i1++) {
            int count = -1 , countPlus = 0;
            istringstream iss (line1);

            while (iss >> a) {

                if (countPlus++ % 2 == 0) count++;

                if (a == columnName[i1]) {
                    columnNum[i1] = count;
                }
            }
        }

        for (int i1 = from ; i1 < size + 1 ; i1++)

            if (!strcmp (c[i1] , "WHERE"))
                whereExist = true;

        file1.close ();

        int arrayCount = 0 ;

        if (whereExist == false) {

            for (int i1 = 0 ; i1 < columnNameSize ; i1++) {
                int i = 0;
                file1.open (tableName);
                getline (file1 , line1);

                while (getline (file1 , line1)) {
                    istringstream iss1 (line1);
                    i = 0;

                    while (iss1 >> a) {

                        if (columnNum[i1] == i) {
                            array[arrayCount] = a ;
                            arrayCount++ ;
                        }
                        i++;
                    }
                }
                file1.close ();
                arrayCheck[arrayCount] = 1;
            }

            int columnNameCount = 0;
            cout << columnName[columnNameCount] << "    ";
            {
                int i1 ;
                for (i1 = 0 ; i1 < arrayCount  ; i1++){

                    if (arrayCheck[i1 + 1] == 1) {
                        columnNameCount++;
                        if (i1 != arrayCount-1)
                            cout << array[i1] << "\n" << columnName[columnNameCount] << "    ";
                        else
                            cout << array[i1] ;
                    }

                    else
                        cout << array[i1] << " , ";
                }

            }

        }

        else {
            char columnName2 [30][30] , column2[30][30] ;
            int column2Counter = 0 , c2c = 0 ;
            string line2 ;

            int kk = 0 ;
            //kk = 0 >> nothing  kk=1 >> and   kk=2 >> or
            for (int i1 = 0 ; i1 < size+1 ; i1++) {
                if (!strcmp (c[i1] , "AND"))
                    kk = 1 ;
                else if (!strcmp (c[i1] , "OR")) {
                    kk = 2 ;
                }
            }

            {
                bool mex = false ;
                int i1 , i2 ;
                for (i1 = size ; strcmp (c[i1] , "WHERE") ; i1--) {
                    mex = false ;
                    if (c[i1][0] == '\'') {
                        for (i2 = 1 ; c[i1][i2] != '\'' ; i2++) {
                            columnName2[column2Counter][i2 - 1] = c[i1][i2];
                        }
                        columnName2[column2Counter][i2-1] = '\0' ;
                        column2Counter++ ;

                    }
                    for (int k1 = 0 ; k1 < strlen (c[i1]) ; k1++ ){
                        if (c[i1][k1] == '=')
                            mex = true ;
                    }

                    int k1 ;
                    if (mex == true) {
                        for (k1 = 0 ; c[i1][k1] != '=' ; k1++ ){

                        }
                        k1++ ;

                        int i5 = 0 ;
                        for (;c[i1][k1+1] != '\'' ;k1++) {
                            column2[c2c][i5] = c[i1][k1+1] ;
                            i5++ ;
                        }
                        column2[c2c][i5] = '\0' ;
                        c2c++ ;

                    }

                }
            }

            column2Counter-- ;


            if (kk == 0) {
                file1.open (tableName) ;
                getline (file1 , line2) ;
                string b ;
                arrayCount = 0 ;
                while (getline (file1 , line1)) {
                    istringstream iss (line1) ;
                    istringstream iss1 (line2) ;
                    istringstream iss2 (line1) ;
                    int h = 0 ;


                    while (iss >> a) {
                        if (column2[0] == a){
                            h = 0 ;
                            while (iss1 >> a) {
                                if (h%2 == 0) iss2 >> b ;
                                for (int i1 = 0 ; i1 < columnNameSize ; i1++) {
                                    if (columnName[i1] == a){
                                        array[arrayCount] = a ;
                                        arrayCount++ ;
                                        array[arrayCount] = b ;
                                        arrayCount++ ;
                                    }

                                }
                                h++ ;

                            }
                        }
                    }
                }

                for (int i1 = 0 ; i1 < arrayCount ; i1++ ){
                    if (i1%2 == 0) cout << endl;
                    cout << array[i1] << " " ;
                }

            }


            else if (kk = 1) {

                file1.open (tableName) ;
                getline (file1,line2) ;
                string b ;
                arrayCount = 0 ;
                int ucheck ;
                while (getline (file1 , line1)){
                    istringstream iss (line1) ;
                    istringstream iss1 (line2) ;
                    istringstream iss2 (line1) ;
                    ucheck = 0 ;
                    while (iss >> a) {
                        if (a == column2[0])
                            ucheck++ ;
                        if (a == column2[1])
                            ucheck++ ;
                    }
                    int h = 0 , w = 0 ;
                    if (ucheck == 2) {

                        while (iss1 >> a) {
                            if (h%2 == 0) iss2 >> b ;
                            for (int i1 = 0 ; i1 < columnNameSize ; i1++) {
                                if (a == columnName[i1]){
                                    array[w] = a ;
                                    w++ ;
                                    cout << a << " " ;
                                    array[w] = b ;
                                    w++ ;
                                    cout << b << endl ;
                                }
                            }
                            h++ ;
                        }
                    }
                }
            }
        }


    }

    else if (key && !strcmp (c[1] , "*")) {

        done = true ;
        int count1 = 0  ;
        {
            int i1 ;
            for (i1 = 1 ; c[3][i1] != '\'' ; i1++)
                tableName[i1 - 1] = c[3][i1];
            tableName[i1-1] = '\0' ;
        }
        file1.open (tableName) ;
        getline (file1 , line1) ;

        istringstream iss (line1) ;

        while (iss >> a) count1++ ;


        int j = 0 ;
        int count = 0 ;
        while (getline (file1 , line1)) count ++ ;
        file1.close () ;
        file1.open (tableName) ;
        getline (file1 , line1) ;

        while (getline (file1 , line1)) {
            istringstream iss1 (line1) ;
            int i = 0 ;

            while (iss1 >> a){
                array [i*(count+1)+j] = a ;
                i++ ;
            }
            cout << endl ;
            j++ ;
        }

        for (int i1 = 0 ; i1 < count*count1 + 1 ; i1++) {
            if (i1%(count +1) == 0) cout << endl ;
            cout << array[i1] << " " ;


        }

        file1.close() ;
    }
}
void update (char c[200][200] , int size) {

    ifstream file1;
    ofstream file2;
    string line1 , line2 ;
    string a , b , d;
    bool key = true;
    int columnSize = size - 4;
    char column[30][30] , columnName[30][30] , lastColumn[30];
    char tableName[30];

    if (strcmp (c[0] , "UPDATE"))
        key = false;

    if (key) {

        done = true ;
        if (!strcmp (c[size - 1] , "WHERE")) {
            {
                int i1 ;
                for (i1 = 0 ; c[1][i1 + 1] != '\'' ; i1++)
                    tableName[i1] = c[1][i1 + 1];
                tableName[i1] = '\0' ;
            }

            for (int i1 = 0 ; i1 < columnSize ; i1++) {
                int i2 = 0;
                int i3 = 0;
                while (c[3 + i1][i2] != '=') {
                    i2++;
                }

                i2 += 2;

                while (c[3 + i1][i3 + 1] != '\'') {
                    columnName[i1][i3] = c[3 + i1][i3 + 1];
                    i3++;
                }
                columnName[i1][i3] = '\0' ;

                i3 = 0;
                while (c[3 + i1][i2 + i3] != '\'') {
                    column[i1][i3] = c[3 + i1][i3 + i2];
                    i3++;
                }
                column[i1][i3] = '\0' ;
                if (column[i1][i3-1] == ',')
                    column[i1][i3-1] = '\0' ;



            }

            int i2 = 0;
            int i3 = 0;

            while (c[size][i2] != '=') {
                i2++;
            }

            i2 += 2;

            while (c[size][i2 + i3] != '\'') {
                lastColumn[i3] = c[size][i3 + i2];
                i3++;
            }
            lastColumn[i3] = '\0' ;

            key = false ;

            file1.open (tableName);
            file2.open ("tempFile");

            getline (file1 , line2) ;
            file2 << line2 << endl ;
            int count = 0 ;

            while (getline (file1 , line1)) {
                istringstream iss3 (line2) ;
                istringstream iss (line1) ;
                istringstream iss1 (line1) ;
                istringstream iss2 (line1) ;
                while (iss >> a) {
                    if (a == lastColumn) {
                        while (iss3 >> b) {
                            if (count % 2 == 0 ) {
                                iss2 >> a ;
                            }
                            for (int k1 = 0 ; k1 < size ; k1++ ) {
                                if (columnName[k1] == b){
                                    a = column[k1] ;
                                }
                            }
                            if (count % 2 == 0){
                                file2 << a << " " ;

                            }

                            count++ ;
                        }
                        file2 << endl ;
                        key = true ;
                    }
                }


                if (key == false){
                    while (iss2 >> a) {
                        file2 << a << " " ;
                    }
                    file2 << endl ;
                }
                key = false ;

            }



            file1.close ();
            file2.close ();

            file1.open ("tempFile");
            file2.open (tableName);

            while (getline (file1 , line1)) {
                file2 << line1 << endl;
            }
            file1.close ();
            file2.close ();
        }


        else {

            string line2;

            for (int i1 = 0 ; c[1][i1 + 1] != '\'' ; i1++)
                tableName[i1] = c[1][i1 + 1];

            for (int i1 = 0 ; i1 <= size - 3 ; i1++) {
                int i2 , i3;

                for (i2 = 0 ; c[i1 + 3][i2 + 1] != '\'' ; i2++) {
                    columnName[i1][i2] = c[i1 + 3][i2 + 1];
                }

                columnName[i1][i2] = '\0';
                i3 = 0;

                while (c[i1 + 3][i3] != '=') {
                    i3++;
                }

                i3 += 2;

                for (i2 = 0 ; c[i1 + 3][i2 + i3] != '\'' ; i2++) {
                    column[i1][i2] = c[i1 + 3][i2 + i3];
                }

            }

            file1.open (tableName);
            file2.open ("tempFile");

            int index[30];
            int indexSize = 0;
            getline (file1 , line1);
            file2 << line1 << endl;
            int count = 0 , countPlus = 0;
            int jk = 0;

            for (int k1 = 0 ; strcmp (columnName[k1] , "\0") ; k1++) {
                jk = 0;
                istringstream iss (line1);

                while (iss >> a) {
                    jk++;

                    if (a == columnName[k1]) {
                        cout << a << endl;
                        index[indexSize] = (jk + 1) / 2;
                        indexSize++;
                    }
                }
            }

            cout << index[0] << " " << index[1] << " " << index[2] << " " << indexSize << "\n\n";

            while (getline (file1 , line1)) {
                istringstream iss2 (line1);

                int count = 0;
                bool key = true;

                while (iss2 >> a) {
                    count++;

                    for (int k1 = 0 ; k1 < indexSize ; k1++) {

                        if (index[k1] == count) {
                            file2 << column[k1] << " ";
                            key = false;
                        }
                    }

                    if (key == true)
                        file2 << a << " ";
                    key = true;
                }
                file2 << endl;
            }
            file1.close ();
            file2.close ();

            file1.open ("tempFile");
            file2.open (tableName);

            while (getline (file1 , line1)) {
                file2 << line1 << endl;
            }

            file1.close ();
            file2.close ();
        }
    }

}
void del (char c[200][200] , int size) {

    bool key = true;
    char tableName[30];
    char column1[30];
    char column2[30];
    ifstream file1;
    ofstream file2;
    string line1 , line2 , a , b;

    if (strcmp (c[0] , "DELETE"))
        key = false;

    if (strcmp (c[1] , "FROM"))
        key = false;

    if (key) {
        done = true ;

        {
            int i1;
            for (i1 = 0 ; c[2][i1 + 1] != '\'' ; i1++)
                tableName[i1] = c[2][i1 + 1];
            tableName[i1] = '\0';
        }

        int k1;

        for (k1 = 0 ; c[4][k1 + 1] != '\'' ; k1++)
            column1[k1] = c[4][k1 + 1];

        column1[k1] = '\0';
        k1 += 4;
        int k2 = 0;

        while (c[4][k1 + k2] != '\'') {
            column2[k2] = c[4][k1 + k2];
            k2++;
        }

        column2[k2] = '\0';

        file1.open (tableName);
        file2.open ("tempFile");

        getline (file1 , line2);
        file2 << line2 << endl;

        while (getline (file1 , line1)) {
            istringstream iss (line1);
            istringstream iss1 (line2);
            iss1 >> b;

            while (iss >> a) {

                if (a == column2 && b == column1) {
                    key = false;
                }

                for (int k2 = 0 ; k2 < 2 ; k2++) {
                    iss1 >> b;
                }
            }

            if (key) {
                file2 << line1 << endl;
            }
            key = true;
        }

        file1.close ();
        file2.close ();

        file1.open ("tempFile");
        file2.open (tableName);

        while (getline (file1 , line1)) {
            file2 << line1 << endl;
        }

        file1.close ();
        file2.close ();

    }

}
void drop (char c[200][200] , int size) {

    bool key = true ;
    char tableName[30] ;
    int i1 ;

    if (strcmp (c[0] , "DROP"))
        key = false ;

    if (strcmp (c[1] , "TABLE"))
        key = false ;

    if (key) {

        done = true ;

        for (i1 = 1 ; c[2][i1] != '\'' ; i1++ )
            tableName[i1-1] = c[2][i1] ;

        tableName[i1-1] = '\0' ;

        if (remove (tableName) != 0)
            cout << "Error deleting file !";

        else
            cout << "Deleted successfully !" ;
    }

}

int main () {


    ofstream tableFake;
    ofstream table;
    int size = 0;
    char c[200][200];
    int temp ;
    cout << "First read READ ME file . " ;
    while (strcmp (c[0] , "EXIT")) {
        cout << "\nInput EXIT ; to stop .\n" ;
        cout << "Write : " ;
        size = 0;
        cin >> c[size++];
        while (strcmp (c[size - 1] , ";"))
            cin >> c[size++];
        size -= 2;
        for (int i1 = 0 ; i1 < size + 1 ; i1++) {
            for (int i2 = 0 ; c[i1][i2] != '\0' ; i2++) {
                if (c[i1][i2] == '=' && c[i1][i2 + 1] != '\'') {
                    temp = strlen (c[i1]);
                    for (int j1 = temp - 1 ; i2 < j1 ; j1--) {
                        c[i1][j1 + 1] = c[i1][j1];
                    }
                    c[i1][i2 + 1] = '\'';
                    c[i1][temp + 1] = '\'';
                    c[i1][temp + 2] = '\0';
                    break;
                }
            }
        }
        setTable (c , size) ;
        insert (c , size) ;
        select (c , size) ;
        update (c , size) ;
        del (c , size) ;
        drop (c , size) ;
        if (done == true)
            cout << "\nDone ." ;
        else
            cout << "\nAn error occured !" ;
        done = false ;
    }
    cout << "\nFINISHED\n" ;
    return 0;
}



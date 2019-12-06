#include <LiquidCrystal.h>

bool inMenu;
int menuLocation; //between 0 and 2

LiquidCrystal lcd(12, 11, 5, 4, 3 , 2);

void setup(){
    inMenu = false;
    menuLocation = 0; 

    lcd.begin(16,2);
}

void loop(){
    lcd.print("menu 0");

    delay(250);
}
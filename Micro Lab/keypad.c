#include <Wire.h>
#include <LiquidCrystal.h>
#include <keypad.h>

const LCDROWNUM = 4;
const LCDCOLNUM = 3;

char hexaKeys[LCDROWNUM][LCDCOLNUM] = {
    {'1', '2', '3'}, 
    {'4', '5', '6'},
    {'7', '8', '9'}, 
    {'*', '0', '#'}
};

byte rowPins[LCDROWNUM] = {5,4,3,2};
byte colPins[LCDCOLNUM] = {8,7,6};

Keypad keypad = Keypad(makeKeymap(hexaKeys), rowPins, colPins, LCDROWNUM, LCDCOLNUM);

void setup(){
    Serial.begin(9600);
}

void loop(){
    char key = keypad.getKey();
    if (key){
        Serial.println(key); 
    }
}

#include <time.h>
#include <Wire.h>
#include <keypad.h>
#include <LiquidCrystal.h>

//Global setup
bool inMenu;
int menuLocation; //between 0 and 2

// LCD setup
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

// Keypad setup
const LCDROWNUM = 4;
const LCDCOLNUM = 3;
char hexaKeys[LCDROWNUM][LCDCOLNUM] = {
    {'1', '2', '3'},
    {'4', '5', '6'},
    {'7', '8', '9'},
    {'*', '0', '#'}};
byte rowPins[LCDROWNUM] = {30, 32, 34, 36, 38};
byte colPins[LCDCOLNUM] = {22, 24, 26, 28};
Keypad keypad = Keypad(makeKeymap(hexaKeys), rowPins, colPins, LCDROWNUM, LCDCOLNUM);

// Temperature
int temperatureInputPin = A0;

// Humidity setup
int humidityInputPin = 52;

void setup()
{
    inMenu = false;
    menuLocation = 0;

    lcd.begin(16, 2);
}

void loop()
{
    checkAndUpdateKeypad();
    checkAndUpdateMenu();
    delay(250);
}

void checkAndUpdateKeypad()
{
    // 1 is up, 7 is down, * is enter, # is exit
    char key = keypad.getKey();
    if (key)
    {
        if (key == '1' && menuLocation < 2)
        {
            menuLocation = menuLocation + 1;
        }
        else if (key == '7' && 0 < menuLocation)
        {
            menuLocation = menuLocation - 1;
        }
        else if (key == '*' && inMenu == false)
        {
            inMenu = true;
        }
        else if (key == '#' && inMenu == true)
        {
            inMenu = false;
        }
    }
}
void checkAndUpdateMenu()
{
    // 0 => temperature
    // 1 => humidity
    // 2 => time

    if (inMenu == true)
    {
        lcd.setCursor(0, 0);
        if (menuLocation == 0)
        {
            lcd.print("Temperature");
        }
        else if (menuLocation == 1)
        {
            lcd.print("Humidity");
        }
        else
        {
            lcd.print("Time");
        }
        lcd.setCursor(0, 1);
        lcd.print("Click *");
    }

    else
    {
        lcd.setCursor(0, 0);

        if (menuLocation == 0)
        {
            lcd.print("Temperature is: ");
            lcd.setCursor(0, 1);
            lcd.print(updateTemperature());
        }
        else if (menuLocation == 1)
        {
            lcd.print("Humidity is: ");
            lcd.setCursor(0, 1);
            lcd.print(updateHumidity());
        }
        else
        {
            lcd.print("Time is: ");
            lcd.setCursor(0, 1);
            time_t now = time(0);
            lcd.print(now);
        }
    }
}

float updateTemperature(){
    int temperatureVal = analogRead(temperatureInputPin);
    float temperature = (temperatureVal / 1024.0) * 330.0;
    return temperature;
}

int updateHumidity()
{
    int humidity = digitalRead(humidityInputPin);
    return humidity;
}
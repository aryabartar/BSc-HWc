#include <time.h>
#include <Wire.h>
#include <Keypad.h>
#include <LiquidCrystal.h>
#include <Servo.h>


//Global setup
bool inMenu;
int menuLocation; //between 0 and 2

// LCD setup
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

// Keypad setup
const int LCDROWNUM = 4;
const int LCDCOLNUM = 3;
char hexaKeys[LCDROWNUM][LCDCOLNUM] = {
    {'1', '2', '3'},
    {'4', '5', '6'},
    {'7', '8', '9'},
    {'*', '0', '#'}};
byte rowPins[LCDROWNUM] = {30, 32, 34, 36};
byte colPins[LCDCOLNUM] = {24, 26, 28};
Keypad keypad = Keypad(makeKeymap(hexaKeys), rowPins, colPins, LCDROWNUM, LCDCOLNUM);

// Temperature
int temperatureInputPin = A0;
int idealTemperature = 30;

// Humidity
int humidityInputPin = 52;

// Light
bool autoLightMode = true;
int lightSensorInputPin = A1;

// Light Servo
Servo lightServo;
int servoOutputPin = 50;
int lightServoAngle = 0; // between 0 and 90


void setup()
{
    inMenu = false;
    menuLocation = 0;
    
    // Light sensor
    pinMode(lightSensorInputPin, INPUT);

    // Light servo
    lightServo.attach(servoOutputPin);

    
    lcd.begin(16, 2);
}

void loop()
{
    checkAndUpdateKeypad();
    checkAndUpdateMenu();
    checkConditions();
    delay(250);
}

void checkAndUpdateKeypad()
{
    // 1 is up, 7 is down, * is enter, # is exit
    char key = keypad.getKey();
    if (key)
    {
        if (key == '1')
        {   
            if (inMenu == true && menuLocation == 0){
                // Change ideal temperature
                idealTemperature += 1;
            }
            else if (inMenu == true && menuLocation == 2 && autoLightMode == false){
                // Change light barrier angle
                if (lightServoAngle < 85){
                    lightServoAngle += 5;
                }   
            }
            else if (inMenu == false && menuLocation < 2){
                menuLocation += 1;
            }
        }
        
        else if (key == '7')
        {
            if (inMenu == true && menuLocation == 0){
                // Change ideal temperature
                idealTemperature -= 1;
            }
            else if (inMenu == true && menuLocation == 2 && autoLightMode == false){
                // Change light barrier angle
                if (5 < lightServoAngle){
                    lightServoAngle -= 5;
                }   
            }
            else if (inMenu == false && 0 < menuLocation){
                menuLocation -= 1;
            }
        }
        
        else if (key == '*')
        {
            if (menuLocation == 1){
                autoLightMode = !autoLightMode;
            }
            else if(inMenu == false && (menuLocation == 0 || menuLocation == 2)){
                inMenu = true;
            }
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
    // 1 => light mode (auto or manual)
    // 2 => light change

    if (menuLocation == 0){
        // Temerature
        if (inMenu == false){
            lcd.setCursor(0, 0);
            lcd.print("Temperature");
            lcd.setCursor(0, 1);
            lcd.print("Click *");
        }
        else {
            lcd.setCursor(0, 0);
            lcd.print("Now: " + String(updateTemperature()));
            lcd.setCursor(0, 1);
            lcd.print("Ideal:" + String(idealTemperature));
        }
    }
    
    else if (menuLocation == 1){
        lcd.setCursor(0, 0);
        lcd.print("Light mode:");
        
        lcd.setCursor(0, 1);
        if  (autoLightMode == true){
            lcd.print("Automatic");
        }
        else{
            lcd.print("Manual");
        }
    }

    else if (menuLocation == 2){
        if (inMenu == false){
            lcd.setCursor(0, 0);
            lcd.print("Light barrier");
            lcd.setCursor(0, 1);
            lcd.print("Click *");
        }
        else{
            
        } 
    }
}

void checkConditions(){

  // light barrier
  if (autoLightMode == true){
      moveLightBarrierAutomatically();
  }
  else{
      lightServo.write(lightServoAngle); // Angle is updated in other places   
  }

  
  
}

void moveLightBarrierAutomatically(){
    int lightSensorVal = analogRead(lightSensorInputPin);
    lightServoAngle = map(lightSensorVal, 0, 1023, 0, 90);
    lightServo.write(lightServoAngle);
}

float getTemperature(){
    int temperatureVal = analogRead(temperatureInputPin);
    float temperature = (temperatureVal / 1024.0) * 330.0;
    return temperature;
}

int getHumidity()
{
    int humidity = digitalRead(humidityInputPin);
    return humidity;
}

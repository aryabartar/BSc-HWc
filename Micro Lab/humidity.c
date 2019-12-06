int humidityInputPin = 52; 

void setup() {
    pinMode(humidityInputPin, INPUT);
    Serial.begin(9600);
}

void loop(){
    int humidity = digitalRead(humidityInputPin);

    Serial.print("Humidity is : "); 
    Serial.print(humidity);
    delay(250);
}
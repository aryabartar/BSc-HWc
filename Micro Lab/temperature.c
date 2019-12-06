int temperatureInputPin = A0;

void setup()
{
    Serial.begin(9600);
}

void loop()
{
    int temperatureVal = analogRead(temperatureInputPin);
    float temperature = (temperatureVal / 1024.0) * 330.0;

    Serial.print("Temp is : ");
    Serial.print(temperature);
    delay(1000);
}
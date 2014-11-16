#include <Servo.h>
Servo servo1;
Servo servo2;

void setup(){
  servo1.attach(5);
  servo2.attach(6);
}

void loop(){
  int angle = analogRead(0);
  angle = map(angle, 0, 1023, 0, 180);
  servo1.write(angle);
  delay(15);
}


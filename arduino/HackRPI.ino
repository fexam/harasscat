#include <Servo.h>
Servo servo1;
Servo servo2;
Servo servo3;
int val;
int angle = 0;
int val2;
int angle2 = 0;

void setup(){
  servo1.attach(3);
  servo2.attach(5);
  servo3.attach(7);
  Serial.begin(9600);
}

void loop(){
  val = analogRead(angle);
  val = map( val, 0, 1023, 0, 180);
  servo1.write(val);
  delay(20);
  Serial.print("\t output = ");      
  Serial.println(val, DEC); 
  val2 = analogRead(angle2);
   
  val2 = map(val2 - 90, 0, 1023, 0, 90);
  servo2.write(val2);
  delay(20);
  Serial.print("\t output2 = ");      
  Serial.println(val2, DEC);
  
  if (val > 0){
    servo3.write(angle);
    delay(20);
  }
  
}

  

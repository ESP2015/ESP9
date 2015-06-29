package processing.test.processing_code;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ketai.sensors.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class processing_code extends PApplet {



KetaiSensor sensor;
float accelerometerX, accelerometerY, accelerometerZ;
int swatch;  //(1)
float r, g, b;

public void setup()
{
 sensor = new KetaiSensor(this);
 sensor.start();
 orientation(PORTRAIT);
 textAlign(CENTER, CENTER);
 textSize(36);
}

public void draw()
{
 // remap sensor values to color range  
 r = map(accelerometerX, -10, 10, 0, 255);
 g = map(accelerometerY, -10, 10, 0, 255);
 b = map(accelerometerZ, -10, 10, 0, 255);
 // assign color to background 
 background(r, g, b);
 // color picker
 fill(swatch);  //(2)
 rect(0, height/2, width, height/2);  //(3)
 fill(0);
 text("Picked Color: \n" + 
   "r: " + red(swatch) + "\n" + //(4)
   "g: " + green(swatch) + "\n" +
   "b: " + blue(swatch), width*0.5f, height*0.75f);
}

public void onAccelerometerEvent(float x, float y, float z)
{
 accelerometerX = x;
 accelerometerY = y;
 accelerometerZ = z;
}

public void mousePressed() 
{
 // updating color value, tapping top half of the screen
 if (mouseY < height/2)
   swatch = color(r, g, b);  //(5)
}


}

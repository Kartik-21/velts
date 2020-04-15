#include<LiquidCrystal.h>
LiquidCrystal lcd(8, 9, 10, 11, 12, 13);
#include <SoftwareSerial.h>
SoftwareSerial gps(3, 4); // RX, TX
///////////////////////////////////////////////////////////////////////////////////////
String gpsString = "";
char *test="$GPRMC";
float latitude=0; 
float longitude=0; 
int temp = 0, i,ii;
boolean gps_status = 0;
///////////////////////////////////////////////////////////////////////////////////////
int fan = 6; 
int BUZZ = 7; 
int led=2;///MESSAGE CHECKING LED
char str[15];  ///  MOBILE NUMBER WITH   STRING COMPARISION
///////////////////////////////////////////////////////////////////////////////////////
unsigned int ACL;//////connected to A0
unsigned int MEMSX;//////connected to A1
unsigned int MEMSY;//////connected to A2
unsigned int VIB;//////connected to A3
///////////////////////////////////////////////////////////////////////////////////////
int aa=0;
int bb=0;
int cc=0;
///////////////////////////////////////////////////////////////////////////////////////
void setup()
{
lcd.begin(16, 2);
Serial.begin(9600);
gps.begin(9600);
//////////////////////////////////
pinMode(fan, OUTPUT); 
pinMode(BUZZ, OUTPUT);  
//////////////////////////////////
digitalWrite(BUZZ,LOW); 
digitalWrite(fan,LOW);
//////////////////////////////////
delay (500);lcd.clear();
lcd.setCursor(0,0);lcd.print("Vehicle . ");
lcd.setCursor(0,1);lcd.print("Enhance ");
delay (5000);lcd.clear();
lcd.setCursor(0,0);lcd.print("Location");
lcd.setCursor(0,1);lcd.print("Tracking System");
delay (5000);lcd.clear();
lcd.setCursor(0,0);lcd.print("GPS Initializing");
lcd.setCursor(0, 1);lcd.print(" No GPS Range  ");
get_gps();show_coordinate();delay(2000);lcd.clear();
lcd.setCursor(0, 1);lcd.print("GPS OK");
delay(2000);lcd.clear(); 
lcd.setCursor(0,0);lcd.print("Init");
lcd.setCursor(0,1);lcd.print("GSM ");delay(1000);
gsm_init();lcd.clear();
lcd.setCursor(0,0);lcd.print("GSM");
lcd.setCursor(0,1);lcd.print("OK");
delay(1000);lcd.clear();temp = 0;
delay(500);get_gps();
show_coordinate();delay(500);
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////
void loop()
{st: 
if(temp==1)
{
check();
temp=0;
ii=0;
delay(500);
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
ACL = analogRead(0);ACL=ACL/4;delay(200);
lcd.setCursor(9,0);lcd.print("A:");lcd.setCursor(12,0);lcd.print(ACL);delay(500);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
if(ACL>150)
{bb=1;
digitalWrite(fan,LOW);digitalWrite(BUZZ,HIGH);delay(1000);
gprs_GPS_send();send_SMS();tracking2();delay(1000);gprs_send();bb=0;goto st;
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
if(ACL<150)
{digitalWrite(fan,HIGH); digitalWrite(BUZZ,LOW);bb=0;}
/////////////////////////////////////////////////////////////////////////////////////////////
lcd.clear() ;
MEMSX = analogRead(1);MEMSX=MEMSX/2;
lcd.setCursor(0,0);lcd.print("X:");lcd.setCursor(3,0);lcd.print(MEMSX);delay(1000);
MEMSY = analogRead(2);MEMSY=MEMSY/2;
lcd.setCursor(0,1);lcd.print("Y: ");lcd.setCursor(3,1);lcd.print(MEMSY);delay(1000);
/////////////////////////////////////////////////////////////////////////////////////////////
if(((MEMSX >= 160) & (MEMSX <= 175)) &  ( (MEMSY >= 160) & (MEMSY <= 175 )) )
{
aa=0;lcd.setCursor(8,0);lcd.print("NORMAL  ");delay(500);digitalWrite(fan,HIGH);digitalWrite(BUZZ,LOW);lcd.clear() ;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
if(((MEMSX >= 130) & (MEMSX <= 150)) &  ( (MEMSY >= 160) & (MEMSY <= 175 )) )
{aa=1;
lcd.setCursor(8,0);lcd.print("RIGHT   ");lcd.setCursor(8,1);lcd.print("ACCIDENT ");
digitalWrite(fan,LOW); digitalWrite(BUZZ,HIGH);delay(1000);
gprs_GPS_send();send_SMS();tracking3();delay(1000);gprs_send();aa=0;goto st;
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
if(((MEMSX >= 185) & (MEMSX <= 200)) &  ( (MEMSY >= 160) & (MEMSY <= 175 )) )
{aa=1;
lcd.setCursor(8,0);lcd.print("LEFT    ");lcd.setCursor(8,1);lcd.print("ACCIDENT ");
digitalWrite(fan,LOW); digitalWrite(BUZZ,HIGH);delay(1000);
gprs_GPS_send();send_SMS();tracking4();delay(1000);gprs_send();aa=0;goto st;
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
if(((MEMSX >= 160) & (MEMSX <= 175)) &  ( (MEMSY >= 180) & (MEMSY <= 195 )) )
{aa=1;
lcd.setCursor(8,0);lcd.print("FRONT  ");lcd.setCursor(8,1);lcd.print("ACCIDENT ");
digitalWrite(fan,LOW); digitalWrite(BUZZ,HIGH);delay(1000);
gprs_GPS_send();send_SMS();tracking5();delay(1000);gprs_send();aa=0;goto st;
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if(((MEMSX >= 160) & (MEMSX <= 175)) &  ( (MEMSY >= 130) & (MEMSY <= 150 )) )
{aa=1;
lcd.setCursor(8,0);lcd.print("BACK  ");lcd.setCursor(8,1);lcd.print("ACCIDENT ");
digitalWrite(fan,LOW); digitalWrite(BUZZ,HIGH);delay(1000);
gprs_GPS_send();send_SMS();tracking6();delay(1000);gprs_send();aa=0;goto st;
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
VIB = analogRead(3);VIB=VIB/4;delay(200);
lcd.setCursor(9,1);lcd.print("v:");lcd.setCursor(12,1);lcd.print(VIB);delay(500);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
if(VIB>200)
{cc=1;
digitalWrite(fan,LOW);digitalWrite(BUZZ,HIGH);delay(1000);
gprs_GPS_send();send_SMS();tracking7();delay(1000);gprs_send();cc=0;goto st;
}
if(VIB<200)
{digitalWrite(fan,HIGH); digitalWrite(BUZZ,LOW);cc=0;}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}


////////////////////////////////////////////////////////////////////////////////////////////
void serialEvent() 
{
while(Serial.available()) 
{
if(Serial.find("#S."))
{
digitalWrite(led, HIGH);
delay(100);
digitalWrite(led, LOW);
while (Serial.available()) 
{
char inChar=Serial.read();
str[ii++]=inChar;
if(inChar=='*')
{
temp=1;
return;
} 
} 
}
}
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void gprs_GPS_send()
{
lcd.clear();lcd.print("WEBSITE LINK");
boolean test7_flag=1;
while(test7_flag){
Serial.print("AT+HTTPPARA=\"URL\",\"http://www.sk-infosoft.top/vehicletracking/putdata.php");
Serial.print("?lat=");Serial.print(latitude,6);
Serial.print("&lon=");Serial.print(longitude,6);
Serial.print("&pid=01");Serial.print("\"");
Serial.print("\r\n");delay(10);
while(Serial.available()>0)
{if(Serial.find("OK"))test7_flag=0;}delay(1000);}
lcd.clear();lcd.print("SUCESS");
///////////////////////////////////////////////////////////////////////////////////////////////////////////
lcd.clear();lcd.print("SENDING DATA");
boolean test8_flag=1;while(test8_flag){Serial.print("AT+HTTPACTION=0\r\n");
while(Serial.available()>0){if(Serial.find("OK"))test8_flag=0;}delay(1000);}
lcd.clear();lcd.print("GPRS SENT OK");delay(2000);
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
void gprs_send()
{
lcd.clear();lcd.print("WEBSITE LINK");
boolean test7_flag=1;
while(test7_flag){
Serial.print("AT+HTTPPARA=\"URL\",\"http://www.sk-infosoft.top/vehicletracking/put_data.php");
if(aa==0){Serial.print("?acc=");Serial.print("NO");}delay(10);
if(aa==1){Serial.print("?acc=");Serial.print("YES");}delay(10);
if(bb==0){Serial.print("&acl=");Serial.print("NO");}delay(10);
if(bb==1){Serial.print("&acl=");Serial.print("YES");}delay(10);
if(cc==0){Serial.print("&vib=");Serial.print("NO");}delay(10);
if(cc==1){Serial.print("&vib=");Serial.print("YES");}delay(10);
Serial.print("\"");Serial.print("\r\n");
while(Serial.available()>0){if(Serial.find("OK"))test7_flag=0;}delay(1000);}
lcd.clear();lcd.print("LINK OK");
///////////////////////////////////////////////////////////////////////////////////////////////////////////
lcd.clear();lcd.print("ACTION");
boolean test8_flag=1;while(test8_flag){Serial.print("AT+HTTPACTION=0\r\n");
while(Serial.available()>0){if(Serial.find("OK"))test8_flag=0;}delay(1000);}
lcd.clear();lcd.print("ACTION OK");delay(2000);
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
void gpsEvent()
{
gpsString="";
while(1)
{
while (gps.available()>0)  //Serial incoming data from GPS
{
char inChar = (char)gps.read();
gpsString+= inChar;  //store incoming data from GPS to temparary string str[]
i++;
if (i < 7)                      
{
if(gpsString[i-1] != test[i-1])  //check for right string
{
i=0;
gpsString="";
}
}
if(inChar=='\r')
{
if(i>60)
{
gps_status=1;
break;
}
else
{
i=0;
}
}
}
if(gps_status)
break;
}
}
///////////////////////////////////////////////////////////////////
void get_gps()
{
lcd.clear();
lcd.print("Getting GPS Data");
lcd.setCursor(0,1);
lcd.print("Please Wait.....");
gps_status=0;
int x=0;
while(gps_status==0)
{
gpsEvent();
int str_lenth=i;
coordinate2dec();
i=0;x=0;
str_lenth=0;
}
}
////////////////////////////////////////////////////////////////////////////
void show_coordinate()
{
lcd.clear();
lcd.print("Lat:");
lcd.print(latitude);
lcd.setCursor(0,1);
lcd.print("Log:");
lcd.print(longitude);
delay(2000);
lcd.clear();
}
////////////////////////////////////////////////////////////////////////////
void coordinate2dec()
{
String lat_degree="";
for(i=19;i<=20;i++)         
lat_degree+=gpsString[i];
String lat_minut="";
for(i=21;i<=27;i++)         
lat_minut+=gpsString[i];
String log_degree="";
for(i=32;i<=34;i++)
log_degree+=gpsString[i];
String log_minut="";
for(i=35;i<=41;i++)
log_minut+=gpsString[i];
float minut= lat_minut.toFloat();
minut=minut/60;
float degree=lat_degree.toFloat();
latitude=degree+minut;
minut= log_minut.toFloat();
minut=minut/60;
degree=log_degree.toFloat();
longitude=degree+minut;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void send_data(String message)
{Serial.print(message);delay(200);}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void send_SMS()
{lcd.clear();lcd.print("Sending SMS ");delay(500);init_sms();}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void tracking2()
{send_data("ALCOHOL DETECTED\n");MSG_SENT();}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void tracking7()
{send_data("VIBRATION DETECTED\n");MSG_SENT();}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void tracking3()
{send_data("RIGHT ACCIDENT\n");MSG_SENT();}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void tracking4()
{send_data("LEFT ACCIDENT\n");MSG_SENT();}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void tracking5()
{send_data("FRONT ACCIDENT\n");MSG_SENT();}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void tracking6()
{send_data("BACK ACCIDENT\n");MSG_SENT();}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void tracking8()
{send_data("VEHICLE TRACK\n");MSG_SENT();}
//////////////////////////////////////////////////////////////////////////////////////////////////
void init_sms()
{Serial.println("AT+CMGF=1");delay(400);Serial.println("AT+CMGS=\"7990161803\"");delay(400);}
//////////////////////////////////////////////////////////////////////////////////////////////////
void MSG_SENT()
{
send_data("VEHICLE NO is GJ1234\n");
Serial.println("Vehicle Location Place");delay(100);
Serial.print("https://www.google.com/maps/place/");
Serial.print(latitude,6);
Serial.print(",");
Serial.println(longitude,6);
delay(500);Serial.write(26);delay(2000);
lcd.clear();lcd.print("Message Sent");
delay(2000);lcd.clear();
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void gsm_init()
{
lcd.clear();
lcd.print("GSM TESTING..");
boolean at_flag=1;
while(at_flag)
{Serial.println("AT");while(Serial.available()>0){if(Serial.find("OK"))at_flag=0;}delay(1000);}
lcd.clear();lcd.print("GSM CONNECTED");delay(1000);lcd.clear();
///////////////////////////////////////////////////////////////////////////////////////////////////////
lcd.print("Disabling ECHO");
boolean echo_flag=1;
while(echo_flag)
{Serial.println("ATE0"); while(Serial.available()>0){if(Serial.find("OK"))echo_flag=0;}delay(1000);}
lcd.clear();  lcd.print("Echo OFF");delay(1000);lcd.clear();
/////////////////////////////////////////////////////////////////////////////////////////////////////////
lcd.print("Finding Network..");
boolean net_flag=1;while(net_flag){Serial.println("AT+CPIN?");
while(Serial.available()>0){if(Serial.find("+CPIN: READY"))net_flag=0;}delay(1000);}
lcd.clear();lcd.print("Network Found..");
///////////////////////////////////////////////////////////////////////////////////////////////////////////
lcd.setCursor(0,1);lcd.print("GSM NETWORK");delay(1000);lcd.clear();
////////////////////////////////////////////////////////////////////////////////
lcd.clear();lcd.print("TEST MESS");
boolean test_flag=1;while(test_flag){Serial.println("AT+CMGF=1");
while(Serial.available()>0){if(Serial.find("OK"))test_flag=0;}delay(1000);}
lcd.clear();lcd.print("TEST MESSAGE");delay(1000);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
lcd.clear();lcd.print("MESS Delete");
boolean testa_flag=1;while(testa_flag){Serial.println("AT+CMGD=1,4");
while(Serial.available()>0){if(Serial.find("OK"))testa_flag=0;}delay(1000);}
lcd.clear();lcd.print("MESS Deleted");delay(1000);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////
lcd.clear();lcd.print("MESS check");
boolean testb_flag=1;while(testb_flag){Serial.println("AT+CNMI=2,2,0,0,0");
while(Serial.available()>0){if(Serial.find("OK"))testb_flag=0;}delay(1000);}
lcd.clear();lcd.print("MESS check ok");delay(1000);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
lcd.clear();lcd.print("GPRS1");
boolean test2_flag=1;while(test2_flag){Serial.print("AT+SAPBR=3,1,\"CONTYPE\",\"GPRS\"\r\n");
while(Serial.available()>0){if(Serial.find("OK"))test2_flag=0;}delay(1000);}
lcd.clear();lcd.print("GPRS START1");delay(1000);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
lcd.clear();lcd.print("GPRS2");
boolean test3_flag=1;while(test3_flag){Serial.print("AT+SAPBR=3,1,\"APN\",\"internet\"\r\n");
while(Serial.available()>0){if(Serial.find("OK"))test3_flag=0;}delay(1000);}
lcd.clear();lcd.print("GPRS START2");delay(3000);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
lcd.clear();lcd.print("GPRS MAIN");
boolean test4_flag=1;while(test4_flag){Serial.print("AT+SAPBR=1,1\r\n");
while(Serial.available()>0){if(Serial.find("OK"))test4_flag=0;}delay(1000);}
lcd.clear();lcd.print("GPRS CAME");delay(1000);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
lcd.clear();lcd.print("HTTP1");
boolean test5_flag=1;while(test5_flag){Serial.print("AT+HTTPINIT\r\n");
while(Serial.available()>0){if(Serial.find("OK"))test5_flag=0;}delay(1000);}
lcd.clear();lcd.print("HTTP1");delay(1000);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
lcd.clear();lcd.print("HTTP2");
boolean test6_flag=1;while(test6_flag){Serial.print("AT+HTTPPARA=\"CID\",1\r\n");
while(Serial.available()>0){if(Serial.find("OK"))test6_flag=0;}delay(1000);}
lcd.clear();lcd.print("HTTP2");delay(1000);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
}
//////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void check()
{
if(!(strncmp(str,"vectrack",8)))
{
lcd.clear();
lcd.setCursor(0,0);
lcd.print("vectrack");
delay(1000);Serial.println("AT");
delay(400);Serial.println("AT+CMGF=1");
delay(400);Serial.println("AT+CMGD=1,4");delay(2000);
delay(1000);lcd.clear();lcd.print("Sending SMS ");
send_SMS();tracking8();delay(1000);
}
}

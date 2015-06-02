#include <IRremote.h>
#include <IRremoteInt.h>
#include <idDHT11.h>  //inclusão da biblioteca sensor de temperatura e umidade


// Chuva = c
// Movimento = m
// Lampada = a
// Gás = g
// Temperatura = t


//declaração do pino do relé
int rele = 7;
int estado = 1;

String t, h;
byte byteRead;


// declaração da pinagem do sensor IR
int RECV_PIN = 5;
IRrecv irrecv(RECV_PIN);
decode_results results;


//declaração dos pinos do sensor de chuva
int chuvaD = 3;  //pino digital
int chuvaA = A5; //pino analógico 
int valD = 0;    //armazena valor lido p/ pino digital
int valA = 0;    //armazena valor lido p/ pino analógico
String Schuva = "Sensor Seco";


//declaração da pinagem do sensor de gás
int gas = A4;
int gasVal = 0;
int gasInt = 0;
String Sgas = "Nenhum vazamento detectado";

//declaração da pinagem do sensor de temperatura/umidade
int idDHT11pin = 2;   //declaração do pino do sensor  DHT11
int idDHT11intNumber = 0;
void dht11_wrapper();   //declaração


//configurando lib do sensor de chuva/umidade
idDHT11 DHT11(idDHT11pin,idDHT11intNumber,dht11_wrapper);


//configuração da pinagem do sensor de movimento
int mov = 8;
String Smovimento = "Nenhum movimento detectado";



void setup(){
  pinMode(rele,OUTPUT);
  Serial.begin(9600);
  //irrecv.enableIRIn(); // Start the receiver
  
  pinMode(chuvaD, INPUT);
  pinMode(chuvaA, INPUT);
  
  pinMode(mov, INPUT);
  
  pinMode(gas, INPUT);
  
}


void loop(){
//Lê entrada de comando via porta serial
char c = Serial.read();


      //liga/desliga relé
  if (c == 'a'){
    if (estado == 1){
      digitalWrite(rele, HIGH);
      estado = 3;
    }else{
      digitalWrite(rele, LOW);
      estado = 1;
    }    
  }
  
  
        //leitura IR
  if (irrecv.decode(&results)) {
    //Serial.print(results.value, DEC);
    if(results.value == 2672){
      //2704 power
      if (estado == 1){
      digitalWrite(rele, HIGH);
      estado = 3;
    }else{
      digitalWrite(rele, LOW);
      estado = 1;
    } 
    }
    dump(&results);
    irrecv.resume(); // Receive the next value
  }
 
 
  

      //leitura sensor de temperatura/umidade
  if (c == 't'){
    DHT11.acquire();
    while (DHT11.acquiring());
    int result = DHT11.getStatus();
    
    switch (result){
    case IDDHTLIB_OK: 
      //Serial.println("OK");
      break;
    case IDDHTLIB_ERROR_CHECKSUM:
      Serial.println("Error\n\r\tChecksum error");
      break;
    case IDDHTLIB_ERROR_ISR_TIMEOUT:
      Serial.println("Error\n\r\tISR Time out error");
      break;
    case IDDHTLIB_ERROR_RESPONSE_TIMEOUT:
      Serial.println("Error\n\r\tResponse time out error");
      break;
    case IDDHTLIB_ERROR_DATA_TIMEOUT:
      Serial.println("Error\n\r\tData time out error");
      break;
    case IDDHTLIB_ERROR_ACQUIRING:
      Serial.println("Error\n\r\tAcquiring");
      break;
    case IDDHTLIB_ERROR_DELTA:
      Serial.println("Error\n\r\tDelta time to small");
      break;
    case IDDHTLIB_ERROR_NOTSTARTED:
      Serial.println("Error\n\r\tNot started");
      break;
    default:
      Serial.println("Erro Desconhecido");
      break;
  }
    Serial.print("\n");
    Serial.print("Umidade: ");
    Serial.print(DHT11.getHumidity(), 2);
    Serial.print("\n");
    Serial.print("Temperatura (c): ");
    Serial.println(DHT11.getCelsius(), 2);
    delay(500);
  }
  
  
  
  //leitura sensor de chuva
  
  if(c == 'c'){
    valD = digitalRead(chuvaD);
    valA = analogRead(chuvaA);

    
      //Serial.print("D= ");
      //Serial.println(valD);
      
      //Serial.print("A= ");
      //Serial.println(valA);
      
    
      if(valA > 900 && valA < 1024){
        Serial.println("sensor seco");
      }
      
      if(valA >= 800 && valA < 900){
        Serial.println("Sensor levemente molhado");
      }
      
      if(valA > 400 && valA < 800){
        Serial.println("Sensor molhado");
      }
      
      if(valA >= 0 && valA <= 400){
        Serial.println("Sensor muito molhado");
      }
      
    
  }
  
  if(digitalRead(mov) == HIGH){
    //Serial.println("Movimento Detectado");
    Smovimento = "movimento detectado";
  }
  if(c == 'm'){
    Serial.println(Smovimento);
  }
  
  
  gasVal = analogRead(gas);
  gasVal = map(gasVal, 0, 1023, 0, 100);
  
  if(gasVal > 25 ){
  //Serial.println("Vazamento de Gás Detectado: ");
  //Serial.println(gasVal);
  //Serial.println(110, DEC);
  //delay(2000);
  Sgas = "houve vazamento de gás";
  }

  if(c == 'g'){
    Serial.println(Sgas);
  }

}



//o whrapper é responsável pela chamada
void dht11_wrapper() {
  DHT11.isrCallback();
}

//o DUMP trata a entrada IR
void dump(decode_results *results) {
  int count = results->rawlen;
  if (results->decode_type == UNKNOWN) {
    Serial.println("Could not decode message");
  }
  else {
    if (results->decode_type == NEC) {
      Serial.print("Decoded NEC: ");
    } 
    else if (results->decode_type == SONY) {
      Serial.print("Decoded SONY: ");
    } 
    else if (results->decode_type == RC5) {
      Serial.print("Decoded RC5: ");
    } 
    else if (results->decode_type == RC6) {
      Serial.print("Decoded RC6: ");
    }
    Serial.print(results->value, HEX);
    Serial.print(" (");
    Serial.print(results->bits, DEC);
    Serial.println(" bits)");
  }
  Serial.print("Raw (");
  Serial.print(count, DEC);
  Serial.print("): ");

  for (int i = 0; i < count; i++) {
    if ((i % 2) == 1) {
      Serial.print(results->rawbuf[i]*USECPERTICK, DEC);
    } 
    else {
      Serial.print(-(int)results->rawbuf[i]*USECPERTICK, DEC);
    }
    Serial.print(" ");
  }
  Serial.println("");
}


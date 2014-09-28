

#include <IRremote.h>
#include <IRremoteInt.h>

#include <idDHT11.h>  //inclusão da biblioteca


//declaração do pino do relé
int rele = 7;
int estado = 1;

String t, h;
byte byteRead;

int RECV_PIN = 5;
IRrecv irrecv(RECV_PIN);
decode_results results;



int idDHT11pin = 2;   //declaração do pino do sensor  DHT11
int idDHT11intNumber = 0; 
void dht11_wrapper();   //declaração


//configurando lib
idDHT11 DHT11(idDHT11pin,idDHT11intNumber,dht11_wrapper);



void setup(){
  pinMode(rele,OUTPUT);
 
  Serial.begin(9600);
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







void loop(){
  
char c = Serial.read();

  if (c == 'a'){
    if (estado == 1){
      digitalWrite(rele, HIGH);
      estado = 3;
    }else{
      digitalWrite(rele, LOW);
      estado = 1;
    }    
  }
  
  
  
  if (irrecv.decode(&results)) {
    Serial.print(results.value, DEC);
    if(results.value == 2704){
      if (estado == 1){
      digitalWrite(rele, HIGH);
      estado = 3;
    }else{
      digitalWrite(rele, LOW);
      estado = 1;
    } 
    }
    //dump(&results);
    irrecv.resume(); // Receive the next value
  }
  
  //Serial.print("\nRecebendo informacao do sensor DHT11: ");
  //Serial.print("Ler sensor: ");
  //delay(100);
  irrecv.enableIRIn(); // Start the receiver
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


  if (c == 't'){
    //String u = ("Umidade(%): "+(DHT11.getHumidity(), 2));
    //String t = ("Temperatura(C): "+(DHT11.getCelsius(), 2));  
  
    Serial.print("Umidade: ");
    Serial.print(DHT11.getHumidity(), 2);
    Serial.print("Temperatura (c): ");
    Serial.print(DHT11.getCelsius(), 2);
   delay(500);
  }


}

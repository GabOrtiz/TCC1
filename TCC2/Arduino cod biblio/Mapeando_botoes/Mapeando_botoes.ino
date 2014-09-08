#include <IRremote.h>
#include <IRremoteInt.h>

String dados="";              // Recebe dados do controle remoto
int receiver = 4;             // pino do sensor I.R

IRrecv irrecv(receiver); 
decode_results results;

void setup()
{
Serial.begin (9600);
irrecv.enableIRIn();          //inicializa o receptor
pinMode(5,OUTPUT);
}

void loop()
{
if (irrecv.decode(&results)){ // Inicia o recebimento dos dados
dados=dados+results.value;    // Cria a string
Serial.println (dados);       // Imprime codigo do botão
irrecv.resume();              // Encerra o recebimento dos dados
}
 

if (dados=="16738455"){       // Testa string
Serial.println ("BOTAO 1");   // Faz qualquer coisa
delay (150);                  // Esse delay evita acionamentos multiplos (pode ser modificado de acordo com as necessidades). 
}


dados="";                    // Limpa variavel (string)
}

Olá escrevi isso pois creio que algo tipo um artigo será de melhor entendimento.

Bom primeiramente sempre que for me enviar um código de algm coisa peço que coloque no seu Github, ou que copie e cole no meu whatsapp, pois fotos não são eficientes.

Segundamente aquele código que você fez aparenta ter bastante erros graves e eu vou te explicar agora.

# INICIO

Nessa temporada iremos utilizar a **[Programação Orientada a Objetos ( POO ) ](https://www.devmedia.com.br/principais-conceitos-da-programacao-orientada-a-objetos/32285)** e aquele seu código tinha tudo mas não tinha poo, entretanto não tem problemas pois vamos corrigir isso agora.
Para que você programe o funcionamento dos motores que se encaixe em uma estrutura orientada a objetos, é nescessario entender a classe Core.java:
```
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Core {
    protected DcMotor leftMotor; // This variable will be responsible for the left engine.
    protected DcMotor rightMotor; // This variable will be responsible for the right engine.
    protected HardwareMap hwMap; // This variable will be responsible for the hardware map.
    protected NormalizedColorSensor colorSensor;
    private Telemetry tlmtr;

    public void setHardwareMap() {
        leftMotor = (DcMotor)hwMap.get("leftMotor");
        rightMotor = (DcMotor)hwMap.get("rightMotor");
        colorSensor = (NormalizedColorSensor)hwMap.get("colorSensor");
        tlmtr.addData("Hardware Map -> ", "Loaded!");

    }

}
```

O método **setHardwareMap** define os motores, em outras palavras a variavel `leftMotor`, `rightMotor`, e `colorSensor`, são agora responsaveis pelo motor esquerdo, direito e o sensor de cor, respectivamente.
Agora que nós ja definimos as variaveis responsaveis pelos motores, vamos trabalhar nelas e fazer um método que faça o robô se movimentar para frente durante 10 segundos.

# DESENVOLVIMENTO

Como se trata de um código orientado a objetos, seria bom criar uma **[Interface](https://www.devmedia.com.br/entendendo-interfaces-em-java/25502)**, para que assim possamos definir algumas regras para a classe que iremos criar do motor.

A estrutura basica de uma interface é da seguinte maneira:
```
public interface NomeDaInterface {

    public String metodoUm();
    public String metodoDois();
}
```
##### **OBS: OS METODOS DA INTERFACE SERÂO OBRIGATORIOS NA CLASSE ONDE A MESMA SERÁ APLICADA!, ENTÃO TENHA CAUTELA AO CRIAR OS MESMOS.**

As classes do motor terão de informar se o motor esta ativo ou não, também terão de terum método que permita a chamada para a utilização dos motores, eu irei adicionar aqui um método que será o de verficação para ver se o motor esta ativo ou não.
```
    public Boolean estaAtivo(); // Esse método servirá para indicar se o motor esta sendo utilizado ou não.
```

Após você por mais métodos na interface, chega a hora de finalmente criar a classe do motor.

# FINALIZANDO

Para poder aplicar a interface dentro da classe é nescessario implementala da seguinte forma:
`public class NomeDaClasse implements NomeDaInterface`

Lembre-se que também temos de extender a classe Core, para isso utilizaremos:
`public class NomeDaClasse extends Core implements NomeDaInterface`

Agora para finalmente começar a trabalhar com o motor precisamos criar a variavel do motor para esta classe e conecta-la com a variavel que esta na classe Core, para isso iremos usar:
`private DcMotor motorEsquerdo = super.nomeDaVariavelComoEstaNaClasseCore`
após isso você irá criar um método que receba os motores que querem ser ativados e para fazer-los andar basta utilizar o:
```
motorEsquerdo.setPower(1) // lembre-se que a força do motor vai de 0 a 1, sendo 0 desligado e 1 com força máxima
sleep(10) // tempo que o motor levará para desligar em segundos.
``` 


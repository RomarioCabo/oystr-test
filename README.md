# oystr-robot-test
Aplicação responsavel por buscar informações em determidados sties.    


## Dependências
 * Java 17+
 * Maven 3

### Stack
 * Spring
 * Selenium

### Execução
```
Para executar sua aplicação no terminal, você precisa primeiro construir o pacote do 
seu aplicativo usando o comando mvn package. Certifique-se de que está no diretório 
raiz do seu projeto, onde o arquivo pom.xml está localizado.

Assim que o pacote for construído, você pode executar a aplicação usando o comando 
java -jar target/<nome do arquivo jar gerado>.jar. Certifique-se de substituir 
<nome do arquivo jar gerado> pelo nome real do arquivo jar gerado pela construção do pacote.

Por exemplo, se o nome do arquivo jar gerado for "oystr-1.0-SNAPSHOT.jar", o comando completo 
seria:

java -jar target/oystr-1.0-SNAPSHOT.jar

Certifique-se de ter o Java instalado em sua máquina e configurado corretamente nas 
variáveis de ambiente antes de executar o comando acima.
```
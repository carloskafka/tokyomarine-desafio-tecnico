# Uma API com dois protocolos: Rest e Mensageria JMS , utilizando o framework Spring MVC e a mensageria ActiveMQ com banco de dados MySQL e um client utilizando o framework Spring Web MVC + Thymeleaf + Bootstrap.

# Getting Started

Como a empresa TokyoMarine possui uma alta demanda de requisições por segundo (rps), o uso da tecnologia assíncrona junto com o uso da mensageria **ActiveMQ** com o uso da abordagem **Producer e Consumer** otimizou o processamento no processo de cadastro de novos clientes. Quanto maior a quantidade de consumidores, mais rápido será o processamento da fila.

A solução possui três módulos: 
   
   - **tokyomarinecommons**: Responsável por compartilhar os **DTO's** ou **objetos de transferência** e o **ContratoRest** responsável por centralizar todos os **Endpoints REST** entre o projeto **server** e o **client**
   - **tokyomarineserver**: API principal que utiliza o framework **Spring Web** e é estruturada no conceito arquitetural de "separação em camadas" para se comunicar com um client onde nela possui os **objetos de domínio** com suas **regras de negócio** (como validação de campos, por exemplo), a fachada principal que simplifica todas as chamadas.
   - **tokyomarineclient**: Client **Spring MVC + Bootstrap** que realiza consulta a API Principal

## Importante 

- Através do link http://localhost:8080/api/customers?size=5&page=1 é capaz de ver o retorno JSON dos clientes paginados pelo parametro **size** e **page**.
- Através do link http://localhost:8081/customers?size=5&page=1 é capaz de visualizar a página que exibe todos os clientes em uma consulta paginada.

## Requisitos

1. Instalar banco de dados **MySQL** em https://dev.mysql.com/downloads/installer/ e executar o script **script_criacao_banco_de_dados.sql** para criação da tabela planeta e usuario de acesso.
2. Executar `mvn install`no projeto **PlanetStarWars-commons**
3. Executar as aplicações **planetstarwars-server** e **planetstarwars-reativo** com o comando `mvn spring-boot:run`

O servidor da aplicação **planetstarwars-server** irá iniciar em <http://localhost:8080>.
O servidor da aplicação **planetstarwars-reativo** irá iniciar em <http://localhost:9090>.

## Rest Endpoints

```
1. POST /api/customer/ - Adicionar cliente

2. GET /api/customers/ - Listar clientes

3. GET /customers/ - Exibe clientes na página Bootstrap

```

## Execução de testes de integração


O projeto também contém testes de integração para todos os endpoints da API. Para executar os testes de integração, vá até o diretório raíz do projeto e escreva `mvn test` no terminal (windows ou linux).

Execute o comando `mvn test` nessa sequência

1. **tokyomarinecommons**
2. **tokyomarineserver**:
3. **tokyomarineclient**

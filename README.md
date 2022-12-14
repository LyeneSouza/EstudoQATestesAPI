# DESAFIO DO MÓDULO 3 #

Automação de testes de API Rest.

### Desafio ###

Trata-se de dois projetos que contemplam testes da API "Fake Rest API".
```shell
https://fakerestapi.azurewebsites.net/index.html
```

### Descrição ###

Um projeto utiliza Postman e o outro utiliza Rest Assured.

## Projeto Postman ##

### O que você precisa ter instalado para rodar o projeto Postman e gerar relatório via linha de comando ###
- node.js 
```shell
https://nodejs.org/en/
```
- newman 
```shell
npm install -g newman
```
- HTML Reporter Extra
```shell
npm install -g newman-reporter-htmlextra
```

### Como rodar o projeto Postman via linha de comando ###
- Fazer o clone do projeto e entrar na respectiva pasta
```shell
cd "diretório de sua preferência"
git clone https://LyeneS@bitbucket.org/lyenes/desafio-qa-modulo3.git
cd desafio-qa-modulo3/Postman/
```
- Executar o newman
```shell
newman run Fake_REST_API.postman_collection.json -e Testes.postman_environment.json
```
- Executar o newman e gerar relatório
```shell
newman run Fake_REST_API.postman_collection.json -e Testes.postman_environment.json -r htmlextra --reporter-htmlextra-title "Fake REST API"
```
Obs: O relatório gerado será salvo na pasta `newman` com o título `Fake REST API` seguido da data e hora da geração do relatório, no mesmo formato do arquivo `Relatorio Fake REST API-2022-12-13-13-59-21-786-0.html`, disponível no repositório.

### Observações acerca do projeto Postman ###
Para passar os parâmetros esperados nas requisições, foram utilizadas variáveis de ambiente com seus valores setados no pre-request, pois cada requisição precisava trabalhar com um valor específico, que atendesse seu cenário. 

Destaca-se que, nas requisições da pasta "Activities", os parâmetros foram mantidos fixos, tendo seus valores definidos na aba de Params, apenas para demonstrar outro modo possível de passar o id para a requisição.

## Projeto Rest Assured ##

### O que você precisa ter instalado para rodar o projeto Rest Assured ###
- Java JDK 1.8 `Java version: 1.8.0_202`
- IDE de sua preferência 

### Como executar o projeto Rest Assured ###
- Fazer o clone do projeto
```shell
cd "diretório de sua preferência"
git clone https://LyeneS@bitbucket.org/lyenes/desafio-qa-modulo3.git
```
- Abrir o projeto, que se encontra no diretório `FakeRestApiTestRestAssured`, no IDE de sua preferência
- Executar a Suíte de Testes
```shell
src -> main -> java -> test -> suite -> SuiteTest.java -> run
```
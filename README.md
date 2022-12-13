# DESAFIO DO MÓDULO 3 #

Automação de testes de API Rest.

### Desafio ###

Trata-se de dois projetos que contemplam testes da API "Fake Rest API".
```shell
https://fakerestapi.azurewebsites.net/index.html
```

### Descrição ###

Um projeto utiliza Postman e o outro utiliza RestAssured.

### O que você precisa ter instalado para rodar o projeto do Postman via linha de comando ###
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

### Como rodar o projeto Postman e gerar relatório via linha de comando ###
- Fazer o clone do projeto e entrar na respectiva pasta
```shell
cd "diretório de sua preferência"
git clone https://LyeneS@bitbucket.org/lyenes/desafio-qa-modulo3.git
cd desafio-qa-modulo3/Postman/
```
- Executar o newman e gerar relatório
```shell
newman run Fake_REST_API.postman_collection.json -e Testes.postman_environment.json -r htmlextra --reporter-htmlextra-title "Fake REST API"
```

### Observações acerca do projeto Postman ###
Para passar os parâmetros esperados nas requisições, foram utilizadas variáveis de ambiente com seus valores setados no pre-request, pois cada requisição precisava trabalhar com um valor específico, que atendesse seu cenário. 

Destaca-se que, nas requisições da pasta "Activities", os parâmetros foram mantidos fixos, tendo seus valores definidos na aba de Params, apenas para demonstrar outro modo possível de passar o id para a requisição.
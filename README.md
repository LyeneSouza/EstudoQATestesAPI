# DESAFIO DO MÓDULO 3 #

Automação de testes de API Rest  .

### Desafio ###

O impulsionando deverá construir os testes para uma API Rest 

### Descrição ###

Deverão ser entregues dois projetos um para o framework Postman e outro utilizando Rest-Assured.
Selecione um endpoint para ser automatizado considerando as APIs publicas sugeridas, para facilitar utilize o mesmo para a automação dos dois frameworks.

APIS públicas sugeridas:


* https://fakerestapi.azurewebsites.net/index.html
* [https://reqres.in](https://reqres.in)


### Requisitos e Funcionalidades Esperadas ###

- [ ] Construir os testes automatizados para a API publica escolhida.
- [ ] Realize ao menos um teste positivo para cada método (GET -POST – PUT - DELETE), sendo no mínimo 4; 
- [ ] Crie adicionalmente os testes de exceções;
- [ ] Criar um README.md contendo as orientações para rodar a aplicação e suas dependências pela linha de comando.

### Bônus ###

- [ ] (REST-ASSURED) Validação de esquema.
- [ ] (POSTMAN) Usar o Newman CLI que execute por linha de comando;
- [ ] (POSTMAN) Gerar Relatório dos testes;

### Instruções para Realização do Desafio ###
1. Faça um *fork* desse repositório para a sua conta
2. Implemente a sua solução conforme solicitado
3. Compartilhe o link do projeto com o seu mentor 

***
## Critérios de Avaliação ##
* Todos os testes estão passando?
* O recursos implementados correspondem ao que foi solicitado no desafio?
* Os títulos dos cenários estão coerentes?
* Existem ao menos um teste que possua pré condições?
* Os testes estão considerando os dados do responseBody como Arrays e objetos?
* Pode ser claramente entendido e o que o código faz?
* Existe um readme contendo instruções de como rodar a aplicação?

* Sobre Projeto Postman
	* Foi utilizado variáveis?
	* O arquivo com Environments foi utilizado corretamente?
	* [Bônus] A linha de comando do  Newman CLI  executa sem falhas?
	* [Bônus] Foram entregues os relatórios de testes?
* Sobre Projeto RestAssured
    * A estrutura do código é modular o suficiente?
    * O código da aplicação está com nomes intuitivos de funções, variáveis ou classes?
    * O escopo das variáveis de classes estão sendo utilizados de forma adequada?
    * Existe alguma variável não inicializada?
    * Foi realizado algum tratamento de exceções?
    * O projeto possui design patterns?
    * [Bônus] Possui testes de validação de esquema e estão corretos?

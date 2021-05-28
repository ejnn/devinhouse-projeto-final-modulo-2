<h1 align="center">
    <img src="https://i.ibb.co/NydcXq0/devinhouse.gif">
</h1>



# Indice

- [Sobre](#-sobre)
- [Conceito](#-conceito)
- [Ficha técnica](#-ficha-tecnica)
- [Cobertura de testes](#-cobertura-de-testes)
- [Tecnologias utilizadas](#-tecnologias-utilizadas)
- [Dependências utilizadas](#-dependências-utilizadas)
- [Autores](#-autores)


---

## 📋 Sobre

O projeto **back-end DEVinHouse** foi criado dentro do curso **DEVinHouse/SENAI** e é uma aplicação back-end com o intuito de colocarmos em prática todo o conteúdo ensinado sobre **spring boot** para complementar um projeto inicial **front-end em ReactJS**, nesse projeto foi proposto criarmos vários endpoints pensando em um sistema de processos.

---

## 📚 Conceito

A ideia é que esse back-end seja capaz de cadastrar processos com chaves de identificação únicas (além do seu ID) e vincula-lo a um assunto e interessado, um processo não pode ser cadastrado ou editado se o assunto ou o interessado informado for invalido, não é possível editar a chave de um processo já cadastrado, por fim, a ultima peculiaridade do projeto é o cadastro de um interessado, pois foi implementado um validador de CPF.

---

## 🔧 Ficha técnica

| End-points  |  Endereços  |
| ------------------- | ------------------- |
| Cadastrar Assunto |  POST localhost:8080/v1/assuntos |
| Listar Assunto |  GET localhost:8080/v1/assuntos |
|  Cadastrar Interessado |  POST localhost:8080/v1/interessado |
|  Cadastrar Processo |  POST localhost:8080/v1/processos |
|  Editar Processo |  PUT localhost:8080/v1/processos/ID |
|  Listar Processos |  GET localhost:8080/v1/processos |
|  Pesquisa Processos com ID |  GET localhost:8080/v1/assuntos/ID |
|  Pesquisa Processos com chave |  GET localhost:8080/v1/assuntos?chave=chave |
|  Pesquisa Processos com ID Assunto |  GET localhost:8080/v1/assuntos?assuntoId=assuntoId |
|  Pesquisa Processos com ID Interessado |  GET localhost:8080/v1/assuntos?interessadoId=interessadoId |
|  Deletar Processos com ID |  DELETE localhost:8080/v1/assuntos/ID |

---

## 👁‍🗨 Cobertura de Testes

O projeto possui 94% de cobertura de testes unitários **(sem contar as entidades)**.

<img src="https://i.ibb.co/rf9q1jJ/cobertura-de-testes.jpg">


---

## 🚀 Tecnologias utilizadas
O projeto foi desenvolvido utilizando as seguintes tecnologias:

* [Trello](https://trello.com/)
* [Spring Boot](https://spring.io/)
* [ModelMapper](http://modelmapper.org/getting-started/)
* [JUnit5](https://junit.org/junit5/docs/current/user-guide/)
* [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)

---

## 🌟 Dependências utilizadas
O projeto foi desenvolvido utilizando as seguintes dependências:

* [Spring web](https://mvnrepository.com/artifact/org.springframework/spring-web)
* [Spring Boot DevTools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
* [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#preface)
* [H2 Database](http://www.h2database.com/html/tutorial.html)
* [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)

---
## 📷 Autores
* [Eduardo Jorge Nicolau](https://github.com/ejnn)
* [Lucas Ricardo Pires](https://github.com/Notnox)
* [Milena Diniz Vieira](https://github.com/milena-diniz)

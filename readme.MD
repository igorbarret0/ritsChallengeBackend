<h1 align="center">
    Desafio Backend Magalu
</h1>

solução do desafio [backend Rits](https://github.com/rits-tecnologia/backend-challenge)
## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)


## Como Executar

- Clonar repositório git
```
git clone git@github.com:igorbarret0/ritsChallengeBackend.git
```

- Construir o projeto:
```
./mvnw clean package
```


A API poderá ser acessada em [localhost:8080](http://localhost:8080).

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/)

-  CLIENT
```
POST /client - Adicionar um cliente

{
    "name": "Cliente",
    "email": "cliente@email.com",
    "phone": "00999911111",
    "address": "Rua 1000"
}

```

```
GET /client - Obter todos os clientes

```

-  PRODUCT
```
POST /product - Adicionar um produto

{
    "name": "Coca-Cola",
    "price": 9.90
}

```

```
GET /product - Obter todos os produtos

```

```
PUT /product/{productId} - Atualizar um produto

{
     "name": "Coca-Cola",
     "price": 10.90
}
```

```
DELETE /product/{productId} - Deletar um produto

```

-  REQUEST
```
POST /request/{clientId} - Fazer um pedido

{
    "requests": [
        {
            "name": "Coca-Cola"
        }
    ]
}
```

```
GET /request/{clientId} - Obter um pedido feito pelo cliente
```

```
DELETE /request/{clientId} - Cancelar um pedido
```

```
GET /request - Obter todos os pedidos
```

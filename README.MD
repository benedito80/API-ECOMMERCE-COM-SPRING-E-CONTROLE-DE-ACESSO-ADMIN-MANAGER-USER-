
## API ECOMMERCE COM SPRING E CONTROLE DE ACESSO (ADMIN, MANAGER & USER)


### CRIANDO O BANCO MYSQL.
Abra seu mysql e crie a seguinte base de dados:
* CREATE DATABASE teste;
* Agora execute o projeto.


<div align="center" style="display: inline_block"><br> 
<img align="center" alt="Rafa-Csharp" height="400" width="800" src="https://github.com/benedito80/API-ECOMMERCE-COM-SPRING-E-CONTROLE-DE-ACESSO-ADMIN-MANAGER-USER-/blob/main/src/main/img/mysql.png?raw=true"> 
</div>

### Vamos agora inser os primeiros usuários para podermos utilizar o sistema.
```
INSERT INTO USER (id,user_name,password,role) 
    VALUES (null, 'usuario1','{bcrypt}$2a$12$R4Q7s48pN57rBJgauzNno.rZwefRxK3u.zzi3reszfma99rNxo4qq','manager');

INSERT INTO USER (id,user_name,password,role) 
    VALUES (null, 'usuario2','{bcrypt}$2a$12$R4Q7s48pN57rBJgauzNno.rZwefRxK3u.zzi3reszfma99rNxo4qq','admin');

INSERT INTO USER (id,user_name,password,role) 
    VALUES (null, 'usuario3','{bcrypt}$2a$12$R4Q7s48pN57rBJgauzNno.rZwefRxK3u.zzi3reszfma99rNxo4qq','user');
```

Obs: a senha acima estão criptografadas, mas é "123456" e o usuário informe um de sua preferência."


##     ABRA O POSTMAN E REALIZE AS SEGUINTES BUSCAR E INSERÇÕES:

* Em "localhost:8080/categorias" realize os (POST & GET) de categorias.
```
  {
    "name":"Refrigerantes"
  }
```

* Em "localhost:8080/img" realise os (POST & GET) de imagens.

```
  {
    "url":"https://cf.shopee.com.br/file/9f46c850a9ab97989df8205898ce9487"
  }
```

* Em "localhost:8080/products" realise os (POST & GET) de produtos.
```
  {
    "name": "2222222222222222",
    "description": "Kaiak",
    "volume": "200ml",
    "categoria":{"id": 1},
    "price":100,
    "estoque": 2,
    "img":{"id": 1}
  }
  
  OBS: Cada "id" da "img" so pode ser utilizada uma vez!
```

* Em "localhost:8080/dadosPessoais" realise os (POST & GET) dos dados pessoais
```
 {
    "username": {"id": 1},
    "rua": "rosario",
    "numero": "85",
    "bairro": "rosario",
    "cidade": "Oeiras",
    "cep": "64500-000",
    "estado": "PI"
  }
  
  OBS: Cada "id" da "username" so pode ser utilizada uma vez!
```

* Em "localhost:8080/itens" realise os (POST & GET) de itens.
```
{
  "product": {"id": 1},
  "qtd": 3
}
```

* Em "localhost:8080/compras" realise os (POST & GET) de compras.
 ```
{
    "desconto": 15.05,
    "status": "PREPARANDO_ENVIO",
    "total": 520.00,
    "itens": [{"id": 1}, {"id": 2}],
    "username": {"id": 1}
 }

```

* Em "localhost:8080/users" realise os (POST & GET) de Usuários
```
 {
    "userName": "benedito",
    "password": "123456",
    "role": "user"
 }
 
 O "userName" é unico!
```

### Rotas de busca... apenas para "ADMIN" ou "MANAGER"
```
    
    
  * Em "localhost:8080/users/buscar?valor=?"
  * Em "localhost:8080/compras/buscar?valor=?"
  * Em "localhost:8080/products/buscar?valor=?"
    
        * Atenção: substituir o ? pelo valor que deseja buscar!
        * Lembrando que no caso de busca por produto não precisa estar logado, os demais sim!
```




### Obs:
* Apenas usuários "manager" pode excluir dados.
* POST e PUT apenas para usuários com perfil "manager" ou "admin".
* Usuários com perfil "USER" não tem acesso aos usuários cadastrados no sistema. Esse apenas poderá atualizar seus próprios dados de login.
* Nenhum usuário poderá ver detalhes dos usuários cadastrados, ou seja, busca por um "id" que não seje o seu.
* GET em quase todas a rotas apenas autenticado, com exceção de "products" e seus respectivos "/id"
* Cada usuário poderá realizar busca e atualizar apenas de seus respectivos "id".
* Claro que o sistema possui outras rotas... estude o código. Dei o foco maior no Post pela dificuldade de alguns... na hora de passar o Json corretamente quando tivermos relacionamentos.
* Lembre-se de inserir as credencias( Login e senha ) no postman ao consultar, cadastrar e deletar os dados... de acordo com o perfil do usuário.
* As rotas GET poderão ser utilizados pelo navegador tendo apenas que informar login e senha para chegar aos dados!

Abraços!!!

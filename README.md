# Blog-back-end
Blog para post e comentários dos usuários

Este projeto utiliza as seguintes tecnologias:
SpringBoot,
Java 8,
Maven,
PostgreSQL


Foi Utilizado Lombok para deixar o código mais limpo e menos verboso;


Utilizado também o Swagger para auxiliar os testes;

==================================================================================

Pra execução do projeto:

<h3>Configurações de Banco</h3>
Ter Postgresql instalado na máquina, acessar o client, criar o banco pelo client ou 
executar o script abaixo para criação do banco

CREATE DATABASE blog
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'C'
    LC_CTYPE = 'C'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
----------------------------------------------------

Após a criação do banco, basta inciar a aplicação;
Pode ser acessada pelo Postman, ou no navegador com swagger

    
A API também está hospedada no Heroku, e pode ser utilizada para testes

<a href="https://app-blog-back-end.herokuapp.com/swagger-ui.html">Link Heroku</a> 


Para acessar com Swagger http://localhost:8080/swagger-ui.html

Acessando pela primeira vez, crie um Usuário users-controller, depois vá em auth-controller para autenticar e pegar o token; 




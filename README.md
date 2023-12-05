<div align="center">

# Plataforma de Aconselhamento Financeiro - Pafy

</div>

## Sobre

Na Plataforma de Aconselhamento Financeiro para Jovens, tem como missão capacitar indivíduos a tomar decisões financeiras informadas, oferecendo orientação especializada e recursos educacionais para uma jornada financeira mais saudável e segura.
Capturar e registrar todas as transações financeiras realizadas pelos usuários, como gastos com cartão de crédito, débito, transferências, depósitos, investimentos e pagamentos.

---

<p align="center">
 <a href="#sobre">Sobre</a> •
 <a href="#time">Time</a> •
 <a href="#pre-requisitos">Pré Requisitos</a> •
 <a href="#tecnologiasstack">Tecnologias/Stack</a> •
 <a href="#variaveis-de-ambiente">Variaveis de Ambiente</a> •
 <a href="#como-executar">Como Executar</a> •
 <a href="#features">Features</a> •
 <a href="#padrões-de-código">Padrões de Código</a> •
</p>

---

## Time

- Desenvolvedores(as): <a href="https://www.linkedin.com/in/mateus-dos-santos/">Mateus dos Santos Loiola</a>
- Github: <a href="https://github.com/Mateus0808">Perfil Github</a>

<p align="right">(<a href="#top">ir para o topo</a>)</p>

## Pre Requisitos

Antes de continuar, certifique-se que você atende aos seguintes requisitos:

- IDE instalada para execução e desenvolvimento do projeto
- Variáveis de ambiente devidamente configuradas
- Java
- MySQL

<p align="right">(<a href="#top">ir para o topo</a>)</p>

## Tecnologias/Stack

Tecnologias utilizadas.

- Spring Boot
- Spring Security
- Spring Data JPA
- Lombok
- Json Web Token
- Maven

<p align="right">(<a href="#top">ir para o topo</a>)</p>

## Variaveis de Ambiente
**Personalize as variáveis do MySQL conforme as credenciais instaladas na sua máquina local do MySQL.**
- `server.port=8080`
- `spring.datasource.url=jdbc:mysql://localhost:3306/pafy_db`
- `spring.datasource.username=root`
- `spring.datasource.password=admin`
- `spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver`
- `spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQLDialect`
- `spring.servlet.multipart.max-file-size=500KB`
- `spring.servlet.multipart.max-request-size=500KB`
- `spring.jpa.show-sql=true`
- `spring.jpa.properties.hibernate.format_sql=true`
- `spring.jpa.hibernate.ddl-auto=update`
- `logging.level.org.hibernate.SQL=DEBUG`
- `logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE`
- `auth.token.expirationInMils=3600000`
- `auth.token.jwtSecret=36763979244226452948404D635166546A576D5A7134743777217A25432A462D`

<p align="right">(<a href="#top">ir para o topo</a>)</p>

## Execução
1. Primeiramente, crie manualmente um banco de dados com o nome `pafy_db` no mysql
2. Execute a aplicação
3. Insira manualmente as role no banco de dados: `ROLE_USER` e `ROLE_ADMIN`

<p align="right">(<a href="#top">ir para o topo</a>)</p>

## Padrões de Código
- MVC - Model, View, Controller
- Convenção de Nomes: PascalCase
- Legibilidade - Variáveis bem definidas

<p align="right">(<a href="#top">ir para o topo</a>)</p>

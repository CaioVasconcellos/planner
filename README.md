# Projeto de Planner

Este é um projeto de planner desenvolvido em colaboração com a Rocketseat. O projeto inclui funcionalidades para gestão de viagens, atividades, convidados e links.

## Tecnologias Utilizadas

- **Spring Web**: Utilizado para construir o backend do projeto.
- **H2 Database**: Banco de dados em memória utilizado para armazenamento de dados.
- **Flyway**: Framework para versionamento e migração do banco de dados.

## Estrutura do Projeto

O projeto segue uma estrutura organizada por camadas, facilitando a manutenção e a escalabilidade:

- **dominio**: Contém as entidades principais do projeto.
- **serviço**: Camada de serviços onde reside a lógica de negócio.
- **repositório**: Contém interfaces para acesso ao banco de dados.
- **controle**: Camada de controle responsável por gerenciar as requisições HTTP.
- **dto**: Objetos de transferência de dados (Data Transfer Objects).

## Funcionalidades

### Viagens
- Criação, edição e recuperar dados
- Visualização de detalhes das viagens.

### Atividades
- Adição de atividades relacionadas a uma viagem.
- Gerenciamento das atividades (criação, edição e recuperação)

### Convidados
- Adição de convidados a uma viagem.
- Gerenciamento dos convidados (criação, edição e recuperação)

### Links
- Adição de links úteis relacionados a uma viagem ou atividade.
- Gerenciamento dos links (criação e recuperação)

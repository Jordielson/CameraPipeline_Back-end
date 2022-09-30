# CameraPipeline_Back-end

CameraPipeline é uma plataforma para o gerenciamento de pipelines permitindo criação e dimensionamento de aplicações computacionais e aprendizagem de maquina em tempo real. Sua estrutura permite o armazenamento de imagens, vídeos e câmeras, além da configuração dos serviços de processamento de imagem.

## Sobre a API do CameraPipeline

Destina-se a fazer o armazenamento de todas as informações relativas a plataforma e solicitar o processamento de imagem a API especificada na criação do PDI(serivço de processamento de imagem). 

### Pré-requisitos
- Java
- PostgreSQL

### Documentação
  A documentação desta API com todos os endpoints e a requisições e respontas podem ser acessada com a execução desse projeto e em seguida acessando a [Documentação]
  
### Configurando seu ambiente local
1. Obtenha os pré-requisitos (instale as seguintes ferramentas, se você ainda não as tiver):
* [Java](https://docs.oracle.com/en/java/javase/19/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
* [PostgreSQL](https://www.postgresql.org/download/)

2. Clone este repositório:
    ```
    git clone https://github.com/Jordielson/CameraPipeline_Back-end.git
    ```
3. Crie o banco de dados com o nome "camera_pipeline". Opcionalmente crie um usuário de conseda todos os privilégios de acesso ao banco de dados, caso contrário altere o application.properties para um usuário que tenha acesso ao banco.
    ```
    CREATE DATABASE camera_pipeline;
    CREATE USER cp_dev WITH PASSWORD 'cp_dev';
    GRANT ALL PRIVILEGES ON DATABASE camera_pipeline TO cp_dev;
    ```

4. Execute a aplicação e acesse a [Documentação].

## Plataforma do CameraPipeline

* [CameraPipeline_Back-end](https://github.com/Jordielson/CameraPipeline_Back-end)
* [CameraPipeline_Front-end](https://github.com/Jordielson/CameraPipeline_Front-end)
* [CameraPipeline_VideoStream](https://github.com/Jordielson/CameraPipeline_VideoStream)

[Documentação]: http://localhost:8080/api/swagger-ui/index.html#/

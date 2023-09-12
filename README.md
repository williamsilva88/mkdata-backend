# Projeto backend Java

## Sobre o projeto
- O projeto de teste MKDATA para cadastro de clientes

## Módulos utilizados
- java 1.8
- Spring Web
- JPA
- Banco de Dados H2 para testes
- Banco de Dados postgresSQL
- lombok

## Configurando ambiente
- Baixar e instalar o intellij idea: `https://www.jetbrains.com/idea/download/#section=windows` ou outro de sua preferência
- Baixar o projeto e colocar na pasta desejada ou sincronizar com o git
- Abrir intellij e abrir projeto indicando a pasta
- Deve baixar JDK 1.8 superior, o projeto foi testado com 1.8
- Ao abrir o projeto o intellij idêntifica que é um projeto spring boot e vai disponibilizar para executar após o maven realizar o download de dependências.

## Testes automatizados
- Não implementado

## Build
- Pode ser feito no intellij, aplicar o parâmetro `-Dspring.profiles.active=production`. 
- Outro opção é utilizar a biblioteca do maven e realizar manualmente pelo pronpt de comando. Para isso deve baixar e instalar o maven na sua maquina e configurar as variaveis de ambiente do seu sistema operacional, com o maven instalado execute `mvn clean package -Dspring.profiles.active=production` na pasta do projeto para gerar o build.

## Deploy
- Para colocar a aplicação no apache tomcat deve apenas pegar o .war gerado no build e colocar na pasta `webapps`. Antes de iniciar o tomcat deve adicionar no java options a configuração do profile de produção `-Dspring.profiles.active=production`, em produção isso é extremamente importante. Este passo de configuração é necessário pois quem está controlando a aplicação é o tomcat externo e não do spring.

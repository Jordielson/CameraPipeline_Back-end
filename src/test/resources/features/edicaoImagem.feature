# language: pt
#enconding: UTF-8

@EdicaoImagem @Geral
Funcionalidade: Validar a aplicação de pipeline em imagens
	Como Usuario 
	Eu quero fazer upload de uma imagem 
	Para baixar a imagem enviada processada pela aplicação
	
	Cenário: Processar uma imagem com sucesso
	Então remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Dado que tenho uma Pipeline
	Entao acessar aba Edição-Camera
	Entao passar file "C:\Users\plgon\Downloads\Power Rangers.jpeg"
	Entao EI clicar no botão Proximo
	Entao selecionar Pipeline "Pipeline Test"
	Entao EI clicar no botão Voltar
	Entao selecionar Pipeline "Pipeline Test"
	Entao EI clicar no botão Baixar
	Entao EI clicar no botão Reiniciar
	 
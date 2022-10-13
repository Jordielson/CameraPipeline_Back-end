# language: pt
#enconding: UTF-8

@EdicaoImagem @Geral
Funcionalidade: Validar a aplicação de pipeline em imagens
	Como Usuario 
	Eu quero fazer upload de uma imagem 
	Para baixar a imagem enviada processada pela aplicação
	
	Cenário: Processar uma imagem com sucesso
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Dado que tenho uma Pipeline
	Entao acessar aba Edicao-Imagem
	Então devo estar na pagina de edicao-Imagem
	Entao EI passar file "src\test\resources\util\TestImage.jpg"
	Entao EI clicar no botão Proximo
  Entao EI devo estar na segunda etapa
	Entao EI selecionar Pipeline "Pipeline Test"
  Entao EI devo estar na terceira etapa
	Então EI verificar processamento salvo
	Entao EI clicar no botão Voltar
  Entao EI devo estar na segunda etapa
	Entao EI selecionar Pipeline "Pipeline Test"
  Entao EI devo estar na terceira etapa
	Então EI verificar processamento salvo
	Entao EI clicar no botão Baixar
	Entao EI clicar no botão Reiniciar
  Entao EI devo estar na primeira etapa
	
	Cenário: Usuario não selecionou um arquivo
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Dado que tenho uma Pipeline
	Entao acessar aba Edicao-Imagem
	Então devo estar na pagina de edicao-Imagem
  Entao EI devo estar na primeira etapa
	* EI clicar no botão Proximo
	Então sistema notifica ArquivoNaoSelecionado_EdicaoPage
  Entao EI devo estar na primeira etapa
	 
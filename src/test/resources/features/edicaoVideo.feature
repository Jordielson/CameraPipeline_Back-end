# language: pt
#enconding: UTF-8

@EdicaoVideo @Geral
Funcionalidade: Validar a aplicação de pipeline em Videos
	Como Usuario 
	Eu quero fazer upload de um video 
	Para baixar o video enviado processado pela aplicação
	
	Cenário: Processar um video com sucesso 
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Dado que tenho uma Pipeline
	Entao acessar aba Edição-Video
	Então devo estar na pagina de edicao-Video
	Entao EV passar file "src\test\resources\util\VideoExample1.mp4"
	Entao EV clicar no botão Proximo
  Entao EV devo estar na segunda etapa
	Entao EV selecionar Pipeline "Pipeline Test"
  Entao EV devo estar na terceira etapa
	Então EV verificar processamento salvo
	Entao EV clicar no botão Voltar
  Entao EV devo estar na segunda etapa
	Entao EV selecionar Pipeline "Pipeline Test"
  Entao EV devo estar na terceira etapa
	Então EV verificar processamento salvo
	Entao EV clicar no botão Reiniciar
  Entao EV devo estar na primeira etapa
  
  Cenário: Usuario não selecionou um arquivo
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Dado que tenho uma Pipeline
	Entao acessar aba Edição-Video
	Então devo estar na pagina de edicao-Video
  Entao EV devo estar na primeira etapa
	* EV clicar no botão Proximo
	Então sistema notifica ArquivoNaoSelecionado_EdicaoVideoPage
  Entao EV devo estar na primeira etapa
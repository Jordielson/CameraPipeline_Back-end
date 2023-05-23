# language: pt
#enconding: UTF-8

@EdicaoCamera @Geral
Funcionalidade: Validar a aplicação de pipeline em cameras
	Como Usuario 
	Eu quero selecionar uma Camera 
	Para aplicar o processameno atravez de uma pipeline
	
	Cenário: Processar imagem em tempo real com sucesso
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Dado que tenho uma Pipeline
	Dado que tenho uma Camera
	Entao acessar aba Edicao-Camera
	Então devo estar na pagina de edicao-Camera
	Entao EC selecionar camera "Camera test"
	Entao EC clicar no botão Proximo
  Entao EC devo estar na segunda etapa
	Entao EC selecionar Pipeline "Pipeline Test"
	Entao EC encerrar Alerta
  Entao EC devo estar na terceira etapa
	Entao EC clicar no botão Voltar
  Entao EC devo estar na segunda etapa
	Entao EC selecionar Pipeline "Pipeline Test"
	Entao EC encerrar Alerta
  Entao EC devo estar na terceira etapa
	Entao EC clicar no botão Salvar
	Entao sistema notifica PipelineAplicadaEmCamera_EdicaoCameraPage
	Então EC verificar processamento salvo
	Entao EC clicar no botão Reiniciar
  Entao EC devo estar na primeira etapa
	
	Cenário: Usuario tentou aplicar pipeline em uma camera já processada 
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Dado que tenho uma Pipeline
	Dado que tenho uma Camera
	Entao acessar aba Edicao-Camera
	Então devo estar na pagina de edicao-Camera
	Entao EC selecionar camera "Camera test"
	Entao EC clicar no botão Proximo
	Entao EC devo estar na segunda etapa
	Entao EC selecionar Pipeline "Pipeline Test"
	Entao EC encerrar Alerta
  Entao EC devo estar na terceira etapa
	Entao EC clicar no botão Salvar
	Entao sistema notifica PipelineAplicadaEmCamera_EdicaoCameraPage
	Então EC verificar processamento salvo
	Entao EC clicar no botão Reiniciar
	Entao EC selecionar camera "Camera test_Pipeline Test"
	Entao EC clicar no botão Proximo
	Entao EC devo estar na segunda etapa
	Entao EC selecionar Pipeline "Pipeline Test"
	Entao sistema notifica ErroCameraJaProcessada_EdicaoCameraPage
	
	Cenário: Usuario tentou aplicar pipeline duas vezes em uma camera 
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Dado que tenho uma Pipeline
	Dado que tenho uma Camera
	Entao acessar aba Edicao-Camera
	Então devo estar na pagina de edicao-Camera
	Entao EC selecionar camera "Camera test"
	Entao EC clicar no botão Proximo
	Entao EC devo estar na segunda etapa
	Entao EC selecionar Pipeline "Pipeline Test"
	Entao EC encerrar Alerta
  Entao EC devo estar na terceira etapa
	Entao EC clicar no botão Salvar
	Entao sistema notifica PipelineAplicadaEmCamera_EdicaoCameraPage
	Então EC verificar processamento salvo
	Entao EC clicar no botão Reiniciar
	Entao EC selecionar camera "Camera test"
	Entao EC clicar no botão Proximo
	Entao EC devo estar na segunda etapa
	Entao EC selecionar Pipeline "Pipeline Test"
	Entao sistema notifica ErroPipelineJaAplicadaEmCamera_EdicaoCameraPage
	
	 
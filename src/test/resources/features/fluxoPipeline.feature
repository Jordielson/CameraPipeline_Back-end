# language: pt
#enconding: UTF-8

@Geral @QuartaIteracao
Funcionalidade: fluxo de processos aplicados a uma Pipeline
	Como Usuario
	Eu quero contruir um fluxo de processamento utilizando PDIs e Pipelines aplicadas a uma Pipeline
	
	Cenário: teste
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho um PDI PDITeste1
	Dado que tenho um PDI PDITeste2
	Dado que tenho uma Pipeline p1
	Dado que tenho uma Pipeline p2
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline p1 Editar
	Então adicionar processo PDI PDITeste1
	Então sistema notifica PDITeste1AdicionadoComSucesso_Pipeline
	Então adicionar processo PDI PDITeste2
	Então sistema notifica PDITeste2AdicionadoComSucesso_Pipeline
	Então adicionar processo Pipeline p2
	Então sistema notifica Pipelinep2AdicionadaComSucesso_Pipeline
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PP Clicar botao FluxoPipeline
	Então FP reposicionar processos
	Então conectar processo 1 bottom ao 2 top
	Então conectar processo 1 bottom ao 3 top
	Então conectar processo 2 bottom ao 3 top
	Então FP salvar Fluxo

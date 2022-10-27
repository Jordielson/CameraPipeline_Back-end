# language: pt
#enconding: UTF-8

@Pipeline @Geral @TerceiraIteracao
Funcionalidade: Deve validar o CRUD de Pipeline
	Como um Usuario
	Eu quero gerenciar o CRUD de um ou mais Pipeline,
	insersao e remocao de processos(PDI & Pipeline),
	definicao de parametros de PDI's Aplicados a Pipeline.
	
	
Cenario: teste geral
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho um PDI
	Quando tentar logar
	Então acessar aba pipelines
	Então Verificar se não existe Pipeline
	Então informar nome da nova Pipeline como PipelineTeste
	Então PP Clicar botao CriarPipeline
	Então sistema notifica SucessoCriarPipeline_Pipeline
	Então editar nome Pipeline como PipelineEditTeste
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PP Desativar Pipeline
	Então PP Ativar Pipeline 
#	Então PP Clicar botao historico Pipelines 
	Então adicionar processo PDI PDITeste
	Então selecionar processo 1
	Então informar parametro string ParamTeste1 parametroTeste
	Então sistema notifica PDIAdicionadoComSucesso_Pipeline
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PP Clicar botao ExcluirPipeline
	
	
	

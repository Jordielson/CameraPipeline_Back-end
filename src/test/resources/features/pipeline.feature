# language: pt
#enconding: UTF-8

@Pipeline @Geral @TerceiraIteracao
Funcionalidade: Deve validar o CRUD de Pipeline
	Como um Usuario
	Eu quero gerenciar o CRUD de um ou mais Pipeline,
	insersao e remocao de processos(PDI & Pipeline),
	definicao de parametros de PDI's Aplicados a Pipeline.
	
	
#Cenario: teste geral
#	* remover usuario userteste1@user.com
#	Dado que existe a conta email userteste1@user.com e senha 123456
#	Dado que informei email userteste1@user.com e senha 123456
#	Dado que tenho um PDI
#	Quando tentar logar
#	Então acessar aba pipelines
#	Então Verificar se não existe Pipeline
#	Então informar nome da nova Pipeline como PipelineTeste
#	Então PP Clicar botao CriarPipeline
#	Então sistema notifica SucessoCriarPipeline_Pipeline
#	Então editar nome Pipeline como PipelineEditTeste
#	Então PP Clicar botao SalvarPipeline
#	Então sistema notifica SucessoSalvarPipeline_Pipeline
#	Então PP Desativar Pipeline
#	Então PP Ativar Pipeline 
#	Então PP Clicar botao historico Pipelines 
#	Então adicionar processo PDI PDITeste
#	Então sistema notifica PDIAdicionadoComSucesso_Pipeline
#	Então selecionar processo 1
#	Então informar parametro string ParamTeste1 parametroTeste
#	Então PP Clicar botao SalvarPipeline
#	Então sistema notifica SucessoSalvarPipeline_Pipeline
#	Então PP Clicar botao ExcluirPipeline
	
	
Funcionalidade: Deve excluir uma Pipeline
	Como um Usuario
	Eu quero excluir uma ou mais Pipelines já criadas.

Cenario: Excluir Pipeline vazia
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho uma Pipeline
	Quando tentar logar
	Então acessar aba pipelines
	Então PP Clicar botao ExcluirPipeline
	Então sistema notifica SucessoExcluirPipeline_Pipeline
	
Cenario: Excluir Pipeline com processos
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho uma Pipeline
	Dado que tenho um PDI
	Quando tentar logar
	Então acessar aba pipelines
	Então adicionar processo PDI PDITeste
	Então sistema notifica PDIAdicionadoComSucesso_Pipeline
	Então selecionar processo 1
	Então informar parametro string ParamTeste1 parametroTeste
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PP Clicar botao ExcluirPipeline
	Então sistema notifica SucessoExcluirPipeline_Pipeline
	
Cenario: Excluir Pipeline usada em outra Pipeline
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho uma Pipeline p1
	Dado que tenho uma Pipeline p2
	Dado que tenho um PDI
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline p1
	Então adicionar processo PDI PDITeste
	Então sistema notifica PDIAdicionadoComSucesso_Pipeline
	Então selecionar processo 1
	Então informar parametro string ParamTeste1 parametroTeste
	Então PP Clicar botao SalvarPipeline
	Então selecionar Pipeline p2
	Então adicionar processo Pipeline p1
#	Então sistema notifica PDIAdicionadoComSucesso_Pipeline
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PP Clicar botao ExcluirPipeline
	Então sistema notifica ErroExcluirPipelineEmUso_Pipeline
	











# language: pt
#enconding: UTF-8

@Pipeline @Geral @TerceiraIteracao
Funcionalidade: Deve validar o CRUD de Pipeline
	Como um Usuario
	Eu quero gerenciar o CRUD de um ou mais Pipeline,
	insersao e remocao de processos(PDI & Pipeline),
	definicao de parametros de PDI's Aplicados a Pipeline.
	
#Cenario: teste geral primario
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
	
Cenario: Atividar e Desativar Pipeline
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho um PDI
	Dado que tenho uma Pipeline pipelineTeste
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline pipelineTeste Ativar
	Então PPE verificar se Pipeline pipelineTeste esta Ativa
	Então selecionar Pipeline pipelineTeste Desativar
	Então PPE verificar se Pipeline pipelineTeste esta Desativada
	
	
Cenario: Criar Pipeline
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então acessar aba pipelines
	Então PP Clicar botao NovaPipeline
	Então informar nome da nova Pipeline como PipelineTeste
	Então PP Clicar botao CriarPipeline
	Então sistema notifica SucessoCriarPipeline_Pipeline
	Então fazer logoff
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então acessar aba pipelines
	Então verificar existencia de Pipeline PipelineTeste
	
	
Cenario: Editar nome Pipeline
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então acessar aba pipelines
	Então PP Clicar botao NovaPipeline
	Então informar nome da nova Pipeline como PipelineTeste
	Então PP Clicar botao CriarPipeline
	Então sistema notifica SucessoCriarPipeline_Pipeline
	Então selecionar Pipeline PipelineTeste Editar
	Então editar nome Pipeline como PipelineEditTeste
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipelin
	Então Verificar nome Pipeline PipelineEditTeste

Cenario: Recuperar versao pipeline 
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então acessar aba pipelines
	Então PP Clicar botao NovaPipeline
	Então informar nome da nova Pipeline como PipelineTeste
	Então PP Clicar botao CriarPipeline
	Então sistema notifica SucessoCriarPipeline_Pipeline
	Então selecionar Pipeline PipelineTeste Editar
	Então PPI Clicar botao historico Pipelines
	Então devo estar na pagina de HistoricoPipeline
	Então restaurar para a versao primeira
	Então Verificar nome Pipeline PipelineTeste

Cenario: Savando Processos com parametros na Pipeline e validando restauração do banco
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho um PDI PDITeste1
	Dado que tenho um PDI PDITeste2
	Dado que tenho uma Pipeline PipelineTeste
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline PipelineTeste Editar
	Então adicionar processo PDI PDITeste1
	Então sistema notifica PDITeste1AdicionadoComSucesso_Pipeline
	Então adicionar processo PDI PDITeste2
	Então sistema notifica PDITeste2AdicionadoComSucesso_Pipeline
	Então selecionar processo 1
	Então informar parametro string ParamTeste1 parametroTeste1
	Então informar parametro string ParamTeste2 123
	Então selecionar processo 2
	Então informar parametro string ParamTeste1 parametroTeste2
	Então informar parametro string ParamTeste2 321
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então fazer logoff
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline PipelineTeste Editar
	Então selecionar processo 1
	Então Verificar parametro ParamTeste1 parametroTeste1
	Então Verificar parametro ParamTeste2 123
	Então selecionar processo 2
	Então Verificar parametro ParamTeste1 parametroTeste2
	Então Verificar parametro ParamTeste2 321

Cenario: validacao de campo CriarNovaPipeline
* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então acessar aba pipelines
	Então PP Clicar botao NovaPipeline
	Então informar nome da nova Pipeline como <nome>
	Então PP Clicar botao CriarPipeline
	Então sistema notifica <notificacao>
	
	Exemplos:
	|								nome								|											notificacao											|
	|								"   "								|							ErroCriarPipeline_Pipeline							|
	|								"12345"							|							SucessoCriarPipeline_Pipeline						|
	|								" teste"						|							SucessoCriarPipeline_Pipeline						|
	|								"t3st3"							|							SucessoCriarPipeline_Pipeline						|
	|						"user@user.com"					|							SucessoCriarPipeline_Pipeline						|


Cenario: Excluir Pipeline vazia
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho uma Pipeline PipelineTeste
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline PipelineTeste Deletar
	Então sistema notifica SucessoExcluirPipeline_Pipeline
	
Cenario: Excluir Pipeline com processos
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho uma Pipeline PipelineTeste
	Dado que tenho um PDI
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline PipelineTeste Editar
	Então adicionar processo PDI PDITeste
	Então sistema notifica PDIAdicionadoComSucesso_Pipeline
	Então selecionar processo 1
	Então informar parametro string ParamTeste1 parametroTeste
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PPI Clicar botao ExcluirPipeline
	Então sistema notifica SucessoExcluirPipeline_Pipeline
	
Cenario: Excluir Pipeline usada em outra Pipeline
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho uma Pipeline p1
	Dado que tenho uma Pipeline p2
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline p1 Editar
	Então adicionar processo Pipeline p2
	Então sistema notifica Pipelinep2AdicionadaComSucesso_Pipeline
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PPI Clicar botao VoltarPipelines
	Então selecionar Pipeline p2 Editar
	Então PPI Clicar botao ExcluirPipeline
	Então sistema notifica ErroExcluirPipelineEmUso_Pipeline
	Então PPI Clicar botao VoltarPipelines
	Então selecionar Pipeline p1 Editar
	Então remover processo 1
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PPI Clicar botao VoltarPipelines
	Então selecionar Pipeline p2 Editar
	Então PPI Clicar botao ExcluirPipeline
	Então sistema notifica SucessoExcluirPipeline_Pipeline
	











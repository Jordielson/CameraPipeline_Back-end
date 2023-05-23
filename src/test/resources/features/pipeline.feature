# language: pt
#enconding: UTF-8

@Pipeline @Geral @TerceiraIteracao @QuintaI
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
	Dado que tenho um Servico
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
	Dado que tenho um Servico PDITeste1
	Dado que tenho um Servico PDITeste2
	Dado que tenho uma Pipeline PipelineTeste
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline PipelineTeste Editar
	Então adicionar processo Servico PDITeste1
	Então sistema notifica PDITeste1AdicionadoComSucesso_Pipeline
	Então adicionar processo Servico PDITeste2
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
	Dado que tenho um Servico effect
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline PipelineTeste Editar
	Então adicionar processo Servico effect
	Então sistema notifica ServicoEffectAdicionadoComSucesso_Pipeline
	Então selecionar processo 1
	Então informar parametro select effect GRAY
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
	
Cenario: Validar parametros SELECT e COLOR Aplicados a Pipeline
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho um Servico effect
	Dado que tenho um Servico color
	Dado que tenho uma Pipeline PipelineTeste
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline PipelineTeste Editar
	Então adicionar processo Servico Effect
	Então sistema notifica ServicoEffectAdicionadoComSucesso_Pipeline
	Então adicionar processo Servico Color
	Então sistema notifica ServicoColorAdicionadoComSucesso_Pipeline
	Então selecionar processo 1
	Então informar parametro select effect GRAY
	Então selecionar processo 2
	Então informar parametro string color #f00000
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então fazer logoff
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline PipelineTeste Editar
	Então selecionar processo 1
	Então Verificar select parametro Effect GRAY
	Então selecionar processo 2
	Então Verificar parametro Color #000000

Cenario: validar Imagem de pré-Visualização
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho uma Pipeline PipelineTeste
	Dado que tenho um Servico effect
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline PipelineTeste Editar
	Então adicionar processo Servico Effect
	Então sistema notifica ServicoEffectAdicionadoComSucesso_Pipeline
	Então selecionar processo 1
	Então informar parametro select effect GRAY
	Então capturar imagem de pre-visualizacao
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então confirmar alteracao de imagem de pre-visualizacao
	
Cenario: Validar campos de pesquisa Pipelines e Servicos
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho um Servico AbcTeste
	Dado que tenho um Servico AbcdTeste
	Dado que tenho um Servico AbdeTeste
	Dado que tenho um Servico BcdTeste
	Dado que tenho um Servico BacTeste
	Dado que tenho um Servico CAbTeste
	Dado que tenho uma Pipeline AbcTeste
	Dado que tenho uma Pipeline AbcdTeste
	Dado que tenho uma Pipeline AbdeTeste
	Dado que tenho uma Pipeline BcdTeste
	Dado que tenho uma Pipeline BacTeste
	Dado que tenho uma Pipeline CAbTeste
	Dado que tenho uma Pipeline PipelineTeste
	Quando tentar logar
	Então acessar aba pipelines
	Então selecionar Pipeline PipelineTeste Editar
	
	Então Pesquisar por servicos ab
	Então validar pesquisa
	|		type				 |  	servico					|	
	| 	resultado    | AbcTeste ; AbcdTeste ; AbdeTeste ; CAbTeste  |	
	
	Então Pesquisar por servicos abc
	Então validar pesquisa
	|		type				 |  	servico						|	
	| 	resultado    | AbcTeste ; AbcdTeste |	

	Então Pesquisar por servicos bde
	Então validar pesquisa
	|		type				 | servico		|	
	| 	resultado    | AbdeTeste  |	

	Então Pesquisar por servicos b
	Então validar pesquisa
	|		type				 |  	servico					|	
	| 	resultado    | AbcTeste ; AbcdTeste ; AbdeTeste ; CAbTeste ; BcdTeste ; BacTeste  |	

	Então Pesquisar por servicos AbcdTeste
	Então validar pesquisa
	|		type				 | 	servico	 |	
	| 	resultado    | AbcdTeste |	


	Então Pesquisar por pipelines ab
	Então validar pesquisa
	|		type				 |  	pipeline					|	
	| 	resultado    | AbcTeste ; AbcdTeste ; AbdeTeste ; CAbTeste  |	
	
	Então Pesquisar por pipelines abc
	Então validar pesquisa
	|		type				 |  	pipeline						|	
	| 	resultado    | AbcTeste ; AbcdTeste |	

	Então Pesquisar por pipelines bde
	Então validar pesquisa
	|		type				 | pipeline		|	
	| 	resultado    | AbdeTeste  |	

	Então Pesquisar por pipelines b
	Então validar pesquisa
	|		type				 |  	pipeline					|	
	| 	resultado    | AbcTeste ; AbcdTeste ; AbdeTeste ; CAbTeste ; BcdTeste ; BacTeste  |	

	Então Pesquisar por pipelines AbcdTeste
	Então validar pesquisa
	|		type				 | 	pipeline	 |	
	| 	resultado    | AbcdTeste |	


# language: pt
#enconding: UTF-8

@Geral @QuartaIteracao
Funcionalidade: fluxo de processos aplicados a uma Pipeline
	Como Usuario
	Eu quero contruir um fluxo de processamento utilizando PDIs e Pipelines aplicadas a uma Pipeline
	
#	Cenário: teste Inicial
#	* remover usuario userteste1@user.com
#	Dado que existe a conta email userteste1@user.com e senha 123456
#	Dado que informei email userteste1@user.com e senha 123456
#	Dado que tenho um PDI PDITeste1
#	Dado que tenho um PDI PDITeste2
#	Dado que tenho uma Pipeline p1
#	Dado que tenho uma Pipeline p2
#	Quando tentar logar
#	Então acessar aba pipelines
#	Então devo estar na pagina de HomePipeline
#	Então selecionar Pipeline p1 Editar
#	Então devo estar na pagina de Pipeline
#	Então adicionar processo PDI PDITeste1
#	Então sistema notifica PDITeste1AdicionadoComSucesso_Pipeline
#	Então adicionar processo PDI PDITeste2
#	Então sistema notifica PDITeste2AdicionadoComSucesso_Pipeline
#	Então adicionar processo Pipeline p2
#	Então sistema notifica Pipelinep2AdicionadaComSucesso_Pipeline
#	Então PP Clicar botao SalvarPipeline
#	Então sistema notifica SucessoSalvarPipeline_Pipeline
#	Então PP Clicar botao FluxoPipeline
#	Então devo estar na pagina de FluxoPipeline
#	Então FP verificar quantidade de processos 3
#	Então FP reposicionar processo 3 x0 y250
#	Então FP reposicionar processo 2 x-350 y0
#	Então FP reposicionar processo 1 x0 y-250
#	Então FP conectar processo 1 bottom ao 2 top
#	Então FP conectar processo 1 bottom ao 3 top
#	Então FP conectar processo 2 bottom ao 3 top
#	Então FP verificar quantidade de conexoes 3
#	Então FP salvar Fluxo
#	Então devo estar na pagina de Pipeline
#	Então PP Clicar botao FluxoPipeline
#	Então devo estar na pagina de FluxoPipeline
#	Então FP verificar quantidade de processos 3
#	Então FP desconectar processo 1 to 3
#	Então FP salvar Fluxo
#	Então devo estar na pagina de Pipeline
#	Então PP Clicar botao FluxoPipeline
#	Então devo estar na pagina de FluxoPipeline
#	Então FP verificar quantidade de processos 3
#	Então FP verificar quantidade de conexoes 2
#	
	Cenário: Adicionar processos em Pipeline e ordenar Fluxo
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho um PDI PDITeste1
	Dado que tenho um PDI PDITeste2
	Dado que tenho uma Pipeline p1
	Dado que tenho uma Pipeline p2
	Quando tentar logar
	Então acessar aba pipelines
	Então devo estar na pagina de HomePipeline
	Então selecionar Pipeline p1 Editar
	Então devo estar na pagina de Pipeline
	Então adicionar processo PDI PDITeste1
	Então sistema notifica PDITeste1AdicionadoComSucesso_Pipeline
	Então adicionar processo PDI PDITeste2
	Então sistema notifica PDITeste2AdicionadoComSucesso_Pipeline
	Então adicionar processo Pipeline p2
	Então sistema notifica Pipelinep2AdicionadaComSucesso_Pipeline
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar quantidade de processos 3
	Então FP reposicionar processo 3 x0 y250
	Então FP reposicionar processo 2 x-350 y0
	Então FP reposicionar processo 1 x0 y-250
	Então FP conectar processo 1 bottom ao 2 top
	Então FP conectar processo 1 bottom ao 3 top
	Então FP conectar processo 2 bottom ao 3 top
	Então FP verificar quantidade de conexoes 3
	Então FP salvar Fluxo
	Então sistema notifica FluxoSalvoSucesso_FluxoPipeline
	Então FP Voltar
	Então devo estar na pagina de Pipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar quantidade de processos 3
	Então FP verificar quantidade de conexoes 3
	
	Cenário: Editar o fluxo dos processos de uma Pipeline
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho um PDI PDITeste1
	Dado que tenho um PDI PDITeste2
	Dado que tenho uma Pipeline p1
	Dado que tenho uma Pipeline p2
	Quando tentar logar
	Então acessar aba pipelines
	Então devo estar na pagina de HomePipeline
	Então selecionar Pipeline p1 Editar
	Então devo estar na pagina de Pipeline
	Então adicionar processo PDI PDITeste1
	Então sistema notifica PDITeste1AdicionadoComSucesso_Pipeline
	Então adicionar processo PDI PDITeste2
	Então sistema notifica PDITeste2AdicionadoComSucesso_Pipeline
	Então adicionar processo Pipeline p2
	Então sistema notifica Pipelinep2AdicionadaComSucesso_Pipeline
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar quantidade de processos 3
	Então FP reposicionar processo 3 x0 y250
	Então FP reposicionar processo 2 x-350 y0
	Então FP reposicionar processo 1 x0 y-250
	Então FP conectar processo 1 bottom ao 2 top
	Então FP conectar processo 1 bottom ao 3 top
	Então FP verificar quantidade de conexoes 2
	Então FP salvar Fluxo
	Então sistema notifica FluxoSalvoSucesso_FluxoPipeline
	Então FP Voltar
	Então devo estar na pagina de Pipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP reposicionar processo 3 x-350 y0
	Então FP reposicionar processo 2 x700 y0
	Então FP reposicionar processo 1 x0 y0
	Então FP conectar processo 2 bottom ao 3 top
	Então FP salvar Fluxo
	Então sistema notifica FluxoSalvoSucesso_FluxoPipeline
	Então FP Voltar
	Então devo estar na pagina de Pipeline
	Então PP Clicar botao FluxoPipeline
	Então FP verificar quantidade de processos 3
	Então FP verificar quantidade de conexoes 3
	
	
	Cenário: Excluir uma conexao do fluxo de uma pipeline
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho um PDI PDITeste1
	Dado que tenho um PDI PDITeste2
	Dado que tenho uma Pipeline p1
	Dado que tenho uma Pipeline p2
	Quando tentar logar
	Então acessar aba pipelines
	Então devo estar na pagina de HomePipeline
	Então selecionar Pipeline p1 Editar
	Então devo estar na pagina de Pipeline
	Então adicionar processo PDI PDITeste1
	Então sistema notifica PDITeste1AdicionadoComSucesso_Pipeline
	Então adicionar processo PDI PDITeste2
	Então sistema notifica PDITeste2AdicionadoComSucesso_Pipeline
	Então adicionar processo Pipeline p2
	Então sistema notifica Pipelinep2AdicionadaComSucesso_Pipeline
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar quantidade de processos 3
	Então FP reposicionar processo 3 x0 y250
	Então FP reposicionar processo 2 x-350 y0
	Então FP reposicionar processo 1 x0 y-250
	Então FP conectar processo 1 bottom ao 2 top
	Então FP conectar processo 1 bottom ao 3 top
	Então FP conectar processo 2 bottom ao 3 top
	Então FP verificar quantidade de conexoes 3
	Então FP salvar Fluxo
	Então sistema notifica FluxoSalvoSucesso_FluxoPipeline
	Então FP Voltar
	Então devo estar na pagina de Pipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar quantidade de processos 3
	Então FP desconectar processo 1 to 3
	Então FP salvar Fluxo
	Então sistema notifica FluxoSalvoSucesso_FluxoPipeline
	Então FP Voltar
	Então devo estar na pagina de Pipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar quantidade de processos 3
	Então FP verificar quantidade de conexoes 2
	
	
	Cenário: Recuperar Fluxo ao Recuperar Historico
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho um PDI PDITeste1
	Dado que tenho um PDI PDITeste2
	Dado que tenho uma Pipeline p1
	Dado que tenho uma Pipeline p2
	Quando tentar logar
	Então acessar aba pipelines
	Então devo estar na pagina de HomePipeline
	Então selecionar Pipeline p1 Editar
	Então devo estar na pagina de Pipeline
	Então adicionar processo PDI PDITeste1
	Então sistema notifica PDITeste1AdicionadoComSucesso_Pipeline
	Então adicionar processo PDI PDITeste2
	Então sistema notifica PDITeste2AdicionadoComSucesso_Pipeline
	Então adicionar processo Pipeline p2
	Então sistema notifica Pipelinep2AdicionadaComSucesso_Pipeline
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar quantidade de processos 3
	Então FP reposicionar processo 3 x0 y250
	Então FP reposicionar processo 2 x-350 y0
	Então FP reposicionar processo 1 x0 y-250
	Então FP conectar processo 1 bottom ao 2 top
	Então FP conectar processo 2 bottom ao 3 top
	Então FP verificar quantidade de conexoes 2
	Então FP salvar Fluxo
	Então sistema notifica FluxoSalvoSucesso_FluxoPipeline
	Então FP Voltar
	Então devo estar na pagina de Pipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar quantidade de processos 3
	Então FP conectar processo 1 bottom ao 3 top
	Então FP desconectar processo 2 to 3
	Então FP verificar conexao 2 to 3 is false
	Então FP verificar conexao 1 to 3 is true
	Então FP salvar Fluxo
	Então sistema notifica FluxoSalvoSucesso_FluxoPipeline
	Então FP Voltar
	Então devo estar na pagina de Pipeline
	Então PPI Clicar botao historico Pipelines
	Então devo estar na pagina de HistoricoPipeline
	Então restaurar para a versao 2
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar conexao 2 to 3 is true
	Então FP verificar conexao 1 to 3 is false
	Então FP verificar quantidade de processos 3
	Então FP verificar quantidade de conexoes 2
	
	Cenário: Atualizar Fluxo ao Remover um Processo
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho um PDI PDITeste1
	Dado que tenho um PDI PDITeste2
	Dado que tenho uma Pipeline p1
	Dado que tenho uma Pipeline p2
	Quando tentar logar
	Então acessar aba pipelines
	Então devo estar na pagina de HomePipeline
	Então selecionar Pipeline p1 Editar
	Então devo estar na pagina de Pipeline
	Então adicionar processo PDI PDITeste1
	Então sistema notifica PDITeste1AdicionadoComSucesso_Pipeline
	Então adicionar processo PDI PDITeste2
	Então sistema notifica PDITeste2AdicionadoComSucesso_Pipeline
	Então adicionar processo Pipeline p2
	Então sistema notifica Pipelinep2AdicionadaComSucesso_Pipeline
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar quantidade de processos 3
	Então FP reposicionar processo 3 x0 y250
	Então FP reposicionar processo 2 x-350 y0
	Então FP reposicionar processo 1 x0 y-250
	Então FP conectar processo 1 bottom ao 2 top
	Então FP conectar processo 1 bottom ao 3 top
	Então FP conectar processo 2 bottom ao 3 top
	Então FP verificar quantidade de conexoes 3
	Então FP salvar Fluxo
	Então sistema notifica FluxoSalvoSucesso_FluxoPipeline
	Então FP Voltar
	Então devo estar na pagina de Pipeline
	Então remover processo 3
	Então PP Clicar botao SalvarPipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar quantidade de processos 2

	Cenário: Sair Sem Salvar Fluxo
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho um PDI PDITeste1
	Dado que tenho um PDI PDITeste2
	Dado que tenho uma Pipeline p1
	Dado que tenho uma Pipeline p2
	Quando tentar logar
	Então acessar aba pipelines
	Então devo estar na pagina de HomePipeline
	Então selecionar Pipeline p1 Editar
	Então devo estar na pagina de Pipeline
	Então adicionar processo PDI PDITeste1
	Então sistema notifica PDITeste1AdicionadoComSucesso_Pipeline
	Então adicionar processo PDI PDITeste2
	Então sistema notifica PDITeste2AdicionadoComSucesso_Pipeline
	Então adicionar processo Pipeline p2
	Então sistema notifica Pipelinep2AdicionadaComSucesso_Pipeline
	Então PP Clicar botao SalvarPipeline
	Então sistema notifica SucessoSalvarPipeline_Pipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar quantidade de processos 3
	Então FP reposicionar processo 3 x0 y250
	Então FP reposicionar processo 2 x-350 y0
	Então FP reposicionar processo 1 x0 y-250
	Então FP conectar processo 1 bottom ao 2 top
	Então FP conectar processo 1 bottom ao 3 top
	Então FP conectar processo 2 bottom ao 3 top
	Então FP verificar quantidade de conexoes 3
	Então FP salvar Fluxo
	Então sistema notifica FluxoSalvoSucesso_FluxoPipeline
	Então FP Voltar
	Então devo estar na pagina de Pipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar quantidade de processos 3
	Então FP desconectar processo 1 to 3
	Então FP desconectar processo 2 to 3
	Então FP Voltar
	Então devo estar na pagina de Pipeline
	Então PP Clicar botao FluxoPipeline
	Então devo estar na pagina de FluxoPipeline
	Então FP verificar quantidade de processos 3
	Então FP verificar quantidade de conexoes 3
	
	
	
	

# language: pt
#enconding: UTF-8

@Servicos @Geral
Funcionalidade: Deve validar o CRUD de Servico
	Como um Usuario
	Eu quero gerenciar o CRUD de um ou mais Servicos
	
	Cenário: Validação CRUD Servico
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então acessar aba Servicos
	Quando CRUD-Servicos clicar no botão adicionarServico
	Quando CRUD-Servicos clicar no botão novoparametro
	Quando CRUD-Servicos clicar no botão novoparametro
	Então editar nome Servico teste
	Então editar URL teste
	Então editar descricao descricao teste
	Então editar parametro 
		| posicao 	 | 1			|
		| nome 			 | teste1 |
		| tipo 			 | number |
		| obrigatorio| sim		|
		| descricao	 | DTeste1|
		
	Então editar parametro
		| posicao 	 | 2 			| 
		| nome 			 | teste2 |
		| tipo 			 | string |
		| obrigatorio| nao		|
		| descricao	 | DTeste2|

	Quando CRUD-Servicos clicar no botão salvarServico
	Então sistema notifica parametrosalvo_ServicoPage
	Então deletar Servico todos 

Cenário: Validação parametro 
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então acessar aba Servicos
	Quando CRUD-Servicos clicar no botão adicionarServico
	Então editar nome Servico teste
	Então editar URL teste
	Quando CRUD-Servicos clicar no botão novoparametro
	Então confirmar que um parametro foi adicionado
	
	Então editar parametro 
		| posicao 	 | 1			|
		| nome 			 | teste1 |
		| tipo 			 | string |
		| obrigatorio| sim		|
		| descricao	 | DTeste1|
		
	Quando CRUD-Servicos clicar no botão salvarServico
	Então sistema notifica parametrosalvo_ServicoPage
	Então clicar editar servico teste
	
	Então confirmar atributos do parametro
		| posicao 	 | 1			|
		| nome 			 | teste1 |
		| tipo 			 | string |
		| obrigatorio| sim		|
		| descricao	 | DTeste1|
		
	Então deletar parametro 1
	Então confirmar que um parametro foi removido
	Quando CRUD-Servicos clicar no botão fecharCardServico
	Então deletar Servico todos 
	
	
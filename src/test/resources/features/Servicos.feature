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
	Então verificar qtdd servicos 0

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
	Então verificar qtdd servicos 0
	
	Cenário: Validação parametro Select - Color
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então acessar aba Servicos
	Quando CRUD-Servicos clicar no botão adicionarServico
	Então editar nome Servico Effect
	Então editar URL http://127.0.0.1:5000/api/apply-effect
	Quando CRUD-Servicos clicar no botão novoparametro
	Então confirmar que um parametro foi adicionado
	
	Então editar parametro 
		| posicao 	 | 1			|
		| nome 			 | effect |
		| tipo 			 | select |
		| obrigatorio| sim		|
		| descricao	 | DTeste1|
		| opcoes   	 | effect ; secondary |
		
	Quando CRUD-Servicos clicar no botão salvarServico
	Então sistema notifica servicosalvo_ServicoPage
	Então verificar qtdd servicos 1
	
	Quando CRUD-Servicos clicar no botão adicionarServico
	Então editar nome Servico Color
	Então editar URL http://127.0.0.1:5000/api/color
	Quando CRUD-Servicos clicar no botão novoparametro
	Então confirmar que um parametro foi adicionado
	
	Então editar parametro 
		| posicao 	 | 1			|
		| nome 			 | color	|
		| tipo 			 | color 	|
		| obrigatorio| sim		|
		| descricao	 | DTeste1|
		
	Quando CRUD-Servicos clicar no botão salvarServico
	Então sistema notifica servicosalvo_ServicoPage
	Então verificar qtdd servicos 2


	Cenário: Validação modal excluir serviço
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho um Servico effect
	Quando tentar logar
	Então acessar aba Servicos
	Então verificar qtdd servicos 1
	Então deletar Servico effect
	Então verificar qtdd servicos 0
	
# language: pt
#enconding: UTF-8

@PDI @Geral
Funcionalidade: Deve validar o CRUD de PDIs
	Como um Usuario
	Eu quero gerenciar o CRUD de um ou mais PDIs
	
Cenário: teste
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então acessar aba PDIs
	Quando CRUD-PDI clicar no botão adicionarPDI
	Quando CRUD-PDI clicar no botão novoparametro
	Quando CRUD-PDI clicar no botão novoparametro
	Então editar nome PDI teste
	Então editar URL teste
	Então editar descricao descricao teste
	Então editar parametro 
		| posicao 	 | 1			|
		| nome 			 | teste1 |
		| tipo 			 | string |
		| obrigatorio| sim		|
		
	Então editar parametro
		| posicao 	 | 2 			| 
		| nome 			 | teste2 |
		| tipo 			 | string |
		| obrigatorio| nao		|
	Quando CRUD-PDI clicar no botão salvarPDI
	Então sistema notifica parametrosalvo_PDIPage
	Então deletar PDI todos 

Cenário: Validação parametro 
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então acessar aba PDIs
	Quando CRUD-PDI clicar no botão adicionarPDI
	Então editar nome PDI teste
	Então editar URL teste
	Quando CRUD-PDI clicar no botão novoparametro
	Então confirmar que um parametro foi adicionado
	Então editar parametro 
		| posicao 	 | 1			|
		| nome 			 | teste1 |
		| tipo 			 | string |
		| obrigatorio| sim		|
	Então confirmar atributos do parametro
		| posicao 	 | 1			|
		| nome 			 | teste1 |
		| tipo 			 | string |
		| obrigatorio| sim		|
	Então deletar parametro 1
	Então confirmar que um parametro foi removido
	Quando CRUD-PDI clicar no botão fecharCardPDI
	Então deletar PDI todos 
	
	
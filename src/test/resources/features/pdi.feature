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
	Então editar parametro 
		| posicao 	 | 1			|
		| nome 			 | teste1 |
		| tipo 			 | string |
		| obrigatorio| sim		|
		
	Então editar parametro
		| posicao 	 | 2 			| 
		| nome 			 | teste2 |
		| tipo 			 | number |
		| obrigatorio| nao		|
	Quando CRUD-PDI clicar no botão salvarPDI
	Então sistema notifica parametrosalvo_PDIPage
	
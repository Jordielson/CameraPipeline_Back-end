# language: pt
#enconding: UTF-8

@Alterarsenha @Geral
Funcionalidade: Deve validar a alteração de senha
	Como um Usuario 
	Eu quero acessar a aplicação
	Para alterar a minha senha de login

Esquema do Cenário: Validar campos da funcionalidade de alteração de senha
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	E acessei a aba AlterarSenha
	E passei senha atual <senhaatual>
	E passei nova senha <novasenha>
	E passei consfirmar nova senha <confirmarnovasenha>
	Quando alterar
	Então sistema notifica <erro>
	
	Exemplos:
	|			senhaatual			| 		novasenha			|			confirmarnovasenha		|				  		erro								|
	|											|										|														|camposenhaatual_Alterarsenha 		|
	|				"      "			|				123456			|						123456					|errotentativa_Alterarsenha				|
	|				12345					|				123456			|						123456					|errotentativa_Alterarsenha				|
	|				654321				|										|														|camponovasenha_Alterarsenha 			|
	|				123456				|										|						123456					|camponovasenha_Alterarsenha			|
	|				123456				|				123456			|														|campoconfirmarsenha_Alterarsenha	|
	|				123456				|				123456			|						123456					|errosenhaigual_Alterarsenha			|
	|				123456				|			 "      "			|					 "      "					|espacosnovasenha_Alterarsenha		|
	|				123456				|				123456			|						654321					|campoconfirmarsenha_Alterarsenha	|
	|				123456				|				12345				|						12345 					|camponovasenha_Alterarsenha			|
	|				123456				|				654321			|						654321 					|senhaalterada_Alterarsenha				|
	
	
Cenário: Alterar a senha com sucesso
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	E acessei a aba AlterarSenha
	E passei senha atual 123456
	E passei nova senha 654321
	E passei consfirmar nova senha 654321
	Quando alterar
	Então sistema notifica senhaalterada_Alterarsenha
	Quando sair 
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então sistema notifica form_login
	Dado que informei email userteste1@user.com e senha 654321
	Quando tentar logar
	Então devo estar na pagina Pipeline
	Então remover usuario userteste1@user.com
	
	
	
	
	
	
	
	
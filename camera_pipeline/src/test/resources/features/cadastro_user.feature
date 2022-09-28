# language: pt
#enconding: UTF-8

# Erros email = campoemail|formatocampoemail
#|camposenha|campoconfirmarsenha|emailexistente|conexao|outro

@CadastroUser @Geral
Funcionalidade: Deve validar o cadastro de um usuario
	Como usuario não cadastrado
	Eu quero me cadastrar com email e senha
	Para ter acesso a plataforma 
	
	
Cenário: funcionalidade dos botões
	Quando clicar no botão voltar
	Então devo estar na pagina Login
	Quando clicar no botão Cadastrar
	Então devo estar na pagina CadastroUser
	Quando clicar no botão Guia
	Então devo estar na pagina Guia
	Quando clicar no botão CameraPipeline
	Então devo estar na pagina Login
	
	
Esquema do Cenário: Deve validar email para cadastro
	Dado que eu<condicao> passe o email <email>
	Quando cadastrar
	Então sistema notifica <erro>

Exemplos:
	| condicao | 					email				 	 | 								 erro 							  |
	|  " não"	 |												 |			campoemail_Cadastrousuario			|
	| 				 |			user@gmail.com		 |			camposenha_Cadastrousuario			|
	| 	 			 |		user.user@gmail.com	 |			camposenha_Cadastrousuario			|
	| 	 			 |	user.user@gmail.com.br |			camposenha_Cadastrousuario			|
	| 	 			 |user.user@gmail.com.br.br|			camposenha_Cadastrousuario			|
	| 	 			 |		user@user@gmail.com	 |	formatocampoemail_Cadastrousuario		|
	| 	 			 |		user#user@gmail.com	 |	formatocampoemail_Cadastrousuario		|
	| 	 			 |		user.user@gmail.		 |	formatocampoemail_Cadastrousuario		|
	| 	 			 |		user.user@gmailcom	 |	formatocampoemail_Cadastrousuario		|
	| 	 			 |		user.user@123456		 |	formatocampoemail_Cadastrousuario		|
	| 	 			 |		user.user@12345.12	 |	formatocampoemail_Cadastrousuario		|
	| 	 			 |			 @gmail.com		     |	formatocampoemail_Cadastrousuario		|
	
	
Cenário: usuario não confirmou a senha
	Dado que eu passe o email userteste2@user.com
	E passe a senha "senha2"
	E não passe confirme a senha
	Quando cadastrar 
	Então sistema notifica campoconfirmarsenha_Cadastrousuario	
	
Esquema do Cenário: usuario digita senhas diferentes
	* remover usuario userteste2@user.com
	Dado que eu passe o email userteste2@user.com
	E passe a senha <senha>
	E passe confirme a senha <confirmsenha>
	Quando cadastrar
	Então sistema notifica campoconfirmarsenha_Cadastrousuario
	
	Exemplos:
	|    senha  		  |   		confirmsenha    	| 
	| 	"senha123"		| 			"SENHA123"				|
	| 	"senha123"		| 			"Senha123"				|
	|		"123456"			|				"1234568"					|
	|		"senha10"			|				"senha(5+5)"			|
	|		"senha10"			|				"senha{5+5}"			|
	
	
Cenário: Cadastro de um usuario com sucesso
	* remover usuario userteste2@user.com
	Dado que eu passe o email userteste2@user.com
	E passe a senha "senha2"
	E passe confirme a senha "senha2"
	Quando cadastrar 
	Então sistema notifica contacriada_Cadastrousuario
	Então usuario userteste2@user.com deve estar no banco
 	Então remover usuario userteste2@user.com

	
Cenário: Email informado já cadastrado
	* remover usuario userteste2@user.com
	Dado que existe a conta email userteste2@user.com e senha "senha2"
	Dado que eu passe o email userteste2@user.com
	E passe a senha "senha2"
	E passe confirme a senha "senha2"
	Quando cadastrar 
	Então sistema notifica emailexistente_Cadastrousuario
	Então usuario userteste2@user.com deve estar no banco
	Então remover usuario userteste2@user.com



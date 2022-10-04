# language: pt

@Login @Geral
Funcionalidade: Realizar Login
	Como um usuario
	Eu quero realizar login
	Para acessar a plataforma
	
Cenário: Deve relaizar login com sucesso
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então devo estar na pagina de Pipeline
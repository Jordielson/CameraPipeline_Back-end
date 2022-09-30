# language: pt

@Login
Funcionalidade: Realizar Login
	Como um usuario
	Eu quero realizar login
	Para acessar a plataforma
	
Cenário: Deve relaizar login com sucesso
	Dado que existe um usuario cadastrado com o email "joao@gmail.com"
	E a senha é "123456"
	Quando logar com email "joao@gmail.com" e senha "123456"
	Então o login deve ser realizado com sucesso
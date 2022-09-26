# language: pt
#enconding: UTF-8

@CadastroUser
Funcionalidade: Deve validar o cadastro de um usuario
	Como usuario não cadastrado
	Eu quero me cadastrar com email e senha
	Para ter acesso a plataforma 
	
Cenário: Deve realizar o cadastro de um usuario com sucesso
	Dado que eu passe o email "userteste1@user.com" 
	E passe a senha "senha1"
	E passe confirme a senha "senha1"
	Quando cadastrar 
	Então a conta de usuario deve ser criada
	

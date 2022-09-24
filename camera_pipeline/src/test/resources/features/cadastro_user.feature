# language: pt
#enconding: UTF-8

@CadastroUser
Funcionalidade: Deve validar o cadastro de um usuario
	Como usuario não cadastrado
	Eu quero me cadastrar com email e senha
	Para ter acesso a plataforma 
	
#Cenário: Cadastro de um usuario com sucesso
#	Dado que eu passe o email "userteste2@user.com" 
#	E passe a senha "senha2"
#	E passe confirme a senha "senha2"
#	Quando cadastrar 
#	Então a conta de usuario deve ser criada
#	Então usuario deve estar no banco

#Cenário: usuario não informou o email
#	Dado que eu não passe o email
#	E passe a senha "senha2"
#	E passe confirme a senha "senha2"
#	Quando cadastrar
#	Então sistema aponta erro "campoemail"
	
#Cenário: Cadastro de um usuario com sucesso
#	Dado que eu não passe o email "userteste2@user.com" 
#	E passe a senha "senha2"
#	E passe confirme a senha "senha2"
#	Quando cadastrar 
#	Então sistema aponta erro campoemail
#	Então usuario não deve estar no banco
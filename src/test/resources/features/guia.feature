# language: pt
#enconding: UTF-8

@PaginaGuia @Geral
Funcionalidade: Deve validar usabilidade da tela Guia
	Como um Usuario
	Eu quero interagir com aplicação 
	Para acessar conteudo da pagina de Guia
	
Cenário: Usuario alterna entre as abas sem estar logado
	* clicar no botão Guia
	Dado que cliquei na aba <aba>
	Então <condicao>vejo o conteudo <conteudo>
	
	Exemplos:
	|condicao|			aba			 	|			conteudo				|
	|				 |visão_geral		 	|visão_geral					|
	|				 |pipelines			 	|pipelines						|
	|				 |fluxoDaPipeline	|fluxoDaPipeline			|
	|				 |aplicarProcessos|aplicarProcessos			|
	|	"não " |visão_geral		 	|aplicarProcessos			|
	|	"não " |pipelines			 	|fluxoDaPipeline			|
	|	"não " |fluxoDaPipeline	|pipelines						|
	|	"não " |aplicarProcessos|visão_geral					|
	
Cenário: Usuario alterna entre as abas Logado
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Quando clicar logado no botão Guia
	Dado que cliquei na aba <aba>
	Então <condicao>vejo o conteudo <conteudo>
	
		Exemplos:
	|condicao|			aba			 	|			conteudo				|
	|				 |visão_geral		 	|visão_geral					|
	|				 |pipelines			 	|pipelines						|
	|				 |fluxoDaPipeline	|fluxoDaPipeline			|
	|				 |aplicarProcessos|aplicarProcessos			|
	|	"não " |visão_geral		 	|aplicarProcessos			|
	|	"não " |pipelines			 	|fluxoDaPipeline			|
	|	"não " |fluxoDaPipeline	|pipelines						|
	|	"não " |aplicarProcessos|visão_geral					|
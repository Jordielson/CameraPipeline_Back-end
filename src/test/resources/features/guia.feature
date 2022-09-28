# language: pt
#enconding: UTF-8

@PaginaGuia @Geral
Funcionalidade: Deve validar usabilidade da tela Guia
	Como um Usuario
	Eu quero interagir com aplicação 
	Para acessar conteudo da pagina de Guia
	
Cenário: Usuario alterna entre as abas
	* clicar no botão Guia
	Dado que cliquei na aba <aba>
	Então <condicao>vejo o conteudo <conteudo>
	
	Exemplos:
	|condicao|			aba			 |			conteudo			|
	|				 |visão_geral		 |visão_geral					|
	|				 |pipelines			 |pipelines						|
	|				 |cameras				 |cameras							|
	|				 |mosaico		 		 |mosaico							|
	|	"não " |visão_geral		 |mosaico							|
	|	"não " |pipelines			 |cameras							|
	|	"não " |cameras				 |pipelines						|
	|	"não " |mosaico		 		 |visão_geral					|
	
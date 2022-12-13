package steps;

import static config.ConfigInit.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.ServicosPage;
import pages.SideBar;

public class ServicosStep extends MainSteps {
	
	private int qtddparametros;


	@Quando("^CRUD-Servicos clicar no botão (.*)$")
	public void crudServicoClicarNoBotão(String value) {

		switch (value) {
		case "adicionarServico":

			Na(ServicosPage.class).clicarBotaoAdicionarNovoServico();
			break;

		case "novoparametro":

			Na(ServicosPage.class).clicarBotaoNovoParametro();
			qtddparametros += 1;
			break;

		case "salvarServico":

			Na(ServicosPage.class).clicarBotaoSalvar();
			break;
			
		case "fecharCardServico":

			Na(ServicosPage.class).clicarBotaoFecharCardServico();
			break;

		default:
			break;
		}
	}
	
	@Então("acessar aba Servicos")
	public void acessarAbaServicos() {
	    Na(SideBar.class).clickAbaPDIs();
	}
	
	@Então("^clicar editar servico (.*)$")
	public void clickBotaoEditarServico(String value) {
		Na(ServicosPage.class).clickBotaoEditarServico(value);
	}


	@Então("^editar nome Servico (.*)$")
	public void editarNomeServico(String nome) {
		Na(ServicosPage.class).inserirCampoNomeServico(nome);
	}

	@Então("^editar URL (.*)$")
	public void editarURLTeste(String url) {
		Na(ServicosPage.class).inserirCampoURL(url);
	}
	
	@Então("^editar descricao (.*)$")
	public void editarDescricaoTeste(String descricao) {
		Na(ServicosPage.class).inserirCampoDescricaoServico(descricao);
	}
	
	
	
	@Então("^deletar parametro (.*)$")
	public void deletarParametro(String posicao) {
		Na(ServicosPage.class).ClicarDeletarParametro(Integer.parseInt(posicao));
		qtddparametros -= 1;
	}
	
	@Então("^verificar qtdd servicos (.*)$")
	public void verificarQTDDServicos(String qtdd) {
		esperar(1);
		assertEquals(Integer.parseInt(qtdd), Na(ServicosPage.class).getTotalServicos());
	}
	
	@Então("^deletar Servico (.*)$")
	public void deletarServico(String value) {
		
		switch (value) {
		case "todos":
			
			Na(ServicosPage.class).deletarTodosServicos();
			break;

		default:
			
			Na(ServicosPage.class).deletarServicoPorNome(value);
			break;
		}
	}
	
	@Então("^confirmar que um parametro foi (.*)?$")
	public void confirmarQueUmParametroFoiAdicionado(String status) {
		int qtdparametrosNaTela =  Na(ServicosPage.class).getTotalParametros();
		assertTrue(qtddparametros == qtdparametrosNaTela);
	}

	@Então("^editar parametro$")
	public void editarParametro(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		int posicao = Integer.parseInt(map.get("posicao"));
		
		if(posicao > 0 && posicao <= Na(ServicosPage.class).getTotalParametros()){
			
			if(!map.get("nome").isEmpty()) {
				
				Na(ServicosPage.class).inserirCampoNomeParametro(
						posicao,
						map.get("nome"));
				
			}if(!map.get("tipo").isEmpty()) {
				
				Na(ServicosPage.class).selecionarTipoParametro(
						posicao, 
						map.get("tipo").equals("string") ? 0:
							map.get("tipo").equals("number") ? 1 :
								map.get("tipo").equals("boolean") ? 2 :
									map.get("tipo").equals("file") ? 3 :
										map.get("tipo").equals("select") ? 4 :
											map.get("tipo").equals("color") ? 5 : null);
			}
			
			if(!map.get("obrigatorio").isEmpty()) { 
				
				Na(ServicosPage.class).setObrigatoriedadeDoParametro(
						posicao,
						map.get("obrigatorio").equals("sim")? true: false);
				
			}
			
			if(!map.get("descricao").isEmpty()) { 
				
				Na(ServicosPage.class).InserirDescricaoParametro(
						posicao,
						map.get("descricao"));
			}
			
		}
		
		esperar(2);
	}

	@Então("confirmar atributos do parametro")
	public void confirmarAtributosDoParametro(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		int posicao = Integer.parseInt(map.get("posicao"));
		
		String nomeRecuperado = "";
		String tipoRecuperado = "";
		String obrigatoriedadeRecuperada = "";
		String descricaoRecuperada = "";
		
		if(posicao > 0 && posicao <= Na(ServicosPage.class).getTotalParametros()){
			
			if(!map.get("nome").isEmpty()) {
				
				nomeRecuperado = Na(ServicosPage.class).getNomeParametro(posicao);
						
				
			}if(!map.get("tipo").isEmpty()) {
				
				int temp = Na(ServicosPage.class).getTipoParametro(posicao);
				tipoRecuperado = temp==0? "string" : "number";
			}
			
			if(!map.get("obrigatorio").isEmpty()) { 
				
				boolean temp = Na(ServicosPage.class).getObrigatoriedadeDoParametro(posicao);
				obrigatoriedadeRecuperada = temp? "sim": "nao";
				
			}
			
			if(!map.get("descricao").isEmpty()) { 
				
				descricaoRecuperada = Na(ServicosPage.class).getDescricaoParametro(posicao);
				
			}
			
		assertEquals(nomeRecuperado, map.get("nome"));
		assertEquals(tipoRecuperado, map.get("tipo"));
		assertEquals(obrigatoriedadeRecuperada, map.get("obrigatorio"));
		assertEquals(descricaoRecuperada, map.get("descricao"));
			
		}

		esperar(2);
		
	}

}

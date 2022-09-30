package steps;

import static config.ConfigInit.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.PDIsPage;
import pages.SideBar;

public class PDIsStep extends MainSteps {
	
	private int ParametrosAdicionados;

	@Quando("^CRUD-PDI clicar no botão (.*)$")
	public void crudPDIClicarNoBotão(String value) {

		switch (value) {
		case "adicionarPDI":

			Na(PDIsPage.class).clicarBotaoAdicionarNovoPDI();
			break;

		case "novoparametro":

			Na(PDIsPage.class).clicarBotaoNovoParametro();
			ParametrosAdicionados += 1;
			break;

		case "salvarPDI":

			Na(PDIsPage.class).clicarBotaoSalvar();
			break;

		default:
			break;
		}
	}
	
	@Então("acessar aba PDIs")
	public void acessarAbaPDIs() {
	    Na(SideBar.class).clickAbaPDIs();
	}


	@Então("^editar nome PDI (.*)$")
	public void editarNomePDI(String nome) {
		Na(PDIsPage.class).inserirCampoNomePDI(nome);
	}

	@Então("^editar URL (.*)$")
	public void editarURLTeste(String url) {
		Na(PDIsPage.class).inserirCampoURL(url);
	}
	
	@Então("^deletar parametro (.*)$")
	public void deletarParametro(String posicao) {
		Na(PDIsPage.class).ClicarDeletarParametro(Integer.parseInt(posicao));
		ParametrosAdicionados -= 1;
	}
	
	@Então("^deletar PDI (.*)$")
	public void deletarPDI(String posicao) {
		
		switch (posicao) {
		case "todos":
			
			Na(PDIsPage.class).deletarTodosPDIs();
			
			break;

		default:
			break;
		}
	}
	
	@Então("^confirmar que um parametro foi (.*)?$")
	public void confirmarQueUmParametroFoiAdicionado(String status) {
		int qtdparametrosNaTela =  Na(PDIsPage.class).getTotalElements();
		assertTrue(ParametrosAdicionados == qtdparametrosNaTela);
	}

	@Então("^editar parametro$")
	public void editarParametro(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		int posicao = Integer.parseInt(map.get("posicao"));
		
		if(posicao > 0 && posicao <= Na(PDIsPage.class).getTotalElements()){
			
			if(!map.get("nome").isEmpty()) {
				
				Na(PDIsPage.class).inserirCampoNomeParametro(
						posicao,
						map.get("nome"));
				
			}if(!map.get("tipo").isEmpty()) {
				
				Na(PDIsPage.class).selecionarTipoParametro(
						posicao, 
						map.get("tipo").equals("string") ? 0:
							map.get("tipo").equals("number") ? 1 : null );
			}
			
			if(!map.get("obrigatorio").isEmpty()) { 
				
				Na(PDIsPage.class).setObrigatoriedadeDoParametro(
						posicao,
						map.get("obrigatorio").equals("sim")? true: false);
				
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
		
		if(posicao > 0 && posicao <= Na(PDIsPage.class).getTotalElements()){
			
			if(!map.get("nome").isEmpty()) {
				
				nomeRecuperado = Na(PDIsPage.class).getNomeParametro(posicao);
						
				
			}if(!map.get("tipo").isEmpty()) {
				
				int temp = Na(PDIsPage.class).getTipoParametro(posicao);
				tipoRecuperado = temp==0? "string" : "number";
			}
			
			if(!map.get("obrigatorio").isEmpty()) { 
				
				boolean temp = Na(PDIsPage.class).getObrigatoriedadeDoParametro(posicao);
				obrigatoriedadeRecuperada = temp? "sim": "nao";
				
			}
			
		assertEquals(nomeRecuperado, map.get("nome"));
		assertEquals(tipoRecuperado, map.get("tipo"));
		assertEquals(obrigatoriedadeRecuperada, map.get("obrigatorio"));
			
		}

		esperar(2);
		
	}

}

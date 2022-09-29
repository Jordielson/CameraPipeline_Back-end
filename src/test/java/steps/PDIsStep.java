package steps;

import static config.ConfigInit.*;

import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.PDIsPage;
import pages.SideBar;

public class PDIsStep extends MainSteps {

	@Quando("^CRUD-PDI clicar no botão (.*)$")
	public void crudPDIClicarNoBotão(String value) {

		switch (value) {
		case "adicionarPDI":

			Na(PDIsPage.class).clicarBotaoAdicionarNovoPDI();
			break;

		case "novoparametro":

			Na(PDIsPage.class).clicarBotaoNovoParametro();
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
				if(map.get("obrigatorio").equals("sim")) {
					Na(PDIsPage.class).ClicarObrigatoriedadeParametro(posicao);
				}
			}
			
		}
		
		esperar(2);
	}

}

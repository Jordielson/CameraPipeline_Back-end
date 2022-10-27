package steps;

import static config.ConfigInit.*;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.pt.Então;
import pages.PipelinePage;
import pages.SideBar;

public class PipelineStep extends MainSteps{
	
	@Então("acessar aba pipelines")
	public void acessarAbaPipelines() {
	    Na(SideBar.class).clickAbaPipeline();
	}

	@Então("Verificar se não existe Pipeline")
	public void verificarSeNãoExistePipeline() {
	    boolean resp = Na(PipelinePage.class).isTextoPadraoPresente();
	    assertTrue(resp);
	}
	
	@Então("^informar nome da nova Pipeline como (.*)$")
	public void informarNomeDaNovaPipelineComo(String value) {
	    Na(PipelinePage.class).inserirCampoNomeCriarPipeline(value);
	}
	
	@Então("PP Clicar botao CriarPipeline")
	public void ppClicarBotaoCriarPipeline() {
	    Na(PipelinePage.class).clickCriarPipeline();
	}
	
	@Então("^editar nome Pipeline como (.*)$")
	public void editarNomePipelineComo(String value) {
	    Na(PipelinePage.class).inserirCampoNomePipeline(value);
	}
	
	@Então("PP Clicar botao SalvarPipeline")
	public void ppClicarBotaoSalvarPipeline() {
	    Na(PipelinePage.class).clickBotaoSalvarPipeline();
	}
	
	@Então("PP Desativar Pipeline")
	public void ppDesativarPipeline() {
	    Na(PipelinePage.class).ativarDesativarPipelineCheckbox(false);
	}
	
	@Então("PP Ativar Pipeline")
	public void ppAtivarPipeline() {
		Na(PipelinePage.class).ativarDesativarPipelineCheckbox(true);
	}
	
	@Então("PP Clicar botao historico Pipelines")
	public void ppClicarBotaoHistoricoPipelines() {
	    Na(PipelinePage.class).clickBotaoHistorioPiprline();
	}
	
	@Então("^adicionar processo (PDI|Pipeline) (.*)$")
	public void adicionarProcessoPDIPDITeste(String tipo, String nome) {
		
		switch (tipo) {
		case "PDI":
			Na(PipelinePage.class).adicionarPDIEmPipeline(nome);
			break;
			
		case "Pipeline":
			Na(PipelinePage.class).adicionarPipelineEmPipeline(nome);
			break;

		default:
			break;
		}
	    Na(PipelinePage.class).adicionarPDIEmPipeline(nome);
	}

	@Então("^selecionar processo (.*)$")
	public void selecionarProcesso(int index) {
		Na(PipelinePage.class).selecionarProcesso(index);
	}
	
	@Então("^informar parametro (string|boolean) (.*) (.*)$")
	public void informarParametroParametroTeste(String tipo, String nome, String value) {
		
		switch (tipo) {
		case "string":

			Na(PipelinePage.class).inserirParametroTipoString(nome, value);
			break;

		case "boolean":
			
			Na(PipelinePage.class).inserirParametroTipoBoolean(nome, value == "true");
			break;

		default:
			break;
		}
	}
	
	@Então("PP Clicar botao ExcluirPipeline")
	public void ppClicarBotaoExcluirPipeline() {
	    Na(PipelinePage.class).clickBotaoExcluirPipeline();
	}

}

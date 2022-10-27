package steps;

import static config.ConfigInit.Na;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.constraints.AssertTrue;

import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;

import io.cucumber.java.pt.Então;
import pages.HistoricoPipelinePage;
import pages.PipelinePage;
import pages.SideBar;

public class PipelineStep extends MainSteps {

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
		value = value.replace("\"", "");
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

	@Então("^verificar se Pipeline esta (Ativa|Desativada)$")
	public void verificarPipelineAtiva(String value) {
		switch (value) {
		
		case "Ativa":

			assertTrue(Na(PipelinePage.class).isPipelineAtiva());
			break;

		case "Desativada":

			assertFalse(Na(PipelinePage.class).isPipelineAtiva());
			break;

		default:
			break;
		}
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

	@Então("^selecionar Pipeline (.*)$")
	public void selecionarPipelineP1(String nome) {
		boolean numerico = nome.matches("[0-9]+");
		Na(PipelinePage.class).selectPipeline(numerico ? ((int) Integer.parseInt(nome)) : nome);
	}

	@Então("^remover processo (.*)$")
	public void removerProcesso(String index) {
		Na(PipelinePage.class).removerProcessoDaPipeline(Integer.parseInt(index));
	}
	
	@Então("^verificar existencia de Pipeline (.*)$")
	public void verificarExistenciaPipeline(String nomePipeline) {
		assertTrue(Na(PipelinePage.class).verificarExistenciaPipeline(nomePipeline));
	}
	
	@Então("^Verificar nome Pipeline (.*)$")
	public void verificarNomePipeline(String value) {
		assertTrue(Na(PipelinePage.class).getCampoNomePipeline().equals(value));
	}
	
	@Então("^restaurar para a versao (.*)$")
	public void restaurarParaAVersao(String value) {
		
	    switch (value) {
		case "ultima":

			Na(HistoricoPipelinePage.class).restaurarVersao(-1);
			break;
			
		case "primeira":
			
			Na(HistoricoPipelinePage.class).restaurarVersao(1);
			break;

		default:
			
			Na(HistoricoPipelinePage.class).restaurarVersao(Integer.parseInt(value));
			break;
		}
	}
	
	@Então("^Verificar parametro (.*) (.*)$")
	public void verificarParametro(String parametro, String value) {
		assertTrue(Na(PipelinePage.class).verificarParametroTipoString(parametro, value));
	}
}

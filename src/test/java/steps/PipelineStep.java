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
		boolean resp = Na(PipelinePage.class).isPipelinesEmpty();
		assertTrue(resp);
	}

	@Então("^informar nome da nova Pipeline como (.*)$")
	public void informarNomeDaNovaPipelineComo(String value) {
		value = value.replace("\"", "");
		Na(PipelinePage.class).inserirCampoNomeCriarPipeline(value);
	}

	@Então("PP Clicar botao NovaPipeline")
	public void ppClicarBotaoNovaPipeline() {
		Na(PipelinePage.class).clickBotaoNovaPipeline();;
	}
	
	@Então("PP Clicar botao CriarPipeline")
	public void ppClicarBotaoCriarPipeline() {
		Na(PipelinePage.class).clickCriarPipeline();
	}
	
	@Então("PPI Clicar botao VoltarPipelines")
	public void ppClicarBotaoVoltarPipelines() {
		Na(PipelinePage.class).clickBotaoVoltarPipelines();
	}
	
	@Então("^editar nome Pipeline como (.*)$")
	public void editarNomePipelineComo(String value) {
		Na(PipelinePage.class).inserirCampoNomePipeline(value);
	}

	@Então("PP Clicar botao SalvarPipeline")
	public void ppClicarBotaoSalvarPipeline() {
		Na(PipelinePage.class).clickBotaoSalvarPipeline();
	}

	@Então("PPI Desativar Pipeline")
	public void ppDesativarPipeline() {
		Na(PipelinePage.class).ativarDesativarPipelineCheckboxInterno(false);
	}

	@Então("PPI Ativar Pipeline")
	public void ppAtivarPipeline() {
		Na(PipelinePage.class).ativarDesativarPipelineCheckboxInterno(true);
	}

	@Então("^(.*) verificar se Pipeline( .*)? esta (Ativa|Desativada)$")
	public void verificarPipelineAtiva(String local, String nome, String value) {
		switch (value) {
		
		case "Ativa":

			if(local.toLowerCase().trim().contentEquals("PPE")){
				assertTrue(Na(PipelinePage.class).verificarIsPipelineAtivaExterno(nome));
				
			}else if(local.toLowerCase().trim().contentEquals("PPI")){
				assertTrue(Na(PipelinePage.class).isPipelineAtiva());
			}
			
			break;

		case "Desativada":
			
			if(local.toLowerCase().trim().contentEquals("PPE")){
				assertFalse(Na(PipelinePage.class).verificarIsPipelineAtivaExterno(nome));
				
			}else if(local.toLowerCase().trim().contentEquals("PPI")){
				assertFalse(Na(PipelinePage.class).isPipelineAtiva());
			}
			break;

		default:
			break;
		}
	}

	@Então("PPI Clicar botao historico Pipelines")
	public void ppClicarBotaoHistoricoPipelines() {
		Na(PipelinePage.class).clickBotaoHistorioPipeline();
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

	@Então("PPI Clicar botao ExcluirPipeline")
	public void ppClicarBotaoExcluirPipeline() {
		Na(PipelinePage.class).clickBotaoExcluirPipeline();
	}
	
	@Então("PP Clicar botao FluxoPipeline")
	public void ppClicarBotaoFluxoPipeline() {
		Na(PipelinePage.class).clickBotaoFluxoPipeline();
	}

	@Então("^selecionar Pipeline (.*) (Ativar|Desativar|Editar|Deletar)$")
	public void selecionarPipeline(String nome, String acao) {
		Na(PipelinePage.class).selectPipeline(nome, acao);
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

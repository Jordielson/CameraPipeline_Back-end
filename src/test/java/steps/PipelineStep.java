package steps;

import static config.ConfigInit.Na;
import static config.ConfigInit.esperar;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.AssertTrue;

import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Então;
import pages.HistoricoPipelinePage;
import pages.PipelinePage;
import pages.ServicosPage;
import pages.SideBar;

public class PipelineStep extends MainSteps {

	String refImg = null;

	@Então("capturar imagem de pre-visualizacao")
	public void capturarImagem() {
		refImg = Na(PipelinePage.class).getSRCImgP();
	}

	@Então("confirmar alteracao de imagem de pre-visualizacao")
	public void confirmarAlteracaoImgP() {
		assertFalse(refImg.equals(Na(PipelinePage.class).getSRCImgP()));
	}

	@Então("acessar aba pipelines")
	public void acessarAbaPipelines() {
		Na(SideBar.class).clickAbaPipeline();
	}

	@Então("Verificar se não existe Pipeline")
	public void verificarSeNãoExistePipeline() {
		boolean resp = Na(PipelinePage.class).isPipelinesEmpty();
		assertTrue(resp);
	}

	@Então("^Pesquisar por (servicos|pipelines) (.*)$")
	public void pesquisarPorServicos(String type, String value) {

		switch (type) {
		case "servicos":
			Na(PipelinePage.class).inserirCampoPesquisaServico(value);
			break;

		default:
			Na(PipelinePage.class).inserirCampoPesquisaPipeline(value);
			break;
		}
		
		esperar(1);
	}

	@Então("^validar pesquisa$")
	public void validarPesquisa(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		List<String> resultadoEsperado = new ArrayList<String>();
		List<String> resultado = null;

		if (!map.get("type").isEmpty() && !map.get("resultado").isEmpty()) {
			resultado = map.get("type").equals("pipeline") ? Na(PipelinePage.class).getNomePipelinesEncontradas()
					: Na(PipelinePage.class).getNomeServicosEncontrados();

			String[] resultadoTemp = map.get("resultado").split(";");
			for (String s : resultadoTemp) {
				resultadoEsperado.add(s.trim());
			}
			
			Collections.sort(resultado);
			Collections.sort(resultadoEsperado);
			
			System.err.println(resultado);
			System.err.println(resultadoEsperado);
			System.err.println(resultado.toString());
			System.err.println(resultadoEsperado.toString());
			System.err.println(resultado.size());
			System.err.println(resultadoEsperado.size());
			
			assertEquals(resultado, resultadoEsperado);
			
			if(map.get("type").equals("pipeline")) {
				Na(PipelinePage.class).clicklinkMostrarResultadosPipeline();
			}else {
				Na(PipelinePage.class).clicklinkMostrarResultadosServico();
			}
			
		}
		esperar(2);
	}

	@Então("^informar nome da nova Pipeline como (.*)$")
	public void informarNomeDaNovaPipelineComo(String value) {
		value = value.replace("\"", "");
		Na(PipelinePage.class).inserirCampoNomeCriarPipeline(value);
	}

	@Então("PP Clicar botao NovaPipeline")
	public void ppClicarBotaoNovaPipeline() {
		Na(PipelinePage.class).clickBotaoNovaPipeline();
		;
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
		Na(PipelinePage.class).clickBotaoPagePipeline("Salvar");
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

			if (local.toLowerCase().trim().contentEquals("PPE")) {
				assertTrue(Na(PipelinePage.class).verificarIsPipelineAtivaExterno(nome));

			} else if (local.toLowerCase().trim().contentEquals("PPI")) {
				assertTrue(Na(PipelinePage.class).isPipelineAtiva());
			}

			break;

		case "Desativada":

			if (local.toLowerCase().trim().contentEquals("PPE")) {
				assertFalse(Na(PipelinePage.class).verificarIsPipelineAtivaExterno(nome));

			} else if (local.toLowerCase().trim().contentEquals("PPI")) {
				assertFalse(Na(PipelinePage.class).isPipelineAtiva());
			}
			break;

		default:
			break;
		}
	}

	@Então("PPI Clicar botao historico Pipelines")
	public void ppClicarBotaoHistoricoPipelines() {
		Na(PipelinePage.class).clickBotaoPagePipeline("Historico");
	}

	@Então("^adicionar processo (Servico|Pipeline) (.*)$")
	public void adicionarProcessoPDIPDITeste(String tipo, String nome) {

		switch (tipo) {
		case "Servico":
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

	@Então("^informar parametro (string|boolean|number|file|select|color) (.*) (.*)$")
	public void informarParametroParametroTeste(String tipo, String nome, String value) {

		switch (tipo) {
		case "string":

			Na(PipelinePage.class).inserirParametroTipoString(nome, value);
			break;

		case "boolean":

			Na(PipelinePage.class).inserirParametroTipoBoolean(nome, value == "true");
			break;

		case "select":
			Na(PipelinePage.class).inserirParametroTipoSelect(nome, value);
			break;

		case "number":
			Na(PipelinePage.class).inserirParametroTipoString(nome, value);
			break;

		case "color":
			Na(PipelinePage.class).inserirParametroTipoColor(nome, value);

			break;

		default:
			break;
		}
	}

	@Então("PPI Clicar botao ExcluirPipeline")
	public void ppClicarBotaoExcluirPipeline() {
		Na(PipelinePage.class).clickBotaoPagePipeline("Excluir");
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

	@Então("^Verificar select parametro (.*) (.*)$")
	public void verificarParametroSelect(String parametro, String value) {
		assertTrue(Na(PipelinePage.class).verificarParametroTipoSelect(parametro, value));
	}
}

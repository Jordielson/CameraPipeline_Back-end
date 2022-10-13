package steps;

import static config.ConfigInit.Na;
import static config.ConfigInit.esperar;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static config.ConfigInit.*;

import java.io.File;
import java.util.List;

import com.camerapipeline.camera_pipeline.model.entities.input.PipelineInput;

import io.cucumber.java.pt.Entao;
import pages.EdicaoCameraPage;
import pages.SideBar;

public class EdicaoCameraStep extends MainSteps{
	
	
	private String nomePipelineSelecionada;
	private String nomeCameraSelecionada;
	private int qtdCamera = 0;
	
	@Entao("EC verificar processamento salvo")
	public void verificarProcessamentoSalvo() {
		boolean condicao = false;
		String nomeInputEsperado = nomeCameraSelecionada + "_" + nomePipelineSelecionada;
		List<PipelineInput> inputs = getAllPipelineInput();
		for(PipelineInput pi : inputs) {
			if(pi.getName().equals(nomeInputEsperado)) {
				condicao = true;
				break;
			}
		}
		assertTrue(condicao);
	}

	@Entao("acessar aba Edicao-Camera")
	public void acessarAbaEdiçãoCamera() {
		Na(SideBar.class).clickAbaEdicaoCamera();;
	}
	
	@Entao("^EC selecionar camera \"(.*)\"$")
	public void selecionarCamera(String value) {
		esperar(1);
		nomeCameraSelecionada = Na(EdicaoCameraPage.class).selecionarCamera(value);
	}
	

	@Entao("^EC selecionar Pipeline \"(.*)\"$")
	public void selecionarPipeline(String value) {
		qtdCamera = getAllImageDataSize();
		nomePipelineSelecionada = Na(EdicaoCameraPage.class).selecionarPipeline(value);
		esperar(1);
		if(Na(EdicaoCameraPage.class).getTituloLabel().equals("Resultado:")) {
			qtdCamera++;
		}
	}
	
	@Entao("^EC encerrar Alerta$")
	public void encerrarAlerta() {
		esperar(3);
		aceitarAlerta();
	}
	
	@Entao("^EC clicar no botão (.*)$")
	public void ecClicarNoBotãoVoltar(String value) {
		esperar(1);

		switch (value) {
		case "Proximo":

			Na(EdicaoCameraPage.class).clicarBotaoProximo();
			break;

		case "Voltar":

			Na(EdicaoCameraPage.class).clicarBotaoVoltar();
			break;

		case "Salvar":

			Na(EdicaoCameraPage.class).clicarBotaoSalvar();;
			esperar(1);
			break;

		case "Reiniciar":

			Na(EdicaoCameraPage.class).clicarBotaoReiniciar();
			break;

		default:
			break;
		}
	}

	@Entao("^EC devo estar na (.*) etapa$")
	public void eiDevoEstarNaEtapa(String value) {
		esperar(1);

		switch (value) {
		case "primeira":

			assertEquals(Na(EdicaoCameraPage.class).getTituloLabel(), "Selecione o arquivo");
			break;

		case "segunda":

			assertEquals(Na(EdicaoCameraPage.class).getTituloLabel(), "Selecione uma pipeline");
			break;

		case "terceira":

			assertEquals(Na(EdicaoCameraPage.class).getTituloLabel(), "Resultado:");
			break;

		default:
			break;
		}
	}
}

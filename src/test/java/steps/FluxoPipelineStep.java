package steps;

import io.cucumber.java.pt.Então;
import pages.FluxoPage;

import static config.ConfigInit.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class FluxoPipelineStep  extends MainSteps{

	@Então("^FP reposicionar processo (.*) x(.*) y(.*)$")
	public void teste(String id, String sx, String sy) {
		int x = Integer.parseInt(sx);
		int y = Integer.parseInt(sy);
		
		try {
			WebElement draggable = Na(FluxoPage.class).getProcessoPorID(id);
			new Actions(driver)
			.dragAndDropBy(draggable, x, y)
			.perform();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Nao foi possivel posicionar Elemento");
		}
	}
	
	@Então("^FP conectar processo (.*) (.*) ao (.*) (.*)$")
	public void conectarProcessos(String idProcesso1, String ancora1, String idProcesso2, String ancora2 ) {
		
		WebElement draggable = Na(FluxoPage.class).getAncoraProcesso(idProcesso1, ancora1);
        WebElement droppable = Na(FluxoPage.class).getAncoraProcesso(idProcesso2, ancora2);
        new Actions(driver)
                .dragAndDrop(draggable, droppable)
                .perform();
	}
	
	@Então("^FP salvar Fluxo$")
	public void salvarFluxo() {
		Na(FluxoPage.class).clickBotaoSalvarEVoltar();
	}
	
	@Então("^FP Voltar$")
	public void sairFluxo() {
		Na(FluxoPage.class).clickBotaoVoltar();
	}
	
	@Então("^FP desconectar processo (.*) to (.*)$")
	public void desconectarProcessos(String id1, String id2) {
		Na(FluxoPage.class).clickDeletarConexao(id1,id2);
	}
	
	@Então("^FP desconectar todos os processos")
	public void desconectarTodosOsProcessos() {
		Na(FluxoPage.class).clickDeletarConexoes();;
	}
	
	@Então("^FP verificar quantidade de processos (.*)$")
	public void verificarQuantidadeDeProcessos(String value) {
		int qtdd = Integer.parseInt(value);
		assertEquals(qtdd, Na(FluxoPage.class).getProcessosSize());
	}
	
	@Então("^FP verificar quantidade de conexoes (.*)$")
	public void verificarQuantidadeDeConexoes(String value) {
		int qtdd = Integer.parseInt(value);
		assertEquals(qtdd, Na(FluxoPage.class).getConectoresSize());
	}
	
	@Então("^FP verificar conexao (.*) to (.*) is (.*)$")
	public void verificarConexao(String id1, String id2, String condicao) {
		
		if(condicao.trim().toLowerCase().equals("true")) {
			assertTrue(Na(FluxoPage.class).verificarConexao(Integer.parseInt(id1), Integer.parseInt(id2)));
		}else {
			assertFalse(Na(FluxoPage.class).verificarConexao(Integer.parseInt(id1), Integer.parseInt(id2)));
		}
	}
	
	
}

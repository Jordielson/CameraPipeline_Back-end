package steps;

import io.cucumber.java.pt.Então;
import pages.FluxoPage;

import static config.ConfigInit.*;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class FluxoPipelineStep  extends MainSteps{

	@Então("FP reposicionar processos")
	public void teste() {
		int[] x = {0, -350 , 0};
		int[] y = {-250, 0, 250};
		for(int i = 3 ; i>0 ; i--) {
			WebElement draggable = Na(FluxoPage.class).getProcessoPorID(i);
			new Actions(driver)
			.dragAndDropBy(draggable, x[i-1], y[i-1])
			.perform();
		}
	}
	
	@Então("^conectar processo (.*) (.*) ao (.*) (.*)$")
	public void conectarProcessos(String idProcesso1, String ancora1, String idProcesso2, String ancora2 ) {
		
		WebElement draggable = Na(FluxoPage.class).getAncoraProcesso(Integer.parseInt(idProcesso1), ancora1);
        WebElement droppable = Na(FluxoPage.class).getAncoraProcesso(Integer.parseInt(idProcesso2), ancora2);
        new Actions(driver)
                .dragAndDrop(draggable, droppable)
                .perform();
	}
	
	@Então("^FP salvar Fluxo$")
	public void salvarFluxo() {
		Na(FluxoPage.class).clickBotaoSalvarEVoltar();
	}
	
}

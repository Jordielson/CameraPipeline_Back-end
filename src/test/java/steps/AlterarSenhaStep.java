package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.AlterarSenhaPage;
import pages.SideBar;

import static config.ConfigInit.*;

public class AlterarSenhaStep extends MainSteps{

	@Dado("acessei a aba AlterarSenha")
	public void acessei_a_aba_alterar_senha() {
		Na(SideBar.class).clickAbaAlterarSenha();
	}

	@Dado("^passei senha atual (.*)$")
	public void passei_senha_atual(String value) {
		value.replace("\"", "");
		Na(AlterarSenhaPage.class).inserirSenhaAtual(value);
		esperar(1);
	}

	@Dado("^passei nova senha (.*)$")
	public void passei_nova_senha(String value) {
		value.replace("\"", "");
		Na(AlterarSenhaPage.class).inserirNovaSenha(value);
		esperar(1);
	}

	@Dado("^passei consfirmar nova senha (.*)$")
	public void passei_consfirmanova_senha(String value) {
		Na(AlterarSenhaPage.class).inserirConfirmeNovaSenha(value);
		esperar(1);
	}

	@Quando("alterar")
	public void alterar() {
	    Na(AlterarSenhaPage.class).clickBotaoAlterar();
	}

	@Quando("sair")
	public void sair() {
		Na(SideBar.class).clickBotaoSair();
	}

}

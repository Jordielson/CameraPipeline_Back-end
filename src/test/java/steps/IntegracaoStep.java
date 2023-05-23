package steps;

import static config.ConfigInit.esperar;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.camerapipeline.camera_pipeline.model.entities.history.DataHistory;
import com.camerapipeline.camera_pipeline.model.entities.history.PdiDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.history.PipelineDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.input.PipelineInput;
import com.camerapipeline.camera_pipeline.model.entities.input.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.input.camera.Coordinate;
import com.camerapipeline.camera_pipeline.model.entities.input.image.ImageData;
import com.camerapipeline.camera_pipeline.model.entities.input.video.VideoData;
import com.camerapipeline.camera_pipeline.model.entities.pdi.DigitalProcess;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pdi.Parameter;
import com.camerapipeline.camera_pipeline.model.entities.pdi.Position;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.model.enums.ParameterType;
import com.camerapipeline.camera_pipeline.model.repository.history.PipelineDataHistoryRepository;
import com.camerapipeline.camera_pipeline.presentation.dto.user.UserResquest;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;

public class IntegracaoStep extends MainSteps {

	@Dado("^que tenho uma Pipeline( .*)?$")
	public void queTenhoUmaPipeline(String nome) {
		Pipeline pipeline = new Pipeline();
		pipeline.setName(nome != null ? nome.trim() : "Pipeline Test");
		pipeline.setUser(recuperarUser());
		pipeline.setPDIList(List.of());
		pipeline.setPdis(List.of());

		pipelineService.create(pipeline, recuperarPrincipal());
	}

	@Dado("que tenho uma Camera")
	public void queTenhoUmaCamera() {
		Camera camera = new Camera();
		camera.setName("Camera test");
		camera.setUrl("www.testes.com.br");
		camera.setCoordinate(new Coordinate(50.00, 50.00));
		camera.setFpsLimiter(90);
		camera.setIsPrivate(false);

		cameraService.create(camera, recuperarPrincipal());
	}
	
	@Dado("^que tenho o Servico effect em (.*)$")
	public void quetenhoEffectEmPipeline(String value) {
		
		Optional<Pipeline> oP = findPipelineByName(value);
		Optional<ModelPDI> oMp = findModelPDIByName("Effect");
		Optional<Parameter> oParam = findParameterByName("effect");

		if(oP.isPresent() && oMp.isPresent() && oParam.isPresent()) {
			
			Position position = new Position();
			position.setX(100.00);
			position.setY(100.00);
			
			ValueParameter vp = new ValueParameter();
			vp.setValue("GRAY");
			vp.setParameter(oParam.get());

			PDI pdi = new PDI();
			pdi.setIndex(1);
			pdi.setDigitalProcess(oMp.get());
			pdi.setPipeline(oP.get());
			pdi.setPosition(position);
			pdi.setValueParameters(List.of(vp));
			
			pdiService.create(pdi, recuperarPrincipal());
			
		}
	}

	@Dado("^que tenho um Servico( .*)?$")
	public void queTenhoUmServico(String nome) {
		
		if(nome == null) {
			nome = "null";
		}

		switch (nome.trim()) {
		
		case "color":

			Parameter parameterColor = new Parameter();
			parameterColor.setName("color");
			parameterColor.setIndex(1);
			parameterColor.setType(ParameterType.COLOR);
			parameterColor.setRequired(true);
			
			ModelPDI mscolor = new ModelPDI();
			mscolor.setName("Color");
			mscolor.setURL("http://127.0.0.1:5000/api/color/");
			mscolor.setDescription("Descricao de Serviço");
			mscolor.setParameters(List.of(parameterColor));

			modelPDIService.create(mscolor, recuperarPrincipal());
			break;
		
		case "effect":

			Parameter parameterEffect = new Parameter();
			parameterEffect.setName("effect");
			parameterEffect.setIndex(1);
			parameterEffect.setType(ParameterType.SELECT);
			parameterEffect.setSelectOptions(Set.of("GRAY", "HSV", "LAB", "LUV"));
			parameterEffect.setRequired(true);
			
			ModelPDI mseffect = new ModelPDI();
			mseffect.setName("Effect");
			mseffect.setURL("http://127.0.0.1:5000/api/apply-effect/");
			mseffect.setDescription("Estilo de efeito");
			mseffect.setParameters(List.of(parameterEffect));

			modelPDIService.create(mseffect, recuperarPrincipal());
			break;

		case "crop":

			Parameter position_x = new Parameter();
			position_x.setName("position_x");
			position_x.setIndex(1);
			position_x.setType(ParameterType.NUMBER);
			position_x.setRequired(true);

			Parameter position_y = new Parameter();
			position_y.setName("position_y");
			position_y.setIndex(2);
			position_y.setType(ParameterType.NUMBER);
			position_y.setRequired(true);

			Parameter width = new Parameter();
			width.setName("width");
			width.setIndex(3);
			width.setType(ParameterType.NUMBER);
			width.setRequired(true);

			Parameter height = new Parameter();
			height.setName("height");
			height.setIndex(4);
			height.setType(ParameterType.NUMBER);
			height.setRequired(true);

			ModelPDI mscrop = new ModelPDI();
			mscrop.setName("Color");
			mscrop.setURL("http://127.0.0.1:5000/api/apply-effect/");
			mscrop.setDescription("Estilo de efeito");
			mscrop.setParameters(List.of(position_x, position_y, width, height));

			modelPDIService.create(mscrop, recuperarPrincipal());
			break;

		default:
			Parameter parameter1 = new Parameter();
			parameter1.setName("ParamTeste1");
			parameter1.setIndex(1);
			parameter1.setType(ParameterType.STRING);
			parameter1.setRequired(false);

			Parameter parameter2 = new Parameter();
			parameter2.setName("ParamTeste2");
			parameter2.setIndex(2);
			parameter2.setType(ParameterType.NUMBER);
			parameter2.setRequired(false);

			ModelPDI mpdi = new ModelPDI();
			mpdi.setName(nome != "null" ? nome.trim() : "PDITeste");
			mpdi.setURL(nome != "null" ? "www." + nome.trim() + ".com" : "www.testePDI.com");
			mpdi.setDescription("DescricaoTestePDI");
			mpdi.setParameters(List.of(parameter1, parameter2));

			modelPDIService.create(mpdi, recuperarPrincipal());
			break;

		}

	}

	@Dado("^que existe a conta email (.*) e senha (.*)$")
	public void queExisteAContaEmailESenha(String email, String senha) {
		User novoUser = new User();
		novoUser.setEmail(email);
		novoUser.setPassword(senha.replace("\"", ""));
		novoUser.setPipelineInputs(Set.of());
		novoUser.setDigitalProcess(Set.of());

		userService.create(novoUser);
	}

	@Então("^usuario (.*)( não)? deve estar no banco$")
	public void usuario_deve_estar_no_banco(String email, String condicao) {
		Optional<User> userRecuperado = userRepository.findByEmail(email);
		if (condicao == null) {
			assertTrue(userRecuperado.isPresent());
		} else {
			assertFalse(userRecuperado.isPresent());
		}

	}

	@Então("^remover usuario( (.*))?$")
	public void removerUsuario(String emailpassado) {
		Optional<User> userRecuperado = userRepository.findByEmail(emailpassado);

		if (userRecuperado.isPresent()) {
			userService.delete(userRecuperado.get().getId(), recuperarPrincipal());
		}
	}

}

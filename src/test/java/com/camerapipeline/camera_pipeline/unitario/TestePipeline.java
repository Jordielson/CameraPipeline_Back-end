package com.camerapipeline.camera_pipeline.unitario;

import java.security.Principal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.camerapipeline.camera_pipeline.CameraPipelineApplication;
import com.camerapipeline.camera_pipeline.model.entities.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.camera.Coordinate;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.provider.services.auth.AuthService;
import com.camerapipeline.camera_pipeline.provider.services.camera.CameraService;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration
@SpringBootTest(classes = CameraPipelineApplication.class)
public class TestePipeline {

	@Autowired
    private AuthService authService;
	
	@Autowired
	private PipelineService pipelineService;
	
	@Autowired
	private CameraService cameraService;
	
	Pageable pageable = Pageable.unpaged();
	
	private Principal principal = null;
	
	private Coordinate coordinate = new Coordinate(50.00,50.00);
	
	@BeforeEach
	public void preConfig() {
		
		principal = authService.authenticateUser("admin@admin.com", "123456");
		
	}
	
	/** sessão de sucesso **/
	
	
	
	/** sessão de falha **/
	
	
private void apagarBanco() {
		
		Pageable pageable = Pageable.unpaged();

		List<Pipeline> pipelinesTemp = pipelineService.getAll(pageable, principal).toList();
		
		for(Pipeline p : pipelinesTemp) {
			p.setCameraList(List.of());
			pipelineService.update(p.getId(), p, principal);
			pipelineService.delete(p.getId(), principal);
		}
		
		List<Camera> camerasTemp = cameraService.getAll(pageable, principal).toList();
		
		for(Camera c : camerasTemp) {
			c.setPipelineList(List.of());
			cameraService.update(c.getId(), c, principal);
			cameraService.delete(c.getId(), principal);
		}
		
	}
	
	private User recuperarUserPrincipal() {
		return authService.loadUserByUsername(principal.getName());
	}
	
	private Pipeline montarPipeline(String nome) {
		
		Pipeline pipeline = new Pipeline();
		pipeline.setName(nome);
		pipeline.setActive(true);
		pipeline.setUser(recuperarUserPrincipal());
		pipeline.setPDIList(List.of());
		
		return pipeline;
	}
	
	private Camera montarCamera(String nome) {
		
		Camera camera = new Camera();
		camera.setName(nome);
		camera.setURL("www.testes.com.br");
		camera.setCoordinate(coordinate);
		camera.setFpsLimiter(90);
		camera.setIsActive(true);
		camera.setIsPrivate(false);
		camera.setUser(recuperarUserPrincipal());
		
		return camera;
	}
	
	
}

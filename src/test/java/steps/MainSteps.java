package steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static config.ConfigInit.*;

import java.security.Principal;

import com.camerapipeline.camera_pipeline.CameraPipelineApplication;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.model.repository.user.UserRepository;
import com.camerapipeline.camera_pipeline.provider.services.auth.AuthService;
import com.camerapipeline.camera_pipeline.provider.services.input.image.ImageDataService;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;
import com.camerapipeline.camera_pipeline.provider.services.user.UserService;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = CameraPipelineApplication.class)
public class MainSteps {

	@Autowired
	protected AuthService authService;
	
	@Autowired
	protected PipelineService pipelineService;
	
	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected ImageDataService imageDataService;

	protected String email;
	protected String senha;
	protected Pageable pageable = Pageable.unpaged();
	
	protected Principal recuperarPrincipal() {
		return authService.authenticateUser("userteste1@user.com", "123456");
	}
	
	protected User recuperarUser() {
		return authService.loadUserByUsername(recuperarPrincipal().getName());
	}
	
	
}

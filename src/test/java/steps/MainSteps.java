package steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static config.ConfigInit.*;

import java.security.Principal;
import java.util.List;

import com.camerapipeline.camera_pipeline.CameraPipelineApplication;
import com.camerapipeline.camera_pipeline.model.entities.history.PipelineDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.input.PipelineInput;
import com.camerapipeline.camera_pipeline.model.entities.input.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.input.image.ImageData;
import com.camerapipeline.camera_pipeline.model.entities.input.video.VideoData;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.model.repository.history.PdiDataHistoryRepository;
import com.camerapipeline.camera_pipeline.model.repository.history.PipelineDataHistoryRepository;
import com.camerapipeline.camera_pipeline.model.repository.history.ValueParameterDataHistoryRepository;
import com.camerapipeline.camera_pipeline.model.repository.user.UserRepository;
import com.camerapipeline.camera_pipeline.presentation.dto.input.camera.CameraDTO;
import com.camerapipeline.camera_pipeline.provider.services.auth.AuthService;
import com.camerapipeline.camera_pipeline.provider.services.history.PdiDataHistoryService;
import com.camerapipeline.camera_pipeline.provider.services.history.PipelineDataHistoryService;
import com.camerapipeline.camera_pipeline.provider.services.history.ValueParameterDataHistoryService;
import com.camerapipeline.camera_pipeline.provider.services.input.camera.CameraService;
import com.camerapipeline.camera_pipeline.provider.services.input.image.ImageDataService;
import com.camerapipeline.camera_pipeline.provider.services.input.video.VideoDataService;
import com.camerapipeline.camera_pipeline.provider.services.pdi.ModelPDIService;
import com.camerapipeline.camera_pipeline.provider.services.pdi.PDIService;
import com.camerapipeline.camera_pipeline.provider.services.pdi.ParameterService;
import com.camerapipeline.camera_pipeline.provider.services.pdi.ValueParameterService;
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
	protected CameraService cameraService;
	
	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected ImageDataService imageDataService;

	@Autowired
	protected VideoDataService videoDataService;
	
	@Autowired
	protected ParameterService parameterService;
	
	@Autowired
	protected ModelPDIService modelPDIService;
	
	@Autowired
	protected PipelineDataHistoryService pipelineDataHistoryService;
	
	@Autowired
	protected PdiDataHistoryService pdiDataHistoryService;
	
	@Autowired
	protected ValueParameterDataHistoryService valueParameterDataService;
	
	@Autowired
	protected ValueParameterService valueParameterService;
	
	@Autowired
	protected PDIService pdiService;

	@Autowired
	protected PdiDataHistoryRepository pdiDataHistoryRepository;
	
	@Autowired
	protected PipelineDataHistoryRepository pipelineDataHistoryRespository;
	
	@Autowired
	protected ValueParameterDataHistoryRepository valueParameterDataHistoryRepository;
	
	
	protected Pageable pageable = Pageable.unpaged();
	
	protected Principal recuperarPrincipal() {
		List<String> senhas = List.of("123456", "654321");
		Principal principal = null ;
		
		for(String senha : senhas) {
			try {
				principal = authService.authenticateUser("userteste1@user.com", senha);
				break;
			} catch (Exception e) {
				continue;
			}
		}
		return principal;
	}
	
	protected User recuperarUser() {
		return authService.loadUserByUsername(recuperarPrincipal().getName());
	}
	
	protected List<ImageData> getAllImageData() {
		return imageDataService.getAll(pageable, recuperarPrincipal()).toList();
	}
	
	protected List<VideoData> getAllVideoData() {
		return videoDataService.getAll(pageable, recuperarPrincipal()).toList();
	}
	
	protected List<Camera> getAllCamera() {
		return cameraService.getAll(pageable, recuperarPrincipal()).toList();
	}
	
	protected List<PipelineInput> getAllPipelineInput() {
		return List.copyOf(recuperarUser().getPipelineInputs());
	}
	
	protected int getAllImageDataSize() {
		return getAllImageData().size();
	}
	
	protected int getAllVideoDataSize() {
		return getAllVideoData().size();
	}
	
	protected int getAllCameraSize() {
		return getAllCamera().size();
	}
	
	protected int getAllPipelineInputSize() {
		return getAllPipelineInput().size();
	}
	
}

//package com.camerapipeline.camera_pipeline;
//
//import java.security.Principal;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import com.camerapipeline.camera_pipeline.model.entities.input.camera.Coordinate;
//import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
//import com.camerapipeline.camera_pipeline.model.entities.user.User;
//import com.camerapipeline.camera_pipeline.model.repository.user.UserRepository;
//import com.camerapipeline.camera_pipeline.provider.services.auth.AuthService;
//import com.camerapipeline.camera_pipeline.provider.services.input.camera.CameraService;
//import com.camerapipeline.camera_pipeline.provider.services.pdi.ModelPDIService;
//import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;
//import com.camerapipeline.camera_pipeline.provider.services.user.UserService;
//
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
//@ContextConfiguration
//@SpringBootTest(classes = CameraPipelineApplication.class)
//public class Utilitario {
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//    private AuthService authService;
//	
//	@Autowired
//	private PipelineService pipelineService;
//	
//	@Autowired
//	private CameraService cameraService;
//	
//	@Autowired
//	private ModelPDIService modelPDIService;
//	
//	
//	private Coordinate coordinate = new Coordinate(50.00,50.00);
//	
//	
//	Pageable pageable = Pageable.unpaged();
//	
//	private User user;
//	
//	private Principal principal = null;
//	
//	
//	@BeforeEach
//	public void preConfig() {
//		
//		principal = authService.authenticateUser("admin@admin.com", "123456");
//		
//	}
//	
//
//	@Test
//	public void utilitario() {
//		
//		Pipeline p = new Pipeline();
//		p.setName("Pipeline Test");
//		p.setDescription("Esta Pipeline é estatica");
//		p.setPDIList(List.of());
//		pipelineService.create(p, principal);
//		
//	}
//	
//}

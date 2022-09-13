package com.camerapipeline.camera_pipeline.services.email;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.enums.StatusEmail;
import com.camerapipeline.camera_pipeline.exception.geral.RegraDeNegocioException;
import com.camerapipeline.camera_pipeline.model.email.EmailModel;
import com.camerapipeline.camera_pipeline.repository.email.EmailRepository;



@Service
public class EmailService {

	@Autowired
    private EmailRepository repository;

    @Autowired
    private JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDataEmail(LocalDateTime.now());
        
        String conteudoEmail = ConverterEmail(emailModel);
        try {
        	
        	 MimeMessage message = emailSender.createMimeMessage();

             MimeMessageHelper helper;
             helper = new MimeMessageHelper(message, true, "UTF-8");
             helper.setFrom(emailModel.getEmailFrom());
             helper.setTo(emailModel.getEmailTo());
//             helper.setCc();
             helper.setSubject(emailModel.getSubject());
             helper.setText(conteudoEmail, true);

            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);

        }catch (MailException e){
        	System.out.println(e.getMessage());
            emailModel.setStatusEmail(StatusEmail.ERROR);

        }finally {
        	emailModel.setResolut(emailModel.getResolut() + 1);
            return repository.save(emailModel);
        }
    }
    
    public void deletarPorId(EmailModel email) {
    	Optional<EmailModel> emailFix = repository.findById(email.getEmailId());
    	if(emailFix.isPresent()) {
    		repository.deleteById(email.getEmailId());
    		return;
    	}
    	
    	throw new RegraDeNegocioException("Email não encontrado");
    	
    }
    
    public ArrayList<EmailModel> obterPorStatus(int status){
    	return repository.findByStatusEmail(status);
    }
    
    public Page<EmailModel> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    
    public EmailModel premoldadoEmailModel(String ownerRef,String emailTo, String subject,String text, String nomeDestinatario) {
		
		EmailModel email = EmailModel.builder()
				.ownerRef(ownerRef)
				.emailFrom("camerapipeline@gmail.com")
				.emailTo(emailTo)
				.subject(subject)
				.resolut(0)
				.text(text)
				.nomeDestinatario(nomeDestinatario).build();
		
		return email;
	}
    
    private String ConverterEmail(EmailModel email) {
 
    	return "<H1> Camera_Pipeline <H1>";
    }

}

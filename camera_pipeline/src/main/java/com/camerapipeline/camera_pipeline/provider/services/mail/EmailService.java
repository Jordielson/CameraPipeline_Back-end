package com.camerapipeline.camera_pipeline.provider.services.mail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.model.email.EmailModel;
import com.camerapipeline.camera_pipeline.model.enums.StatusEmail;
import com.camerapipeline.camera_pipeline.model.repository.mail.EmailRepository;
import com.camerapipeline.camera_pipeline.provider.exception.BusinessException;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;

@Service
public class EmailService {

	@Autowired
    private EmailRepository repository;

    @Autowired
    private JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDataEmail(LocalDateTime.now());
        
        String content = convertEmail(emailModel, "");
        try {
        	 MimeMessage message = emailSender.createMimeMessage();

             MimeMessageHelper helper;
             helper = new MimeMessageHelper(message, true, "UTF-8");
             helper.setFrom(emailModel.getEmailFrom());
             helper.setTo(emailModel.getEmailTo());
             helper.setSubject(emailModel.getSubject());
             helper.setText(content, true);

            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);

        } catch (MailException|MessagingException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
            throw new BusinessException(
                "Could not send the email because of an"+
                " internal error, please try again later",
                e
            );
        } finally {
        	emailModel.setResolut(emailModel.getResolut() + 1);
        }
        return repository.save(emailModel);
    }
    
    public void delete(EmailModel email) {
    	Optional<EmailModel> emailFix = repository.findById(email.getEmailId());
    	if(emailFix.isPresent()) {
    		repository.deleteById(email.getEmailId());
    	}
    	
    	throw new CustomEntityNotFoundException("Email", email.getEmailId().toString());
    	
    }
    
    public ArrayList<EmailModel> getByStatus(int status){
    	return repository.findByStatusEmail(status);
    }
    
    public Page<EmailModel> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    
    public EmailModel preShapedEmail(String ownerRef,String emailTo, String subject,String text, String nomeDestinatario) {
		
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
    
    private String convertEmail(EmailModel email, String link) {
 
    	return "<H1> Camera_Pipeline <H1>"
            + "<p>You have requested to reset your password.</p>"
            + "<p>Click the link below to change your password:</p>"
            + "<p><a href=\"" + link + "\">Change my password</a></p>"
            + "<br>"
            + "<p>Ignore this email if you do remember your password, "
            + "or you have not made the request.</p>";
    }

}

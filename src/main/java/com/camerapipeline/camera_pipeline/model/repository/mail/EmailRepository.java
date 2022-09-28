package com.camerapipeline.camera_pipeline.model.repository.mail;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.camerapipeline.camera_pipeline.model.entities.email.EmailModel;
import com.camerapipeline.camera_pipeline.model.repository.RepositoryAbstract;

public interface EmailRepository extends RepositoryAbstract<EmailModel, Long> {
	
	@Query(value = "SELECT e FROM EmailModel e WHERE e.statusEmail = :status_email")
	public ArrayList<EmailModel> findByStatusEmail(@Param("status_email") int statusEmail);

}

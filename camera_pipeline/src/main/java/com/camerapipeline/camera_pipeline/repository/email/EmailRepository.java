package com.camerapipeline.camera_pipeline.repository.email;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.camerapipeline.camera_pipeline.model.email.EmailModel;
import com.camerapipeline.camera_pipeline.repository.RepositoryAbstract;

public interface EmailRepository extends RepositoryAbstract<EmailModel, Long> {
	
	@Query(value = "select * from tb_email email where email.status_email = :status_email", nativeQuery = true)
	public ArrayList<EmailModel> findByStatusEmail(@Param("status_email") int statusEmail);

}

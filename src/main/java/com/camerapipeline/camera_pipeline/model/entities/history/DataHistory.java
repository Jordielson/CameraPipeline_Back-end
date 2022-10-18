package com.camerapipeline.camera_pipeline.model.entities.history;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.camerapipeline.camera_pipeline.model.enums.DataHistoryEnum;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@MappedSuperclass
public abstract class DataHistory {
    
    protected Integer id;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid_generate_v4")
	@Column(name = "revision", updatable = false, unique = true, nullable = false)
    protected UUID revision;

    @Enumerated(EnumType.STRING)
    @Column(length = 8, updatable = false, nullable = false)
    protected DataHistoryEnum action = DataHistoryEnum.INSERT;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    protected ZonedDateTime dateTime;
}

package com.camerapipeline.camera_pipeline.model.entities.camera;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Range;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Coordinate implements Serializable{
    @Schema(
        name = "latitude",
        type = "double",
        example = "-25.24568",
		required = false
    )
	@Range(min = -90, max=90)
	@Column(name="latitude", columnDefinition="NUMERIC(10,8)")
	private Double latitude;
	
    @Schema(
        name = "longitude",
        type = "double",
        example = "75.24623",
		required = false
    )
	@Range(min = -180, max=180)
	@Column(name="longitude", columnDefinition="NUMERIC(11,8)")
	private Double longitude;
}


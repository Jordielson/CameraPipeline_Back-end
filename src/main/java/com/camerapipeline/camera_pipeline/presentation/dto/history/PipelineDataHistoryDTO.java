package com.camerapipeline.camera_pipeline.presentation.dto.history;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi.PdiDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PipelineDataHistoryDTO {
  @Schema(
    title = "Pipeline identifier",
    name = "id",
    type = "int",
    example = "22",
		required = false
  )
  private Integer id;

  @Schema(
    title = "Pipeline revision identifier",
    name = "revision",
    type = "uuid",
    required = false
  )
  private UUID revision;

  @Schema(
    name = "name",
    example = "Ergonomic Risk Analysis",
    type = "string",
    required = true
  )
  @NotBlank
  @Size(max = 60)
  private String name;

  @Schema(
    name = "description",
    example = "AI-based ergonomic risk analysis"+
            " using cameras to detect the human posture of employees",
    type = "string",
    required = false
	)
  private String description;
    
  @Schema(
    name = "isActive",
    type = "boolean",
    example = "true",
    required = false
  )
  private boolean isActive;

  @Schema(
    name = "PDIList",
    type = "[PdiDTO]",
    required = false
  )
  private List<PdiDTO> PDIList;
}

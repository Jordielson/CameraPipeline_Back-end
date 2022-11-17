package com.camerapipeline.camera_pipeline.presentation.dto.pipeline;

import java.util.List;

import com.camerapipeline.camera_pipeline.model.enums.Category;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi.DigitalProcessDTO;
import com.camerapipeline.camera_pipeline.presentation.dto.pdi.pdi.PdiDTO;
import com.camerapipeline.camera_pipeline.provider.utils.app.AppUtils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PipelineDTO extends DigitalProcessDTO {
    @Schema(
        title = "Pipeline identifier",
        name = "id",
        type = "int",
        example = "22",
		required = false
    )
    @Override
    public int getId() {
        return super.getId();
    }

    @Schema(
		name = "name",
		example = "Ergonomic Risk Analysis",
		type = "string",
		required = true
	)
    @Override
    public String getName() {
        return super.getName();
    }

    @Schema(
		name = "description",
		example = "AI-based ergonomic risk analysis"+
            " using cameras to detect the human posture of employees",
		type = "string",
		required = false
	)
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Schema(
      name = "URL",
      example = "https://camerapipeline/api/process-pipeline",
      type = "string",
      required = false
    )
    @Override
    public String getUrl() {
        return AppUtils.getProperties().getProperty("CameraPipeline.service-api");
    }
    
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

    @Schema(
        title = "PDI category (ModelPDI or Pipeline)",
        name = "category",
        required = true,
        example = "PIPELINE"
    )
    @Override
    public Category getCategory() {
        return Category.PIPELINE;
    }


    public PipelineDTO id(int id) {
        setId(id);
        return this;
    }

    public PipelineDTO name(String name) {
        setName(name);
        return this;
    }

    public PipelineDTO description(String description) {
        setDescription(description);
        return this;
    }

    public PipelineDTO isActive(boolean isActive) {
        setActive(isActive);
        return this;
    }

    public PipelineDTO PDIList(List<PdiDTO> PDIList) {
        setPDIList(PDIList);
        return this;
    }
}
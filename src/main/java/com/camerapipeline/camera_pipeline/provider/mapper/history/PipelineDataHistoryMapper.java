package com.camerapipeline.camera_pipeline.provider.mapper.history;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.camerapipeline.camera_pipeline.model.entities.history.PdiDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.history.PipelineDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.history.ValueParameterDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;

@Component
public class PipelineDataHistoryMapper {
    public Pipeline toDTO(PipelineDataHistory model) {
        Pipeline pipe = new Pipeline();
        pipe.setActive(model.isActive());

        List<PDI> PDIList = new ArrayList<>();
        for (PdiDataHistory pdiHistory : model.getPDIList()) {
            PDI pdi = new PDI();
            pdi.setId(pdiHistory.getId());
            pdi.setModelPdi(pdiHistory.getModelPdi());
            List<ValueParameter> valueParameters = new ArrayList<>();
            for (ValueParameterDataHistory value : pdiHistory.getValueParameters()) {
                ValueParameter valueParameter = new ValueParameter();
                valueParameter.setId(value.getId());
                valueParameter.setParameter(value.getParameter());
                valueParameter.setValue(value.getValue());
                valueParameters.add(valueParameter);
            }
            pdi.setValueParameters(valueParameters);
            PDIList.add(pdi);
        }
        pipe.setId(model.getId());
        pipe.setPDIList(PDIList);
        pipe.setDescription(model.getDescription());
        pipe.setName(model.getName());
        pipe.setUser(model.getUser());
        return pipe;
    }

    public Page<Pipeline> toDTOPage(Page<PipelineDataHistory> modelList) {
        return modelList.map(this::toDTO);
    }
}

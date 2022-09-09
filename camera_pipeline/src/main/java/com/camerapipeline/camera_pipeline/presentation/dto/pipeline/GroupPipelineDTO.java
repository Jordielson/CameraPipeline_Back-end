package com.camerapipeline.camera_pipeline.presentation.dto.pipeline;

import java.util.List;
import java.util.Objects;

public class GroupPipelineDTO {
    private int id;
    private String name;
    private List<PipelineDTO> pipelineList;

    public GroupPipelineDTO() {
    }

    public GroupPipelineDTO(int id, String name, List<PipelineDTO> pipelineList) {
        this.id = id;
        this.name = name;
        this.pipelineList = pipelineList;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PipelineDTO> getPipelineList() {
        return this.pipelineList;
    }

    public void setPipelineList(List<PipelineDTO> pipelineList) {
        this.pipelineList = pipelineList;
    }

    public GroupPipelineDTO id(int id) {
        setId(id);
        return this;
    }

    public GroupPipelineDTO name(String name) {
        setName(name);
        return this;
    }

    public GroupPipelineDTO pipelineList(List<PipelineDTO> pipelineList) {
        setPipelineList(pipelineList);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GroupPipelineDTO)) {
            return false;
        }
        GroupPipelineDTO groupPipelineDTO = (GroupPipelineDTO) o;
        return id == groupPipelineDTO.id && Objects.equals(name, groupPipelineDTO.name) && Objects.equals(pipelineList, groupPipelineDTO.pipelineList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pipelineList);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", pipelineList='" + getPipelineList() + "'" +
            "}";
    }
}

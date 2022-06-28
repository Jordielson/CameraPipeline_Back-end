package com.camerapipeline.camera_pipeline.model.pipeline;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.user.User;

@Entity
public class GroupPipeline {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    @OneToMany(mappedBy = "groupPipeline")
    private List<Pipeline> pipelineList;
    @NotNull
    @ManyToOne
    private User user;


    public GroupPipeline() {
    }


    public GroupPipeline(int id, String name, List<Pipeline> pipelineList, User user) {
        this.id = id;
        this.name = name;
        this.pipelineList = pipelineList;
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GroupPipeline user(User user) {
        setUser(user);
        return this;
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

    public List<Pipeline> getPipelineList() {
        return this.pipelineList;
    }

    public void setPipelineList(List<Pipeline> pipelineList) {
        this.pipelineList = pipelineList;
    }

    public GroupPipeline id(int id) {
        setId(id);
        return this;
    }

    public GroupPipeline name(String name) {
        setName(name);
        return this;
    }

    public GroupPipeline pipelineList(List<Pipeline> pipelineList) {
        setPipelineList(pipelineList);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GroupPipeline)) {
            return false;
        }
        GroupPipeline groupPipeline = (GroupPipeline) o;
        return id == groupPipeline.id && Objects.equals(name, groupPipeline.name) && Objects.equals(pipelineList, groupPipeline.pipelineList);
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

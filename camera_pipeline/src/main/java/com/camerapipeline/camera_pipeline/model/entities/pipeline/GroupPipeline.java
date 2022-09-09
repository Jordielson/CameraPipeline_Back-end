package com.camerapipeline.camera_pipeline.model.entities.pipeline;

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

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.user.User;

@Entity
public class GroupPipeline implements ModelAbstract<Integer> {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    @OneToMany(mappedBy = "groupPipeline")
    private List<Pipeline> pipelineList;
    @NotNull
    @ManyToOne
    private User user;

    public GroupPipeline() {
    }

    public GroupPipeline(Integer id, String name, List<Pipeline> pipelineList, User user) {
        this.id = id;
        this.name = name;
        this.pipelineList = pipelineList;
        this.user = user;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GroupPipeline id(Integer id) {
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

    public GroupPipeline user(User user) {
        setUser(user);
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
        return Objects.equals(id, groupPipeline.id) && Objects.equals(name, groupPipeline.name) && Objects.equals(pipelineList, groupPipeline.pipelineList) && Objects.equals(user, groupPipeline.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pipelineList, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", pipelineList='" + getPipelineList() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }
}

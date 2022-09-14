package com.camerapipeline.camera_pipeline.model.entities.pdi;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.model.enums.Category;

@Entity(name = "model_pdi")
public class ModelPDI implements ModelAbstract<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(unique=true, length = 60)
    private String name;

    @NotBlank
    private String URL;

    @OneToMany(mappedBy = "modelPdi")
    private List<Parameter> parameters;

    @Enumerated(EnumType.ORDINAL)
    private Category category;

    @ManyToOne
    private User user;

    public ModelPDI() {
    }

    public ModelPDI(Integer id, String name, String URL, List<Parameter> parameters, Category category, User user) {
        this.id = id;
        this.name = name;
        this.URL = URL;
        this.parameters = parameters;
        this.category = category;
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

    public String getURL() {
        return this.URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public List<Parameter> getParameters() {
        return this.parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ModelPDI id(Integer id) {
        setId(id);
        return this;
    }

    public ModelPDI name(String name) {
        setName(name);
        return this;
    }

    public ModelPDI URL(String URL) {
        setURL(URL);
        return this;
    }

    public ModelPDI parameters(List<Parameter> parameters) {
        setParameters(parameters);
        return this;
    }

    public ModelPDI category(Category category) {
        setCategory(category);
        return this;
    }

    public ModelPDI user(User user) {
        setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ModelPDI)) {
            return false;
        }
        ModelPDI modelPDI = (ModelPDI) o;
        return Objects.equals(id, modelPDI.id) && Objects.equals(name, modelPDI.name) && Objects.equals(URL, modelPDI.URL) && Objects.equals(parameters, modelPDI.parameters) && Objects.equals(category, modelPDI.category) && Objects.equals(user, modelPDI.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, URL, parameters, category, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", URL='" + getURL() + "'" +
            ", parameters='" + getParameters() + "'" +
            ", category='" + getCategory() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }
}

package com.camerapipeline.camera_pipeline.model.entities.pdi;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
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
import com.camerapipeline.camera_pipeline.model.enums.ParameterType;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
public class Parameter implements ModelAbstract<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    private String description;

    @Enumerated(EnumType.ORDINAL)
    private ParameterType type;

    @Column(name="required")
    private boolean required;

    @Column(name="\"index\"")
    private Integer index;

    @OneToMany(mappedBy = "parameter", cascade = CascadeType.REMOVE)
    private List<ValueParameter> valueParameters;

    @ManyToOne
    private ModelPDI modelPdi;

    @Override
    public User getUser() {
        return modelPdi.getUser();
    }

    @Override
    public void setUser(User user) {
        modelPdi.setUser(user);
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Parameter)) {
            return false;
        }
        Parameter parameter = (Parameter) o;
        return Objects.equals(id, parameter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, type, required, index, modelPdi);
    }


}

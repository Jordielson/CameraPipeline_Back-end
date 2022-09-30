package com.camerapipeline.camera_pipeline.model.entities.pdi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;


import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.model.enums.ParameterType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
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


}

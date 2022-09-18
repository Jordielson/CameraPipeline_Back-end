package com.camerapipeline.camera_pipeline.model.entities.pdi;

import java.util.List;

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
import com.camerapipeline.camera_pipeline.model.enums.Category;

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

    @OneToMany(mappedBy = "modelPdi", cascade = CascadeType.ALL)
    private List<PDI> pdiList;

    @OneToMany(mappedBy = "modelPdi", cascade = CascadeType.ALL)
    private List<Parameter> parameters;

    @Enumerated(EnumType.ORDINAL)
    private Category category;

    @ManyToOne
    private User user;
}

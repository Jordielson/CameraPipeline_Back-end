package com.camerapipeline.camera_pipeline.model.entities.pdi;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@Entity
@Table(name = "model_pdi")
public class ModelPDI extends DigitalProcess {

    @NotBlank
    private String URL;

    @OneToMany(mappedBy = "digitalProcess", cascade = CascadeType.ALL)
    private List<PDI> pdiList;

    @OneToMany(mappedBy = "modelPdi", fetch=FetchType.EAGER , cascade = CascadeType.REMOVE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Parameter> parameters;
}

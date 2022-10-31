package com.camerapipeline.camera_pipeline.model.entities.pdi;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.entities.user.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Data
@Entity
public class PDI implements ModelAbstract<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    protected Integer index;
    
    @NotNull
    @ManyToOne
    private DigitalProcess digitalProcess;

    @Embedded
    private Position position;

    @ElementCollection
    @CollectionTable(name="children")
    @Column(name="child")
    private List<Integer> children;
    
    @NotNull
    @ManyToOne
    private Pipeline pipeline;

    @OneToMany(mappedBy = "pdi", cascade = CascadeType.REMOVE)
    private List<ValueParameter> valueParameters;

    @Override
    public User getUser() {
        return digitalProcess.getUser();
    }

    @Override
    public void setUser(User user) {
        digitalProcess.setUser(user);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PDI)) {
            return false;
        }
        PDI pDI = (PDI) o;
        return Objects.equals(id, pDI.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, digitalProcess, valueParameters);
    }
}
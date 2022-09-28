package com.camerapipeline.camera_pipeline.model.entities.pdi;

import java.util.List;

import javax.persistence.CascadeType;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Data
@Entity
public class PDI implements ModelAbstract<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @ManyToOne
    private ModelPDI modelPdi;

    @NotNull
    @ManyToOne
    private Pipeline pipeline;

    @OneToMany(mappedBy = "pdi", cascade = CascadeType.REMOVE)
    private List<ValueParameter> valueParameters;

    @Override
    public User getUser() {
        return pipeline.getUser();
    }

    @Override
    public void setUser(User user) {
        pipeline.setUser(user);
    }
}

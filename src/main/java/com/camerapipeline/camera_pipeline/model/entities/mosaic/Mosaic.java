package com.camerapipeline.camera_pipeline.model.entities.mosaic;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
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

import lombok.Data;

@Data
@Entity
public class Mosaic implements ModelAbstract<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(length = 60)
    private String name;

    @NotNull
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "mosaic")
    List<MosaicPipeline> mosaicPipelineList;

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mosaic)) {
            return false;
        }
        Mosaic mosaic = (Mosaic) o;
        return Objects.equals(id, mosaic.id) 
            && Objects.equals(name, mosaic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user);
    }

}

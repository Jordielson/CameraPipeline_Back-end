package com.camerapipeline.camera_pipeline.model.entities.pdi;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.user.User;

import lombok.Data;

@Entity
@Data
public class ValueParameter implements ModelAbstract<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String value;

    @ManyToOne
    private Parameter parameter;

    @ManyToOne
    private PDI pdi;


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ValueParameter)) {
            return false;
        }
        ValueParameter valueParameter = (ValueParameter) o;
        return Objects.equals(id, valueParameter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, parameter, pdi);
    }


    @Override
    public User getUser() {
        return pdi.getUser();
    }

    @Override
    public void setUser(User user) {
        pdi.setUser(user);
    }
}

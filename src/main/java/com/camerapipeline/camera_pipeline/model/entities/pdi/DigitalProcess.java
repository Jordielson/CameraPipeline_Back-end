package com.camerapipeline.camera_pipeline.model.entities.pdi;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DigitalProcess implements ModelAbstract<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotBlank
    @Column(length = 60)
    private String name;
    
    private String description;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    protected ZonedDateTime creationTime;

    @NotNull
    @ManyToOne
    protected User user;

    @OneToMany(mappedBy = "digitalProcess", cascade = CascadeType.REMOVE)
    private List<PDI> pdis;

    @Override
    public User getUser() {
        return this.user;
    }
    
    @Override
    public void setUser(User user) {
        this.user = user;
    }
}


package com.camerapipeline.camera_pipeline.model.entities.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class User implements UserDetails, ModelAbstract<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    @Email(message="invalid email")
    private String email;
    @NotBlank
	@Size(min = 6, message = "Password must be longer than 6 characters")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "role_user", 
		joinColumns = @JoinColumn(
			name = "user_id", 
			referencedColumnName = "id"
		),
		inverseJoinColumns = @JoinColumn(
			name = "role_id",
			referencedColumnName = "role"
		),
        uniqueConstraints = { @UniqueConstraint(
            name = "UniqueRoleAndUser", 
            columnNames = { "user_id", "role_id" }) 
        }
	)
	private List<Role> roles = new ArrayList<Role>();

    @OneToMany(mappedBy = "user")
    private List<Pipeline> pipelines;
    @OneToMany(mappedBy = "user")
    private List<Camera> cameras;

    public User id(int id) {
        setId(id);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User roles(List<Role> roles) {
        setRoles(roles);
        return this;
    }

    public User cameras(List<Camera> cameras) {
        setCameras(cameras);
        return this;
    }

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

    @Override
    public User getUser() {
        return this;
    }

    @Override
    public void setUser(User user) {
        setId(user.id);
        setEmail(user.email);
        setCameras(user.cameras);
        setPipelines(user.pipelines);
        setPassword(user.password);
        setRoles(user.roles);
    }
    
}
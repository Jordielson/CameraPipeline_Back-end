package com.camerapipeline.camera_pipeline.model.entities.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
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
import com.camerapipeline.camera_pipeline.model.entities.pipeline.GroupPipeline;

@Entity
public class User implements UserDetails, ModelAbstract<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    private List<GroupPipeline> groupPipeline;
    @OneToMany(mappedBy = "user")
    private List<Camera> cameras;


    public User() {
    }

    public User(Integer id, String email, String password, List<Role> roles, List<GroupPipeline> groupPipeline, List<Camera> cameras) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.groupPipeline = groupPipeline;
        this.cameras = cameras;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<GroupPipeline> getGroupPipeline() {
        return this.groupPipeline;
    }

    public void setGroupPipeline(List<GroupPipeline> groupPipeline) {
        this.groupPipeline = groupPipeline;
    }

    public List<Camera> getCameras() {
        return this.cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }

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

    public User groupPipeline(List<GroupPipeline> groupPipeline) {
        setGroupPipeline(groupPipeline);
        return this;
    }

    public User cameras(List<Camera> cameras) {
        setCameras(cameras);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && Objects.equals(groupPipeline, user.groupPipeline) && Objects.equals(cameras, user.cameras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, roles, groupPipeline, cameras);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", roles='" + getRoles() + "'" +
            ", groupPipeline='" + getGroupPipeline() + "'" +
            ", cameras='" + getCameras() + "'" +
            "}";
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
    
}
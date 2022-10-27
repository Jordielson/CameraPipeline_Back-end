package com.camerapipeline.camera_pipeline.model.entities.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.camerapipeline.camera_pipeline.core.validation.ExtendedEmailValidator;
import com.camerapipeline.camera_pipeline.model.entities.ModelAbstract;
import com.camerapipeline.camera_pipeline.model.entities.input.PipelineInput;
import com.camerapipeline.camera_pipeline.model.entities.pdi.DigitalProcess;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Setter
@Getter
@Table(name="users")
public class User implements UserDetails, ModelAbstract<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    @ExtendedEmailValidator
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
	private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<DigitalProcess> digitalProcess = new HashSet<>();
    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<PipelineInput> pipelineInputs = new HashSet<>();

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

    public User roles(Set<Role> roles) {
        setRoles(roles);
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
        setPipelineInputs(user.pipelineInputs);
        setDigitalProcess(user.digitalProcess);
        setPassword(user.password);
        setRoles(user.roles);
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	
	
    
   
    
}
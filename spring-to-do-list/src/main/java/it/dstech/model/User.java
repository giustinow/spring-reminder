package it.dstech.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	
	private String email;
	
	private String password;
	
	private Boolean active;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_image", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Image image;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_ruolo", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
    private Set<Ruolo> ruolo;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Activity> listaActivity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Set<Ruolo> getRuolo() {
		return ruolo;
	}
	public void setRuolo(Set<Ruolo> ruolo) {
		this.ruolo = ruolo;
	}
	
	public List<Activity> getListaActivity() {
		return listaActivity;
	}
	public void setListaActivity(List<Activity> listaActivity) {
		this.listaActivity = listaActivity;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
}

package it.dstech.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_id")
	private Long id;
	
	private String titolo;
	
	private String tipo;
	
	private String descrizione;
	
	private String data;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime scadenza;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public LocalDateTime getScadenza() {
		return scadenza;
	}

	public void setScadenza(LocalDateTime scadenza) {
		this.scadenza = scadenza;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

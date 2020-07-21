package it.dstech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ruolo {

 	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ruolo_id")
    private int id;
    @Column(name = "ruolo")
    private String ruolo;

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
}

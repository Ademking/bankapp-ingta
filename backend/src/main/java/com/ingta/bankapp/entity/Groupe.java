package com.ingta.bankapp.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Groupes")
public class Groupe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codeGroupe;

	private String nomGroupe;

	@ManyToMany(mappedBy = "groupes")
	@JsonIgnore
	private Collection<Employes> employes;

	public Groupe() {
	}

	public Groupe(String nomString) {
		this.nomGroupe = nomString;
	}

	public Groupe(String nomGroupe, Collection<Employes> employes) {
		super();
		this.nomGroupe = nomGroupe;
		this.employes = employes;
	}

	public Long getCodeGroupe() {
		return codeGroupe;
	}

	public void setCodeGroupe(Long codeGroupe) {
		this.codeGroupe = codeGroupe;
	}

	public String getNomGroupe() {
		return nomGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}

	public Collection<Employes> getEmployes() {
		return employes;
	}

	public void setEmployes(Collection<Employes> employes) {
		this.employes = employes;
	}

	@Override
	public String toString() {
		return "Groupe [codeGroupe=" + codeGroupe + ", nomGroupe=" + nomGroupe + ", employes=" + employes
				+ "]";
	}

}

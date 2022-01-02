package com.ingta.bankapp.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Employes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codeEmploye;

	private String nomEmploye;

	@ManyToOne
	@JoinColumn(name = "Code_emp_sup")
	private Employes employesSup;

	@ManyToMany
	@JoinTable(name = "Emp_Gr", joinColumns = @JoinColumn(name = "Num_EMP"), inverseJoinColumns = @JoinColumn(name = "Num_groupe"))
	private Collection<Groupe> groupes;

	public Employes() {
	}

	public Employes(String nomEmploye) {
		super();
		this.nomEmploye = nomEmploye;
	}

	public Employes(String nomEmploye, Employes employesSup, Collection<Groupe> groupes) {
		super();
		this.nomEmploye = nomEmploye;
		this.employesSup = employesSup;
		this.groupes = groupes;
	}

	public Long getcodeEmploye() {
		return codeEmploye;
	}

	public void setcodeEmploye(Long codeEmploye) {
		this.codeEmploye = codeEmploye;
	}

	public String getNomEmploye() {
		return nomEmploye;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public Employes getEmployesSup() {
		return employesSup;
	}

	public void setEmployesSup(Employes employesSup) {
		this.employesSup = employesSup;
	}

	public Collection<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(Collection<Groupe> groupes) {
		this.groupes = groupes;
	}

	@Override
	public String toString() {
		return "Employes [codeEmploye=" + codeEmploye + ", nomEmploye=" + nomEmploye
				+ ", employesSup=" + employesSup + ", groupes=" + groupes + "]";
	}

}

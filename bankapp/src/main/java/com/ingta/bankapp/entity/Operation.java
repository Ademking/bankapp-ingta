package com.ingta.bankapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(length = 1)
@SuppressWarnings("serial")
public abstract class Operation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long numeroOperation;

	private String dateOperation;

	private double montant;

	@ManyToOne
	@JoinColumn(name = "Code_CPT")
	private Compte compte;

	@ManyToOne
	@JoinColumn(name = "Code_Employe")
	private Employes employes;

	public Operation() {
	}

	public Operation(double montant, Compte compte, Employes employes) {
		super();
		this.dateOperation = (new Date()).toString();
		this.montant = montant;
		this.compte = compte;
		this.employes = employes;
	}

	public Long getNumeroOperation() {
		return numeroOperation;
	}

	public void setNumeroOperation(Long numeroOperation) {
		this.numeroOperation = numeroOperation;
	}

	public String getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(String dateOperation) {
		this.dateOperation = dateOperation;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Employes getEmployes() {
		return employes;
	}

	public void setEmployes(Employes employes) {
		this.employes = employes;
	}

	@Override
	public String toString() {
		return "Operation [numeroOperation=" + numeroOperation + ", dateOperation=" + dateOperation + ", montant="
				+ montant + ", compte=" + compte + ", employes=" + employes + "]";
	}

}

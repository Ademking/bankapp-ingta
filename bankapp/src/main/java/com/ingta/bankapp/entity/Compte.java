package com.ingta.bankapp.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type_CPT", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Compte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codeCompte;

	private String dateCreation;

	private double solde;

	@ManyToOne
	@JoinColumn(name = "Code_cli")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "Code_Emp")
	private Employes employes;

	@OneToMany(mappedBy = "compte")
	private Collection<Operation> operations;

	public Compte() {
	}

	public Compte(double solde, Client client, Employes employes) {
		this.solde = solde;
		this.client = client;
		this.employes = employes;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		
		this.dateCreation = formatter.format(date);
	}

	public Compte(String dateCreation, double solde, Client client, Employes employes,
			Collection<Operation> operations) {
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.client = client;
		this.employes = employes;
		this.operations = operations;
	}

	private void submontant(double solde) {
		this.solde = this.solde - solde;
	}

	private void addmontant(double d) {
		this.solde += d;
	}

	public void setaddmontant(double d) {
		if (d != 0) {
			this.addmontant(d);
		}
	}

	public void setsubmontant(double s) {
		if (s == 0) {
			System.out.print("can't sub from solde");
		} else {
			this.submontant(s);
		}
	}

	public Long getCodeCompte() {
		return codeCompte;
	}

	public void setCodeCompte(Long codeCompte) {
		this.codeCompte = codeCompte;
	}

	@PreUpdate
	protected void preUpdate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		
		this.dateCreation = formatter.format(date);
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Employes getEmployes() {
		return employes;
	}

	public void setEmployes(Employes employes) {
		this.employes = employes;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
	
	@Column(name = "Type_CPT", insertable = false, updatable = false)
    private String type_CPT;
	
	public String getType_CPT() {
		return type_CPT;
	}

	public void setType_CPT(String type_CPT) {
		this.type_CPT = type_CPT;
	}


	
	

}

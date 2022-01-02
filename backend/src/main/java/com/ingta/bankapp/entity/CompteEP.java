package com.ingta.bankapp.entity;

import java.util.Collection;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
public class CompteEP extends Compte {

	public CompteEP() {
		super();
	}

	public CompteEP(String dateCreation, double solde, Client client, Employes employes,
			Collection<Operation> operations) {
		super(dateCreation, solde, client, employes, operations);
	}
	
	public CompteEP( double solde, Client client, Employes employes) {
		super( solde, client, employes);
	}

}

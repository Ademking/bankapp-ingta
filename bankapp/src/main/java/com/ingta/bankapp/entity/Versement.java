package com.ingta.bankapp.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("v")
public class Versement extends Operation {

	public Versement() {
	}

	public Versement(double montant, Compte compte, Employes employes) {
		super(montant, compte, employes);
	}

}

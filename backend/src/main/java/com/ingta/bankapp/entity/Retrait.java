package com.ingta.bankapp.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {

	public Retrait() {
	}

	public Retrait(double montant, Compte compte, Employes employes) {
		super(montant, compte, employes);
	}

}

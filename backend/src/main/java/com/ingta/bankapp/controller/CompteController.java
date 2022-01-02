package com.ingta.bankapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ingta.bankapp.dao.ClientRepo;
import com.ingta.bankapp.dao.CompteCCDAO;
import com.ingta.bankapp.dao.CompteEPDAO;
import com.ingta.bankapp.dao.EmployeeDAO;
import com.ingta.bankapp.entity.Client;
import com.ingta.bankapp.entity.Compte;
import com.ingta.bankapp.entity.CompteCC;
import com.ingta.bankapp.entity.CompteEP;
import com.ingta.bankapp.entity.Employes;
import com.ingta.bankapp.entity.Groupe;

@RestController
public class CompteController {

	public CompteCCDAO ccDAO;
	public CompteEPDAO epDAO;
	public ClientRepo cr;
	public EmployeeDAO emps;

	CompteController(CompteCCDAO ccDAO, CompteEPDAO epDAO, ClientRepo cr, EmployeeDAO emps) {
		this.ccDAO = ccDAO;
		this.epDAO = epDAO;
		this.cr = cr;
		this.emps = emps;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/accounts")
	List<Compte> all() {
		List<Compte> accounts = new ArrayList<Compte>();
		List<CompteCC> ccList = this.ccDAO.findAll();
		List<CompteEP> epList = this.epDAO.findAll();
		accounts.addAll(ccList);
		accounts.addAll(epList);
		return accounts;
	}

	// end::get-aggregate-root[]
	@CrossOrigin()
	@PostMapping("/accounts/{empId}/{clientId}/{accountType}")
	Compte newAccount(@RequestBody CompteCC newAccount, @PathVariable String empId, @PathVariable String clientId,
			@PathVariable String accountType) {

		if (accountType.equals("CC")) {
			CompteCC cc = new CompteCC();
			Employes emp = this.emps.findById(Long.parseLong(empId)).orElse(null);
			Client client = this.cr.findById(Long.parseLong(clientId)).orElse(null);

			System.out.println(client);
			cc.setClient(client);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			cc.setDateCreation(formatter.format(date));
			cc.setEmployes(emp);
			cc.setSolde(newAccount.getSolde());
			cc.setDecouvert(newAccount.getDecouvert());

			return ccDAO.save(cc);
		} else {

			CompteEP ep = new CompteEP();
			Employes emp = this.emps.findById(Long.parseLong(empId)).orElse(null);
			Client client = this.cr.findById(Long.parseLong(clientId)).orElse(null);

			System.out.println(client);
			ep.setClient(client);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			ep.setDateCreation(formatter.format(date));
			ep.setEmployes(emp);
			ep.setSolde(newAccount.getSolde());

			return epDAO.save(ep);

		}

	}

	@PutMapping("/accounts/cc/{id}/{clientId}/{empId}")
	CompteCC replaceAccountCC(@RequestBody CompteCC newAccount, @PathVariable Long id, 
			@PathVariable Long clientId, @PathVariable Long empId) {

		CompteCC ccOld = this.ccDAO.findById(id).orElseThrow();
		Client newClient = this.cr.findById(clientId).orElseThrow();
		Employes newEmp = this.emps.findById(empId).orElseThrow();

		ccOld.setClient(newClient);
		ccOld.setEmployes(newEmp);
		ccOld.setSolde(newAccount.getSolde());
		ccOld.setDecouvert(newAccount.getDecouvert());
		return ccDAO.save(ccOld);
	}
	
	@PutMapping("/accounts/ce/{id}/{clientId}/{empId}")
	CompteEP replaceAccountCE(@RequestBody CompteCC newAccount, @PathVariable Long id, 
			@PathVariable Long clientId, @PathVariable Long empId) {

		CompteEP ceOld = this.epDAO.findById(id).orElseThrow();
		Client newClient = this.cr.findById(clientId).orElseThrow();
		Employes newEmp = this.emps.findById(empId).orElseThrow();

		ceOld.setClient(newClient);
		ceOld.setEmployes(newEmp);
		ceOld.setSolde(newAccount.getSolde());
		return epDAO.save(ceOld);
	}

	// Single item
	@GetMapping("/accounts/{id}")
	Compte one(@PathVariable Long id) {
		return ccDAO.findById(id).orElseThrow(() -> new RuntimeException("No Account found"));
	}

	@DeleteMapping("/accounts/{id}/{type}")
	void deleteAccount(@PathVariable Long id, @PathVariable String type) {
		if (type.equals("CC")) {
			ccDAO.deleteById(id);
		} else if (type.equals("CE")) {
			epDAO.deleteById(id);
		} else {

		}

	}
}

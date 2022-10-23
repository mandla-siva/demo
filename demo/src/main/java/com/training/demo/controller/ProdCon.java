package com.training.demo.controller;

import java.util.NoSuchElementException;

import javax.annotation.security.RolesAllowed;

import com.training.demo.model.Prodct;
import com.training.demo.service.BillpayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Payment") // Root url
public class ProdCon {
	@Autowired
	public BillpayService payService;

	
	@GetMapping("getAll")
	@RolesAllowed("user_siva")
	public Iterable<Prodct> paymentsList() {
		return payService.listAllPayments();
	}

	@GetMapping("select/{id}") // sub url http://localhost:8080/Payment/select/1
	@RolesAllowed({"user_siva"})
	public ResponseEntity<Prodct> get(@PathVariable int id) {
		try {
			Prodct prod = payService.getPayment(id);
			return new ResponseEntity<Prodct>(prod, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Prodct>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("insert") // http://localhost:8080/Payment/insert
	@RolesAllowed("user_siva")
	public void add(@RequestBody Prodct prod) {
		payService.postPayment(prod);
	}

	@PutMapping("update/{id}") // http://localhost:8080/Payment/update/1
	@RolesAllowed("user_siva")
	public ResponseEntity<Prodct> update(@PathVariable int id, @RequestBody Prodct prod) {
		
		//Prodct obj = payService.getPayment(id);
		
//		obj.setQuantity(prod.getQuantity());
//		obj.setSku_Cost(prod.getSku_Cost());
//		obj.setSku_Name(prod.getSku_Name());
//		obj.setTotal_Cost(prod.getTotal_Cost());
	//	payService.postPayment(obj);
	//	return new ResponseEntity<Prodct>(obj,HttpStatus.OK);
		
		 try {
	            Prodct exist=payService.getPayment(id);
	            prod.setId(id);
	            payService.update(prod);
	            return new  ResponseEntity<>(prod,HttpStatus.OK);
	            }catch(Exception e) {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	}

	@DeleteMapping("delete/{id}") // http://localhost:8080/Payment/update
	@RolesAllowed("user_siva")
	public String delete(@PathVariable int id) {
		Prodct obj = payService.getPayment(id);
		payService.deletePayment(obj);
		return "{ GIven ID " + id + " has been Deleted }";
	}
}
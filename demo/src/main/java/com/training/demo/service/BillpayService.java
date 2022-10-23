package com.training.demo.service;

import java.util.List;

import com.training.demo.dao.ProductRepository;
import com.training.demo.model.Prodct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillpayService {
	@Autowired
	private ProductRepository prodRepo;

	public Iterable<Prodct> listAllPayments() {
		return prodRepo.findAll();
	}

	public Prodct getPayment(int id) {
		return prodRepo.findById(id).get();
	}

	public Prodct postPayment(Prodct prod) {
		return prodRepo.save(prod);
	}
	
	public void deletePayment(Prodct prod) {
		prodRepo.delete(prod);
	}
	public void update(Prodct prod) {
		prodRepo.save(prod);
	}
}

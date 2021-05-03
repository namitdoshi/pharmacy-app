package com.cognizant.pharmacysupply.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.exception.TokenValidationFailedException;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupply;
import com.cognizant.pharmacysupply.service.PharmacyServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PharmacyController {
	
	@Autowired
	private PharmacyServiceImpl pharmacyService;
	
	@PostMapping("/pharmacy-supply")
	public List<PharmacyMedicineSupply> getPharmacySupply(@Valid @RequestBody List<MedicineDemand> medicineDemandList) throws MedicineNotFoundException {
		log.info("Start");

		log.debug("medicineDemandList {}:", medicineDemandList);
		List<PharmacyMedicineSupply> pharmacySupply = null;
//		if (pharmacyService.validateToken(token)) {
//			pharmacySupply = pharmacyService.getPharmacySupplyCount(token, medicineDemandList);
			pharmacySupply = pharmacyService.getPharmacySupplyCount(medicineDemandList);
			
			if (pharmacySupply == null)  {
				return pharmacySupply;
			}
			
			log.info("End");
			return pharmacySupply;
//		}
//		log.info("End");
//		throw new TokenValidationFailedException("Token is no longer valid");
	}
	

	@GetMapping("/getMedicineSupply")
	public List<PharmacyMedicineSupply> getMedicineSupply() {
		List<PharmacyMedicineSupply> medicineSupply = null;
			medicineSupply = pharmacyService.getMedicineSupply();
			return medicineSupply;
	}


	@GetMapping("/getMedicineDemand")
	public List<MedicineDemand> getMedicineDemand() {
		List<MedicineDemand> medicineDemand = null;
			medicineDemand = pharmacyService.getMedicineDemand();
			return medicineDemand;
	}

}

package com.cognizant.pharmacysupply.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.exception.TokenValidationFailedException;
import com.cognizant.pharmacysupply.feignclient.AuthFeignClient;
import com.cognizant.pharmacysupply.model.JwtResponse;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.MedicineStock;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupply;
import com.cognizant.pharmacysupply.repository.MedicineDemandRepository;
import com.cognizant.pharmacysupply.repository.PharmacyMedicineSupplyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PharmacyServiceImpl implements PharmacyService {
	@Autowired
	private MedicineDemandRepository medicineDemandRepo;

	@Autowired
	private PharmacyMedicineSupplyRepository pharmacyMedicineSupplyRepository;

	@Autowired
	private MedicineDemandRepository medicineDemandRepository;

	@Autowired
	private AuthFeignClient authFeign;

	@Override
	public List<PharmacyMedicineSupply> getPharmacySupplyCount(String token, List<MedicineDemand> medicineDemandList)
			throws MedicineNotFoundException {
		log.info("Start");
		log.info("Medicine Demand List {} ", medicineDemandList);
		List<PharmacyMedicineSupply> list = new ArrayList<>();

		for (MedicineDemand medicineDemand : medicineDemandList) {
			PharmacyMedicineSupply pharmacyMedicineSupply = new PharmacyMedicineSupply();
			MedicineStock medicineStock = getNumberOfTablets(medicineDemand);
			log.info("{}", medicineStock);
			log.info("Medicine {} , Tablets {}", medicineDemand.getMedicineName(), medicineStock.getTabletsInStock());

			if (medicineStock.getTabletsInStock() < medicineDemand.getDemandCount()) {
				return null;
			}

			setSupply(pharmacyMedicineSupply, medicineDemand, medicineStock);
			list.add(pharmacyMedicineSupply);
		}
		pharmacyMedicineSupplyRepository.saveAll(list);
		medicineDemandRepository.saveAll(medicineDemandList);
		log.info("End");
		return list;
	}

	public void setSupply(PharmacyMedicineSupply medicineSupply, MedicineDemand medicineDemand,
			MedicineStock medicineStock) throws MedicineNotFoundException {
		log.info("Start");
		int val = 0;
		log.info("number of tablets {}", medicineStock.getTabletsInStock());
		if (medicineStock.getTabletsInStock() < medicineDemand.getDemandCount()) {
			medicineSupply.setSupplyCount(medicineStock.getTabletsInStock());

		} else {
			medicineSupply.setSupplyCount(medicineDemand.getDemandCount());
			val = medicineStock.getTabletsInStock() - medicineDemand.getDemandCount();

		}
		log.info("val = {}", val);

		medicineSupply.setMedicineName(medicineDemand.getMedicineName());
		log.info("medicineDemand {} medicineSupply {}", medicineDemand, medicineSupply);
		medicineSupply.setPharmacyName(medicineStock.getPharmacyName());
		log.info("medicineSupply{}:", medicineSupply);
		log.info("End");
	}

	public MedicineStock getNumberOfTablets(MedicineDemand medicineDemand) throws MedicineNotFoundException {
		// TODO Auto-generated method stub
		log.info("Start");
		MedicineStock medicineStock = null;
		log.info("Medicine : {}", medicineDemand);

		if (medicineStock == null) {
			throw new MedicineNotFoundException("Medicine not found");
		}
		log.info("End");
		return medicineStock;
	}

	@Override
	public List<MedicineDemand> getMedicineDemand() {
		log.info("Start");
		return medicineDemandRepo.findAll();
	}

	@Override
	public List<PharmacyMedicineSupply> getMedicineSupply() {
		log.info("Start");
		return pharmacyMedicineSupplyRepository.findAll();
	}

	@Override
	public Boolean validateToken(String token) {
		log.info("Validating token");
		JwtResponse jwtResponse = authFeign.verifyToken(token);

		if (jwtResponse.isValid())
			return true;
		throw new TokenValidationFailedException("Token is no longer valid");
	}
}

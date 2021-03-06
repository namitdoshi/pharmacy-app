package com.cognizant.medicinestock.service;

import java.util.List;

import com.cognizant.medicinestock.model.MedicineStock;

public interface MedicineStockService {

	public List<MedicineStock> getMedicineStockInformation();
	public MedicineStock getNumberOfTabletsInStockByName(String medicine);
	public Boolean updateNumberOfTabletsInStockByName(String medicine, int count);
	public List<MedicineStock> getMedicineByTargetAilment(String treatingAilment);
}

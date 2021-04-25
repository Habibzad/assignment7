package com.meritamerica.assignment6.service;

import java.util.List;

import com.meritamerica.assignment6.exceptions.InvalidArgumentException;
import com.meritamerica.assignment6.models.CDOffering;

public interface CDOfferingService {
	
	public CDOffering addCDOffering(CDOffering cdOffering) throws InvalidArgumentException;

	public List<CDOffering> getCDOfferings();
	
}

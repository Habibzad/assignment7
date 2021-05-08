package com.meritamerica.assignment7.service;

import java.util.List;

import com.meritamerica.assignment7.exceptions.InvalidArgumentException;
import com.meritamerica.assignment7.models.CDOffering;

public interface CDOfferingService {
	
	public CDOffering addCDOffering(CDOffering cdOffering) throws InvalidArgumentException;

	public List<CDOffering> getCDOfferings();
	
}

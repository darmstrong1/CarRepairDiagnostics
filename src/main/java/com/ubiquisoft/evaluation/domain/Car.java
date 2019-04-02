package com.ubiquisoft.evaluation.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

	private String year;
	private String make;
	private String model;

	private List<Part> parts;

	public Map<PartType, Integer> getMissingPartsMap() {
		/*
		 * Return map of the part types missing.
		 *
		 * Each car requires one of each of the following types:
		 *      ENGINE, ELECTRICAL, FUEL_FILTER, OIL_FILTER
		 * and four of the type: TIRE
		 *
		 * Example: a car only missing three of the four tires should return a map like this:
		 *
		 *      {
		 *          "TIRE": 3
		 *      }
		 */
		Map<PartType, Integer> missingParts = new HashMap<>();

		if(parts != null) {
			List<Part> tires = parts.stream().filter(p -> p.getType().equals(PartType.TIRE)).collect(Collectors.toList());
			if(tires.size() < 4) {
				missingParts.put(PartType.TIRE, 4 - tires.size());
			}
			checkforMissingPart(PartType.ENGINE, missingParts);
			checkforMissingPart(PartType.ELECTRICAL, missingParts);
			checkforMissingPart(PartType.FUEL_FILTER, missingParts);
			checkforMissingPart(PartType.OIL_FILTER, missingParts);
		}

		return missingParts;
	}

	private void checkforMissingPart(PartType type, Map<PartType, Integer> missingParts) {
		if(!parts.stream().filter(p -> p.getType().equals(type)).findFirst().isPresent()) {
			missingParts.put(type, 1);
		}
	}

	@Override
	public String toString() {
		return "Car{" +
				       "year='" + year + '\'' +
				       ", make='" + make + '\'' +
				       ", model='" + model + '\'' +
				       ", parts=" + parts +
				       '}';
	}

	/* --------------------------------------------------------------------------------------------------------------- */
	/*  Getters and Setters *///region
	/* --------------------------------------------------------------------------------------------------------------- */

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	/* --------------------------------------------------------------------------------------------------------------- */
	/*  Getters and Setters End *///endregion
	/* --------------------------------------------------------------------------------------------------------------- */

}

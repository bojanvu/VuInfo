package com.algebra.vuinfo.models;

public class Country {

	private String countryId;
	private String countryName;
	private String countryOfficialName;
	private String countryStatus;
	private String countryDomain;
	private String officialLanguages;
	private String countryCurrency;
	private String countryTimeZones;
	private String countryCapital;
	private String countryCode;
	private String countryCallingCode;
	private String countryArea;
	private String perWaterArea;
	private String countryPopulation;
	private String populationPerArea;
	private String populationPerNumber;
	private String populationPerDensity;
	private String countryGdp;
	private String gdpPerPerson;
	private String countryPresident;
	private String countryPrimeMinister;
	private String countryIndependance;

	public Country() {
	}

	public Country(String countryName, String countryStatus,
			String countryDomain) {
		super();
		this.countryName = countryName;
		this.countryStatus = countryStatus;
		this.countryDomain = countryDomain;
	}

	public Country(String countryName, String countryOfficialName,
			String countryStatus, String countryDomain,
			String officialLanguages, String countryCurrency,
			String countryTimeZones, String countryCapital, String countryCode,
			String countryCallingCode, String countryArea, String perWaterArea,
			String countryPopulation, String populationPerArea,
			String populationPerNumber, String populationPerDensity,
			String countryGdp, String gdpPerPerson, String countryPresident,
			String countryPrimeMinister, String countryIndependance) {
		super();
		this.countryName = countryName;
		this.countryOfficialName = countryOfficialName;
		this.countryStatus = countryStatus;
		this.countryDomain = countryDomain;
		this.officialLanguages = officialLanguages;
		this.countryCurrency = countryCurrency;
		this.countryTimeZones = countryTimeZones;
		this.countryCapital = countryCapital;
		this.countryCode = countryCode;
		this.countryCallingCode = countryCallingCode;
		this.countryArea = countryArea;
		this.perWaterArea = perWaterArea;
		this.countryPopulation = countryPopulation;
		this.populationPerArea = populationPerArea;
		this.populationPerNumber = populationPerNumber;
		this.populationPerDensity = populationPerDensity;
		this.countryGdp = countryGdp;
		this.gdpPerPerson = gdpPerPerson;
		this.countryPresident = countryPresident;
		this.countryPrimeMinister = countryPrimeMinister;
		this.countryIndependance = countryIndependance;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCallingCode() {
		return countryCallingCode;
	}

	public void setCountryCallingCode(String countryCallingCode) {
		this.countryCallingCode = countryCallingCode;
	}

	public String getCountryArea() {
		return countryArea;
	}

	public void setCountryArea(String countryArea) {
		this.countryArea = countryArea;
	}

	public String getPerWaterArea() {
		return perWaterArea;
	}

	public void setPerWaterArea(String perWaterArea) {
		this.perWaterArea = perWaterArea;
	}

	public String getCountryPopulation() {
		return countryPopulation;
	}

	public void setCountryPopulation(String countryPopulation) {
		this.countryPopulation = countryPopulation;
	}

	public String getPopulationPerArea() {
		return populationPerArea;
	}

	public void setPopulationPerArea(String populationPerArea) {
		this.populationPerArea = populationPerArea;
	}

	public String getPopulationPerNumber() {
		return populationPerNumber;
	}

	public void setPopulationPerNumber(String populationPerNumber) {
		this.populationPerNumber = populationPerNumber;
	}

	public String getPopulationPerDensity() {
		return populationPerDensity;
	}

	public void setPopulationPerDensity(String populationPerDensity) {
		this.populationPerDensity = populationPerDensity;
	}

	public String getCountryGdp() {
		return countryGdp;
	}

	public void setCountryGdp(String countryGdp) {
		this.countryGdp = countryGdp;
	}

	public String getGdpPerPerson() {
		return gdpPerPerson;
	}

	public void setGdpPerPerson(String gdpPerPerson) {
		this.gdpPerPerson = gdpPerPerson;
	}

	public String getCountryPresident() {
		return countryPresident;
	}

	public void setCountryPresident(String countryPresident) {
		this.countryPresident = countryPresident;
	}

	public String getCountryPrimeMinister() {
		return countryPrimeMinister;
	}

	public void setCountryPrimeMinister(String countryPrimeMinister) {
		this.countryPrimeMinister = countryPrimeMinister;
	}

	public String getCountryIndependance() {
		return countryIndependance;
	}

	public void setCountryIndependance(String countryIndependance) {
		this.countryIndependance = countryIndependance;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryOfficialName() {
		return countryOfficialName;
	}

	public void setCountryOfficialName(String countryOfficialName) {
		this.countryOfficialName = countryOfficialName;
	}

	public String getCountryStatus() {
		return countryStatus;
	}

	public void setCountryStatus(String countryStatus) {
		this.countryStatus = countryStatus;
	}

	public String getCountryDomain() {
		return countryDomain;
	}

	public void setCountryDomain(String countryDomain) {
		this.countryDomain = countryDomain;
	}

	public String getOfficialLanguages() {
		return officialLanguages;
	}

	public void setOfficialLanguages(String officialLanguages) {
		this.officialLanguages = officialLanguages;
	}

	public String getCountryCurrency() {
		return countryCurrency;
	}

	public void setCountryCurrency(String countryCurrency) {
		this.countryCurrency = countryCurrency;
	}

	public String getCountryTimeZones() {
		return countryTimeZones;
	}

	public void setCountryTimeZones(String countryTimeZones) {
		this.countryTimeZones = countryTimeZones;
	}

	public String getCountryCapital() {
		return countryCapital;
	}

	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
	}

}

package com.globalLogic.modal;

import java.util.Comparator;

/**
 * A Company object to holds company details.
 * 
 * @author Ashish
 * 
 */
public class Company {
	private String name;
	private String year;
	private String month;
	private Double sharePrice;

	public Company() {
	}

	public Company(String name, String year, String month, Double sharePrice) {
		this.name = name;
		this.year = year;
		this.month = month;
		this.sharePrice = sharePrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(Double sharePrice) {
		this.sharePrice = sharePrice;
	}

	@Override
	public String toString() {
		return name + " share price " + sharePrice + " were highest in "
				+ month + " " + year;
	}

	public static Comparator<Company> CompanyComparatorBySharePrice = new Comparator<Company>() {
		@Override
		public int compare(Company c1, Company c2) {
			return c2.getSharePrice().compareTo(c1.getSharePrice());
		}
	};
}

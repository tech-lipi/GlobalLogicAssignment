package com.globalLogic.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.globalLogic.modal.Company;

/**
 * This class is written to fetch company details(year and month) which has
 * highest stock price.
 * 
 * There are three methods named main, printResult and loadCompanyDetails.
 * 
 * @author Ashish
 * 
 */

public class CompanyHelper {

	/**
	 * main() method to set fileName and call printResult() method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "CompanyShareDetails.csv";
		CompanyHelper companyHelper = new CompanyHelper();
		companyHelper.printResult(fileName);
	}

	/**
	 * printResult method calls loadCompanyDetails and print result map
	 * 
	 * @param fileName
	 */
	public void printResult(String fileName) {
		Map<String, Company> companyMap = loadCompanyDetails(fileName);
		for (Entry<String, Company> entry : companyMap.entrySet()) {
			System.out.println(entry.getValue());
		}
	}

	/**
	 * loadCompanyDetails contains main logic to find highest stock company for
	 * particular year and month. Here we read file and create list of companies
	 * for each row, then sort list using comparator named
	 * CompanyComparatorBySharePrice written in Company class which returns
	 * sorted list of companies based on highest stock price. Took first object
	 * from list and add to Map.
	 * 
	 * For each row does the same process and got result Map in last then return
	 * the result Map to printResult method
	 * 
	 * 
	 * @param fileName
	 * @return
	 */

	public Map<String, Company> loadCompanyDetails(String fileName) {
		BufferedReader br = null;
		String line = "";
		int noOfColumns = 0;
		int noOfRows = 0;
		String[] header = null;

		Map<String, Company> companyMap = null;

		try {
			if (fileName != null && !fileName.isEmpty()
					&& fileName.endsWith(".csv")) {
				br = new BufferedReader(new FileReader(fileName));
				companyMap = new HashMap<>();
				while ((line = br.readLine()) != null) {
					if (noOfRows == 0) {
						header = line.split(",");
						noOfColumns = header.length;
					} else {
						List<Company> companies = new ArrayList<>();
						String[] companyData = line.split(",");
						/*
						 * Skipping a row if length of company data is not equal
						 * to header length and print message
						 */
						if (companyData.length != header.length) {
							System.out
									.println("Invalid Data - data count does not match with header count");
							break;
						}
						String year = companyData[0];
						String month = companyData[1];
						/* Making list of company object for each row */
						for (int i = 2; i < noOfColumns; i++) {
							Company company = new Company(header[i], year,
									month, Double.parseDouble(companyData[i]));
							companies.add(company);
						}
						/* Sorting list using comparator */
						Collections.sort(companies,
								Company.CompanyComparatorBySharePrice);
						/* Adding top object of list in Map */
						companyMap.put(year + "_" + month, companies.get(0));
					}
					noOfRows++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return companyMap;
	}
}

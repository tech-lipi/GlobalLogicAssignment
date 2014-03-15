package com.globalLogic.unitTest;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.globalLogic.helper.CompanyHelper;
import com.globalLogic.modal.Company;

public class CompanyHelperTest {

	private static final String EXPECTED_COMPANY_KEY = "2013_Nov";
	private static final String EXPECTED_COMPANY_NAME = "Company B";

	String fileName = "";
	CompanyHelper companyHelper = null;

	@Before
	public void setUp() {
		fileName = "CompanyShareDetails.csv";
		companyHelper = new CompanyHelper();
	}

	@Test
	public void testEmptyFileName() {
		assertEquals(null, companyHelper.loadCompanyDetails(""));
	}

	@Test
	public void testNullFileName() {
		assertEquals(null, companyHelper.loadCompanyDetails(null));
	}

	@Test
	public void testIncorrectFileName() {
		assertEquals(null, companyHelper.loadCompanyDetails("abcd"));
	}

	@Test
	public void testInvalidFileExtension() {
		assertEquals(null, companyHelper.loadCompanyDetails("shortcuts41.pdf"));
	}

	@Test
	public void testValidCase() {
		Map<String, Company> companyMap = companyHelper
				.loadCompanyDetails(fileName);
		assertEquals(EXPECTED_COMPANY_NAME, companyMap
				.get(EXPECTED_COMPANY_KEY).getName());
	}

}

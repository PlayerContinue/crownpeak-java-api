package org.crownpeak.api;

import org.crownpeak.api.request.APIRequest;
import org.crownpeak.api.response.ReportSiteSummaryReportDataResponse;

public class AccessReport {

	public API api;
	
	@SuppressWarnings("unused")
	private AccessReport() {
		
	}
	/**
	 * 
	 * @param api - The API to be utilized
	 * @throws Exception  - Throws error when API is not authenticated
	 */
	public AccessReport(API api) throws Exception {
		if(!api.isAuthenticated()) {
			throw new Exception("API is not authenticated. No functions can be run.");
		}
		this.api = api;
		
	}
	
	/**
	 * Request the Site Summary
	 * @return - A summary about the cms instance
	 */
	public ReportSiteSummaryReportDataResponse siteSummary() {
		return MakeRequest.makeRequest("/Report/sitesummary/", new APIRequest(), api, ReportSiteSummaryReportDataResponse.class);
	}
	
}

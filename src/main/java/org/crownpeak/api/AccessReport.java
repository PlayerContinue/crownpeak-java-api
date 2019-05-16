package org.crownpeak.api;

import org.crownpeak.api.request.APIRequest;
import org.crownpeak.api.response.ReportSiteSummaryReportDataResponse;

public class AccessReport {

	public API api;
	
	@SuppressWarnings("unused")
	private AccessReport() {
		
	}
	
	public AccessReport(API api) throws Exception {
		if(!api.isAuthenticated()) {
			throw new Exception("API is not authenticated. No functions can be run.");
		}
		this.api = api;
		
	}
	
	public ReportSiteSummaryReportDataResponse siteSummary() {
		return MakeRequest.makeRequest("/Report/sitesummary/", new APIRequest(), api, ReportSiteSummaryReportDataResponse.class);
	}
	
}

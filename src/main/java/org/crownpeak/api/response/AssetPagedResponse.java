package org.crownpeak.api.response;

public class AssetPagedResponse extends APIResponse {
	public AssetResponse[] assets;
	public int normalCount;
	public int deletedCount;
	public int hiddenCount;
	public SortInfoResponse sortInfo;
	public FilterResponse filter;
	public int currentPage;
}

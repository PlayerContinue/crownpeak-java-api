package org.crownpeak.api.request;

public class AssetPagedRequest extends APIRequest {
	public int assetId;
	public int assetIdToFindPage;
	public int currentPage;
	public boolean ignoreFilter;
	public boolean ignoreSort;
	public OrderType orderType;
	public int pageSize;
	public boolean saveSort;
	public String sortColumn;
	public VisibilityType visibilityType;

	
	/**
     * Create an instance of the AssetPagedRequest for getting lists of assets
     * @param assetId - The assetId of the folder to get the list of assets from
     * @param assetIdToFindPage - Unsure, find out
     * @param currentPage - The current page number, if greater than the max returns (TODO find out)
     * @param ignoreFilter - Ignore the currently set filter
     * @param  ignoreSort - Ignore the current set Sort
     * @param  orderType - Change the order of how the paged values will be returned (Ascending, Descending, NotSet, Saved) 
     * @param  pageSize - Number of assets to return on each page
     * @param  saveSort - TODO find out
     * @param  sortColumn - TODO find out
     * @param visibilityType - Display Hidden, Deleted, Or Normal 
     */
	public AssetPagedRequest(int assetId, int assetIdToFindPage, int currentPage, boolean ignoreFilter,
			boolean ignoreSort, OrderType orderType, int pageSize, boolean saveSort, String sortColumn,
			VisibilityType visibiltyType) {

		this.assetId = assetId;
		this.assetIdToFindPage = assetIdToFindPage;
		this.currentPage = currentPage;
		this.ignoreFilter = ignoreFilter;
		this.ignoreSort = ignoreSort;
		this.orderType = orderType;
		this.pageSize = pageSize;
		this.saveSort = saveSort;
		this.sortColumn = sortColumn;
		this.visibilityType = visibiltyType;
		
	}
	
	

}

package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppsLoveUser
{
	
	private int page;
	private int per_page;
	private int totalrecord;
	private int total_pages;
	@JsonProperty("data")
	private List<AppsLoveData> AppsLoveData;
	

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPer_page() {
		return per_page;
	}
	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}
	public int getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}
	public int getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}
	
	public List<AppsLoveData> getAppsLoveData() {
		return AppsLoveData;
	}
	public void setAppsLoveData(List<AppsLoveData> appsLoveData) {
		AppsLoveData = appsLoveData;
	}
	
	
	

}

package datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataObject
{
	int year;
	double price;
	@JsonProperty("CPU model")
	String cpuModel;
	@JsonProperty("Hard disk size")
	String hardDiskSize;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCpuModel() {
		return cpuModel;
	}
	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}
	public String getHardDiskSize() {
		return hardDiskSize;
	}
	public void setHardDiskSize(String hardDiskSize) {
		this.hardDiskSize = hardDiskSize;
	}
	
	

}

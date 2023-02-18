package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
	
	String status;
	@JsonProperty("data")
	List<EmployeeData> empData;
	String message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<EmployeeData> getEmpData() {
		return empData;
	}
	public void setEmpData(List<EmployeeData> empData) {
		this.empData = empData;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}

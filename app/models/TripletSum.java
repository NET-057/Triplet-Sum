package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.ebean.Model;

@Entity
@Table(name="tripplet_sum")
public class TripletSum extends Model{

	@Id
	private Long id;
	
	private String inputArray;
	
	private Integer requiredSum;
	
	private String outputArray;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInputArray() {
		return inputArray;
	}

	public void setInputArray(String inputArray) {
		this.inputArray = inputArray;
	}

	public Integer getRequiredSum() {
		return requiredSum;
	}

	public void setRequiredSum(Integer requiredSum) {
		this.requiredSum = requiredSum;
	}

	public String getOutputArray() {
		return outputArray;
	}

	public void setOutputArray(String outputArray) {
		this.outputArray = outputArray;
	}

}

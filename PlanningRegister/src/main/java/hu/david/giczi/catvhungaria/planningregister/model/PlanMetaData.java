package hu.david.giczi.catvhungaria.planningregister.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity 
public class PlanMetaData implements Serializable, Comparable<PlanMetaData> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	Long id;
	private String planNumber;
	private String planName;
	private String nameOfCATVControlPerson;
	private String dateOfCATVControl;
	private String nameOfUPCControlPerson;
	private String dateOfUPCControl;
	private String ekozmu;
	private String roadStatement;
	private String ownerStatement;
	private String comment;
	private Boolean isOK;
	
	
	
	

	public PlanMetaData(String planNumber, String planName, String nameOfCATVControlPerson, String dateOfCATVControl,
			String nameOfUPCControlPerson, String dateOfUPCControl, String ekozmu, String roadStatement,  String ownerStatement, String comment, Boolean isOK) {
		
		this.planNumber = "CATV-"+planNumber+"/"+TimeStamp.getYear();
		this.planName = planName;
		this.nameOfCATVControlPerson = nameOfCATVControlPerson;
		this.dateOfCATVControl = dateOfCATVControl;
		this.nameOfUPCControlPerson = nameOfUPCControlPerson;
		this.dateOfUPCControl = dateOfUPCControl;
		this.ekozmu=ekozmu;
		this.roadStatement=roadStatement;
		this.ownerStatement=ownerStatement;
		this.comment = comment;
		this.isOK = isOK;
	}



	public PlanMetaData() {
		
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getPlanNumber() {
		return planNumber;
	}



	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}



	public String getPlanName() {
		return planName;
	}



	public void setPlanName(String planName) {
		this.planName = planName;
	}



	public String getNameOfCATVControlPerson() {
		return nameOfCATVControlPerson;
	}



	public void setNameOfCATVControlPerson(String nameOfCATVControlPerson) {
		this.nameOfCATVControlPerson = nameOfCATVControlPerson;
	}



	public String getDateOfCATVControl() {
		return dateOfCATVControl;
	}



	public void setDateOfCATVControl(String dateOfCATVControl) {
		this.dateOfCATVControl = dateOfCATVControl;
	}



	public String getNameOfUPCControlPerson() {
		return nameOfUPCControlPerson;
	}



	public void setNameOfUPCControlPerson(String nameOfUPCControlPerson) {
		this.nameOfUPCControlPerson = nameOfUPCControlPerson;
	}



	public String getDateOfUPCControl() {
		return dateOfUPCControl;
	}



	public void setDateOfUPCControl(String dateOfUPCControl) {
		this.dateOfUPCControl = dateOfUPCControl;
	}

	
	public String getEkozmu() {
		return ekozmu;
	}



	public void setEkozmu(String ekozmu) {
		this.ekozmu = ekozmu;
	}



	public String getRoadStatement() {
		return roadStatement;
	}



	public void setRoadStatement(String roadStatement) {
		this.roadStatement = roadStatement;
	}



	public String getOwnerStatement() {
		return ownerStatement;
	}



	public void setOwnerStatement(String ownerStatement) {
		this.ownerStatement = ownerStatement;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	public Boolean getIsOK() {
		return isOK;
	}



	public void setIsOK(Boolean isOK) {
		this.isOK = isOK;
	}

	

	public int compareTo(PlanMetaData o) {
		
		return this.getId()<o.getId() ? -1 : this.getId()==o.getId() ? 0 : 1;
	}



	@Override
	public String toString() {
		return "PlanMetaData [id=" + id + ", planNumber=" + planNumber + ", planName=" + planName
				+ ", nameOfCATVControlPerson=" + nameOfCATVControlPerson + ", dateOfCATVControl=" + dateOfCATVControl
				+ ", nameOfUPCControlPerson=" + nameOfUPCControlPerson + ", dateOfUPCControl=" + dateOfUPCControl
				+ ", ekozmu=" + ekozmu + ", roadStatement=" + roadStatement + ", ownerStatement=" + ownerStatement
				+ ", comment=" + comment + ", isOK=" + isOK + "]";
	}


	
	

	
	
}

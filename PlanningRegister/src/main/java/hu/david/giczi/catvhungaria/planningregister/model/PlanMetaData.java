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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((dateOfCATVControl == null) ? 0 : dateOfCATVControl.hashCode());
		result = prime * result + ((dateOfUPCControl == null) ? 0 : dateOfUPCControl.hashCode());
		result = prime * result + ((ekozmu == null) ? 0 : ekozmu.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isOK == null) ? 0 : isOK.hashCode());
		result = prime * result + ((nameOfCATVControlPerson == null) ? 0 : nameOfCATVControlPerson.hashCode());
		result = prime * result + ((nameOfUPCControlPerson == null) ? 0 : nameOfUPCControlPerson.hashCode());
		result = prime * result + ((ownerStatement == null) ? 0 : ownerStatement.hashCode());
		result = prime * result + ((planName == null) ? 0 : planName.hashCode());
		result = prime * result + ((planNumber == null) ? 0 : planNumber.hashCode());
		result = prime * result + ((roadStatement == null) ? 0 : roadStatement.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanMetaData other = (PlanMetaData) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (dateOfCATVControl == null) {
			if (other.dateOfCATVControl != null)
				return false;
		} else if (!dateOfCATVControl.equals(other.dateOfCATVControl))
			return false;
		if (dateOfUPCControl == null) {
			if (other.dateOfUPCControl != null)
				return false;
		} else if (!dateOfUPCControl.equals(other.dateOfUPCControl))
			return false;
		if (ekozmu == null) {
			if (other.ekozmu != null)
				return false;
		} else if (!ekozmu.equals(other.ekozmu))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isOK == null) {
			if (other.isOK != null)
				return false;
		} else if (!isOK.equals(other.isOK))
			return false;
		if (nameOfCATVControlPerson == null) {
			if (other.nameOfCATVControlPerson != null)
				return false;
		} else if (!nameOfCATVControlPerson.equals(other.nameOfCATVControlPerson))
			return false;
		if (nameOfUPCControlPerson == null) {
			if (other.nameOfUPCControlPerson != null)
				return false;
		} else if (!nameOfUPCControlPerson.equals(other.nameOfUPCControlPerson))
			return false;
		if (ownerStatement == null) {
			if (other.ownerStatement != null)
				return false;
		} else if (!ownerStatement.equals(other.ownerStatement))
			return false;
		if (planName == null) {
			if (other.planName != null)
				return false;
		} else if (!planName.equals(other.planName))
			return false;
		if (planNumber == null) {
			if (other.planNumber != null)
				return false;
		} else if (!planNumber.equals(other.planNumber))
			return false;
		if (roadStatement == null) {
			if (other.roadStatement != null)
				return false;
		} else if (!roadStatement.equals(other.roadStatement))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return id + "^"+planNumber + "^" + planName+"^" + nameOfCATVControlPerson + "^" + dateOfCATVControl
				+ "^" + nameOfUPCControlPerson + "^" + dateOfUPCControl+"^" + comment +"^" + ekozmu + "^"
				+ roadStatement + "^" + ownerStatement+"^" + isOK;
	}


	
		
}

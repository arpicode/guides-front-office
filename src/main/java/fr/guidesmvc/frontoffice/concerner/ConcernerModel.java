package fr.guidesmvc.frontoffice.concerner;

import java.sql.Date;
import java.util.Objects;

import fr.guidesmvc.frontoffice.randonnees.RandonneeModel;
import fr.guidesmvc.frontoffice.sommets.SommetModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@IdClass(ConcernerID.class)
@Table(name = "concerner")
public class ConcernerModel {
	
	@Id
	@ManyToOne
    @JoinColumn(name="code_Sommets")
    private SommetModel sommetConcerner;

	@Id
	@ManyToOne
	@JoinColumn(name="code_Randonnees")
	private RandonneeModel randonneeConcerner;
	
	@Id
	@Column(name = "date_Concerner")
	private Date dateConcerner;
	
	public ConcernerModel() { }

	public ConcernerModel(SommetModel sommetConcerner, RandonneeModel randonneeConcerner, Date dateConcerner) {
		this.sommetConcerner = sommetConcerner;
		this.randonneeConcerner = randonneeConcerner;
		this.dateConcerner = dateConcerner;
	}

	public SommetModel getSommetConcerner() {
		return sommetConcerner;
	}

	public void setSommetConcerner(SommetModel sommetConcerner) {
		this.sommetConcerner = sommetConcerner;
	}

	public RandonneeModel getRandonneeConcerner() {
		return randonneeConcerner;
	}

	public void setRandonneeConcerner(RandonneeModel randonneeConcerner) {
		this.randonneeConcerner = randonneeConcerner;
	}

	public Date getDateConcerner() {
		return dateConcerner;
	}

	public void setDateConcerner(Date dateConcerner) {
		this.dateConcerner = dateConcerner;
	}

	@Override
	public String toString() {
		return "ConcernerModel [sommetConcerner=" + sommetConcerner + ", randonneeConcerner=" + randonneeConcerner
				+ ", dateConcerner=" + dateConcerner + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateConcerner, randonneeConcerner, sommetConcerner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConcernerModel other = (ConcernerModel) obj;
		return Objects.equals(dateConcerner, other.dateConcerner)
				&& Objects.equals(randonneeConcerner, other.randonneeConcerner)
				&& Objects.equals(sommetConcerner, other.sommetConcerner);
	}
	
}

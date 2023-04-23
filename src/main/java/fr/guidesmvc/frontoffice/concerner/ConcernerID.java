package fr.guidesmvc.frontoffice.concerner;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import fr.guidesmvc.frontoffice.randonnees.RandonneeModel;
import fr.guidesmvc.frontoffice.sommets.SommetModel;

public class ConcernerID implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private SommetModel sommetConcerner;
    
    private RandonneeModel randonneeConcerner;
    
    private Date dateConcerner;
    
    public ConcernerID() { }

	public ConcernerID(SommetModel sommetConcerner, RandonneeModel randonneeConcerner, Date dateConcerner) {
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
		return "ConcernerID [sommetConcerner=" + sommetConcerner + ", randonneeConcerner=" + randonneeConcerner
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
		ConcernerID other = (ConcernerID) obj;
		return Objects.equals(dateConcerner, other.dateConcerner)
				&& Objects.equals(randonneeConcerner, other.randonneeConcerner)
				&& Objects.equals(sommetConcerner, other.sommetConcerner);
	}
    
}

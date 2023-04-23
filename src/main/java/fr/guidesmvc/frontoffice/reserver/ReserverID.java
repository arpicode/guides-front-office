package fr.guidesmvc.frontoffice.reserver;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import fr.guidesmvc.frontoffice.abris.AbriModel;
import fr.guidesmvc.frontoffice.randonnees.RandonneeModel;

public class ReserverID implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private AbriModel abriReserve;
    
    private RandonneeModel randonnee;
    
    private Date dateReserver;
    
    public ReserverID() { }

	public ReserverID(AbriModel abris, RandonneeModel randonnees, Date dateReserver) {
		super();
		this.abriReserve = abris;
		this.randonnee = randonnees;
		this.dateReserver = dateReserver;
	}

	public AbriModel getAbrisReserve() {
		return abriReserve;
	}

	public void setAbrisReserve(AbriModel abris) {
		this.abriReserve = abris;
	}

	public RandonneeModel getRandonnee() {
		return randonnee;
	}

	public void setRandonnee(RandonneeModel randonnees) {
		this.randonnee = randonnees;
	}

	public Date getDateReserver() {
		return dateReserver;
	}

	public void setDateReserver(Date dateReserver) {
		this.dateReserver = dateReserver;
	}

	@Override
	public String toString() {
		return "ReserverID [abris=" + abriReserve + ", randonnees=" + randonnee + ", dateReserver=" + dateReserver + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(abriReserve, dateReserver, randonnee);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReserverID other = (ReserverID) obj;
		return Objects.equals(abriReserve, other.abriReserve) && Objects.equals(dateReserver, other.dateReserver)
				&& Objects.equals(randonnee, other.randonnee);
	}
    
}

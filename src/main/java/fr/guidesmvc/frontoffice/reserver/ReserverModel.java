package fr.guidesmvc.frontoffice.reserver;

import java.sql.Date;
import java.util.Objects;

import fr.guidesmvc.frontoffice.abris.AbriModel;
import fr.guidesmvc.frontoffice.randonnees.RandonneeModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@IdClass(ReserverID.class)
@Table(name = "reserver")
public class ReserverModel {

	@Id
	@ManyToOne
    @JoinColumn(name="code_Abris")
    private AbriModel abriReserve;

	@Id
	@ManyToOne
	@JoinColumn(name="code_Randonnees")
	private RandonneeModel randonnee;
	
	@Id
	@Column(name = "date_Reserver")
	private Date dateReserver;
	
	@Column(name = "statut_Reserver")
	private String statutReserver;
	
	public ReserverModel() { }

	public ReserverModel(AbriModel abriReserve, RandonneeModel randonnee, Date dateReserver, String statutReserver) {
		super();
		this.abriReserve = abriReserve;
		this.randonnee = randonnee;
		this.dateReserver = dateReserver;
		this.statutReserver = statutReserver;
	}

	public AbriModel getAbriReserve() {
		return abriReserve;
	}

	public void setAbriReserve(AbriModel abriReserve) {
		this.abriReserve = abriReserve;
	}

	public RandonneeModel getRandonnee() {
		return randonnee;
	}

	public void setRandonnee(RandonneeModel randonnee) {
		this.randonnee = randonnee;
	}

	public Date getDateReserver() {
		return dateReserver;
	}

	public void setDateReserver(Date dateReserver) {
		this.dateReserver = dateReserver;
	}

	public String getStatutReserver() {
		return statutReserver;
	}

	public void setStatutReserver(String statutReserver) {
		this.statutReserver = statutReserver;
	}

	@Override
	public String toString() {
		return "ReserverModel [abriReserve=" + abriReserve + ", randonnee=" + randonnee + ", dateReserver="
				+ dateReserver + ", statutReserver=" + statutReserver + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(abriReserve, dateReserver, randonnee, statutReserver);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReserverModel other = (ReserverModel) obj;
		return Objects.equals(abriReserve, other.abriReserve) && Objects.equals(dateReserver, other.dateReserver)
				&& Objects.equals(randonnee, other.randonnee) && Objects.equals(statutReserver, other.statutReserver);
	}
	
}

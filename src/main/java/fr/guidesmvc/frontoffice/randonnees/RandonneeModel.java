package fr.guidesmvc.frontoffice.randonnees;

import java.sql.Date;
import java.util.Objects;

import fr.guidesmvc.frontoffice.guides.GuideModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "randonnees")
public class RandonneeModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code_Randonnees")
	private Long codeRandonnees;
	
	@Column(name = "nbPersonnes_Randonnees")
	private Integer nbPersonnesRandonnees;
	
	@Column(name = "dateDebut_Randonnees")
	private Date dateDebutRandonnees;
	
	@Column(name = "dateFin_Randonnees")
	private Date dateFinRandonnees;
	
//	@Column(name = "code_Guides")
//	private Long codeGuides;
	
	@ManyToOne
    @JoinColumn(name="code_Guides")
    private GuideModel guideResponsable;
	
	public RandonneeModel() { }

	public RandonneeModel(Long codeRandonnees, Integer nbPersonnesRandonnees, Date dateDebutRandonnees,
			Date dateFinRandonnees, GuideModel guideResponsable) {
		this.codeRandonnees = codeRandonnees;
		this.nbPersonnesRandonnees = nbPersonnesRandonnees;
		this.dateDebutRandonnees = dateDebutRandonnees;
		this.dateFinRandonnees = dateFinRandonnees;
		this.guideResponsable = guideResponsable;
	}

	public Long getCodeRandonnees() {
		return codeRandonnees;
	}

	public void setCodeRandonnees(Long codeRandonnees) {
		this.codeRandonnees = codeRandonnees;
	}

	public Integer getNbPersonnesRandonnees() {
		return nbPersonnesRandonnees;
	}

	public void setNbPersonnesRandonnees(Integer nbPersonnesRandonnees) {
		this.nbPersonnesRandonnees = nbPersonnesRandonnees;
	}

	public Date getDateDebutRandonnees() {
		return dateDebutRandonnees;
	}

	public void setDateDebutRandonnees(Date dateDebutRandonnees) {
		this.dateDebutRandonnees = dateDebutRandonnees;
	}

	public Date getDateFinRandonnees() {
		return dateFinRandonnees;
	}

	public void setDateFinRandonnees(Date dateFinRandonnees) {
		this.dateFinRandonnees = dateFinRandonnees;
	}

	public GuideModel getGuideResponsable() {
		return guideResponsable;
	}

	public void setGuideResponsable(GuideModel guideResponsable) {
		this.guideResponsable = guideResponsable;
	}

	@Override
	public String toString() {
		return "RandonneeModel [ID=" + codeRandonnees + ", Participants=" + nbPersonnesRandonnees
				+ ", Début=" + dateDebutRandonnees + ", Fin=" + dateFinRandonnees
				+ ", guide=" + guideResponsable + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codeRandonnees, dateDebutRandonnees, dateFinRandonnees, guideResponsable,
				nbPersonnesRandonnees);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RandonneeModel other = (RandonneeModel) obj;
		return Objects.equals(codeRandonnees, other.codeRandonnees)
				&& Objects.equals(dateDebutRandonnees, other.dateDebutRandonnees)
				&& Objects.equals(dateFinRandonnees, other.dateFinRandonnees)
				&& Objects.equals(guideResponsable, other.guideResponsable)
				&& Objects.equals(nbPersonnesRandonnees, other.nbPersonnesRandonnees);
	}
	
}

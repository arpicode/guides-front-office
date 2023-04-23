package fr.guidesmvc.frontoffice.guides;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "guides")
public class GuideModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code_Guides")
	private Long codeGuides;
	
	@Column(name = "nom_Guides")
	private String nomGuides;
	
	@Column(name = "prenom_Guides")
	private String prenomGuides;
	
	@Column(name = "email_Guides")
	private String emailGuides;
	
	@Column(name = "motdepasse_Guides")
	private String motdepasseGuides;
	
	public GuideModel() { }

	public GuideModel(Long codeGuides, String nomGuides, String prenomGuides, String emailGuides,
			String motdepasseGuides) {
		this.codeGuides = codeGuides;
		this.nomGuides = nomGuides;
		this.prenomGuides = prenomGuides;
		this.emailGuides = emailGuides;
		this.motdepasseGuides = motdepasseGuides;
	}

	public Long getCodeGuides() {
		return codeGuides;
	}

	public void setCodeGuides(Long codeGuides) {
		this.codeGuides = codeGuides;
	}

	public String getNomGuides() {
		return nomGuides;
	}

	public void setNomGuides(String nomGuides) {
		this.nomGuides = nomGuides;
	}

	public String getPrenomGuides() {
		return prenomGuides;
	}

	public void setPrenomGuides(String prenomGuides) {
		this.prenomGuides = prenomGuides;
	}

	public String getEmailGuides() {
		return emailGuides;
	}

	public void setEmailGuides(String emailGuides) {
		this.emailGuides = emailGuides;
	}

	public String getMotdepasseGuides() {
		return motdepasseGuides;
	}

	public void setMotdepasseGuides(String motdepasseGuides) {
		this.motdepasseGuides = motdepasseGuides;
	}

	@Override
	public String toString() {
		return "GuideModel [ID=" + codeGuides + ", Nom=" + nomGuides + ", Prénom=" + prenomGuides
				+ ", Email=" + emailGuides + ", MdP=" + motdepasseGuides + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codeGuides, emailGuides, motdepasseGuides, nomGuides, prenomGuides);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GuideModel other = (GuideModel) obj;
		return Objects.equals(codeGuides, other.codeGuides) && Objects.equals(emailGuides, other.emailGuides)
				&& Objects.equals(motdepasseGuides, other.motdepasseGuides)
				&& Objects.equals(nomGuides, other.nomGuides) && Objects.equals(prenomGuides, other.prenomGuides);
	}
	
}

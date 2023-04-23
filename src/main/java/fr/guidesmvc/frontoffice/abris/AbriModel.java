package fr.guidesmvc.frontoffice.abris;

import java.util.Objects;

import fr.guidesmvc.frontoffice.vallees.ValleeModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "abris")
public class AbriModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code_Abris")
	private Long codeAbris;
	
	@Column(name = "nom_Abris")
	private String nomAbris;
	
	@Column(name = "type_Abris")
	private String typeAbris;
	
	@Column(name = "altitude_Abris")
	private Integer altitudeAbris;
	
	@Column(name = "places_Abris")
	private Integer placesAbris;
	
	@Column(name = "prixNuit_Abris")
	private Double prixNuitAbris;
	
	@Column(name = "prixRepas_Abris")
	private Double prixRepasAbris;
	
	@Column(name = "telGardien_Abris")
	private String telGardienAbris;
	
	@ManyToOne
    @JoinColumn(name="code_Vallees")
    private ValleeModel valleeOrigine;
	
	public AbriModel() { }

	public AbriModel(Long codeAbris, String nomAbris, String typeAbris, Integer altitudeAbris, Integer placesAbris,
			Double prixNuitAbris, Double prixRepasAbris, String telGardienAbris, ValleeModel valleeOrigine) {
		this.codeAbris = codeAbris;
		this.nomAbris = nomAbris;
		this.typeAbris = typeAbris;
		this.altitudeAbris = altitudeAbris;
		this.placesAbris = placesAbris;
		this.prixNuitAbris = prixNuitAbris;
		this.prixRepasAbris = prixRepasAbris;
		this.telGardienAbris = telGardienAbris;
		this.valleeOrigine = valleeOrigine;
	}

	public Long getCodeAbris() {
		return codeAbris;
	}

	public void setCodeAbris(Long codeAbris) {
		this.codeAbris = codeAbris;
	}

	public String getNomAbris() {
		return nomAbris;
	}

	public void setNomAbris(String nomAbris) {
		this.nomAbris = nomAbris;
	}

	public String getTypeAbris() {
		return typeAbris;
	}

	public void setTypeAbris(String typeAbris) {
		this.typeAbris = typeAbris;
	}

	public Integer getAltitudeAbris() {
		return altitudeAbris;
	}

	public void setAltitudeAbris(Integer altitudeAbris) {
		this.altitudeAbris = altitudeAbris;
	}

	public Integer getPlacesAbris() {
		return placesAbris;
	}

	public void setPlacesAbris(Integer placesAbris) {
		this.placesAbris = placesAbris;
	}

	public Double getPrixNuitAbris() {
		return prixNuitAbris;
	}

	public void setPrixNuitAbris(Double prixNuitAbris) {
		this.prixNuitAbris = prixNuitAbris;
	}

	public Double getPrixRepasAbris() {
		return prixRepasAbris;
	}

	public void setPrixRepasAbris(Double prixRepasAbris) {
		this.prixRepasAbris = prixRepasAbris;
	}

	public String getTelGardienAbris() {
		return telGardienAbris;
	}

	public void setTelGardienAbris(String telGardienAbris) {
		this.telGardienAbris = telGardienAbris;
	}

	public ValleeModel getValleeOrigine() {
		return valleeOrigine;
	}

	public void setValleeOrigine(ValleeModel valleeOrigine) {
		this.valleeOrigine = valleeOrigine;
	}

	@Override
	public String toString() {
		return "AbriModel [ID=" + codeAbris + ", Nom=" + nomAbris + ", Type=" + typeAbris
				+ ", Altitude=" + altitudeAbris + ", Places=" + placesAbris + ", PrixNuit="
				+ prixNuitAbris + ", PrixRepas=" + prixRepasAbris + ", TelGardien=" + telGardienAbris
				+ ", ValleeOrigine=" + valleeOrigine + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(altitudeAbris, codeAbris, nomAbris, placesAbris, prixNuitAbris, prixRepasAbris,
				telGardienAbris, typeAbris, valleeOrigine);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbriModel other = (AbriModel) obj;
		return Objects.equals(altitudeAbris, other.altitudeAbris) && Objects.equals(codeAbris, other.codeAbris)
				&& Objects.equals(nomAbris, other.nomAbris) && Objects.equals(placesAbris, other.placesAbris)
				&& Objects.equals(prixNuitAbris, other.prixNuitAbris)
				&& Objects.equals(prixRepasAbris, other.prixRepasAbris)
				&& Objects.equals(telGardienAbris, other.telGardienAbris) && Objects.equals(typeAbris, other.typeAbris)
				&& Objects.equals(valleeOrigine, other.valleeOrigine);
	}	
	
}

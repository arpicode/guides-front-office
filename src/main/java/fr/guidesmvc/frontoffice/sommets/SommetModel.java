package fr.guidesmvc.frontoffice.sommets;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sommets")
public class SommetModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code_Sommets")
	private Long codeSommets;
	
    @Column(name = "nom_Sommets")
    private String nomSommets;

    @Column(name = "altitude_Sommets")
    private int altitudeSommets;

	public SommetModel() { }

	public SommetModel(Long codeSommets, String nomSommets, int altitudeSommets) {
		this.codeSommets = codeSommets;
		this.nomSommets = nomSommets;
		this.altitudeSommets = altitudeSommets;
	}

	public Long getCodeSommets() {
		return codeSommets;
	}

	public void setCodeSommets(Long codeSommets) {
		this.codeSommets = codeSommets;
	}

	public String getNomSommets() {
		return nomSommets;
	}

	public void setNomSommets(String nomSommets) {
		this.nomSommets = nomSommets;
	}

	public int getAltitudeSommets() {
		return altitudeSommets;
	}

	public void setAltitudeSommets(int altitudeSommets) {
		this.altitudeSommets = altitudeSommets;
	}
	

	@Override
	public String toString() {
		return "SommetModel [codeSommets=" + codeSommets + ", nomSommets=" + nomSommets + ", altitudeSommets="
				+ altitudeSommets + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(altitudeSommets, codeSommets, nomSommets);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SommetModel other = (SommetModel) obj;
		return altitudeSommets == other.altitudeSommets && Objects.equals(codeSommets, other.codeSommets)
				&& Objects.equals(nomSommets, other.nomSommets);
	}

	
    
}

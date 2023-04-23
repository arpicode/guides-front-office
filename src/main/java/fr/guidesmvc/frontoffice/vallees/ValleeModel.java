package fr.guidesmvc.frontoffice.vallees;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vallees")
public class ValleeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code_Vallees")
	private Long codeVallees;
	
	@Column(name = "nom_Vallees")
	private String nomVallees;
	
	public ValleeModel() { }

	public ValleeModel(Long codeVallees, String nomVallees) {
		super();
		this.codeVallees = codeVallees;
		this.nomVallees = nomVallees;
	}
	
	public Long getCodeVallees() {
		return codeVallees;
	}

	public void setCodeVallees(Long codeVallees) {
		this.codeVallees = codeVallees;
	}

	public String getNomVallees() {
		return nomVallees;
	}

	public void setNomVallees(String nomVallees) {
		this.nomVallees = nomVallees;
	}

	@Override
	public String toString() {
		return "ValleeModel [codeVallees=" + codeVallees + ", nomVallees=" + nomVallees + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codeVallees, nomVallees);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValleeModel other = (ValleeModel) obj;
		return Objects.equals(codeVallees, other.codeVallees) && Objects.equals(nomVallees, other.nomVallees);
	}
	
}

package fr.guidesmvc.frontoffice.randonnees;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RandonneeRepository extends CrudRepository<RandonneeModel, Long> {
	
	@Query(value = """
			SELECT randonnees.*
			FROM randonnees
			WHERE randonnees.code_Guides = :guideId
			""", nativeQuery = true)
	Iterable<RandonneeModel> findAllByGuideId(@Param("guideId") Long guideId);
	
	@Query(value = """
			SELECT randonnees.*
			FROM randonnees
			WHERE randonnees.code_Randonnees = :randonneeId
			AND randonnees.code_Guides = :guideId
			""", nativeQuery = true)
	Optional<RandonneeModel> findByIdAndGuideId(@Param("randonneeId") Long randonneeId,
									  @Param("guideId") Long guideId);
	
	@Query(value = """
			INSERT INTO randonnees (nbPersonnes_Randonnees, dateDebut_Randonnees, dateFin_Randonnees, code_Guides)
			VALUES (:nbPersonnes, :dateDebut, :dateFin, :guideId)
			""", nativeQuery = true)
	void insertRandonnee(@Param("nbPersonnes") Integer nbPersonnes,
						 @Param("dateDebut") Date dateDebut,
						 @Param("dateFin") Date dateFin,
						 @Param("guideId") Long guideId);
	
	List<RandonneeModel> findAllRandonneeByGuideResponsableCodeGuides(Long guideId);
	
	@Transactional
	@Modifying
	@Query(value = 
			"""
			UPDATE randonnees
			SET dateFin_Randonnees = DATE_ADD(:dateReservation, INTERVAL 1 DAY)
			WHERE code_Randonnees = :randonneeId
			""", nativeQuery = true)
	void updateDateFin(@Param("randonneeId") Long randonneeId,
					   @Param("dateReservation") Date dateReservation);
	
	@Transactional
	@Modifying
	@Query(value = 
			"""
			UPDATE randonnees
			SET randonnees.nbPersonnes_Randonnees = :nbPersonnes
			WHERE code_Randonnees = :randonneeId
			""", nativeQuery = true)
	void updateNbPersonnes(@Param("randonneeId") Long randonneeId,
						   @Param("nbPersonnes") Integer nbPersonnes);
	
	@Transactional
	@Modifying
	@Query(value = 
			"""
			UPDATE randonnees
			SET dateFin_Randonnees = dateDebut_Randonnees
			WHERE code_Randonnees = :randonneeId
			""", nativeQuery = true)
	void resetDateFin(@Param("randonneeId") Long randonneeId);
	
	@Transactional
	@Modifying
	@Query(value = 
			"""
			DELETE FROM randonnees
			WHERE code_Randonnees = :randonneeId
			""", nativeQuery = true)
	void deleteRandonnee(@Param("randonneeId") Long randonneeId);
	
}

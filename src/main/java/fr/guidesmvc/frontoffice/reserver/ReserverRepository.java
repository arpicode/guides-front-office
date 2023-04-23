package fr.guidesmvc.frontoffice.reserver;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReserverRepository extends CrudRepository<ReserverModel, ReserverID> {

	List<ReserverModel> findReserverByRandonneeCodeRandonneesOrderByDateReserver(Long randonneeId);
	
	ReserverModel findReserverByRandonneeCodeRandonneesAndDateReserver(Long randonneeId, Date dateReverser);
	
	@Query(value = 
			"""
			SELECT reserver.*
			FROM reserver
			WHERE reserver.code_Randonnees = :randonneeId
			ORDER BY reserver.date_Reserver DESC
			LIMIT 1
			""", nativeQuery = true)
	ReserverModel findLastReservation(Long randonneeId);
	
	@Transactional
	@Modifying
	@Query(value = 
			"""
			INSERT INTO reserver (reserver.code_Abris, reserver.code_Randonnees, reserver.date_Reserver, reserver.statut_Reserver)
			VALUES (:abriId, :randonneeId, :dateReservation, :statut)
			""", nativeQuery = true)
	void saveReservation(@Param("abriId") Long abriId,
						 @Param("randonneeId") Long randonneeId,
						 @Param("dateReservation") Date dateReservation,
						 @Param("statut") String statut);
	
	@Transactional
	@Modifying
	@Query(value = 
			"""
			UPDATE reserver
			SET reserver.statut_Reserver = :statut
			WHERE reserver.code_Randonnees = :randonneeId
			AND reserver.date_Reserver = :dateReservation
			""", nativeQuery = true)
	void updateStatut(@Param("randonneeId") Long randonneeId,
					 @Param("dateReservation") Date dateReservation,
					 @Param("statut") String statut);
	
	@Transactional
	@Modifying
	@Query(value = 
			"""
			DELETE FROM reserver
			WHERE reserver.code_Randonnees = :randonneeId
			AND reserver.date_Reserver = :dateReservation
			""", nativeQuery = true)
	void deleteReservation(@Param("dateReservation") Date dateReservation,
						   @Param("randonneeId") Long randonneeId);
}

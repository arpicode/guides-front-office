package fr.guidesmvc.frontoffice.concerner;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ConcernerRepository extends CrudRepository<ConcernerModel, ConcernerID> {

	List<ConcernerModel> findConcernerByRandonneeConcernerCodeRandonneesOrderByDateConcerner(Long randonneeConcernerId);
	
	ConcernerModel findConcernerByRandonneeConcernerCodeRandonneesAndDateConcerner(Long randonneeConcernerId, Date dateConcerner);
	
	@Transactional
	@Modifying
    @Query(value = 
    		"""
    		INSERT INTO concerner (concerner.code_Sommets, concerner.code_Randonnees, concerner.date_Concerner)
    		VALUES (:sommetId, :randonneeId, :dateConcerner)
    		""", nativeQuery = true)
    void saveConcerner(@Param("sommetId") Long sommetId,
    		   		   @Param("randonneeId") Long randonneeId,
    		   		   @Param("dateConcerner") Date dateConcerner);
	
	@Transactional
	@Modifying
	@Query(value = 
			"""
			DELETE FROM concerner
			WHERE concerner.code_Randonnees = :randonneeId
			AND concerner.date_Concerner = :dateConcerner
			""", nativeQuery = true)
	void deleteConcerner(@Param("dateConcerner") Date dateConcerner,
						 @Param("randonneeId") Long randonneeId);

}

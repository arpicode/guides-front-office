package fr.guidesmvc.frontoffice.sommets;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SommetRepository extends CrudRepository<SommetModel, Long> {

	List<SommetModel> findAll();
	
    @Query(value =
            """
            SELECT sommets.*, ascension.duree_Ascension
            FROM ascension
            JOIN sommets ON ascension.code_Sommets = sommets.code_Sommets
            JOIN abris ON ascension.code_Abris = abris.code_Abris
            WHERE abris.code_Abris = :abriId
            """, nativeQuery = true)
        List<SommetModel> findReachableSommetFromAbri(@Param("abriId") Long abriId);

}

package fr.guidesmvc.frontoffice.abris;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AbriRepository extends CrudRepository<AbriModel, Long> {

    @Query(value =
            """
            SELECT abris.*
            FROM abris
            JOIN ascension ON abris.code_Abris = ascension.code_Abris
            JOIN sommets ON ascension.code_Sommets = sommets.code_Sommets
            WHERE sommets.code_Sommets = :sommetId
            """, nativeQuery = true)
    List<AbriModel> findAbrisReachableFromSommet(@Param("sommetId") Long sommetId);

}

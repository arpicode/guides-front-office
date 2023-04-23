package fr.guidesmvc.frontoffice.guides;

import org.springframework.data.repository.CrudRepository;

public interface GuideRepository extends CrudRepository<GuideModel, Long> {
	
	GuideModel findByEmailGuidesAndMotdepasseGuides(String email, String password);
	
}

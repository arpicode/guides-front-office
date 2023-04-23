package fr.guidesmvc.frontoffice.guides;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fr.guidesmvc.frontoffice.randonnees.RandonneeRepository;

@RestController
public class GuideController {
	
	@Autowired
	private GuideRepository guideRepository;
	
	@Autowired
	private ApplicationContext context;

	@RequestMapping("/guides")
	public ModelAndView index() {
		ModelAndView mnv = new ModelAndView("guides/index");
		mnv.addObject("guides", guideRepository.findAll());
		
		return mnv;
	}
	
	@RequestMapping("/guides/{guideId}")
	public ModelAndView show(@PathVariable("guideId") Long guideId) {
		ModelAndView mnv = new ModelAndView("guides/show");
		RandonneeRepository randonneeRepository = (RandonneeRepository) context.getBean("randonneeRepository");
		
		mnv.addObject("guide", guideRepository.findById(guideId));
		mnv.addObject("randonnees", randonneeRepository.findAllRandonneeByGuideResponsableCodeGuides(guideId));
		
		return mnv;
	}
	
	
}

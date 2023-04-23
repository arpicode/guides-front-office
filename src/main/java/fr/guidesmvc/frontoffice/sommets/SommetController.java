package fr.guidesmvc.frontoffice.sommets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SommetController {
	
	@Autowired
	SommetRepository sommetRepository;

	@RequestMapping("/sommets")
	public ModelAndView index() {
		ModelAndView mnv = new ModelAndView("sommets/index");
		mnv.addObject("sommets", sommetRepository.findAll());
		
		return mnv;
	}
}

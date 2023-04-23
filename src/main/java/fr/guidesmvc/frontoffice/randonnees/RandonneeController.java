package fr.guidesmvc.frontoffice.randonnees;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import fr.guidesmvc.frontoffice.concerner.ConcernerRepository;
import fr.guidesmvc.frontoffice.guides.GuideModel;
import fr.guidesmvc.frontoffice.reserver.ReserverRepository;
import jakarta.servlet.http.HttpServletRequest;



@RestController
public class RandonneeController {
	
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	
	@Autowired
	RandonneeRepository randonneeRepository;
	
	@Autowired
	ReserverRepository reserverRepository;
	
	@Autowired
	ConcernerRepository concernerRepository;
	
	@RequestMapping("/randonnees")
	public ModelAndView index(HttpServletRequest req) {
		
		GuideModel guide = (GuideModel)	req.getSession().getAttribute("guide");	
		if (guide == null) {
			System.out.println("Pas de guide authentifié.");
			return new ModelAndView("redirect:/login");
		}
		
		ModelAndView mnv = new ModelAndView("randonnees/index");
		mnv.addObject("randonnees", randonneeRepository.findAllByGuideId(guide.getCodeGuides()));
		
		return mnv;
	}
	
	@RequestMapping("/randonnees/create")
	public ModelAndView create(HttpServletRequest req) {
		
		if (req.getSession().getAttribute("guide") == null) {
			System.out.println("Pas de guide authentifié.");
			return new ModelAndView("redirect:/login");
		}
		
		return new ModelAndView("randonnees/create");
	}
	
	@PostMapping("/randonnees")
	public ModelAndView store(@RequestParam(value = "nbPersonnes", defaultValue = "1") Integer nbPersonnes,
							  @RequestParam(value = "dateDebut", required = false) String dateDebut,
							  HttpServletRequest req) throws ParseException {
		
		GuideModel guide = (GuideModel) req.getSession().getAttribute("guide");
		
		Date sqlDateDebut;
		if (!dateDebut.isBlank()) {
			sqlDateDebut = new Date(dateFormat.parse(dateDebut).getTime());
			
			randonneeRepository.insertRandonnee(nbPersonnes, sqlDateDebut, sqlDateDebut, guide.getCodeGuides());
		} else {
			System.out.println("ERREUR CREATION DE RANDONNEE");
		}
		
		return new ModelAndView("redirect:/randonnees");
	}
	
	@GetMapping("/randonnees/{randonneeId}")
	public ModelAndView show(@PathVariable("randonneeId") Long randonneeId,
							 HttpServletRequest req) {
		
		GuideModel guide = (GuideModel)	req.getSession().getAttribute("guide");	
		if (req.getSession().getAttribute("guide") == null) {
			System.out.println("Pas de guide authentifié.");
			return new ModelAndView("redirect:/login");
		}

		ModelAndView mnv = new ModelAndView("randonnees/show");
		mnv.addObject("randonnee", randonneeRepository.findByIdAndGuideId(randonneeId, guide.getCodeGuides()));
		mnv.addObject("reservations", reserverRepository.findReserverByRandonneeCodeRandonneesOrderByDateReserver(randonneeId));
		mnv.addObject("sommets", concernerRepository.findConcernerByRandonneeConcernerCodeRandonneesOrderByDateConcerner(randonneeId));
		
		return mnv;
	}
	
	@PostMapping("/randonnees/{randonneeId}")
	public RedirectView update(@PathVariable("randonneeId") Long randonneeId,
							   @RequestParam(value = "nbPersonnes", defaultValue = "1") Integer nbPersonnes) {
		
		randonneeRepository.updateNbPersonnes(randonneeId, nbPersonnes);
		
		RedirectView rv = new RedirectView("/randonnees/{randonneeId}");
        rv.setContextRelative(true);
        rv.addStaticAttribute("randonneeId", randonneeId);

        return rv;
	}
	
	@RequestMapping("/randonnees/delete/{randonneeId}")
	public RedirectView destroy(@PathVariable("randonneeId") Long randonneeId) {
		System.out.println("DESTROY RANDONNEE");
		
		randonneeRepository.deleteRandonnee(randonneeId);
		
		RedirectView rv = new RedirectView("/randonnees");
        rv.setContextRelative(true);

        return rv;
	}
	
}

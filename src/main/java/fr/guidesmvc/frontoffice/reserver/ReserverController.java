package fr.guidesmvc.frontoffice.reserver;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import fr.guidesmvc.frontoffice.abris.AbriRepository;
import fr.guidesmvc.frontoffice.concerner.ConcernerModel;
import fr.guidesmvc.frontoffice.concerner.ConcernerRepository;
import fr.guidesmvc.frontoffice.randonnees.RandonneeModel;
import fr.guidesmvc.frontoffice.randonnees.RandonneeRepository;
import fr.guidesmvc.frontoffice.sommets.SommetModel;
import fr.guidesmvc.frontoffice.sommets.SommetRepository;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ReserverController {
	
	@Autowired
	private ReserverRepository reserverRepository;
	
	@Autowired
	private SommetRepository sommetRepository;
	
	@Autowired
	private ConcernerRepository concernerRepository;
	
	@Autowired
	private AbriRepository abriRepository;
	
	@Autowired
	private RandonneeRepository randonneeRepository;

	@RequestMapping("/reservations")
	public ModelAndView index(HttpServletRequest req) {
		
		if (req.getSession().getAttribute("guide") == null) {
			System.out.println("Pas de guide authentifié.");
			return new ModelAndView("redirect:/login");
		}

		ModelAndView mnv = new ModelAndView("reservations/index");
		mnv.addObject("reservations", reserverRepository.findAll());
		
		return mnv;
	}
	
	@GetMapping("/reservations/{randonneeId}/{dateReserver}")
	public ModelAndView show(@PathVariable("randonneeId") Long randonneeId,
							 @PathVariable("dateReserver") Date dateReserver,
							 HttpServletRequest req) {
		
		if (req.getSession().getAttribute("guide") == null) {
			System.out.println("Pas de guide authentifié.");
			return new ModelAndView("redirect:/login");
		}

		ModelAndView mnv = new ModelAndView("reservations/show");
		mnv.addObject("reservation", reserverRepository.findReserverByRandonneeCodeRandonneesAndDateReserver(randonneeId, dateReserver));
		mnv.addObject("sommetConcerner", concernerRepository.findConcernerByRandonneeConcernerCodeRandonneesAndDateConcerner(randonneeId, dateReserver));
		
		return mnv;
	}
	
	@PostMapping("/reservations/{randonneeId}/{dateReserver}")
	public RedirectView update(@PathVariable("randonneeId") Long randonneeId,
			 				   @PathVariable("dateReserver") Date dateReserver,
			 				   @RequestParam("statut") String statut) {
		
		
		reserverRepository.updateStatut(randonneeId, dateReserver, statut);
		
		RedirectView rv = new RedirectView("/randonnees/{randonneeId}");
        rv.setContextRelative(true);
        rv.addStaticAttribute("randonneeId", randonneeId);

        return rv;
	}

	
	@RequestMapping("/reservations/create")
	public ModelAndView create(@RequestParam(name = "randonneeId") Long randonneeId,
							   @RequestParam(name = "dateReservation", defaultValue = "1900-01-01") Date dateReservation,
			 				   @RequestParam(name = "selectedSommet", defaultValue = "0") Long selectedSommet,
                               @RequestParam(name = "selectedAbri", defaultValue = "0") Long selectedAbri,
                               HttpServletRequest req) {
		
		if (req.getSession().getAttribute("guide") == null) {
			System.out.println("Pas de guide authentifié.");
			return new ModelAndView("redirect:/login");
		}

		ModelAndView mnv = new ModelAndView("reservations/create");
		ReserverModel lastReservation = reserverRepository.findLastReservation(randonneeId);
		// Dernière réservation
		mnv.addObject("derniereReservation", lastReservation);
		mnv.addObject("randonneeId", randonneeId);
		
		if (lastReservation != null) {
			mnv.addObject("dernierSommet", concernerRepository.findConcernerByRandonneeConcernerCodeRandonneesAndDateConcerner(randonneeId, lastReservation.getDateReserver()));
		}
		
		// Si la date de réservation n'est pas encore définie.
		if ("1900-01-01".equals(dateReservation.toString())) {
			// Si il n'y a pas de dernière réservation.
			if (lastReservation == null) {
				// Fixer la date de réservation à la date de début de la randonnée.
				RandonneeModel r = randonneeRepository.findById(randonneeId).orElse(null);
				if (r != null) {
					Date dd = r.getDateDebutRandonnees();
					mnv.addObject("dateReservation", dd);
				} else {
					System.out.println("Erreur: impossible de trouver la randonnée.");
				}
				
			} else {
				// Fixer la date à J+1 de la date de la dernière réservation.
				LocalDate datePlus1 = lastReservation.getDateReserver().toLocalDate().plusDays(1);
				mnv.addObject("dateReservation", Date.valueOf(datePlus1));
			}
		} else {
			mnv.addObject("dateReservation", dateReservation);
		}
		
		// Si on est à l'étape de saisie d'un sommet.
		if (selectedSommet == 0) {
			List<SommetModel> sommets = null;
			// S'il n'y a pas de dernière réservation pour la randonnée.
            if (lastReservation == null) {
                // Récupérer la liste de tous les sommets pour valoriser le select des sommets.
                sommets = sommetRepository.findAll();
            } else {
                // Si non, récupérer la liste des sommets atteignables depuis le dernier abris réservé
                // pour valoriser le select.
                sommets = sommetRepository.findReachableSommetFromAbri(lastReservation.getAbriReserve().getCodeAbris());
            }
            mnv.addObject("sommets", sommets);
            
		} else {
			// Récupérer le sommet qui vien d'être sélectionné.
			mnv.addObject("sommet", sommetRepository.findById(selectedSommet).orElse(null));
			
			// Si on est à l'étape de saisie d'un abri.
			if (selectedAbri == 0) {
				// Récupérer la liste des abris atteignables depuis le sommet qui vient d'être saisie
                // pour valoriser le select des abris.
				mnv.addObject("abris", abriRepository.findAbrisReachableFromSommet(selectedSommet));
			} else {
				// Récupérer l'abri qui vient d'être sélectionné.
				mnv.addObject("abri", abriRepository.findById(selectedAbri).orElse(null));
				mnv.addObject("isLastStep", true);
			}
		}
		
		mnv.addObject("selectedSommet", selectedSommet);
        mnv.addObject("selectedAbri", selectedAbri);
        
		
		return mnv;
	}
	
	@PostMapping("/reservations")
	public RedirectView store(@RequestParam(name = "randonneeId") Long randonneeId,
							  @RequestParam(name = "dateReservation", defaultValue = "1900-01-01") Date dateReservation,
							  @RequestParam(name = "selectedSommet", defaultValue = "0") Long selectedSommet,
				              @RequestParam(name = "selectedAbri", defaultValue = "0") Long selectedAbri,
				              @RequestParam(name = "statut", defaultValue = "") String statut) {
		
        RedirectView rv = new RedirectView("randonnees/{randonneeId}");
        rv.addStaticAttribute("randonneeId", randonneeId);
        
        // Sauvegarder la réservation.
        reserverRepository.saveReservation(selectedAbri, randonneeId, dateReservation, statut);
        // Sauvegarder le sommet concerner.
        concernerRepository.saveConcerner(selectedSommet, randonneeId, dateReservation);
        // Mettre à jour la date de fin de randonnée.
        randonneeRepository.updateDateFin(randonneeId, dateReservation);
        
        return rv;
	}
	
	@PostMapping("/reservations/{dateReservation}")
	public RedirectView destroy(@PathVariable("dateReservation") Date dateReservation,
								@RequestParam("randonneeId") Long randonneeId) {
		
		// Supprimer la réservation.
		reserverRepository.deleteReservation(dateReservation, randonneeId);
		// Supprimer le sommet concerner.
		concernerRepository.deleteConcerner(dateReservation, randonneeId);
		
		// Mettre à jours les dates
		
		/* Dans la mesure ou les dates on été rajouté dans la clé primaire composite des tables
		 * 'reserver' et 'concerner' on ne peut pas simplement les UPDATE si non on aurait la
		 * possibilité d'avoir 2 clés primaires identiques et donc une erreur SQL. Une meilleur
		 * solution aurait probablement été de rajouter une colonne id aux tables pour avoir :
		 * [id, code_Abris, code_Randonnees] comme PK pour 'reserver'
		 * et
		 * [id, code_Sommets, code_Randonnees] comme PK pour 'concerner'
		 * 
		 * on ne voulait pas altérer la structure de la DB fournie.
		 * 
		 */
		
		// Récuperer les réservations de la randonnée et les sommets concernés restants.
		List<ReserverModel> reservations = reserverRepository.findReserverByRandonneeCodeRandonneesOrderByDateReserver(randonneeId);
		List<ConcernerModel> sommetsConcernes = concernerRepository.findConcernerByRandonneeConcernerCodeRandonneesOrderByDateConcerner(randonneeId);
		
		// Supprimer toutes les réservations de la randonnée.
		for (ReserverModel rm : reservations) {
			reserverRepository.deleteReservation(rm.getDateReserver(), randonneeId);
		}
		// Supprimer tous les sommets concernés par la randonnée.
		for (ConcernerModel cm : sommetsConcernes) {
			concernerRepository.deleteConcerner(cm.getDateConcerner(), randonneeId);
		}
		
		// Réinsérer toutes les réservations précédement récupérées avec les dates mises à jour
		for (int i = 0; i < reservations.size(); i++) {
			LocalDate dateDebutRandonnee = reservations.get(i).getRandonnee().getDateDebutRandonnees().toLocalDate();
	        // Sauvegarder la réservation courante.
	        reserverRepository.saveReservation(reservations.get(i).getAbriReserve().getCodeAbris(),
	        								   randonneeId,
	        								   Date.valueOf(dateDebutRandonnee.plusDays(i)),
	        								   reservations.get(i).getStatutReserver());
		}
		
		// Réinsérer tous les sommets réservés avec les dates mises à jour
		for (int i = 0; i < reservations.size(); i++) {
			LocalDate dateDebutRandonnee = reservations.get(i).getRandonnee().getDateDebutRandonnees().toLocalDate();
			// Sauvegarder le sommet concerné courant.
			concernerRepository.saveConcerner(sommetsConcernes.get(i).getSommetConcerner().getCodeSommets(),
											  randonneeId,
											  Date.valueOf(dateDebutRandonnee.plusDays(i)));
		}
		
		
		// Mettre à jour la date de fin de randonnée
		ReserverModel lastReservation = reserverRepository.findLastReservation(randonneeId);
		if (lastReservation == null) {
			// S'il n'y a pas de réservation datefin = datedebut
			randonneeRepository.resetDateFin(randonneeId);
		} else {
			// Si non dateFinRandonnée = date dernière réservation + 1
			randonneeRepository.updateDateFin(randonneeId, lastReservation.getDateReserver());
		}

		RedirectView rv = new RedirectView("/randonnees/{randonneeId}");
        rv.setContextRelative(true);
        rv.addStaticAttribute("randonneeId", randonneeId);

        return rv;
	}
		
}

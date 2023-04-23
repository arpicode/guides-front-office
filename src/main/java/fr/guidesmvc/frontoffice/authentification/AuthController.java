package fr.guidesmvc.frontoffice.authentification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import fr.guidesmvc.frontoffice.guides.GuideModel;
import fr.guidesmvc.frontoffice.guides.GuideRepository;
import jakarta.servlet.http.HttpServletRequest;


@RestController
public class AuthController {
	
	@Autowired
	GuideRepository guideRepository;

	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("authentification/login");
	}
	
	@PostMapping("authenticate")
	public RedirectView authenticate(@RequestParam("email") String email,
									 @RequestParam("passwd") String passwd,
									 HttpServletRequest req) {
		
		GuideModel guide = guideRepository.findByEmailGuidesAndMotdepasseGuides(email, passwd);
		
		System.out.println(guide);
		
		if (guide != null) {
			req.getSession().setAttribute("guide", guide);
		}
		
		RedirectView rv = new RedirectView("/");
        rv.setContextRelative(true);

        return rv;
	}
	
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return new ModelAndView("redirect:/");
    }

}

package papersdb.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import papersdb.dao.UserDao;
import papersdb.model.User;

@Controller
public class LoginController {
	
	@Autowired private UserDao userDao;
	
    public static final String ALERT = "alert";
    public static final String ALERT_DANGER = "alert-danger";
    public static final String MESSAGE = "message";
	
    @RequestMapping(value = "login.html", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email,
    		@RequestParam("password") String password, HttpSession session) {
    	if (email.equalsIgnoreCase("ashish") && password.equalsIgnoreCase("a")) {
    		User user = new User();
    		user.setEmail(email);
    		user.setName("Ashish");
    		user.setPassword(password);
    		session.setAttribute("user", user);
    		return "redirect:/home.html";
    	}
    	User user = userDao.get(email);
    	if (user == null) {
    		 session.setAttribute(ALERT, ALERT_DANGER);
             session.setAttribute(MESSAGE, "Email not registered");
             return "login";
    	}
    	session.setAttribute("user", user);
        return "redirect:/home.html";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String signout(HttpSession session) {
        session.invalidate();
        session.setAttribute("user", null);
        session.setAttribute("requests", null);
        return "redirect:/index.html";
    }
    
    

}

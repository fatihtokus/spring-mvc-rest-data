package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/greeting")
public class GreetingController {

    @RequestMapping(method = RequestMethod.GET)
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        if (name.contentEquals("World")) {
        	throw new SpittleNotFoundException();
        	}
        return "greeting";
    }

    
    @ExceptionHandler(SpittleNotFoundException.class)
    public String handleSpittleNotFoundException() {
    return "error/notFound";
    }
}

package hello;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/rest")
public class RestController {
	@Autowired
	SpitterRepository spitterRepository;

	@RequestMapping(path = "/byUserName", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody Spitter path1Get(@RequestParam(value="userName", defaultValue="20") String userName) {
		return spitterRepository.findByUserName(userName).get(0);
	}
	@RequestMapping(path = "/allSpitter", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Spitter> getAllSpitter() {
		return spitterRepository.findAll();
	}

}

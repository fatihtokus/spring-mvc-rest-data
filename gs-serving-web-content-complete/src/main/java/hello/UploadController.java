package hello;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@RequestMapping(method = RequestMethod.GET)
	public String registerFormGet(Model model) {
		// model.addAttribute("spitter", new Spitter());
		return "upload";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		//storageService.store(file);
		redirectAttributes.addFlashAttribute("message",	"You successfully uploaded " + file.getOriginalFilename() + "!");
		return "redirect:/upload";
	}
}
	
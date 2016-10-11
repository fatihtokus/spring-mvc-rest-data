package hello;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Controller
@RequestMapping("/registerForm")
public class RegisterFormController {
	/*
	 * @Bean public JpaVendorAdapter jpaVendorAdapter() {
	 * HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
	 * adapter.setDatabasePlatform("H2"); adapter.setShowSql(true);
	 * adapter.setGenerateDdl(false);
	 * adapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect"); return
	 * adapter; }
	 * 
	 * @Bean public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	 * DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
	 * LocalContainerEntityManagerFactoryBean emfb = new
	 * LocalContainerEntityManagerFactoryBean(); emfb.setDataSource(dataSource);
	 * emfb.setJpaVendorAdapter(jpaVendorAdapter);
	 * emfb.setPackagesToScan("hello"); return emfb; }
	 */
	@Autowired
	SpitterRepository spitterRepository;
	@Autowired
	private JavaMailSenderImpl javaMailSender;

	@Autowired
	private SpringTemplateEngine thymeleaf;

	@Autowired
	public void SpitterEmailServiceImpl(SpringTemplateEngine thymeleaf) {
		this.thymeleaf = thymeleaf;
	}

	@Bean
	public ClassLoaderTemplateResolver emailTemplateResolver() {
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix("templates/");
		resolver.setTemplateMode("HTML5");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setOrder(1);
		return resolver;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String registerFormGet(Model model) {
		Spitter spitter = spitterRepository.findOne(1L);
		spitter = new Spitter();

		RestTemplate restTemplate = new RestTemplate();
		spitter=restTemplate.getForObject("http://localhost:8080/rest/byUserName?userName=user1", Spitter.class);  
		model.addAttribute("spitter", spitter);
		spitter.setId(0L);
		spitterRepository.save(spitter);
		
		//ResponseEntity<Spitter> rs=(ResponseEntity)restTemplate.getForEntity("http://localhost:8080/rest/allSpitter", Spitter.class); 
		//System.out.println("rs : "+rs);
		//List<Spitter> spitters = (List<Spitter>)restTemplate.getForObject("http://localhost:8080/rest/allSpitter", List.class); 
		Spitter[] spitters  = restTemplate.getForObject("http://localhost:8080/rest/allSpitter", Spitter[].class);
		
	//	spitters.forEach(s->{
			//Calendar c=Calendar.getInstance();
			//System.out.println("s.getCreateDate() : "+s.getCreateDate());
			//s.setCreateDate(new Date(s.getCreateDate()));
			
		//});
		model.addAttribute("spitters", spitters);
		//model.addAttribute("spitters", spitterRepository.findAll());
		return "registerForm";
	}

	// @RequestMapping(method = RequestMethod.GET)
	public String registerFormGetExt(Model model) {
		Spitter spitter = spitterRepository.findOne(1L);
		// spitter=spitterRepository.findAllGmailSpitters().get(0);
		// spitterRepository.eliteSweep();

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("fatih.tokus@gmail.com");
		mailMessage.setTo("fatih.tokus@gmail.com");
		mailMessage.setSubject("Registration");
		mailMessage.setText("Hello " + spitter.getUserName() + "\n Your registration is successfull");
		javaMailSender.send(mailMessage);

		Context ctx = new Context();
		ctx.setVariable("spitterName", spitter.getUserName());
		ctx.setVariable("spittleText", spitter.getUserName());
		String emailText = thymeleaf.process("email.html", ctx);
		mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("fatih.tokus@gmail.com");
		mailMessage.setTo("fatih.tokus@gmail.com");
		mailMessage.setSubject("Registration with thymeleaf");
		mailMessage.setText(emailText);
		javaMailSender.send(mailMessage);

		model.addAttribute("spitter", spitter);

		return "registerForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerFormPost(@Valid Spitter spitter, Errors errors) {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		spitter.setCreateDate(Calendar.getInstance().getTime());
		spitterRepository.save(spitter);
		System.out.println(
				"registerFormPost registerFormPost registerFormPostregisterFormPost: " + spitter.getFirstName());

		return "redirect:/registerForm";
		// return "redirect:/registerForm/"+spitter.getUserName();
	}

	@RequestMapping(value = "/{userName}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		model.addAttribute("name", username);
		return "profile";
	}
}

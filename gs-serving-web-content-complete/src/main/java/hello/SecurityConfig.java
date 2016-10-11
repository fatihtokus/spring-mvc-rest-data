package hello;

import org.springframework.context.annotation.Configuration;
import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import org.springframework.context.annotation.Bean;

/*
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
*/
@Configuration
// @EnableWebMvcSecurity
public class SecurityConfig {// extends WebSecurityConfigurerAdapter {
	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "805");
	    javaMailSender.setJavaMailProperties(props);		
	    javaMailSender.setUsername("fatih.tokus@gmail.com");
	    javaMailSender.setPassword("Gbahar12345.");
		return javaMailSender;
	}
	
	 @Bean
	    ServletRegistrationBean h2servletRegistration(){
	        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
	        registrationBean.addUrlMappings("/console/*");
	        return registrationBean;
	    }
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth)
	 * throws Exception { auth .inMemoryAuthentication()
	 * .withUser("user").password("password").roles("USER").and()
	 * .withUser("admin").password("password").roles("USER", "ADMIN"); }
	 * 
	 * @Bean public JndiObjectFactoryBean dataSource() { JndiObjectFactoryBean
	 * jndiObjectFB = new JndiObjectFactoryBean();
	 * jndiObjectFB.setJndiName("jdbc/SpittrDS");
	 * jndiObjectFB.setResourceRef(true);
	 * jndiObjectFB.setProxyInterface(javax.sql.DataSource.class); return
	 * jndiObjectFB; }
	 */
}
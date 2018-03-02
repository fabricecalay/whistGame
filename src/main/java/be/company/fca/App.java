package be.company.fca;

import be.company.fca.config.ZKCEApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;

/**
 * First class of Whist Game
 * Created by fca on 18-09-17.
 */
@SpringBootApplication
@ZKCEApplication
//@Configuration
//@EnableWebSecurity
//@EnableOAuth2Sso
public class App { //extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//            http.csrf().disable();
//    }

////                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//                .authorizeRequests()
//                .antMatchers("/","/*","/login**","/webjars/**","/zkau/**","/images/**","/scripts/**","/css/**").permitAll()
//                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login.zul")
////                .permitAll()
//                .and()
//                .logout().logoutSuccessUrl("/")
//                .permitAll();
//    }


//    @RequestMapping(value = "/oauth/revoke-token", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public void logout(HttpServletRequest request) {
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null) {
//            String tokenValue = authHeader.replace("Bearer", "").trim();
//            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
//            tokenStore.removeAccessToken(accessToken);
//        }
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//    }

    public static void main(String[] args) throws Exception{

        System.setProperty("http.proxyHost", "proxy-http.spw.wallonie.be");
        System.setProperty("http.proxyPort", "3129");

        System.setProperty("https.proxyHost", "proxy-http.spw.wallonie.be");
        System.setProperty("https.proxyPort", "3129");

        SpringApplication.run(App.class, args);

//        Game game = new Game();
//        game.initGame();
//
//        game.chooseContract();
////        game.showPlayersDeck();
//
//        game.launchCardGame();
    }
}

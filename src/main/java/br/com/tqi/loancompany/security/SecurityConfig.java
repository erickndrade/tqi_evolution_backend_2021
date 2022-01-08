package br.com.tqi.loancompany.security;

import br.com.tqi.loancompany.repository.ClienteRepository;
import br.com.tqi.loancompany.services.AuthenticationService;
//import br.com.tqi.loancompany.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;

//    @Autowired
//    private TokenService tokenService;

    @Autowired
    private ClienteRepository clienteRepository;

    //Configurações de Autenticação
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/clientes").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/clientes/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "clientes/signup").permitAll()
                .antMatchers(HttpMethod.DELETE, "/clientes/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/clientes/{id}").authenticated()
                .antMatchers(HttpMethod.GET, "/emprestimos").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/emprestimos/{id}").authenticated()
                .antMatchers(HttpMethod.GET, "/emprestimos/clientes/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "/emprestimos/create/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/emprestimos/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/emprestimos/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST,"/auth").permitAll()
                .anyRequest().permitAll()
                .and().csrf().disable();
//                .addFilter(new AuthenticationFilter(authenticationManager()))
//                .addFilter(new AuthorizationFilter(authenticationManager()))
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring()
                .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

}

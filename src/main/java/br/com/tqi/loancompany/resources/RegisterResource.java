//package br.com.tqi.loancompany.resources;
//
//import br.com.tqi.loancompany.domain.Cliente;
//import br.com.tqi.loancompany.repository.ClienteRepository;
//import br.com.tqi.loancompany.resources.dto.DadosLogin;
//import br.com.tqi.loancompany.resources.dto.TokenDto;
//import br.com.tqi.loancompany.security.AuthenticationService;
//import br.com.tqi.loancompany.services.TokenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@RestController
//public class RegisterResource {
//
//    @Autowired
//    private AuthenticationService authenticationService;
//
//    @Autowired
//    private ClienteRepository clienteRepository;
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
////    @PostMapping("/login")
////    public void singUp(@RequestBody Cliente cliente){
////        cliente.setSenha(cliente.getSenha());
////        clienteRepository.save(cliente);
////    }
//
//
////    @PostMapping("/login")
////    public ResponseEntity<?> registrar(@RequestBody Cliente registroCliente) throws Exception {
////        authenticate(registroCliente.getEmail(), registroCliente.getSenha());
////        final Cliente cliente = clienteRepository.findByEmail(registroCliente.getEmail());
////        final String token = tokenService.buildToken(cliente);
////        return ResponseEntity.ok(new TokenDto(token));
////    }
////
////    private void authenticate(String username, String password) throws Exception {
////        try {
////            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
////        } catch (DisabledException e) {
////            throw new Exception("USER_DISABLED", e);
////        } catch (BadCredentialsException e) {
////            throw new Exception("INVALID_CREDENTIALS", e);
////        }
////    }
//}

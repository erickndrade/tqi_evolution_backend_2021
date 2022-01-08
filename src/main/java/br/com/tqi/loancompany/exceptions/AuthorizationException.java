package br.com.tqi.loancompany.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthorizationException extends RuntimeException{

    public AuthorizationException(){
        super();
    }

    public AuthorizationException(String txt, Throwable cause){
        super(txt, cause);
    }

    public AuthorizationException(Throwable cause){
        super(cause);
    }

    public AuthorizationException(String msg){
        super(msg);
    }


}

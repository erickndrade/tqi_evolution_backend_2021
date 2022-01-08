package br.com.tqi.loancompany.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidLoginException extends RuntimeException{

    public InvalidLoginException(){
        super();
    }
    public InvalidLoginException(String txt, Throwable cause){
        super(txt, cause);
    }
    public InvalidLoginException(String msg){
        super(msg);
    }
    public InvalidLoginException(Throwable cause){
        super(cause);
    }
}

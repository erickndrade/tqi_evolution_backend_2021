package br.com.tqi.loancompany.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    public BusinessException(){
        super();
    }
    public BusinessException(String txt, Throwable cause){
        super(txt, cause);
    }
    public BusinessException(String msg){
        super(msg);
    }
    public BusinessException(Throwable cause){
        super(cause);
    }

}

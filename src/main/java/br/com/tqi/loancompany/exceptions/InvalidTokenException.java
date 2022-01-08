package br.com.tqi.loancompany.exceptions;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(){
        super();
    }
    public InvalidTokenException(String txt, Throwable cause){
        super(txt, cause);
    }
    public InvalidTokenException(String msg){
        super(msg);
    }
    public InvalidTokenException(Throwable cause){
        super(cause);
    }
}

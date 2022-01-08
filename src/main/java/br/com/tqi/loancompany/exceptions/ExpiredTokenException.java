package br.com.tqi.loancompany.exceptions;

public class ExpiredTokenException extends RuntimeException{

    public ExpiredTokenException(){
        super();
    }
    public ExpiredTokenException(String txt, Throwable cause){
        super(txt, cause);
    }
    public ExpiredTokenException(String msg){
        super(msg);
    }
    public ExpiredTokenException(Throwable cause){
        super(cause);
    }
}

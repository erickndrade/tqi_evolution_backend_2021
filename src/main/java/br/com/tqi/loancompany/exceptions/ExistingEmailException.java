package br.com.tqi.loancompany.exceptions;


public class ExistingEmailException extends RuntimeException {

    public ExistingEmailException(){
        super();
    }
    public ExistingEmailException(String txt, Throwable cause){
        super(txt, cause);
    }
    public ExistingEmailException(String msg){
        super(msg);
    }
    public ExistingEmailException(Throwable cause){
        super(cause);
    }
}

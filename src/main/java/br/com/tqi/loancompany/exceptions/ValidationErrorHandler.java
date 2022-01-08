package br.com.tqi.loancompany.exceptions;

import br.com.tqi.loancompany.resources.dto.ValidacaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidacaoDto> handle(MethodArgumentNotValidException exception) {
        List<ValidacaoDto> validacaoDtos = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(ex ->{
            String mensagem = messageSource.getMessage(ex, LocaleContextHolder.getLocale());
            ValidacaoDto erro = new ValidacaoDto(ex.getField(), mensagem);
            validacaoDtos.add(erro);
        });
        return validacaoDtos;
    }
}

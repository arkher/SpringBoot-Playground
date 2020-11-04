package br.com.ithappens.model.exceptionHandler;

import br.com.ithappens.model.exceptions.FilialNaoEncontradaException;
import br.com.ithappens.model.exceptions.ProdutoInexistenteException;
import br.com.ithappens.model.exceptions.QuantidadeInvalidaException;
import br.com.ithappens.model.exceptions.SemItensPedidoException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SemItensPedidoException.class)
    protected ResponseEntity<Object> handleSemItensPedido(SemItensPedidoException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(FilialNaoEncontradaException.class)
    protected ResponseEntity<Object> handleFilialNaoEncontrada(FilialNaoEncontradaException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ProdutoInexistenteException.class)
    protected ResponseEntity<Object> handleProdutoNaoEncontrado(ProdutoInexistenteException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(QuantidadeInvalidaException.class)
    protected ResponseEntity<Object> handleQuantidadeInvalida(QuantidadeInvalidaException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
package br.com.everis.applicant.exceptions;

public class NotImplementationConstructionException extends RuntimeException {

    public NotImplementationConstructionException() {
    }

    public NotImplementationConstructionException(String message) {
        super(message);
    }

    public NotImplementationConstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotImplementationConstructionException(Throwable cause) {
        super(cause);
    }

    public NotImplementationConstructionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

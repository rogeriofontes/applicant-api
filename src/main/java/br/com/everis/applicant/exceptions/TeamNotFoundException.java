package br.com.everis.applicant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Team does not exists!")
public class TeamNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -6469007988060422304L;

    public TeamNotFoundException(String message) {
        super(message);
    }
}

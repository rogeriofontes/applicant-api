package br.com.everis.applicant.constants;

import br.com.everis.applicant.exceptions.NotImplementationConstructionException;

public class AuthoritiesConstants {

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";

    public static final String SYSTEM_EMAIL = "root@localhost";
    public static final String SYSTEM_NAME = "home-office-api";
    public static final Long SYSTEM_ID = 1l;

    private AuthoritiesConstants() {
        throw new NotImplementationConstructionException("Classe não pode ser instanciada");
    }
}

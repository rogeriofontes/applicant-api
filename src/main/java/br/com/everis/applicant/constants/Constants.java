package br.com.everis.applicant.constants;

import br.com.everis.applicant.exceptions.NotImplementationConstructionException;

public class Constants {

    // Auxs Constants for Controllers
    public static final String RESPONSE_UNSUCCESS = "unsuccess";

    public static final String RESPONSE_SUCCESS = "success";

    public static final String CURRENT_USER = "root@localhost";

    public static final String DADOS_DELETADOS = "Dados Deletados!";

    public static final String DISCIPLINES_IN_CACHE = "disciplinesInCache";

    public static final String TOTAL = "Total: ";

    public static final int MAXIMUM_SIZE = 100;

    public static final String CENTERS_IN_CACHE = "centersInCache";

    public static final String COUNTRYS_IN_CACHE = "countrysInCache";

    public static final String MANAGE_HOME_OFFICE_IN_CACHE = "manageHomeOfficeInCache";

    public static final String POSITION_IN_CACHE = "positionInCache";

    public static final String PROFESSIONAL_IN_CACHE = "professionalInCache";

    public static final String APPLICANT_IN_CACHE = "applicantInCache";

    public static final String APPLICANT_EVALUATION_IN_CACHE = "applicantEvaluationInCache";

    public static final String DOCUMENT_REGIONS_IN_CACHE = "documentRegionsInCache";

    public static final String PERSON_TYPES_IN_CACHE = "personTypesInCache";

    public static final String PROFILE_IN_CACHE = "profileInCache";

    public static final String PROGRAMMING_LANGUAGE_IN_CACHE = "programmingLanguageInCache";

    public static final String PROJECT_IN_CACHE = "projectInCache";

    public static final String TEAM_IN_CACHE = "teamInCache";

    public static final String USER_IN_CACHE = "UserInCache";

    private Constants() {
        throw new NotImplementationConstructionException("Classe não pode ser instanciada");
    }
}

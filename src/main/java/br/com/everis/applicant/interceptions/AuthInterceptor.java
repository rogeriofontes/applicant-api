package br.com.everis.applicant.interceptions;

import br.com.everis.applicant.web.dto.request.LoginRequest;
import br.com.everis.applicant.web.dto.response.LoginResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class AuthInterceptor implements ClientHttpRequestInterceptor {
    private String email;
    private String password;

    public AuthInterceptor(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
                                        ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpHeaders headers = httpRequest.getHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, encodeJWTAuth(email, password));

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }

    public static String encodeJWTAuth(String username, String password) {
        LoginRequest login = new LoginRequest();

        login.setEmail(username);
        login.setPassword(password);

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");

        HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(login, headers);

        String url = "http://localhost:8181/login";
        LoginResponse response = restTemplate.postForObject(url, requestEntity, LoginResponse.class);
        return response.getToken();
    }
}

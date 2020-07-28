package br.com.everis.applicant.model.domain;

public enum  Roles {
    USER("USER"), ADMIN("ADMIN"), ROOT("ROOT");

    private String role;

    private Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

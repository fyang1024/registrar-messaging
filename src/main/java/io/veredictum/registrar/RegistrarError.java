package io.veredictum.registrar;

public class RegistrarError {

    private String type;
    private String errorMessage;

    public RegistrarError(Exception e) {
        this.type = e.getClass().getSimpleName();
        this.errorMessage = e.getMessage();
    }

    public String getType() {
        return type;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

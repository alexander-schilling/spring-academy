package com.aclti.academy.enums;

public enum ErrorCode {
    USERNAME_IS_BLANK(0, "El nombre de usuario no puede estar en blanco"),
    PASSWORD_IS_BLANK(1, "La contraseña no puede estar en blanco"),
    FIRSTNAME_IS_BLANK(2, "El nombre no puede estar en blanco"),
    LASTNAME_IS_BLANK(3, "El apellido no puede estar en blanco"),
    PASSWORDS_DONT_MATCH(4, "Las contraseñas no coinciden"),
    USER_ALREADY_EXISTS(5, "El nombre de usario está ocupado"),
    USER_NOT_FOUND(6, "No se ha encontrado el usuario"),
    USER_WRONG_PASSWORD(7, "La contraseña es incorrecta");

    private final int code;
    private final String label;

    ErrorCode(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() { return code; }
    public String getLabel() { return label; }

    @Override
    public String toString() {
        return String.format("%d: %s", code, label);
    }
}

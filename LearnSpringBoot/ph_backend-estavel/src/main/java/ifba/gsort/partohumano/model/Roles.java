package ifba.gsort.partohumano.model;

public enum Roles {

	Admin("Admin"), // Gsort
    Sesab("Sesab"),
    Fesf("Fesf"),
    Ssm("Ssm"),
    Enfermeira("Enfermeira");

    private String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public static Boolean igualOuSuperior(Roles doUsuario, Roles necessaria) {
        return doUsuario.ordinal() <= necessaria.ordinal();
    }

}

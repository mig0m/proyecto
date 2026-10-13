package ordenes.ejecucion;

/**
 * DTO que representa la solicitud de documentos.
 */
public class SolicitudDTO {
    private final String tipo; // e.g. "TALON", "MOVIMIENTOS", "TODOS"
    private final String cuenta;

    public SolicitudDTO(String tipo, String cuenta) {
        this.tipo = tipo;
        this.cuenta = cuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCuenta() {
        return cuenta;
    }
}

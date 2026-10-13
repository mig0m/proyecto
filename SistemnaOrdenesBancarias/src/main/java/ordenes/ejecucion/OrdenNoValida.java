package ordenes.ejecucion;

/**
 * Excepción lanzada cuando la solicitud no es válida.
 * El mensaje de la excepción contendrá el tipo de error.
 */
public class OrdenNoValida extends RuntimeException {
    public OrdenNoValida(String mensaje) {
        super(mensaje);
    }
}

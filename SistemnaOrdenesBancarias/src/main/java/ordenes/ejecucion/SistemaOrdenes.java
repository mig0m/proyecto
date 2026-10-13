package ordenes.ejecucion;

import ordenes.documentos.Documento;
import ordenes.documentos.Talon;
import ordenes.documentos.Movimientos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Servicio que procesa la solicitud y devuelve los documentos pedidos.
 */
public class SistemaOrdenes {

    /**
     * Procesa la solicitud y devuelve una lista de Documentos.
     * Lanza OrdenNoValida con el tipo de error en el mensaje cuando algo falla.
     *
     * Reglas mínimas de validación implementadas:
     *  - solicitud no nula -> si es nula lanza OrdenNoValida("SolicitudNull")
     *  - tipo no nulo -> si tipo es nulo lanza OrdenNoValida("TipoNull")
     *  - tipo válido -> "TALON", "MOVIMIENTOS", "TODOS"
     */
    public List<Documento> procesarSolicitud(SolicitudDTO solicitud) {
        if (solicitud == null) {
            throw new OrdenNoValida("SolicitudNull");
        }
        String tipo = solicitud.getTipo();
        if (tipo == null) {
            throw new OrdenNoValida("TipoNull");
        }

        switch (tipo.toUpperCase()) {
            case "TALON":
                return Collections.singletonList(crearTalon());
            case "MOVIMIENTOS":
                return Collections.singletonList(crearMovimientos());
            case "TODOS":
                List<Documento> lista = new ArrayList<>();
                lista.add(crearTalon());
                lista.add(crearMovimientos());
                return lista;
            default:
                throw new OrdenNoValida("TipoNoValido");
        }
    }

    // Fábricas locales para instanciar los documentos; asumen constructores por defecto
    private Talon crearTalon() {
        return new Talon();
    }

    private Movimientos crearMovimientos() {
        return new Movimientos();
    }
}

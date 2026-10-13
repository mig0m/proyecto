package ordenes.ejecucion;

import ordenes.documentos.Documento;
import ordenes.documentos.Talon;
import ordenes.documentos.Movimientos;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SistemaOrdenesTest {

    private final SistemaOrdenes sistema = new SistemaOrdenes();

    @Test
    void solicitudNulaLanzaOrdenNoValida() {
        OrdenNoValida ex = assertThrows(OrdenNoValida.class, () -> sistema.procesarSolicitud(null));
        assertEquals("SolicitudNull", ex.getMessage());
    }

    @Test
    void tipoNuloLanzaOrdenNoValida() {
        SolicitudDTO dto = new SolicitudDTO(null, "12345");
        OrdenNoValida ex = assertThrows(OrdenNoValida.class, () -> sistema.procesarSolicitud(dto));
        assertEquals("TipoNull", ex.getMessage());
    }

    @Test
    void tipoInvalidoLanzaOrdenNoValida() {
        SolicitudDTO dto = new SolicitudDTO("INEXISTENTE", "12345");
        OrdenNoValida ex = assertThrows(OrdenNoValida.class, () -> sistema.procesarSolicitud(dto));
        assertEquals("TipoNoValido", ex.getMessage());
    }

    @Test
    void solicitudValidaTalonDevuelveTalon() {
        SolicitudDTO dto = new SolicitudDTO("TALON", "cuenta1");
        List<Documento> docs = sistema.procesarSolicitud(dto);
        assertNotNull(docs);
        assertEquals(1, docs.size());
        assertTrue(docs.get(0) instanceof Talon);
    }

    @Test
    void solicitudValidaMovimientosDevuelveMovimientos() {
        SolicitudDTO dto = new SolicitudDTO("MOVIMIENTOS", "cuenta2");
        List<Documento> docs = sistema.procesarSolicitud(dto);
        assertNotNull(docs);
        assertEquals(1, docs.size());
        assertTrue(docs.get(0) instanceof Movimientos);
    }

    @Test
    void solicitudValidaTodosDevuelveAmbos() {
        SolicitudDTO dto = new SolicitudDTO("TODOS", "cuenta3");
        List<Documento> docs = sistema.procesarSolicitud(dto);
        assertNotNull(docs);
        assertEquals(2, docs.size());
        boolean tieneTalon = docs.stream().anyMatch(d -> d instanceof Talon);
        boolean tieneMov = docs.stream().anyMatch(d -> d instanceof Movimientos);
        assertTrue(tieneTalon && tieneMov);
    }
}

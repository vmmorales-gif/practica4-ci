package org.ejemplo.musica;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ParticionEstadoTests {

    Cancion c1;
    Playlist p;
    BibliotecaMusical bib;
    Usuario u;

    @BeforeEach
    void setUp() {
        c1 = new Cancion("SongA","ArtistA",120);
        p = new Playlist("P");
        bib = new BibliotecaMusical();
        u = new Usuario("user1");
    }

    // --- Tests para métodos M (mutan) ---
    @Test
    void testAgregarCancion_muta() {
        assertEquals(0, p.tamanyo());
        p.agregarCancion(c1); // MUTACIÓN
        assertEquals(1, p.tamanyo());
        assertTrue(p.getCanciones().contains(c1));
    }

    @Test
    void testAnadirCancionBiblioteca_muta() {
        assertEquals(0, bib.totalCanciones());
        bib.anadirCancion(c1); // MUTACIÓN
        assertEquals(1, bib.totalCanciones());
        List<Cancion> res = bib.buscarPorArtista("ArtistA");
        assertEquals(1, res.size());
    }

    @Test
    void testEliminarTodasDeArtista_muta() {
        bib.anadirCancion(c1);
        assertEquals(1, bib.totalCanciones());
        bib.eliminarTodasDeArtista("ArtistA"); // MUTACIÓN
        assertEquals(0, bib.totalCanciones());
    }

    // --- Tests para métodos NM (no mutan) ---
    @Test
    void testBuscarPorArtista_noMuta() {
        // Sin añadir nada -> buscar no debe lanzar ni mutar
        List<Cancion> r = bib.buscarPorArtista("NoOne");
        assertNotNull(r);
        assertEquals(0, r.size());
        // totalCanciones sigue 0
        assertEquals(0, bib.totalCanciones());
    }

    @Test
    void testGettersUsuario_noMuta() {
        u.crearPlaylist("X");
        assertEquals("user1", u.getNombreUsuario());
        // getPlaylists devuelve la lista actual pero getter en sí no muta
        assertEquals(1, u.getPlaylists().size());
    }
}

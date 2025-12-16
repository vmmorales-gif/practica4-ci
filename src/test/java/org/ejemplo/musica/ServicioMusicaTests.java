package org.ejemplo.musica;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Disabled;


/**
 * Tests para el servicio de música.
 * Con esta versión deberías ver 4 tests que fallan intencionadamente
 * (comentados como FAIL) y el resto pasar.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServicioMusicaTests {

    Cancion c1, c2;
    Playlist p;
    Usuario u;
    BibliotecaMusical bib;

    @BeforeEach
    void setUp() {
        c1 = new Cancion("Bohemian Rhapsody", "Queen", 355);
        c2 = new Cancion("Imagine", "Lennon", 183);
        p = new Playlist("Favoritas");
        u = new Usuario("alice");
        bib = new BibliotecaMusical();
        p.agregarCancion(c1);
        u.crearPlaylist("Favoritas");
        bib.anadirCancion(c1);
        bib.anadirCancion(c2);
    }

    // ---------- PASAN ----------
    @Test
    void testGetTitulo_equals_pass() {
        assertEquals("Bohemian Rhapsody", c1.getTitulo());
    }

    @Test
    void testPlaylist_contains_pass() {
        assertTrue(p.getCanciones().contains(c1));
    }

    @Test
    void testEliminar_noContiene_pass() {
        assertFalse(p.getCanciones().contains(c2));
    }

    @Test
    void testNotNull_pass() {
        // buscarPorArtista devuelve lista (posible vacía) pero nunca null
        assertNotNull(bib.buscarPorArtista("Queen"));
    }

    @Test
    void testAssertSame_and_NotSame_pass() {
        Playlist same = p;
        Playlist another = new Playlist("Otra");
        assertSame(p, same);
        assertNotSame(p, another);
    }

    @Test
    void testArrayEquals_like_pass() {
        List<Cancion> lista = bib.buscarPorArtista("Lennon");
        String[] esperado = {"Imagine"};
        String[] actual = lista.stream().map(Cancion::getTitulo).toArray(String[]::new);
        assertArrayEquals(esperado, actual);
    }

    @Test
    void testBuscarPorArtista_null_throws_pass() {
        assertThrows(IllegalArgumentException.class, () -> bib.buscarPorArtista(null));
    }

    // ---------- FALLOS INTENCIONALES (4) ----------
    @Disabled("Fallo intencional para la práctica de CS")
    @Test
    void testGetTitulo_equals_fail_intencional() {
        // FAIL intencional: case distinto
        assertEquals("bohemian rhapsody", c1.getTitulo());
    }
    @Disabled("Fallo intencional para la práctica de CS")
    @Test
    void testPlaylist_contains_fail_intencional() {
        // FAIL intencional: p no contiene c2
        assertTrue(p.getCanciones().contains(c2));
    }
    @Disabled("Fallo intencional para la práctica de CS")
    @Test
    void testTamanyo_fail_intencional() {
        // FAIL intencional: p.tamanyo() == 1, comprobamos lo contrario
        assertFalse(p.tamanyo() == 1);
    }
    @Disabled("Fallo intencional para la práctica de CS")
    @Test
    void testNull_fail_intencional() {
        // FAIL intencional: buscarPorArtista devuelve lista vacía, no null
        assertNull(bib.buscarPorArtista("NoExiste"));
    }
    
 // Partición 1: límite - canción con duración 0
    @Test
    void testCancionDuracionCero() {
        Cancion c0 = new Cancion("Silencio","Anon",0);
        assertEquals(0, c0.getDuracionSegundos());
    }

    // Partición 2: playlist vacía - eliminar canción no existente devuelve false
    @Test
    void testEliminarCancionNoExistente() {
        Playlist vacia = new Playlist("Vacia");
        assertFalse(vacia.eliminarCancion(c1)); // c1 no está en 'vacia'
    }

    // Partición 3: buscar artista inexistente devuelve lista vacía (no null)
    @Test
    void testBuscarArtistaInexistenteDevuelveListaVacia() {
        List<Cancion> res = bib.buscarPorArtista("ArtistaQueNoExiste");
        assertNotNull(res);
        assertEquals(0, res.size());
    }

    // Partición 4: inmutabilidad - getCanciones() no permite añadir desde fuera
    @Test
    void testGetCancionesInmutable() {
        List<Cancion> lista = p.getCanciones();
        assertThrows(UnsupportedOperationException.class, () -> lista.add(c2));
    }
}
package org.ejemplo.musica;

import java.util.*;

/**
 * Biblioteca que mantiene canciones globales y búsquedas.
 */
public class BibliotecaMusical {
    private final Map<String, List<Cancion>> porArtista = new HashMap<>();

    public void anadirCancion(Cancion c) {
        porArtista.computeIfAbsent(c.getArtista(), a -> new ArrayList<>()).add(c);
    }

    public List<Cancion> buscarPorArtista(String artista) {
        if (artista == null) throw new IllegalArgumentException("artista nulo");
        return Collections.unmodifiableList(porArtista.getOrDefault(artista, Collections.emptyList()));
    }

    public int totalCanciones() {
        return porArtista.values().stream().mapToInt(List::size).sum();
    }

    public void eliminarTodasDeArtista(String artista) {
        porArtista.remove(artista);
    }
}

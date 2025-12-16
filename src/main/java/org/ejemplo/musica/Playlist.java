package org.ejemplo.musica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Playlist perteneciente a un Usuario.
 */
public class Playlist {
    private final String nombre;
    private final List<Cancion> canciones = new ArrayList<>();

    public Playlist(String nombre) {
        if (nombre == null) throw new IllegalArgumentException("nombre nulo");
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }

    public void agregarCancion(Cancion c) {
        if (c == null) throw new IllegalArgumentException("cancion nula");
        canciones.add(c);
    }

    public boolean eliminarCancion(Cancion c) {
        return canciones.remove(c);
    }

    public List<Cancion> getCanciones() {
        return Collections.unmodifiableList(canciones);
    }

    public int tamanyo() {
        return canciones.size();
    }
}

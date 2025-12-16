package org.ejemplo.musica;

import java.util.ArrayList;
import java.util.List;

/**
 * Usuario con varias playlists.
 */
public class Usuario {
    private final String nombreUsuario;
    private final List<Playlist> playlists = new ArrayList<>();

    public Usuario(String nombreUsuario) {
        if (nombreUsuario == null) throw new IllegalArgumentException("nombre nulo");
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() { return nombreUsuario; }

    public Playlist crearPlaylist(String nombre) {
        Playlist p = new Playlist(nombre);
        playlists.add(p);
        return p;
    }

    public List<Playlist> getPlaylists() {
        return playlists; // deliberado: prueba podrá observar mutabilidad aquí
    }
}

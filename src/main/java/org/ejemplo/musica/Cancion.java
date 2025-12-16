package org.ejemplo.musica;

import java.util.Objects;

/**
 * Representa una canción simple.
 */
public class Cancion {
    private final String titulo;
    private final String artista;
    private final int duracionSegundos;

    public Cancion(String titulo, String artista, int duracionSegundos) {
        if (titulo == null || artista == null) throw new IllegalArgumentException("titulo/artista nulos");
        this.titulo = titulo;
        this.artista = artista;
        this.duracionSegundos = duracionSegundos;
    }

    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public int getDuracionSegundos() { return duracionSegundos; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cancion)) return false;
        Cancion c = (Cancion) o;
        return duracionSegundos == c.duracionSegundos &&
               titulo.equals(c.titulo) &&
               artista.equals(c.artista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, artista, duracionSegundos);
    }

    @Override
    public String toString() {
        return titulo + " - " + artista + " (" + duracionSegundos + "s)";
    }
}
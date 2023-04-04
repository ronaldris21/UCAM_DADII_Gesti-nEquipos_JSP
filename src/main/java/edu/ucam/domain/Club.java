//
// Decompiled by Procyon v0.5.36
//

package edu.ucam.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
/**
 * <p>
 * Esta es la clase en la que se crean los clubes
 * </p>
 */
public class Club implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private String img;
    private Hashtable<Integer, Jugador> jugadores;

    public Club() {
        this.jugadores = new Hashtable<>();
    }

    public Club(final int id, final String nombre) {
        this.jugadores = new Hashtable<>();
        this.id = id;
        this.nombre = nombre;
    }
    public Club(final int id, final String nombre, final String img) {
        this.jugadores = new Hashtable<>();
        this.id = id;
        this.nombre = nombre;
        this.img = img;
    }
    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(final String img) {
        this.img = img;
    }
    /**
     * Anade un jugador al club
     */
    public void addJugador(final int id, final Jugador jugador) {
        this.jugadores.put(id, jugador);
    }
    /**
     * Actualiza un jugador que esta en un club
     */
    public void updateJugador(final int id, final Jugador jugador) {
        if (this.jugadores.get(id) != null) {
            this.jugadores.replace(id, jugador);
        }
    }
    /**
     * Borra un jugador que esta en un club
     */
    public boolean removeJugador(final int id) {
        if (this.jugadores.get(id) != null) {
            this.jugadores.remove(id);
            return true;
        }
        return false;
    }
    /**
     * Imprime el numero total de jugadores
     */
    public int totalJugadores() {
        return this.jugadores.size();
    }
    /**
     * Obtiene jugadores del ArrayList jugador
     */
    public ArrayList<Jugador> getJugadores() {
        final ArrayList<Jugador> jugadores = new ArrayList<>();
        for (final Jugador jugador : this.jugadores.values()) {
            jugadores.add(jugador);
        }
        return jugadores;
    }
    /**
     * Devuelve un string
     */
    @Override
	public String toString()
    {
    	return String.format("\tClub %s: %s \n", id, nombre) ;
    }
}

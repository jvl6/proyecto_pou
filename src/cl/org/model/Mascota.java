/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.org.model;

/**
 *
 * @author Jorge A
 */
public class Mascota {
    private int entretencion;
    private int limpieza;
    private int salud;
    
    public Mascota() {
        
    }

    public Mascota(int entretencion, int limpieza, int salud) {
        this.entretencion = entretencion;
        this.limpieza = limpieza;
        this.salud = salud;
    }

    public int getEntretencion() {
        return entretencion;
    }

    public void setEntretencion(int entretencion) {
        this.entretencion = entretencion;
    }

    public int getLimpieza() {
        return limpieza;
    }

    public void setLimpieza(int limpieza) {
        this.limpieza = limpieza;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    @Override
    public String toString() {
        return "Mascota{" + "entretencion=" + entretencion + ", limpieza=" + limpieza + ", salud=" + salud + '}';
    }
}

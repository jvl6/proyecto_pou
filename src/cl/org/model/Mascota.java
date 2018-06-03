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
    private int hambre;
    private int energia;

    public Mascota() {
    }

    public Mascota(int entretencion, int limpieza, int salud, int hambre, int energia) {
        this.entretencion = entretencion;
        this.limpieza = limpieza;
        this.salud = salud;
        this.hambre = hambre;
        this.energia = energia;
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

    public int getHambre() {
        return hambre;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    @Override
    public String toString() {
        return "Mascota{" + "entretencion=" + entretencion + ", limpieza=" + limpieza + ", salud=" + salud + ", hambre=" + hambre + ", energia=" + energia + '}';
    }
}

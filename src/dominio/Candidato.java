package dominio;
import java.io.*;
import java.util.*;

public class Candidato implements Serializable {

    private String nombre;
    private int votos;

    public Candidato (String nombre, int votos){
        this.nombre=nombre;
        this.votos=votos;
    }

    public Candidato (String nombre){
        this.nombre=nombre;
    }

    public Candidato(){}

    public Candidato setNombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    public String getNombre(){
        return nombre;
    }

    public void setVotos(int votos){
        this.votos=votos;
    }

    public int getVotos(){
        return votos;
    }

    public void resetearVotos(){
        this.votos=0;
    }

    public void aumentarVotos(){
        this.votos++;
    }

    @Override
    public boolean equals(Object o){
        if (this==o){
            return true;
        }
        if (o==null) {
            return false;
        }
        if (o instanceof Candidato){
            Candidato other = (Candidato) o;
            return Objects.equals(nombre, other.nombre);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(nombre);
    }

    @Override
    public String toString(){
        StringBuilder datos = new StringBuilder();
        datos.append("\n Nombre del candidato: ")
                .append(nombre)
                .append("\n")
                .append("Numero de votos: ")
                .append(votos)
                .append("\n\n");
        return datos.toString();
    }

}
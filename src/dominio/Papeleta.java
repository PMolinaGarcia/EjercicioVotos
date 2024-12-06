package dominio;
import java.util.*;
import java.io.*;

public class Papeleta implements Serializable {

    private String votante;

    private ArrayList<Candidato> listaPreferencias = new ArrayList<>();

    public Papeleta(String votante){
        this.votante=votante;
    }

    public Papeleta setVotante(String votante){
        this.votante = votante;
        return this;
    }

    public String getVotante(){
        return votante;
    }

    public Papeleta(){}

    public Candidato obtenerPrimeraPreferencia(){
       return listaPreferencias.get(0);
    }

    public Candidato obtenerNuevaPreferencia(int i){
        return listaPreferencias.get(i);
    }

    public void anniadirCandidatoPapeleta(Candidato candidato){
        listaPreferencias.add(candidato);
    }

    public void eliminarCandidato(Candidato candidato){
        listaPreferencias.remove(candidato);
    }

    public int getNumeroCandidatos(){
        return listaPreferencias.size();
    }

    public Candidato getCandidato(int i){
        return listaPreferencias.get(i);
    }

    @Override
    public String toString(){
        StringBuilder datos = new StringBuilder();
        datos.append(listaPreferencias.toString());
        return datos.toString();
    }

}

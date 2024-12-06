package dominio;
import java.io.*;
import java.util.*;

public class Eleccion implements Serializable{

    private ArrayList<Candidato> candidatos = new ArrayList<>();
    private ArrayList<Papeleta> papeletas = new ArrayList<>();

    public void datosCandidatos(){
        for (Candidato candidato : candidatos){
            System.out.println("- "+ candidato.getNombre());
        }
    }

    public void anniadirCandidato(Candidato candidato){
        candidatos.add(candidato);
    }

    public void eliminarCandidato(Candidato candidato){
        candidatos.remove(candidato);
    }

    public int cantidadCandidatos(){
        return candidatos.size();
    }

    public void anniadirPapeleta(Papeleta papeleta){
        papeletas.add(papeleta);
    }

    public void eliminarPapeleta(Papeleta papeleta){
        papeletas.remove(papeleta);
    }

    public void mostrarCandidatosYVotos() {
        for (Candidato candidato : candidatos) {
            System.out.println("Candidato: " + candidato.getNombre() + ". Votos: " + candidato.getVotos());
            for (int i = 0; i < cantidadCandidatos(); i++) {
                Candidato c0 = candidatos.get(i);
                if (candidato.getVotos() > c0.getVotos()) {
                    if (candidato!=c0) {
                        System.out.println("El ganador ha sido " + candidato.getNombre());
                    }
                }
            }
        }
    }

    public void realizarRecuento() {
        for (Papeleta papeleta : papeletas) {
            Candidato c1 = papeleta.obtenerPrimeraPreferencia();
            for (Candidato candidato : candidatos){
                if (c1.equals(candidato)){
                    candidato.aumentarVotos();
                }else{
                    System.out.print("");
                }
            }
        }
    }

    public void realizarNuevoRecuento(int i) {
        for (Papeleta papeleta : papeletas) {
            Candidato c2 = papeleta.obtenerNuevaPreferencia(i);
            for (Candidato candidato : candidatos) {
                if (c2.equals(candidato)) {
                    candidato.aumentarVotos();
                } else {
                    System.out.print("");
                }
            }
        }
    }

    public int contarVotosTotales() {
        int suma = 0;
        for (Candidato candidato : candidatos) {
            suma += candidato.getVotos();
        }
        return suma;
    }

    public void eliminarCandidatoConMenosVotos() {
        for (Candidato candidato : candidatos) {
            for (int i = 0; i < contarVotosTotales(); i++) {
                    if (candidato.getVotos() == i) {
                        eliminarCandidato(candidato);
                        System.out.println("Se ha eliminado el candidato " + candidato.getNombre() + " por tener el menor numero de votos.");
                        break;
                    }
            }
        }
    }


    public void comprobarMayoriaAbsoluta() {
        int k=0;
        for (Candidato candidato : candidatos) {
            if (candidato.getVotos() >= ((contarVotosTotales() / 2) + 1)) {
                for (int i = 0; i < cantidadCandidatos(); i++) {
                    Candidato c3 = candidatos.get(i);
                    if (c3.getVotos() == candidato.getVotos()) {
                        if (c3.equals(candidato)) {
                            System.out.print("");
                        } else {
                            System.out.println("Ha habido un empate. Se procede a la eliminacion del candidato con menos votos y se prosigue con la siguiente preferencia. ");
                            eliminarCandidatoConMenosVotos();
                            ++k;
                            realizarNuevoRecuento(k);
                        }
                    }
                }
            }
        }
    }

    public void validarPapeletas(){
        for (Papeleta papeleta : papeletas){
            for (int i =0; i < papeleta.getNumeroCandidatos(); i++) {
                Candidato c4 = papeleta.obtenerNuevaPreferencia(i);
                    for (Candidato candidato : candidatos)
                        if (c4 == candidato){
                            eliminarPapeleta(papeleta);
                            System.out.println("Se han eliminado las papeletas invalidas.");
                        }
            }
                }
            }

    public void reiniciarVotos(){
        for (Candidato candidato : candidatos){
            candidato.setVotos(0);
        }
    }

    public void guardar_datos(){
        try {
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("datos.dat"));
            oo.writeObject(this);
            oo.close();
            System.out.println("Datos guardados correctamente. ");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("La funcion de guardado no se ha ejecutado correctamente. Revise el analisis realizado por el programa y vuelva a intentarlo.");
        }
    }

    public static Eleccion leer_datos(){
        try{
            ObjectInputStream oi = new ObjectInputStream(new FileInputStream("datos.dat"));
            Eleccion e1 = (Eleccion) oi.readObject();
            oi.close();
            return e1;

        }catch(Exception e){
            return new Eleccion();
        }
    }

}

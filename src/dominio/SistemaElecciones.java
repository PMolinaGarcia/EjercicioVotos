package dominio;
import java.util.*;
import java.io.*;

public class SistemaElecciones {

    private Scanner sc = new Scanner(System.in);

    private Eleccion eleccion = new Eleccion();

    public SistemaElecciones(){
        eleccion = Eleccion.leer_datos();
    }


    public void iniciarEleccion(){
        eleccion.validarPapeletas();
        System.out.println("Papeletas validadas");
        eleccion.realizarRecuento();
        System.out.println("Recuento realizado");
        eleccion.comprobarMayoriaAbsoluta();
        System.out.println("Mayoria absoluta comprobada");
    }

    public void mostrarResultados(){
        eleccion.mostrarCandidatosYVotos();
    }



    public String[] leerInstruccion(){
        System.out.print("?>");
        String cadena = sc.nextLine();
        return cadena.split(" ");
    }

    public void anniadirPapeleta(){
        System.out.println("Escriba el nombre del votante: ");
        String nombreVotante = sc.nextLine();
        Papeleta papeleta = new Papeleta(nombreVotante);
        eleccion.anniadirPapeleta(papeleta);
        System.out.println("A continuacion, debe elegir entre los candidatos disponibles, que son: ");
        eleccion.datosCandidatos();
        System.out.println("\n Escriba el nombre de su candidato con mayor preferencia: ");
        String nombreCandidato = sc.nextLine();
        papeleta.anniadirCandidatoPapeleta(new Candidato(nombreCandidato));
        for (int i = 1; i < eleccion.cantidadCandidatos(); i++){
            System.out.println("Escriba su siguiente candidato en orden de preferencia: ");
            String nombreCandidatoSig = sc.nextLine();
            papeleta.anniadirCandidatoPapeleta(new Candidato(nombreCandidatoSig));
        }
        System.out.println("Candidatos anniadidos a la papeleta. Papeleta registrada a nombre de " + nombreVotante + ". ");
    }

    public void eliminarPapeleta(){
        System.out.println("Escriba el nombre del votante de quien desee eliminar la papeleta: ");
        String nombreVotante = sc.nextLine();
        eleccion.eliminarPapeleta(new Papeleta(nombreVotante));
        System.out.println("Se ha eliminado la papeleta correctamente. ");
    }

    public void anniadirCandidato(){
        System.out.println("Los candidatos son los siguientes: ");
        eleccion.datosCandidatos();
        System.out.println("Escriba el nombre del candidato que desee anniadir: ");
        String nombreCandidato = sc.nextLine();
        eleccion.anniadirCandidato(new Candidato(nombreCandidato));
        System.out.println("Se ha anniadido el candidato " + nombreCandidato);
    }

    public void eliminarCandidato(){
        System.out.println("Los candidatos son los siguientes: ");
        eleccion.datosCandidatos();
        System.out.println("Escriba el nombre del candidato que desee eliminar: ");
        String nombreCandidato = sc.nextLine();
        eleccion.eliminarCandidato(new Candidato(nombreCandidato));
        System.out.println("Se ha eliminado el candidato " + nombreCandidato);
    }

    public void ayuda(){
        System.out.println("Las instrucciones disponibles son las siguientes: " +
                "\n iniciarEleccion: para comenzar con el proceso de eleccion, que se lleva a cabo de forma automatica con los datos introducidos. "+
                "\n mostrarResultados: para ver los resultados de las elecciones. ATENCION: Reinicia los votos tambien. " +
                "\n addCandidato: para anniadir candidatos posibles a las elecciones. " +
                "\n addPapeleta: para anniadir papeletas segun el nombre del votante. " +
                "\n guardar: para guardar los datos en un archivo .dat ." +
                "\n salir: para salir. ");
    }

    public boolean procesarInstruccion(String [] instruccion){
        try {
            if (instruccion[0].equals("ayuda")) {
                ayuda();
            } else if (instruccion[0].equals("iniciarEleccion")) {
                iniciarEleccion();
            } else if (instruccion[0].equals("mostrarResultados")) {
                mostrarResultados();
                eleccion.reiniciarVotos();
            } else if (instruccion[0].equals("addCandidato")) {
                anniadirCandidato();
            } else if (instruccion[0].equals("elimCandidato")){
                eliminarCandidato();
            } else if (instruccion[0].equals("addPapeleta")) {
                anniadirPapeleta();
            } else if (instruccion[0].equals("elimPapeleta")) {
                eliminarPapeleta();
            } else if (instruccion[0].equals("guardar")){
                eleccion.guardar_datos();
            } else if (instruccion[0].equals("salir")){
                eleccion.guardar_datos();
                return false;
            }
        }
        catch (Exception e){
            System.out.println("Algo no ha salido bien y el proceso no se ha ejecutado correctamente.");
        }

        return true;
    }

}

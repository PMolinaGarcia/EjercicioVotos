package aplicacion;
import dominio.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema de votaciones para elecciones disenniado por Jorge Delgado y Pablo Molina.");
        SistemaElecciones si = new SistemaElecciones();
        String [] instruccion;
        si.ayuda();
        do {
            instruccion = si.leerInstruccion();
        } while (si.procesarInstruccion(instruccion));
    }
}
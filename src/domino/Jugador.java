package domino;

import java.util.LinkedList;
import java.util.Random;

public class Jugador {

    protected final String jugador;
    protected final LinkedList<Ficha> fichas;

    public Jugador(String jugador) {
        this.jugador = jugador;
        this.fichas = new LinkedList<>();
        repartirFichas();
    }
    
    public Jugador(String jugador, LinkedList<Ficha> fichasJ) {
        this.jugador = jugador;
        this.fichas = new LinkedList<>();
        repartirFichas(fichasJ);
    }
    
    private void repartirFichas(LinkedList<Ficha> fichasJ) {
        Random random = new Random();
        int numA, numB;
        for (int i = 0; i < 7; i++) {
            Ficha ficha, fichaAux;
            do {
                numA = random.nextInt(7);
                numB = random.nextInt(7);
                ficha = new Ficha(numA, numB);
                fichaAux = new Ficha(numB, numA);
            } while (fichas.contains(ficha) || fichas.contains(fichaAux) || fichasJ.contains(ficha) || fichasJ.contains(fichaAux));
            fichas.add(ficha);
        }
    }

    private void repartirFichas() {
        Random random = new Random();
        int numA, numB;
        for (int i = 0; i < 7; i++) {
            Ficha ficha, fichaAux;
            do {
                numA = random.nextInt(7);
                numB = random.nextInt(7);
                ficha = new Ficha(numA, numB);
                fichaAux = new Ficha(numB, numA);
            } while (fichas.contains(ficha) || fichas.contains(fichaAux));
            fichas.add(ficha);
        }
    }

    public void mostrarFichas() {
        System.out.println("Fichas del " + jugador + "\n\n");
        if (!fichas.isEmpty()) {
            for (Ficha f : fichas) {
                System.out.print("[" + f.getNumA() + " - " + f.getNumB() + "]");
            }
            System.out.println("\n\n");
        } else {
            System.out.println("El " + jugador + " Se Quedo Sin Fichas!");
        }
    }

    public LinkedList<Ficha> getFichas() {
        return new LinkedList<>(this.fichas);
    }

    public int getNumFichas() {
        return this.fichas.size();
    }

    public Ficha getFicha(int pos) {
        return this.fichas.get(pos);
    }

    public void quitarFicha(Ficha ficha) {
        this.fichas.remove(ficha);
    }
    
    public Ficha getDoble() {
        int tam = this.fichas.size();
        for(int i = 0; i < tam; i++) {
            Ficha f = this.fichas.get(i);
            if(f.getNumA() == f.getNumB()) {
                return f;
            }
        }
        return null;
    }

    public int sumaPuntos() {
        int contador = 0;
        for (Ficha f : fichas) {
            contador += f.getNumA() + f.getNumB();
        }
        return contador;
    }
}
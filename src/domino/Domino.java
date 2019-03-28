package domino;

import java.util.LinkedList;
import java.util.Random;

public class Domino {

    private final Jugador maquina;
    private final LinkedList<Ficha> tablero; 
    private static int turno = 1;

    public Domino(String mNombre, LinkedList<Ficha> fichasJ) {
        maquina = new Jugador(mNombre, fichasJ);
        this.tablero = new LinkedList<>();
    }

    public void mostrarFichas() {
        maquina.mostrarFichas();
    }

    public boolean sinFichas() {
        return maquina.getNumFichas() == 0;
    }

    public Ficha getDoble() {
        return maquina.getDoble();
    }
    
    public boolean jugar() {
        if(turno == 1) {
            Ficha aux = getDoble();
            colocarFichaTablero('I', aux);
            maquina.quitarFicha(aux);
            turno = turno + 1;
            return true;
        }else{
            int error = 0;
            boolean validacion;
            Random random = new Random();
            int rdPos;
            Ficha ficha, fichaAux;
            do {
                error++;
                validacion = false;
                rdPos = random.nextInt(maquina.getNumFichas());
                ficha = maquina.getFicha(rdPos);
                fichaAux = getExtremoIzq();
                if (ficha.getNumA() == fichaAux.getNumA()) {
                    colocarFichaTablero('I', new Ficha(ficha.getNumB(), ficha.getNumA()));
                    maquina.quitarFicha(ficha);
                    validacion = true;
                } else if (ficha.getNumB() == fichaAux.getNumA()) {
                    colocarFichaTablero('I', new Ficha(ficha));
                    maquina.quitarFicha(ficha);
                    validacion = true;
                }

                if (!validacion) {
                    fichaAux = getExtremoDer();
                    if (ficha.getNumA() == fichaAux.getNumB()) {
                        colocarFichaTablero('D', new Ficha(ficha));
                        maquina.quitarFicha(ficha);
                        validacion = true;
                    } else if (ficha.getNumB() == fichaAux.getNumB()) {
                        colocarFichaTablero('D', new Ficha(ficha.getNumB(), ficha.getNumA()));
                        maquina.quitarFicha(ficha);
                        validacion = true;
                    }
                }

                if(error > maquina.getNumFichas() && !validacion) {
                    return false;
                }
            } while (!validacion);

            return true;
        }
    }
    
    public int sumaPuntos() {
        return maquina.sumaPuntos();
    }

    public void colocarFichaTablero(char opcion, Ficha ficha) {
        switch(opcion) {
            case 'I':
                this.tablero.addFirst(ficha);
                break;
            case 'D':
                this.tablero.addLast(ficha);
                break;
        }
    }

    public void mostrarTablero() {
        for (Ficha f : tablero) {
            System.out.print("[" + f.getNumA() + " - " + f.getNumB() + "]");
        }
        System.out.println("\n\n");
    }

    public Ficha getExtremoIzq() {
        return this.tablero.getFirst();
    }

    public Ficha getExtremoDer() {
        return this.tablero.getLast();
    }
}
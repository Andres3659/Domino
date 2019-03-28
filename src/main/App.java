package main;

import domino.Ficha;
import domino.Jugador;
import domino.Domino;
import java.util.Scanner;

public class App {
    
    public static void main(String[] args) {

        Scanner sn = new Scanner(System.in);
        boolean fichaValida, pasoJugador, pasoMaquina;
        char opcion;

        Ficha ficha, fichaAux;
        Jugador jugador = new Jugador("Jugador");
        Domino maquina = new Domino("a Maquina", jugador.getFichas());
        System.out.println(".:Bienvenido al Juego de Domino!:.");
        System.out.println(".:Jugador .vs. Máquina:.");
        maquina.mostrarFichas();
        System.out.println("La Máquina Empieza");
        maquina.jugar();
        System.out.println("La Máquina ha Jugado!");
        maquina.mostrarFichas();

        do {
            fichaValida = pasoJugador = pasoMaquina = false;
            maquina.mostrarTablero();
            System.out.println("Turno del Jugador");
            jugador.mostrarFichas();
            System.out.print("¿Desea pasar el Turno? [S/N]: ");
            opcion = sn.next().charAt(0); 
            if (opcion == 'N' || opcion == 'n') {
                do {
                    System.out.print("Digite la Ficha a Escoger (Pos): ");
                    int pos = sn.nextInt();
                    if (pos > jugador.getNumFichas()) {
                        fichaValida = false;
                    } else {
                        ficha = jugador.getFicha(pos - 1);
                        fichaAux = maquina.getExtremoIzq();
                        if (ficha.getNumA() == fichaAux.getNumA()) { 
                            maquina.colocarFichaTablero('I', new Ficha(ficha.getNumB(), ficha.getNumA()));
                            jugador.quitarFicha(ficha);
                            fichaValida = true;
                        } else if (ficha.getNumB() == fichaAux.getNumA()) {      
                            maquina.colocarFichaTablero('I', new Ficha(ficha));
                            jugador.quitarFicha(ficha);
                            fichaValida = true;
                        }

                        if (!fichaValida) {
                            fichaAux = maquina.getExtremoDer();
                            if (ficha.getNumA() == fichaAux.getNumB()) { 
                                maquina.colocarFichaTablero('D', new Ficha(ficha));
                                jugador.quitarFicha(ficha);
                                fichaValida = true;
                            } else if (ficha.getNumB() == fichaAux.getNumB()) {
                                maquina.colocarFichaTablero('D', new Ficha(ficha.getNumB(), ficha.getNumA()));
                                jugador.quitarFicha(ficha);
                                fichaValida = true;
                            }
                        }

                        if (!fichaValida) {
                            System.out.println("Ficha Invalida!");
                        }
                    }
                } while (!fichaValida);
                System.out.println("Tablero Actual");
                maquina.mostrarTablero();
            } else {
                pasoJugador = true;
            }

            if(jugador.getNumFichas() != 0) {
                System.out.println("Turno de la Maquina...");
                pasoMaquina = maquina.jugar();
                if (!pasoMaquina) {
                    System.out.println("La Maquina Pasó el Turno!");
                    if (pasoJugador) {
                        break;
                    }
                } else {
                    System.out.println("La Maquina Ha Jugado");
                    maquina.mostrarFichas();
                }
            }
            System.out.println("\n-----------------------------------------------------------------------------\n");
        } while (jugador.getNumFichas() > 0 && !maquina.sinFichas()); 
        
        System.out.println("\n\n");
        System.out.println(".:JUEGO TERMINADO!:.");
        int puntosJ = jugador.sumaPuntos();
        int puntosM = maquina.sumaPuntos();
        if(puntosJ <= puntosM) {
            System.out.println("JUGADOR 1 HA GANADO!! FELICIDADES!!");
        } else{
            System.out.println("LA MAQUINA HA GANADO!! SUERTE LA PROXIMA!!");
        }
    }
}
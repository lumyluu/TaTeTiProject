package tatetiproject;

/*1. Juego del TaTeTi (X ficha 1 y O ficha 2)
(matriz de 3x3, hay una jugada por turno, se tienen que ubicar tres fichas iguales 
en línea diagonal o horizonal o vertical hasta que llene el tablero o alguien gane)
Después de cada jugada mostrar el tablero con las fichas.
/*
 */
import java.util.Scanner;

public class Tateti {
	static int filas = 3;
	static int columnas = 3;
	static String coordenadas[][] = new String[filas][columnas];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		inicializarTablero();
		mostrarTablero();
		boolean juegoContinua = true;
		String jugador = "X";
		while (juegoContinua) {

			ingresarFicha(jugador);
			mostrarTablero();
			juegoContinua = sigueElJuego(jugador);
			jugador = cambiarJugador(jugador);

		}
		System.out.println("El juego terminó");

	}

	private static boolean ganadorVertical(String jugador) {
		int sumaPuntos = 0;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (jugador.equals(coordenadas[j][i])) {

					sumaPuntos++;
				}

			}
			if (sumaPuntos == 3) {
				System.out.println("¡Ganó el juego! " + jugador);
				return true;
			}
			sumaPuntos = 0;

		}

		return false;
	}

	private static boolean ganadorHorizontal(String jugador) {
		int sumaPuntos = 0;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (jugador.equals(coordenadas[i][j])) {
					sumaPuntos++;
				}
			}
			if (sumaPuntos == 3) {
				System.out.println("¡Ganó el juego! " + jugador);
				return true;
			}
			sumaPuntos = 0;
		}
		return false;
	}

	private static boolean ganadorDiagonal(String jugador) {
		int sumaPuntos = 0;
		for (int i = 0; i < filas; i++) {
			if (jugador.equals(coordenadas[i][i])) {
				sumaPuntos++;
			}

		}
		int sumaPuntosB = 0;
		int contadorInverso = 2;
		for (int i = 0; i < filas; i++) {
			if (jugador.equals(coordenadas[contadorInverso][i])) {
				sumaPuntosB++;

			}
			contadorInverso--;

		}
		if (sumaPuntos == 3 || sumaPuntosB == 3) {
			System.out.println("¡Ganó el juego! " + jugador);
			return true;
		} else {
			return false;
		}
	}

	private static String cambiarJugador(String jugador) {
		if ("X".equals(jugador)) {
			return "O";

		} else {
			return "X";
		}
	}

	private static boolean hayEspaciosLibres() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if ("*".equals(coordenadas[i][j])) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean espacioVacio(int fila, int columna) {
		if ("*".equals(coordenadas[fila][columna])) {
			return true;
		}
		return false;
	}

	private static boolean sigueElJuego(String jugador) {
		return hayEspaciosLibres() && !ganadorDiagonal(jugador) && !ganadorHorizontal(jugador)
				&& !ganadorVertical(jugador);
	}

	private static void ingresarFicha(String ficha) {
		Scanner scanner = new Scanner(System.in);
		int filaSelec, columnaSelec;
		boolean datoValido = false;
		while (!datoValido) {
			System.out.println("Jugador: " + ficha + " Ingrese una coordenada (fila 0, 1 o 2)");
			filaSelec = scanner.nextInt();
			System.out.println("Jugador: " + ficha + " Ingrese una coordenada (columna 0, 1 o 2)");
			columnaSelec = scanner.nextInt();

			if (filaSelec > 2 || filaSelec < 0 || columnaSelec > 2 || columnaSelec < 0) {
				System.out.println("Dato no válido. Por favor, ingrese 0, 1 o 2");

			} else {
				if (espacioVacio(filaSelec, columnaSelec)) {
					coordenadas[filaSelec][columnaSelec] = ficha;
					datoValido = true;
				} else {
					System.out.println("Espacio ocupado. Seleccione otra coordenada");
				}

			}
		}

	}

	private static void inicializarTablero() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				coordenadas[i][j] = "*";
			}
		}
	}

	private static void mostrarTablero() {
		for (int i = 0; i < filas; i++) {
			System.out.print("|");
			for (int j = 0; j < columnas; j++) {
				System.out.print(coordenadas[i][j] + "|");
			}
			System.out.println();
		}
	}
}

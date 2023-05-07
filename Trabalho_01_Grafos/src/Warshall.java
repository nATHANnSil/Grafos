import java.util.ArrayList;

public class Warshall {
	
	public static ArrayList<Integer>[] determinarFechoTransitivoDiretoWarshall(boolean[][] matrizAdjacencia) {
		int numVertices = matrizAdjacencia.length;
		ArrayList<Integer>[] fechosDiretos = new ArrayList[numVertices];
		
		// Inicialização do fecho transitivo direto
		for (int i = 0; i < numVertices; i++) {
			fechosDiretos[i] = new ArrayList<Integer>();
			for (int j = 0; j < numVertices; j++) {
				if (matrizAdjacencia[i][j]) {
					fechosDiretos[i].add(j);
				}
			}
		}
		
		// Cálculo do fecho transitivo direto utilizando o algoritmo de Warshall
		for (int k = 0; k < numVertices; k++) {
			for (int i = 0; i < numVertices; i++) {
				for (int j = 0; j < numVertices; j++) {
					if (!matrizAdjacencia[i][j] && matrizAdjacencia[i][k] && matrizAdjacencia[k][j]) {
						matrizAdjacencia[i][j] = true;
						fechosDiretos[i].add(j);
					}
				}
			}
		}
		
		return fechosDiretos;
	}
	
	public static ArrayList<Integer>[] determinarFechoTransitivoInversoWarshall(boolean[][] matrizAdjacencia) {
		int numVertices = matrizAdjacencia.length;
		ArrayList<Integer>[] fechosIndiretos = new ArrayList[numVertices];
		
		// Inicialização do fecho transitivo indireto
		for (int i = 0; i < numVertices; i++) {
			fechosIndiretos[i] = new ArrayList<Integer>();
			for (int j = 0; j < numVertices; j++) {
				if (matrizAdjacencia[j][i]) {
					fechosIndiretos[i].add(j);
				}
			}
		}
		
		// Cálculo do fecho transitivo indireto utilizando o algoritmo de Warshall
		for (int k = 0; k < numVertices; k++) {
			for (int i = 0; i < numVertices; i++) {
				for (int j = 0; j < numVertices; j++) {
					if (!matrizAdjacencia[i][j] && matrizAdjacencia[k][j] && matrizAdjacencia[i][k]) {
						matrizAdjacencia[i][j] = true;
						fechosIndiretos[i].add(j);
					}
				}
			}
		}
		
		return fechosIndiretos;
	}
	
}

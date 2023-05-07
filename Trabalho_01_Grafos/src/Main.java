import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite o número de vértices do grafo (100, 1000, 10000, ou 100000): ");
		int numVertices = scanner.nextInt();

		Grafo grafo = new Grafo(numVertices);
		grafo.determinarBase();
		grafo.determinarAntibase();

		Random random = new Random();
		for (int i = 0; i < numVertices; i++) {
			for (int j = i + 1; j < numVertices; j++) {
				if (random.nextBoolean()) {
					grafo.adicionarAresta(i, j);
				}
			}
		}
		
		String nomeArquivo = String.format("Grafo%dv.txt", numVertices);
		System.out.println("Grafo criado com sucesso!");
		grafo.salvarGrafo(nomeArquivo);

		System.out.print("Digite o método para cálculo do fecho transitivo direto (1 - Naive, 2 - Warshall): ");
		int opcao = scanner.nextInt();

		System.out.println("Fecho transitivo direto:");
		ArrayList<Integer>[] fechosDiretos = new ArrayList[numVertices];
		if (opcao == 1) {
			for (int i = 0; i < numVertices; i++) {
				fechosDiretos[i] = grafo.determinarFechoTransitivoDiretoNaive(i);
			}
		} else if (opcao == 2) {
			boolean[][] matrizAdjacenciaBoolean = new boolean[numVertices][numVertices];
			for (int i = 0; i < numVertices; i++) {
				for (int j = 0; j < numVertices; j++) {
					matrizAdjacenciaBoolean[i][j] = grafo.temAresta(i, j);
				}
			}
			fechosDiretos = Warshall.determinarFechoTransitivoDiretoWarshall(matrizAdjacenciaBoolean);
		} else {
			System.out.println("Opção inválida!");
			scanner.close();
			return;
		}
		imprimirLista(fechosDiretos);

		System.out.print("Digite o método para cálculo do fecho transitivo indireto (1 - Naive, 2 - Warshall): ");
		opcao = scanner.nextInt();

		System.out.println("Fecho transitivo indireto:");
		ArrayList<Integer>[] fechosIndiretos = new ArrayList[numVertices];
		if (opcao == 1) {
			for (int i = 0; i < numVertices; i++) {
				fechosIndiretos[i] = grafo.determinarFechoTransitivoInversoNaive(i);
			}
		} else if (opcao == 2) {
			int[][] matrizAdjacencia = grafo.getMatrizAdjacencia();
			boolean[][] matrizAdjacenciaBoolean = new boolean[numVertices][numVertices];
			for (int i = 0; i < numVertices; i++) {
				for (int j = 0; j < numVertices; j++) {
					matrizAdjacenciaBoolean[i][j] = grafo.temAresta(i, j);
				}
			}
			fechosIndiretos = Warshall.determinarFechoTransitivoInversoWarshall(matrizAdjacenciaBoolean);
		} else {
			System.out.println("Opção inválida!");
			scanner.close();
			return;
		}
		imprimirLista(fechosIndiretos);
		System.out.println("Base(s): " + grafo.determinarBase());
		System.out.println("Antibase(s): " + grafo.determinarAntibase());

		scanner.close();
		long end = System.currentTimeMillis();
		long totalTime = end - start;
		System.out.println("Tempo total de execução: " + totalTime/1000.0 + " segundos");

	}

	private static void imprimirLista(ArrayList<Integer>[] lista) {
		for (int i = 0; i < lista.length; i++) {
			System.out.print(i + ": ");
			for (int j = 0; j < lista[i].size(); j++) {
				System.out.print(lista[i].get(j) + " ");
			}
			System.out.println();

		}
	}
}
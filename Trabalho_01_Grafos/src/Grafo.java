import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Grafo {
	private int numVertices;
	private ArrayList<ArrayList<Integer>> adjacencias;

	public Grafo(int numVertices) {
		this.numVertices = numVertices;
		this.adjacencias = new ArrayList<>();
		for (int i = 0; i < numVertices; i++) {
			adjacencias.add(new ArrayList<>());
		}
	}

	public void adicionarAresta(int origem, int destino) {
		this.adjacencias.get(origem).add(destino);
	}

	public ArrayList<Integer> determinarFechoTransitivoDiretoNaive(int vertice) {
	    boolean[] visitados = new boolean[this.numVertices];
	    Queue<Integer> fila = new LinkedList<>();
	    ArrayList<Integer> fecho = new ArrayList<>();

	    fila.offer(vertice);
	    visitados[vertice] = true;

	    while (!fila.isEmpty()) {
	        int v = fila.poll();
	        fecho.add(v);

	        for (int adj : this.adjacencias.get(v)) {
	            if (!visitados[adj]) {
	                visitados[adj] = true;
	                fila.offer(adj);
	            }
	        }
	    }

	    return fecho;
	}


	public ArrayList<Integer> determinarFechoTransitivoInversoNaive(int vertice) {
	    boolean[] visitados = new boolean[this.numVertices];
	    Queue<Integer> fila = new LinkedList<>();
	    fila.offer(vertice);
	    visitados[vertice] = true;

	    while (!fila.isEmpty()) {
	        int v = fila.poll();
	        for (int i = 0; i < this.numVertices; i++) {
	            if (this.adjacencias.get(i).contains(v) && !visitados[i]) {
	                visitados[i] = true;
	                fila.offer(i);
	            }
	        }
	    }

	    ArrayList<Integer> fechoTransitivo = new ArrayList<>();
	    for (int i = 0; i < this.numVertices; i++) {
	        if (visitados[i]) {
	            fechoTransitivo.add(i);
	        }
	    }
	    return fechoTransitivo;
	}


	public ArrayList<Integer> determinarBase() {
		ArrayList<Integer> visitados = new ArrayList<>();
		ArrayList<Integer> base = new ArrayList<>();
		Stack<Integer> pilha = new Stack<>();

		// Inicia a busca em profundidade a partir do v�rtice 0
		visitados.add(0);
		pilha.push(0);

		while (!pilha.isEmpty()) {
			int vertice = pilha.pop();
			boolean temVizinhoNaBase = false;

			for (int i = 0; i < adjacencias.get(vertice).size(); i++) {
				int adjacente = adjacencias.get(vertice).get(i);

				// Se o vizinho ainda n�o foi visitado, marca como visitado e empilha
				if (!visitados.contains(adjacente)) {
					visitados.add(adjacente);
					pilha.push(adjacente);
				}

				// Se o vizinho j� foi visitado e pertence � base, marca a flag
				if (base.contains(adjacente)) {
					temVizinhoNaBase = true;
				}
			}

			// Se nenhum vizinho pertence � base, adiciona o v�rtice � base
			if (!temVizinhoNaBase) {
				base.add(vertice);
			}
		}

		return base;
	}

	public ArrayList<Integer> determinarAntibase() {
		ArrayList<Integer> visitados = new ArrayList<>();
		ArrayList<Integer> antibase = new ArrayList<>();
		Stack<Integer> pilha = new Stack<>();

		// Inicia a busca em profundidade a partir do v�rtice 0
		visitados.add(0);
		pilha.push(0);

		while (!pilha.isEmpty()) {
			int vertice = pilha.pop();
			boolean temVizinhoNaAntibase = false;

			for (int i = 0; i < adjacencias.get(vertice).size(); i++) {
				int adjacente = adjacencias.get(vertice).get(i);

				// Se o vizinho ainda n�o foi visitado, marca como visitado e empilha
				if (!visitados.contains(adjacente)) {
					visitados.add(adjacente);
					pilha.push(adjacente);
				}

				// Se o vizinho j� foi visitado e pertence � antibase, marca a flag
				if (antibase.contains(adjacente)) {
					temVizinhoNaAntibase = true;
				}
			}

			// Se pelo menos um vizinho pertence � antibase, adiciona o v�rtice � antibase
			if (temVizinhoNaAntibase) {
				antibase.add(vertice);
			}
		}

		return antibase;
	}
	
	public int[][] getMatrizAdjacencia() {
	    int[][] matriz = new int[this.numVertices][this.numVertices];

	    for (int i = 0; i < this.numVertices; i++) {
	        for (int j = 0; j < this.numVertices; j++) {
	            matriz[i][j] = this.adjacencias.get(i).contains(j) ? 1 : 0;
	        }
	    }

	    return matriz;
	}
	
	public boolean temAresta(int origem, int destino) {
	    return getMatrizAdjacencia()[origem][destino] != 0;
	}

	public void salvarGrafo(String nomeArquivo) {
		try {
			FileWriter fw = new FileWriter(nomeArquivo);
			PrintWriter pw = new PrintWriter(fw);

			// Escreve o n�mero de v�rtices no arquivo
			pw.println(numVertices);

			// Escreve as arestas no arquivo
			for (int i = 0; i < numVertices; i++) {
				for (int j = 0; j < adjacencias.get(i).size(); j++) {
					int adjacente = adjacencias.get(i).get(j);
					pw.println(i + " " + adjacente);
				}
			}

			pw.close();
			fw.close();

			System.out.println("Grafo salvo com sucesso no arquivo " + nomeArquivo);
		} catch (IOException e) {
			System.out.println("Erro ao salvar grafo:" + e.getMessage());
		}
	}
}

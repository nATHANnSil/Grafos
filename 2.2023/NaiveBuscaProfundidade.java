import java.util.Iterator;

public class NaiveBuscaProfundidade {
    private final Grafo grafo;
    private boolean[] visited;

    NaiveBuscaProfundidade(Grafo grafo) {
        this.grafo = grafo;
        this.visited = new boolean[grafo.getAdjListArray().length];
    }

    void search() {
        for (int v = 0; v < grafo.getAdjListArray().length; v++) {
            if (!visited[v]) {
                DFS(v);
            }
        }
    }

    void DFS(int v) {
        // Marca o nó como visitado e imprime
        visited[v] = true;
        System.out.print(v + " ");

        // Recorre a todos os vértices adjacentes a este
        Iterator<Integer> i = grafo.getAdjListArray()[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                DFS(n);
            }
        }
    }

    void findBridges() {
        for (int v = 0; v < grafo.getAdjListArray().length; v++) {
            for (int w : grafo.getAdjListArray()[v]) {
                grafo.getAdjListArray()[v].remove(Integer.valueOf(w));
                grafo.getAdjListArray()[w].remove(Integer.valueOf(v));
                visited = new boolean[grafo.getAdjListArray().length];
                DFS(0);
                if (!allVisited()) {
                    System.out.println("A aresta entre " + v + " e " + w + " é uma ponte.");
                }
                grafo.getAdjListArray()[v].add(w);
                grafo.getAdjListArray()[w].add(v);
            }
        }
    }

    boolean allVisited() {
        for (boolean b : visited) {
            if (!b) return false;
        }
        return true;
    }

    void printVisitedVertices() {
        System.out.print("Sequência de vértices visitados: ");
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
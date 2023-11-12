import java.util.LinkedList;

class Grafo {
    private final int numVertices;
    private LinkedList<Integer>[] adjListArray;

    // construtor
    Grafo(int numVertices) {
        this.numVertices = numVertices;

        // define o tamanho da lista de arrays para o número de vértices
        adjListArray = new LinkedList[numVertices];

        // Cria uma nova lista para cada vértice
        // de modo que os nós adjacentes possam ser armazenados
        for (int i = 0; i < numVertices; i++) {
            adjListArray[i] = new LinkedList<>();
        }
    }

    void addVertex(int src) {
        adjListArray[src] = new LinkedList<>();
    }

    void addEdge(int src, int dest) {
        adjListArray[src].add(dest);
        adjListArray[dest].add(src);
    }

    LinkedList<Integer>[] getAdjListArray() {
        return adjListArray;
    }

    
}

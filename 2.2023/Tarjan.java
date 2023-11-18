class Tarjan {
    private final Grafo grafo;
    private boolean[] visited;
    private int[] low;
    private int[] pre;
    private int cnt;

    Tarjan(Grafo grafo) {
        this.grafo = grafo;
        this.visited = new boolean[grafo.getAdjListArray().length];
        this.low = new int[grafo.getAdjListArray().length];
        this.pre = new int[grafo.getAdjListArray().length];
    }

    void findBridges() {
        for (int v = 0; v < grafo.getAdjListArray().length; v++) {
            if (!visited[v]) {
                DFS(v, v);
            }
        }
    }

    void DFS(int u, int v) {
        visited[v] = true;
        pre[v] = cnt++;
        low[v] = pre[v];
        
        for (int w : grafo.getAdjListArray()[v]) {
            if (!visited[w]) {
                DFS(v, w);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] == pre[w]) {
                    System.out.println("A aresta entre " + v + " e " + w + " é uma ponte.");
                }
            } else if (w != u) { 
                low[v] = Math.min(low[v], pre[w]);
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

import java.util.*;

class Fleury {
    private final Grafo grafo;
    private boolean[] visited;

    Fleury(Grafo grafo) {
        this.grafo = grafo;
        this.visited = new boolean[grafo.getAdjListArray().length];
    }

    List<Integer> findEulerianPath() {
        // Verifica se o grafo tem um caminho euleriano
        if (!hasEulerianPath()) return null;

        // Encontra o vértice inicial
        int v = findStartVertex();

        // Realiza a busca em profundidade para encontrar o caminho euleriano
        List<Integer> path = new ArrayList<>();
        findEulerianPath(v, path);

        return path;
    }

    private boolean hasEulerianPath() {
        int startNodes = 0;
        int endNodes = 0;
        for (int i = 0; i < grafo.getAdjListArray().length; i++) {
            if ((grafo.getAdjListArray()[i].size() & 1) == 1) {
                startNodes++;
            } else {
                endNodes++;
            }
        }
        
        return (endNodes == 0 && startNodes <= 2);
    }

    private int findStartVertex() {
        int startVertex = 0;
        for (int i = 0; i < grafo.getAdjListArray().length; i++) {
            if ((grafo.getAdjListArray()[i].size() & 1) == 1) {
                startVertex = i;
                break;
            }
        }
        
        return startVertex;
    }

    private void findEulerianPath(int v, List<Integer> path) {
       for (int i = 0; i < grafo.getAdjListArray()[v].size(); i++) {
           Integer u = grafo.getAdjListArray()[v].get(i);
           if (isValidNextEdge(v, u)) {
               path.add(u);
               grafo.getAdjListArray()[v].remove(u);
               findEulerianPath(u, path);
           }
       }
    }

    private boolean isValidNextEdge(int v, int u) {
        if (grafo.getAdjListArray()[v].size() == 1) return true;
        
        boolean[] visited = new boolean[grafo.getAdjListArray().length];
        int count1 = DFSCount(v, visited);

        grafo.getAdjListArray()[v].remove(Integer.valueOf(u));
        visited = new boolean[grafo.getAdjListArray().length];
        int count2 = DFSCount(v, visited);

        grafo.getAdjListArray()[v].add(u);

        return (count1 <= count2);
    }

    private int DFSCount(int v, boolean[] visited) {
        visited[v] = true;
        int count = 1;

        for (int adj : grafo.getAdjListArray()[v]) {
            if (!visited[adj]) {
                count += DFSCount(adj, visited);
            }
        }

        return count;
    }
}

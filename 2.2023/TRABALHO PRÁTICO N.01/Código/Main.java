import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número de vértices: ");
        System.out.println("100, 1.000, 10.000 e 100.000 vértices para a execução do trabalho.");
        int numVertices = scanner.nextInt();

        // Cria um grafo vazio
        Grafo grafo = new Grafo(numVertices);

        // Adicione vértices ao grafo de maneira aleatória
        Random rand = new Random();
        for (int i = 0; i < numVertices; i++) {
            int randomVertex = rand.nextInt(numVertices);
            grafo.addVertex(randomVertex);
        }

        // Adicione arestas ao grafo de maneira aleatória
        for (int i = 0; i < numVertices; i++) {
            int vertex1 = rand.nextInt(numVertices);
            int vertex2 = rand.nextInt(numVertices);
            grafo.addEdge(vertex1, vertex2);
        }

        System.out.println("Grafo aleatório criado com " + numVertices + " vértices:");

        // Imprime o grafo criado
        printGraph(grafo);

        while (true) {
            System.out.println("Escolha o método que deseja usar: ");
            System.out.println("1 - Método Naïve de busca em profundidade");
            System.out.println("2 - Método de Tarjan");
            System.out.println("3 - Método de Fleury para caminhos Eulerianos");
            System.out.println("4 - Sair");
            int methodChoice = scanner.nextInt();

            if (methodChoice == 1) {
                NaiveBuscaProfundidade naiveBuscaProfundidade = new NaiveBuscaProfundidade(grafo);
                naiveBuscaProfundidade.search();
                if (naiveBuscaProfundidade.allVisited()) {
                    System.out.println("O grafo é conexo.");
                    naiveBuscaProfundidade.printVisitedVertices();
                    checkEulerian(grafo);
                    naiveBuscaProfundidade.findBridges();
                } else {
                    System.out.println("O grafo não é conexo.");
                }
                
            } else if (methodChoice == 2) {
                Tarjan tarjan = new Tarjan(grafo);
                tarjan.findBridges();
                if (tarjan.allVisited()) {
                    System.out.println("O grafo é conexo.");
                    tarjan.printVisitedVertices();
                    checkEulerian(grafo);
                } else {
                    System.out.println("O grafo não é conexo.");
                }
                
            } else if (methodChoice == 3) {
                Fleury fleury = new Fleury(grafo);
                List<Integer> eulerianPath = fleury.findEulerianPath();
                if (eulerianPath != null) {
                    System.out.println("O caminho euleriano é: " + eulerianPath);
                } else {
                    System.out.println("Não existe um caminho euleriano para este grafo.");
                }
                checkEulerian(grafo);
            } else if (methodChoice == 4) {
                break;
            } else {
                System.out.println("Escolha inválida.");
            }
        }
    }

    private static void printGraph(Grafo grafo) {
        for (int v = 0; v < grafo.getAdjListArray().length; v++) {
            System.out.print("Vértice " + v + ":");
            for (int w : grafo.getAdjListArray()[v]) {
                System.out.print(" " + w);
            }
            System.out.println();
        }
    }

    private static void checkEulerian(Grafo grafo) {
        int odd = 0;
        for (int i = 0; i < grafo.getAdjListArray().length; i++) {
            if ((grafo.getAdjListArray()[i].size() & 1) == 1) {
                odd++;
            }
        }

        if (odd > 2) {
            System.out.println("O grafo não é euleriano.");
        } else if (odd == 2) {
            System.out.println("O grafo é semi-euleriano.");
        } else {
            System.out.println("O grafo é euleriano.");
        }
    }
}

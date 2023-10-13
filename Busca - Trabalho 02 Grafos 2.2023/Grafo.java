import java.util.*;

public class Grafo {
    private int vertices;
    private LinkedList<Integer> adjacencias[];

    @SuppressWarnings("unchecked")
    Grafo(int v) {
        vertices = v;
        adjacencias = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adjacencias[i] = new LinkedList();
    }

    void adicionarAresta(int v, int w) {
        adjacencias[v-1].add(w-1);
    }

    void buscaProfundidade(int v, boolean visitado[], List<String> classificacoes) {
        visitado[v-1] = true;

        for (Integer n : adjacencias[v-1]) {
            if (!visitado[n]) {
                classificacoes.add("Aresta " + (v) + " -> " + (n+1) + ": Árvore");
                buscaProfundidade(n+1, visitado, classificacoes);
            } else {
                classificacoes.add("Aresta " + (v) + " -> " + (n+1) + ": Retorno");
            }
        }
    }

    void buscaEmProfundidade(int verticeInicial) {
        boolean visitado[] = new boolean[vertices];
        List<String> classificacoes = new ArrayList<>();

        buscaProfundidade(verticeInicial, visitado, classificacoes);

        System.out.println("Classificações das arestas:");
        for (String classificacao : classificacoes) {
            System.out.println(classificacao);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do arquivo de dados do grafo: ");
        String nomeArquivo = scanner.nextLine();
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura(nomeArquivo);

        String primeiraLinha = leitura.ler();
        String[] partesPrimeiraLinha = primeiraLinha.split("\\s+");
        
        int vertices = Integer.parseInt(partesPrimeiraLinha[0]);
        int arestas = Integer.parseInt(partesPrimeiraLinha[1]);

        Grafo grafo = new Grafo(vertices);

        for (int i = 0; i < arestas; i++) {
            String linha = leitura.ler();
            if (!linha.isEmpty()) {
                String[] partes = linha.trim().split("\\s+");
                int origem = Integer.parseInt(partes[0]);
                int destino = Integer.parseInt(partes[1]);
                grafo.adicionarAresta(origem, destino);
            }
        }

        System.out.print("Digite o número de um vértice do grafo: ");
        int verticeInicial = scanner.nextInt();

        grafo.buscaEmProfundidade(verticeInicial);

        leitura.fecharArquivo();
        scanner.close();
    }
}

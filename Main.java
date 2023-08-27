import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o nome do arquivo: ");
        String nome_arquivo = sc.nextLine();
        File arquivo = new File(nome_arquivo);
        Scanner leitor = new Scanner(arquivo);

        int n_vertices = leitor.nextInt();
        int n_arestas = leitor.nextInt();
        ArrayList<ArrayList<Integer>> grafo = new ArrayList<ArrayList<Integer>>(n_vertices);
        for (int i = 0; i < n_vertices; i++) {
            grafo.add(new ArrayList<Integer>());
        }

        // Preencher lista de adjacência
        while (leitor.hasNextLine()) {
            int origem = leitor.nextInt() - 1;
            int destino = leitor.nextInt() - 1;
            grafo.get(origem).add(destino);
        }

        System.out.print("Digite o numero do vértice: ");
        int vertice = sc.nextInt() - 1;
        ArrayList<Integer> lista_predecessores = new ArrayList<Integer>();

        for (int i = 0; i < grafo.size(); i++) {
            if (grafo.get(i).contains(vertice)) {
                lista_predecessores.add(i);
            }
        }

        int grau_entrada = lista_predecessores.size();
        int grau_saida = grafo.get(vertice).size();

        System.out.println("O grau de saída do vértice é de: " + grau_saida);
        System.out.println("O grau de entrada do vértice é de: " + grau_entrada);
        System.out.println("Os sucessores desse vértice são: " + grafo.get(vertice).toString().substring(1, grafo.get(vertice).toString().length() - 1));
        System.out.println("Os antepassados do vértice são: " + lista_predecessores.toString().substring(1, lista_predecessores.toString().length() - 1));
        
        sc.close();
        leitor.close();
    }
}

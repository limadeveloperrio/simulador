package simulador.repositories;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import simulador.modelos.Produto;

public class RepositoryProduto {

    // Caminho do arquivo CSV
    private String caminhoArquivo;

    public RepositoryProduto(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public List<Produto> getProdutos() {
        List<Produto> produtos = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Quebrar a linha em partes usando vírgula como delimitador
                String[] dados = linha.split(",");

                // Converter as partes em id, nome e preco
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                double preco = Double.parseDouble(dados[2]);

                // Criar um novo objeto Produto e adicionar à lista
                Produto produto = new Produto(id, nome, preco);
                produtos.add(produto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return produtos;
    }
}

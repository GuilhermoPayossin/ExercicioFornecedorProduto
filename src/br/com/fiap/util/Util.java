package br.com.fiap.util;

import br.com.fiap.fornecedor.Fornecedor;
import br.com.fiap.produto.Produto;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

public class Util {
    private Fornecedor[] fornecedor = new Fornecedor[5];
    private Produto[] produtos = new Produto[fornecedor.length * 2];

    private int indexFornecedor = 0;
    private int indexProduto = 0;

    public void menu() {
        int opcao;
        String aux = """
                1 - Cadastrar Produto
                2 - Pesquisar Produto
                3 - Pesquisar Fornecedor
                4 - Finalizar
                """;
        while (true) {
            opcao = parseInt(showInputDialog(aux));
            if (opcao == 4) {
                return;
            }
            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    pesquisarProduto();
                    break;
                case 3:
                    pesquisar();
                    break;
                default:
                    showMessageDialog(null, "Opção inválida");
                    break;
            }
        }
    }

    private void pesquisar() {
        Fornecedor fornecedor = pesquisarFornecedor();
        if (fornecedor != null) {
            showMessageDialog(null, "CNPJ:" + fornecedor.getCnpj() +
                    "\nNome: " + fornecedor.getNome());
        }
    }

    private void cadastrarProduto() {
        String nome;
        int qtdEstoque;
        double valorUnitario;
        Fornecedor fornecedor = pesquisarFornecedor();
        if (fornecedor == null) {
            fornecedor = cadastrarFornecedor();
        }
        nome = showInputDialog("Nome do produto");
        qtdEstoque = parseInt(showInputDialog("Quantidade em estoque"));
        valorUnitario = parseDouble(showInputDialog("Valor do produto"));
        produtos[indexProduto] = new Produto(nome, valorUnitario, qtdEstoque, fornecedor);
        indexProduto++;
    }

    private Fornecedor cadastrarFornecedor() {
        long cnpj = parseLong(showInputDialog("Digite o CNPJ do Fornecedor"));
        String nome = showInputDialog("Digite o nome do Fornecedor");
        fornecedor[indexFornecedor] = new Fornecedor(nome, cnpj);
        indexFornecedor++;
        return fornecedor[indexFornecedor - 1];
    }

    private void pesquisarProduto() {
        String aux = "Produto não encontrado!";
        String nome = showInputDialog("Digite um nome para pesquisar");
        for (int i = 0; i < indexProduto; i++) {
            if (produtos[i].getNome().equalsIgnoreCase(nome)) {
                aux = "";
                aux += "Nome: " + nome + "\n";
                aux += "Valor: R$" + produtos[i].getValor() + "\n";
                aux += "Quantidade: " + produtos[i].getQtdEstoque() + "\n";
                aux += "Fornecedor: " + produtos[i].getFornecedor().getNome() + "\n";
                break;
            }
        }
        showMessageDialog(null, aux);
    }

    private Fornecedor pesquisarFornecedor() {
        long cnpj = parseLong(showInputDialog("Digite o CNPJ do Fornecedor"));
        for (int i = 0; i < indexFornecedor; i++) {
            if (fornecedor[i].getCnpj() == cnpj) {
                return fornecedor[i];
            }
        }
        showMessageDialog(null, "CNPJ " + cnpj + " não encontrado!");
        return null;
    }
}

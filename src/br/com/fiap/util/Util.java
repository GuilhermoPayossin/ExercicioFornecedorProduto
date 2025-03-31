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
                    pesquisarFornecedor();
                    break;
                default:
                    showMessageDialog(null, "Opção inválida");
                    break;
            }
        }
    }

    private void cadastrarProduto() {
        String nome;
        int qtdEstoque;
        double valorUnitario;
        Fornecedor fornecedor = pesquisarFornecedor();
        if (fornecedor == null) {
            cadastrarFornecedor();
            return;
        }
    }

    private void cadastrarFornecedor() {
        if (indexFornecedor < fornecedor.length) {
            long cnpj = parseLong(showInputDialog("Digite o CNPJ do Fornecedor"));
            String nome = showInputDialog("Digite o nome do Fornecedor");
            fornecedor[indexFornecedor] = new Fornecedor(nome, cnpj);
            indexFornecedor++;
        } else {
            showMessageDialog(null,
                    "Número máximo de Fornecedores adicionados");
        }
    }

    private void pesquisarProduto() {

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

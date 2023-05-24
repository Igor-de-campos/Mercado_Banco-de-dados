package crud;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import produto.Produtos;

public class read {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("Market");
        entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        String resposta;
        do {
        	System.out.println("");
            System.out.println("Escolha uma opcao de pesquisa:");
            System.out.println(" 1 - Pesquisar por ID");
            System.out.println(" 2 - Pesquisar por Codigo");
            System.out.println(" 3 - Pesquisar por Nome");
            System.out.println(" 4-  Pesquisar todos");
            System.out.println(" 0 - Sair");
            System.out.println("'M' para voltar ao menu");
            System.out.print("Opcao: ");
            resposta = scanner.nextLine();

            switch (resposta.toUpperCase()) {
                case "1":
                    pesquisarPorId(scanner);
                    break;
                case "2":
                    pesquisarPorCodigo(scanner);
                    break;
                case "3":
                    pesquisarPorNome(scanner);
                    break;
                case "4":
                	pesquisaTodos(entityManager);
                	break;
                case "0":
                    System.out.println("Encerrando o programa.");
                    break;
                case "M":
                	principal.Principal.main(null);
                default:
                    System.out.println("Opcao inválida. Tente novamente.");
                    break;
            }
        } while (!resposta.equals("0"));

        scanner.close();
        entityManager.close();
        entityManagerFactory.close();
    }

    private static void pesquisarPorId(Scanner scanner) {
        System.out.print("Digite o ID do produto: ");
        int idInput = scanner.nextInt();
        scanner.nextLine();

        String jpql = "SELECT p FROM Produtos p WHERE p.id = :id";
        TypedQuery<Produtos> query = entityManager.createQuery(jpql, Produtos.class);
        query.setParameter("id", idInput);

        exibirResultados(query.getResultList());
    }

    private static void pesquisarPorCodigo(Scanner scanner) {
        System.out.print("Digite o codigo do produto: ");
        int codigoInput = scanner.nextInt();
        scanner.nextLine();

        String jpql = "SELECT p FROM Produtos p WHERE p.codigo = :codigo";
        TypedQuery<Produtos> query = entityManager.createQuery(jpql, Produtos.class);
        query.setParameter("codigo", codigoInput);

        exibirResultados(query.getResultList());
    }

    private static void pesquisarPorNome(Scanner scanner) {
        System.out.print("Digite o nome do produto: ");
        String nomeInput = scanner.nextLine();

        String jpql = "SELECT p FROM Produtos p WHERE p.nome = :nome";
        TypedQuery<Produtos> query = entityManager.createQuery(jpql, Produtos.class);
        query.setParameter("nome", nomeInput);

        exibirResultados(query.getResultList());
    }
    
    private static void pesquisaTodos(EntityManager entityManager) {
        String jpql = "SELECT p FROM Produtos p"; // Correção da consulta JPQL
        TypedQuery<Produtos> query = entityManager.createQuery(jpql, Produtos.class);

        exibirResultados(query.getResultList());
    }


    private static void exibirResultados(List<Produtos> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            for (Produtos produto : produtos) {
                System.out.println("ID: " + produto.getId());
                System.out.println("Nome: " + produto.getNome());
                System.out.println("Codigo: " + produto.getCodigo());
                System.out.println("Setor: " + produto.getSetor());
                System.out.println("-------------------");
            }
        }
    }
}
	
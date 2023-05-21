package crud;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import produto.Produtos;

public class delete {
    private EntityManagerFactory entityManagerFactory;

    public delete() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Market");
    }

    public void deletarPorId(int produtoId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Produtos produto = entityManager.find(Produtos.class, produtoId);
            if (produto != null) {
                entityManager.remove(produto);
                transaction.commit();
                System.out.println("Produto deletado com sucesso.");
            } else {
                System.out.println("Produto nao encontrado.");
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void deletarPorNome(String nome) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            String jpql = "DELETE FROM Produtos p WHERE p.nome = :nome";
            int deletedCount = entityManager.createQuery(jpql)
                    .setParameter("nome", nome)
                    .executeUpdate();

            transaction.commit();
            System.out.println(deletedCount + " produtos deletados com sucesso.");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void deletarPorCodigoUnico(int codigo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            String jpql = "DELETE FROM Produtos p WHERE p.codigo = :codigo";
            int deletedCount = entityManager.createQuery(jpql)
                    .setParameter("codigo", codigo)
                    .executeUpdate();

            transaction.commit();
            System.out.println(deletedCount + " produtos deletados com sucesso.");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String entrada = "";

        while (!entrada.equals(":")) {
        	System.out.println("                               ");
            System.out.println("----- MENU -----");
            System.out.println("1. Deletar por ID");
            System.out.println("2. Deletar por Nome");
            System.out.println("3. Deletar por Codigo Unico");
            System.out.println("Digite ':' para sair.");
            System.out.println("Digite 'M' para voltar ao menu");
            System.out.print("Opcao: ");
            entrada = scanner.nextLine();

            switch (entrada.toUpperCase()) {
                case "1":
                    System.out.print("Digite o ID do produto que deseja deletar: ");
                    String idStr = scanner.nextLine();
                    if (!idStr.isBlank()) {
                        int produtoId = Integer.parseInt(idStr);
                        deletarPorId(produtoId);
                    } else {
                        System.out.println("ID invalido.");
                    }
                    break;
                case "2":
                    System.out.print("Digite o Nome do produto que deseja deletar: ");
                    String nome = scanner.nextLine();
                    if (!nome.isBlank()) {
                        deletarPorNome(nome);
                    } else {
                        System.out.println("Nome invalido.");
                    }
                    break;
                case "3":
                    System.out.print("Digite o Codigo Unico do produto que deseja deletar: ");
                    String codigoUnicoStr = scanner.nextLine();
                    if (!codigoUnicoStr.isBlank()) {
                        int codigoUnico = Integer.parseInt(codigoUnicoStr);
                        deletarPorCodigoUnico(codigoUnico);
                    } else {
                        System.out.println("Codigo Unico invalido.");
                    }
                    break;
                case ":":
                    break;
                case "M" :
                	principal.Principal.main(null);
                	
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }

        scanner.close();
        System.out.println("Encerrando o programa...");
    }

    public static void main(String[] args) {
        delete deleteObj = new delete();
        deleteObj.run();
    }
}


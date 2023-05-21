package crud;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import produto.Produtos;

public class update {
    private EntityManagerFactory entityManagerFactory;

    public update() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Market");
    }

    public void atualizarProduto(int produtoId, String nome, int unidades, String setor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Produtos produto = entityManager.find(Produtos.class, produtoId);
            if (produto != null) {
                produto.setNome(nome);
                produto.setUnidades(unidades);
                produto.setSetor(setor);
                entityManager.merge(produto);
                transaction.commit();
                System.out.println("Produto atualizado com sucesso.");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Digite o ID do produto que deseja atualizar (ou 'sair' para sair e 'M' pro menu): ");
            String input = scanner.nextLine();
            
            if (input.toUpperCase().equalsIgnoreCase("M")) {
               principal.Principal.main(null);
            }
            if (input.equalsIgnoreCase("sair")) {
                System.out.println("Encerrando o programa...");
                break;
            }

            if (input.isBlank()) {
                System.out.println("ID do produto inválido.");
                continue;
            }

            int produtoId;
            try {
                produtoId = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("ID do produto inválido.");
                continue;
            }

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Produtos produto = entityManager.find(Produtos.class, produtoId);
            entityManager.close();

            if (produto != null) {
            	System.out.println("");
            	System.out.println("----------------------------");
                System.out.println("Produto encontrado:");
                System.out.println("ID: " + produto.getId());
                System.out.println("Nome: " + produto.getNome());
                System.out.println("Unidades: " + produto.getUnidades());
                System.out.println("Setor: " + produto.getSetor());
                System.out.println("----------------------------");
                

                System.out.println("----- MENU -----");
                System.out.println("1. Atualizar Nome");
                System.out.println("2. Atualizar Unidades");
                System.out.println("3. Atualizar Setor");
                System.out.println("Digite qualquer outra opcao para sair.");
                System.out.print("Opcao: ");
                String opcao = scanner.nextLine();

                switch (opcao) {
                    case "1":
                        System.out.print("Digite o novo nome: ");
                        String nome = scanner.nextLine();
                        if (!nome.isBlank()) {
                            atualizarProduto(produtoId, nome, produto.getUnidades(), produto.getSetor());
                        } else {
                            System.out.println("Nome inválido.");
                        }
                        break;
                    case "2":
                        System.out.print("Digite a nova quantidade de unidades: ");
                        String unidadesStr = scanner.nextLine();
                        if (!unidadesStr.isBlank()) {
                            try {
                                int unidades = Integer.parseInt(unidadesStr);
                                atualizarProduto(produtoId, produto.getNome(), unidades, produto.getSetor());
                            } catch (NumberFormatException e) {
                                System.out.println("Quantidade de unidades inválida.");
                            }
                        } else {
                            System.out.println("Quantidade de unidades invalida");
                            }
                        break;
                            
                        case "3":
                            System.out.print("Digite o novo setor: ");
                            String setor = scanner.nextLine();
                            if (!setor.isBlank()) {
                                atualizarProduto(produtoId, produto.getNome(), produto.getUnidades(), setor);
                            } else {
                                System.out.println("Setor inválido.");
                            }
                            break;
                        default:
                            System.out.println("Encerrando o programa...");
                            break;
                    }
                } else {
                    System.out.println("Produto não encontrado.");
                }
            }scanner.close();
        }

        
        

        public static void main(String[] args) {
            update updateObj = new update();
            updateObj.run();
        }
        }


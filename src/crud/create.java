package crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import produto.Produtos;

import java.util.Scanner;

public class create {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String resposta;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Market");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            do {
            	
            	System.out.println("DESEJA VOLTAR AO MENU?");
            	String rspt = scanner.next();

            	if (rspt.equalsIgnoreCase("sim") || rspt.equalsIgnoreCase("s")) {
            	    principal.Principal.main(null);
            	} else if (rspt.equalsIgnoreCase("nao") || rspt.equalsIgnoreCase("n")) {
         	
            	} else {
            	    System.out.println("Letra errada, retornando ao menu...");
            	    principal.Principal.main(args);
            	}

              
                scanner.nextLine(); 
            	   
                System.out.println("DIGITE O NOME DO PRODUTO :");
                String nome = scanner.nextLine();

                String setor;
                do {
                    System.out.println("DIGITE O SETOR DO PRODUTO");
                    System.out.println("SETORES DISPONÍVEIS :");
                    for (Setor s : Setor.values()) {
                        System.out.println("- " + s.name().toUpperCase());
                    }
                    System.out.print("ESCOLHA O SETOR:");
                    setor = scanner.nextLine();
                } while (!validarSetor(setor));

                System.out.println("DIGITE A QUANTIDADE DE UNIDADES:");
                int unidades = scanner.nextInt();
                scanner.nextLine();

                entityManager.getTransaction().begin();

                Produtos produto = new Produtos();
                produto.setUnidades(unidades);
                produto.setNome(nome);
                produto.setSetor(setor);

                Query query = entityManager.createQuery("SELECT MAX(p.codigo) FROM Produtos p");
                Integer ultimoCodigoCadastrado = (Integer) query.getSingleResult();

                if (ultimoCodigoCadastrado != null) {
                    int proximoCodigo = ultimoCodigoCadastrado + 1;
                    produto.setCodigo(proximoCodigo);
                } else {
                    produto.setCodigo(1000);
                }

                entityManager.persist(produto);

                entityManager.getTransaction().commit();

                System.out.println("DESEJA CADASTRAR OUTRO PRODUTO (sim/não)");
                resposta = scanner.nextLine();
                
            } while (!resposta.equals("nao") || !resposta.equals("não") || !resposta.equals("NAO") || !resposta.equals("NÃO"));
            
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("OCORREU UM ERRO AO SALVAR O PRODUTO " + e.getMessage());
        } finally {
            entityManager.close();
            entityManagerFactory.close();
            scanner.close();
        }
    
        		
    }	
        		
        	


    private static boolean validarSetor(String setor) {
        Setor[] setoresPermitidos = Setor.values();
        for (Setor s : setoresPermitidos) {
            if (s.name().equalsIgnoreCase(setor)) {
                return true;
            }
        }
        System.out.println("SETOR INVÁLIDO, TENTE NOVAMENTE ");
        return false;
    }

    private enum Setor {
        GRAOS, FRUTAS, VEGETAIS, CARNES, PEIXES, PADARIA, LATICINIOS, CONGELADOS, MERCEARIA, BEBIDAS, HIGIENE, LIMPEZA, PET
    }
}

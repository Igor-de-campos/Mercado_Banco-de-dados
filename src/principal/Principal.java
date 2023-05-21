package principal;
import java.util.Scanner;

import crud.create;
import crud.delete;
import crud.read;
import crud.update;


public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String resposta = "";

        while (!resposta.equalsIgnoreCase("nao")) {
        	System.out.println("");
        	System.out.println("BEM VINDO AO MERCADO CAMPOS!");
            System.out.println("--- Menu Principal ---");
            System.out.println("1. Criar Produto");
            System.out.println("2. Atualizar Produto");
            System.out.println("3. Deletar Produto");
            System.out.println("4. Ler Produtos");
            System.out.println("Digite 'sair' para sair.");

            System.out.print("Opcao: ");
            resposta = scanner.nextLine();

            switch (resposta) {
                case "1":
                    create.main(args);;
                    break;
                case "2":
                    update updates = new update();
                    updates.run();
                    break;
                case "3":
                    delete.main(args);;
                    break;
                case "4":
                 read.main(args);;
                    break;
                case "sair":
                    System.out.println("Encerrando o programa...");
               System.exit(0);
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        scanner.close();
    }
}

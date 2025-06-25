package javahashseparatechaining;

import java.util.Scanner;

public class JavaHashMain { 

    private static int menu(Scanner scanner) {
        System.out.println("\n\t\t*** IFSULDEMINAS - CAMPUS MACHADO ***");
        System.out.println("\t\t*** Estrutura de Dados I ***");
        System.out.println("\t\t*** HASH ENCADEADO - Separate Chaining ***");
        System.out.println("1- Inserir/Atualizar");
        System.out.println("2- Remover");
        System.out.println("3- Buscar Chave");
        System.out.println("4- Alterar Valor (Apenas se existir)");
        System.out.println("0- Sair");
        System.out.print("Escolha uma opcao: ");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Tamanho inicial da tabela: ");
        int n = scanner.nextInt();
        scanner.nextLine(); 

        CustomHashMap<String> meuHashMap = new CustomHashMap<>(n);
        int op;

        do {
            System.out.println(meuHashMap.toString());
            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine(); 

            op = menu(scanner);
            scanner.nextLine(); 
            long chave;

            switch (op) {
                case 1:
                    System.out.print("Entre com a chave (numerica): ");
                    chave = scanner.nextLong();
                    scanner.nextLine(); 
                    System.out.print("Entre com o valor (string): ");
                    String nome = scanner.nextLine();
                    
                    boolean inseriu = meuHashMap.put(chave, nome);
                    if (inseriu) {
                        System.out.println("Chave " + chave + " inserida com sucesso!");
                    } else {
                        System.out.println("Chave " + chave + " existente teve seu valor atualizado.");
                    }
                    System.out.println("Comparacoes realizadas: " + meuHashMap.getComparacoes());
                    break;

                case 2:
                    System.out.print("Chave para remover: ");
                    chave = scanner.nextLong();
                    
                    boolean removeu = meuHashMap.remove(chave);
                    if (removeu) {
                        System.out.println("Chave " + chave + " removida com sucesso! :)");
                    } else {
                        System.out.println("Chave " + chave + " nao encontrada para remocao. :(");
                    }
                    System.out.println("Comparacoes realizadas: " + meuHashMap.getComparacoes());
                    break;

                case 3:
                    System.out.print("Chave para busca: ");
                    chave = scanner.nextLong();
                    
                    boolean encontrado = meuHashMap.containsKey(chave);
                    if (encontrado) {
                        System.out.println("Chave " + chave + " encontrada!");
 
                    } else {
                        System.out.println("Chave " + chave + " nao encontrada. :(");
                    }
                    System.out.println("Comparacoes realizadas: " + meuHashMap.getComparacoes());
                    break;

                case 4:
                    System.out.print("Chave para alterar: ");
                    chave = scanner.nextLong();
                    scanner.nextLine(); 
                    System.out.print("Novo valor: ");
                    String novoValor = scanner.nextLine();

                    boolean alterou = meuHashMap.replace(chave, novoValor);
                    if (alterou) {
                        System.out.println("Valor da chave " + chave + " alterado com sucesso! :)");
                    } else {
                        System.out.println("Chave " + chave + " nao encontrada para alteracao. :(");
                    }
                    System.out.println("Comparacoes realizadas: " + meuHashMap.getComparacoes());
                    break;

                case 0:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }

        } while (op != 0);

        scanner.close();
    }
}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class EditorTextoLinhas {
    public static void main(String[] args) {
        File arquivo = new File(
                System.getProperty("user.home") + File.separator + "Documents",
                "texto_editor.txt");
        Vector<String> linhas = new Vector<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            boolean executando = true;

            while (executando) {
                exibirMenu();
                System.out.print("Escolha uma opção: ");
                String opcao = scanner.nextLine().trim();

                switch (opcao) {
                    case "1":
                        listarLinhas(scanner, linhas);
                        break;
                    case "2":
                        editarLinha(scanner, linhas);
                        break;
                    case "3":
                        inserirLinha(scanner, linhas);
                        break;
                    case "4":
                        apagarLinha(scanner, linhas);
                        break;
                    case "5":
                        if (confirmar(scanner, "Abandonar sem salvar as alterações? (s/n): ")) {
                            executando = false;
                        }
                        break;
                    case "6":
                        if (confirmar(scanner, "Salvar as alterações e sair? (s/n): ")
                                && salvarArquivo(arquivo, linhas)) {
                            System.out.println("Arquivo salvo em: " + arquivo.getAbsolutePath());
                            executando = false;
                        }
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- EDITOR DE TEXTO ---");
        System.out.println("1 - Listar linhas");
        System.out.println("2 - Editar linha");
        System.out.println("3 - Inserir linha");
        System.out.println("4 - Apagar linha");
        System.out.println("5 - Abandonar sem salvar");
        System.out.println("6 - Salvar e sair");
    }

    private static void listarLinhas(Scanner scanner, Vector<String> linhas) {
        int inicio = lerInteiro(scanner, "Número da linha inicial: ");
        int fim = lerInteiro(scanner, "Número da linha final: ");

        if (inicio < 1 || fim < inicio || fim > linhas.size()) {
            System.out.println("Intervalo inválido. O arquivo contém " + linhas.size() + " linhas.");
            return;
        }

        for (int i = inicio - 1; i < fim; i++) {
            System.out.println((i + 1) + ": " + linhas.get(i));
        }
    }

    private static void editarLinha(Scanner scanner, Vector<String> linhas) {
        int numeroLinha = lerInteiro(scanner, "Número da linha que deseja editar: ");
        if (!linhaExiste(numeroLinha, linhas)) {
            System.out.println("Número de linha inválido.");
            return;
        }

        System.out.println("Conteúdo atual: " + linhas.get(numeroLinha - 1));
        System.out.print("Digite o novo conteúdo: ");
        linhas.set(numeroLinha - 1, scanner.nextLine());
        System.out.println("Linha editada.");
    }

    private static void inserirLinha(Scanner scanner, Vector<String> linhas) {
        int depoisDaLinha = lerInteiro(scanner, "Inserir depois de qual linha? (0 para o início): ");
        if (depoisDaLinha < 0 || depoisDaLinha > linhas.size()) {
            System.out.println("Número de linha inválido.");
            return;
        }

        System.out.print("Digite o conteúdo da nova linha: ");
        linhas.add(depoisDaLinha, scanner.nextLine());
        System.out.println("Linha inserida.");
    }

    private static void apagarLinha(Scanner scanner, Vector<String> linhas) {
        int numeroLinha = lerInteiro(scanner, "Número da linha que deseja apagar: ");
        if (!linhaExiste(numeroLinha, linhas)) {
            System.out.println("Número de linha inválido.");
            return;
        }

        System.out.println("Conteúdo: " + linhas.get(numeroLinha - 1));
        if (confirmar(scanner, "Confirma a exclusão desta linha? (s/n): ")) {
            linhas.remove(numeroLinha - 1);
            System.out.println("Linha apagada.");
        }
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private static boolean confirmar(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim().equalsIgnoreCase("s");
    }

    private static boolean linhaExiste(int numeroLinha, Vector<String> linhas) {
        return numeroLinha >= 1 && numeroLinha <= linhas.size();
    }

    private static boolean salvarArquivo(File arquivo, Vector<String> linhas) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo))) {
            for (String linha : linhas) {
                escritor.write(linha);
                escritor.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            return false;
        }
    }
}

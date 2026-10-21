import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BuscaPalavra {
    public static void main(String[] args) {
        String pastaDocumentos = System.getProperty("user.home") + File.separator + "Documents";
        File arquivo = new File(pastaDocumentos, "meu_arquivo.txt");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite a palavra ou texto que deseja procurar: ");
            String busca = scanner.nextLine();

            try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                int numeroLinha = 0;
                boolean encontrou = false;

                while ((linha = leitor.readLine()) != null) {
                    numeroLinha++;
                    if (linha.contains(busca)) {
                        System.out.println("Linha " + numeroLinha + ": " + linha);
                        encontrou = true;
                    }
                }

                if (!encontrou) {
                    System.out.println("A informação não foi encontrada no arquivo.");
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        }
    }
}

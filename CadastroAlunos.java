import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner; 


public class CadastroAlunos {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        String [] nomes = new String[10];
        double [] nota1 = new double[10];
        double [] nota2 = new double[10];
        double [] medias = new double[10];

        String caminhoArquivo = System.getProperty("user.home");
        File arquivo = new File(caminhoArquivo + File.separator + "Documents" + File.separator + "meu_arquivo.txt");

        try (FileWriter fileWriter = new FileWriter(arquivo);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            for(int i = 0; i<10; i++){
                System.out.println("Digite o nome do aluno" + (i+1) + " :");
                nomes[i] = scanner.nextLine();

                System.out.println("Digite a nota 1 do aluno" + (i+1) + " :");
                nota1[i] = scanner.nextDouble();

                System.out.println("Digite a nota 2 do aluno" + (i+1) + " :");
                nota2[i] = scanner.nextDouble();
                scanner.nextLine();

                medias[i] = (nota1[i] + nota2[i]) / 2;

                printWriter.printf("Aluno: %s, Nota 1: %.2f, Nota 2: %.2f, Média: %.2f%n",
                        nomes[i], nota1[i], nota2[i], medias[i]);
            }

            System.out.println("Dados gravados com sucesso em: " + arquivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao gravar o arquivo: " + e.getMessage());
        }

    }
}

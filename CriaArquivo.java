import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;

public class CriaArquivo{
    public static void main(String[] args){
        String caminhoArquivo = System.getProperty("user.home");

        File arquivo = new File(caminhoArquivo + File.separator + "Documents" + File.separator + "meu_arquivo.txt");

        try(FileWriter filewritter = new FileWriter(arquivo);
            PrintWriter printWritter = new PrintWriter(filewritter)){

            printWritter.println("Texto gravado usando filewritter e printwritter");

            printWritter.println("Salvo diretamente na pasta documentos");

            // CORREÇÃO: Adicionado o ponto e vírgula no final da linha
            System.out.println("Arquivo criado com sucesso em: " + arquivo.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
        }
    }
}

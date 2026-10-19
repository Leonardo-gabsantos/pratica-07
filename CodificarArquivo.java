import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CodificarArquivo {
    public static void main(String[] args) {
        String pastaDocumentos = System.getProperty("user.home") + File.separator + "Documents";
        File arquivoOriginal = new File(pastaDocumentos, "meu_arquivo.txt");
        File arquivoCodificado = new File(pastaDocumentos, "arquivo_codificado.txt");

        try (FileReader leitor = new FileReader(arquivoOriginal);
             FileWriter escritor = new FileWriter(arquivoCodificado)) {
            int caractere;

            while ((caractere = leitor.read()) != -1) {
                escritor.write(caractere + 1);
            }

            System.out.println("Arquivo codificado com sucesso em: " + arquivoCodificado.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao codificar o arquivo: " + e.getMessage());
        }
    }
}

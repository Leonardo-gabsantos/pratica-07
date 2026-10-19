import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DecodificarArquivo {
    public static void main(String[] args) {
        String pastaDocumentos = System.getProperty("user.home") + File.separator + "Documents";
        File arquivoCodificado = new File(pastaDocumentos, "arquivo_codificado.txt");
        File arquivoDecodificado = new File(pastaDocumentos, "arquivo_decodificado.txt");

        try (FileReader leitor = new FileReader(arquivoCodificado);
             FileWriter escritor = new FileWriter(arquivoDecodificado)) {
            int caractere;

            while ((caractere = leitor.read()) != -1) {
                escritor.write(caractere - 1);
            }

            System.out.println("Arquivo decodificado com sucesso em: " + arquivoDecodificado.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao decodificar o arquivo: " + e.getMessage());
        }
    }
}

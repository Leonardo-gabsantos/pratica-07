import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

public class LerArquivo {
    public static void main(String[] args){
        //aponta para o mesmo caminho que a classe CriaArquivo salvou
        String caminhoArquivo = System.getProperty("user.home");
        File arquivo = new File(caminhoArquivo + File.separator + "Documents" + File.separator + "meu_arquivo.txt");

        try(FileReader filereader = new FileReader(arquivo);
        BufferedReader bufferedReader = new BufferedReader(filereader)){
            String linha;
            System.out.println("------LENDO CONTEUDO DO ARQUIVO------");

            // CORREÇÃO: Adicionados os parênteses em volta de (linha = bufferedReader.readLine())
            while((linha = bufferedReader.readLine()) != null){
                System.out.println(linha);
            }

             System.out.println("-----------------------------------");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
        }
    }    
}

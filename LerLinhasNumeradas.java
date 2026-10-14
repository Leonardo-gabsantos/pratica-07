import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class LerLinhasNumeradas {
    public static void main(String [] args){
        String pastaUsuario = System.getProperty("user.home");
        File arquivo = new File(pastaUsuario + File.separator + "Documents" + File.separator + "meu_arquivo.txt");

        try(FileReader fileReader = new FileReader(arquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader)){
            
                String linha;
                int numeroLinha = 1;

                System.out.println("Exibindo conteúdo numerado");
                System.out.println("--------------------"); 

                
                while ((linha = bufferedReader.readLine()) != null){
                    System.out.println(numeroLinha + ": " + linha);
                    numeroLinha ++;
                }
                
                System.out.println("--------------------"); 

            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }
    }
}

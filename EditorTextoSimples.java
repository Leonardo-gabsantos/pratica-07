import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class EditorTextoSimples {
    public static void main(String[] args) {
        
        String[] textoEmMemoria = new String[50];
        int contadorLinhas = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("--- EDITOR DE TEXTO SIMPLES ---");
        System.out.println("Digite seu texto linha por linha.");
        System.out.println("Para encerrar e salvar, digite apenas a palavra 'FIM'.");
        System.out.println("--------------------------------");

        
        while (contadorLinhas < 50) {
            System.out.print((contadorLinhas + 1) + ": ");
            String linhaDigitada = scanner.nextLine();

        
            if (linhaDigitada.equalsIgnoreCase("FIM")) {
                break; 
            }

        
            textoEmMemoria[contadorLinhas] = linhaDigitada;
            contadorLinhas++;
        }

        System.out.println("\nSalvando arquivo no disco...");

        
        String pastaUsuario = System.getProperty("user.home");
        File arquivo = new File(pastaUsuario + File.separator + "Documents" + File.separator + "texto_editor.txt");

        
        try (FileWriter fileWriter = new FileWriter(arquivo);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

        
            for (int i = 0; i < contadorLinhas; i++) {
                printWriter.println(textoEmMemoria[i]);
            }

            System.out.println("Arquivo gravado com sucesso em: " + arquivo.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo em disco: " + e.getMessage());
        } finally {
            scanner.close(); 
        }
    }
}

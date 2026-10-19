import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LerCadastroAlunos {
    private static final Pattern REGISTRO_ALUNO = Pattern.compile(
            "^Aluno: (.*), Nota 1: ([0-9]+(?:[.,][0-9]+)?), Nota 2: ([0-9]+(?:[.,][0-9]+)?), Média: ([0-9]+(?:[.,][0-9]+)?)$");

    public static void main(String[] args) {
        String caminhoArquivo = System.getProperty("user.home");
        File arquivo = new File(caminhoArquivo + File.separator + "Documents" + File.separator + "meu_arquivo.txt");
        double somaMedias = 0;
        int quantidadeAlunos = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            System.out.println("------ CADASTRO DE ALUNOS ------");

            while ((linha = bufferedReader.readLine()) != null) {
                Matcher matcher = REGISTRO_ALUNO.matcher(linha);

                if (!matcher.matches()) {
                    System.out.println("Linha ignorada: " + linha);
                    continue;
                }

                String nome = matcher.group(1);
                double nota1 = converterNumero(matcher.group(2));
                double nota2 = converterNumero(matcher.group(3));
                double media = converterNumero(matcher.group(4));

                System.out.printf(Locale.US,
                        "Aluno: %s, Nota 1: %.2f, Nota 2: %.2f, Média: %.2f%n",
                        nome, nota1, nota2, media);
                somaMedias += media;
                quantidadeAlunos++;
            }

            if (quantidadeAlunos > 0) {
                double mediaGeral = somaMedias / quantidadeAlunos;
                System.out.printf(Locale.US, "Média geral da turma: %.2f%n", mediaGeral);
            } else {
                System.out.println("Nenhum aluno foi encontrado no arquivo.");
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static double converterNumero(String numero) {
        return Double.parseDouble(numero.replace(',', '.'));
    }
}

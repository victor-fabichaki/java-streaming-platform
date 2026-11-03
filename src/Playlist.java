import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
    private String nome;
    private Usuario dono;
    private ArrayList<Conteudo> conteudos = new ArrayList<>();

    public Playlist(String nome, Usuario dono) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        if (dono == null) {
            throw new IllegalArgumentException("Dono inexistente");
        }
        this.nome = nome;
        this.dono = dono;
    }

    public String getNome() {
        return this.nome;
    }

    public Usuario getDono() {
        return this.dono;
    }

    public int getQuantidade() {
        return conteudos.size();
    }

    public boolean adicionar(Conteudo conteudo) {
        if (conteudo == null) {
            return false;
        }
        conteudos.add(conteudo);
        return true;
    }

    public Conteudo getNaPosicao(int indice) {
        if (indice < 0 || indice >= conteudos.size()) {
            throw new IndexOutOfBoundsException("Índice inválido: " + indice);
        }
        return conteudos.get(indice);
    }

    public boolean removerNaPosicao(int indice) {
        if (indice < 0 || indice >= conteudos.size()) {
            throw new IndexOutOfBoundsException("Índice inválido: " + indice);
        }
        conteudos.remove(indice);
        return true;
    }

    public int getDuracaoTotalSegundos() {
        int total = 0;
        for (Conteudo c : conteudos) {
            total += c.getDuracaoSegundos();
        }
        return total;
    }

    public void reproduzirTudo() {
        for (Conteudo c : conteudos) {
            c.reproduzir();
        }
    }

    public List<Conteudo> getConteudos() {
        return Collections.unmodifiableList(conteudos);
    }
}
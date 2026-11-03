import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private static int contador = 0;
    private int id;
    private String nome;
    private String email;
    private Plano plano;
    private ArrayList<Usuario> seguindo = new ArrayList<>();

    public Usuario(String nome, String email) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.id = ++contador;
        this.nome = nome;
        this.email = email;
        this.plano = new PlanoGratuito();
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public Plano getPlano() {
        return plano;
    }

    public void assinar(Plano novoPlano) {
        if (novoPlano == null) {
            throw new IllegalArgumentException("Plano não pode ser nulo");
        }
        this.plano = novoPlano;
    }

    public void seguir(Usuario outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Usuário inexistente");
        }
        if (outro == this) {
            throw new IllegalArgumentException("Um usuário não pode seguir a si mesmo");
        }
        if (seguindo.contains(outro)) {
            throw new IllegalArgumentException("Usuário já está sendo seguido");
        }
        seguindo.add(outro);
    }

    public void deixarDeSeguir(Usuario outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Usuário inexistente");
        }
        if (!seguindo.contains(outro)) {
            throw new IllegalArgumentException("Usuário não está na lista de seguidos");
        }
        seguindo.remove(outro);
    }

    public int getQuantidadeSeguindo() {
        return seguindo.size();
    }

    public List<Usuario> getSeguindo() {
        return Collections.unmodifiableList(seguindo);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " (" + email + ") - Plano: " + plano.getNome();
    }
}
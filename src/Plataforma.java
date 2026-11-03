import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Plataforma {
    private ArrayList<Conteudo> acervo = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Anuncio> anuncios = new ArrayList<>();

    public boolean cadastrarConteudo(Conteudo conteudo) {
        if (conteudo == null) {
            return false;
        }
        acervo.add(conteudo);
        return true;
    }

    public boolean cadastrarMusica(Musica musica) {
        return cadastrarConteudo(musica);
    }

    public boolean cadastrarPodcast(Podcast podcast) {
        return cadastrarConteudo(podcast);
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        usuarios.add(usuario);
        return true;
    }

    public boolean cadastrarAnuncio(Anuncio anuncio) {
        if (anuncio == null) {
            return false;
        }
        anuncios.add(anuncio);
        return true;
    }

    public Conteudo buscarConteudoPorId(int id) {
        for (Conteudo c : acervo) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public Musica buscarMusicaPorId(int id) {
        for (Conteudo c : acervo) {
            if (c instanceof Musica && c.getId() == id) {
                return (Musica) c;
            }
        }
        return null;
    }

    public Conteudo buscarConteudo(String titulo) {
        if (titulo == null) {
            return null;
        }
        for (Conteudo c : acervo) {
            if (c.getTitulo() != null && c.getTitulo().equalsIgnoreCase(titulo)) {
                return c;
            }
        }
        return null;
    }

    public Musica buscarMusica(String titulo) {
        if (titulo == null) {
            return null;
        }
        for (Conteudo c : acervo) {
            if (c instanceof Musica && c.getTitulo() != null && c.getTitulo().equalsIgnoreCase(titulo)) {
                return (Musica) c;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public int getTotalConteudos() {
        return acervo.size();
    }

    public int getTotalMusicas() {
        int count = 0;
        for (Conteudo c : acervo) {
            if (c instanceof Musica) {
                count++;
            }
        }
        return count;
    }

    public int getTotalUsuarios() {
        return usuarios.size();
    }

    public int getTotalAnuncios() {
        return anuncios.size();
    }

    public List<Conteudo> getAcervo() {
        return Collections.unmodifiableList(acervo);
    }

    public List<Usuario> getUsuarios() {
        return Collections.unmodifiableList(usuarios);
    }

    public List<Anuncio> getAnuncios() {
        return Collections.unmodifiableList(anuncios);
    }

    /**
     * Percorre o acervo e imprime os podcasts do apresentador informado.
     * Uso legítimo de instanceof com downcasting pois getApresentador() é exclusivo de Podcast.
     */
    public void listarEpisodiosDe(String apresentador) {
        if (apresentador == null) {
            return;
        }
        boolean encontrou = false;
        for (Conteudo c : acervo) {
            if (c instanceof Podcast) {
                Podcast p = (Podcast) c;
                if (p.getApresentador().equalsIgnoreCase(apresentador)) {
                    System.out.println(p);
                    encontrou = true;
                }
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum episódio encontrado para o apresentador: " + apresentador);
        }
    }

    public double calcularReceitaMensal() {
        ArrayList<Faturavel> cobrancas = new ArrayList<>();

        for (Usuario u : usuarios) {
            cobrancas.add(u.getPlano());
        }

        for (Anuncio a : anuncios) {
            cobrancas.add(a);
        }

        double total = 0.0;
        for (Faturavel f : cobrancas) {
            total += f.calcularMensalidade();
        }

        return total;
    }

    public void tocarPlaylist(Usuario ouvinte, Playlist playlist) {
        if (ouvinte == null || playlist == null) {
            throw new IllegalArgumentException("Usuário ouvinte e Playlist não podem ser nulos.");
        }

        ArrayList<Reproduzivel> fila = new ArrayList<>();
        boolean incluirAnuncios = ouvinte.getPlano().temAnuncios() && !anuncios.isEmpty();
        int indiceAnuncio = 0;

        for (int i = 0; i < playlist.getQuantidade(); i++) {
            fila.add(playlist.getNaPosicao(i));
            if (incluirAnuncios && (i + 1) % 2 == 0) {
                fila.add(anuncios.get(indiceAnuncio % anuncios.size()));
                indiceAnuncio++;
            }
        }

        for (Reproduzivel item : fila) {
            System.out.println("[" + item.getDuracaoFormatada() + "]");
            item.reproduzir();
        }
    }
}
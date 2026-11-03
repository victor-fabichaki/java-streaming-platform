public class Musica extends Conteudo {
    private String artista;
    private String album;

    public Musica(String titulo, int duracaoSegundos, String artista, String album) {
        super(titulo, duracaoSegundos);
        setArtista(artista);
        setAlbum(album);
    }

    public Musica(String titulo, String artista, int duracaoSegundos) {
        this(titulo, duracaoSegundos, artista, "Desconhecido");
    }

    public String getArtista() {
        return this.artista;
    }

    public void setArtista(String artista) {
        if (artista == null || artista.isBlank()) {
            throw new IllegalArgumentException("Artista inválido");
        }
        this.artista = artista;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album) {
        if (album == null || album.isBlank()) {
            throw new IllegalArgumentException("Álbum inválido");
        }
        this.album = album;
    }

    @Override
    public String getCreditos() {
        return artista + " (" + album + ")";
    }

    @Override
    public String toString() {
        return super.toString() + " - " + getCreditos();
    }
}
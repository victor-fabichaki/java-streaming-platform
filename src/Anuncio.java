public class Anuncio implements Faturavel, Reproduzivel {
    private String titulo;
    private String anunciante;
    private int duracaoSegundos;
    private double valorPorMilImpressoes;
    private int impressoes;

    public Anuncio(String titulo, String anunciante, int duracaoSegundos, double valorPorMilImpressoes) {
        setTitulo(titulo);
        setAnunciante(anunciante);
        setDuracaoSegundos(duracaoSegundos);
        setValorPorMilImpressoes(valorPorMilImpressoes);
        this.impressoes = 0;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título inválido");
        }
        this.titulo = titulo;
    }

    public String getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(String anunciante) {
        if (anunciante == null || anunciante.isBlank()) {
            throw new IllegalArgumentException("Anunciante inválido");
        }
        this.anunciante = anunciante;
    }

    @Override
    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public void setDuracaoSegundos(int duracaoSegundos) {
        if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException("Duração deve ser maior que zero");
        }
        this.duracaoSegundos = duracaoSegundos;
    }

    public double getValorPorMilImpressoes() {
        return valorPorMilImpressoes;
    }

    public void setValorPorMilImpressoes(double valorPorMilImpressoes) {
        if (valorPorMilImpressoes <= 0) {
            throw new IllegalArgumentException("Valor por mil impressões deve ser maior que zero");
        }
        this.valorPorMilImpressoes = valorPorMilImpressoes;
    }

    public int getImpressoes() {
        return impressoes;
    }

    @Override
    public void reproduzir() {
        impressoes++;
        System.out.println("[Anuncio] " + titulo + " - oferecimento de " + anunciante);
    }

    public double calcularValorVeiculacao() {
        return (impressoes / 1000.0) * valorPorMilImpressoes;
    }

    @Override
    public double calcularMensalidade() {
        return calcularValorVeiculacao();
    }

    @Override
    public String toString() {
        return "[Anuncio] " + titulo + " (" + anunciante + ") - " + getDuracaoFormatada();
    }
}

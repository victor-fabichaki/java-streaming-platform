import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Plataforma plataforma = new Plataforma();
        Usuario usuarioLogado = null;

        Musica musicaBohemian = new Musica("Bohemian Rhapsody", 355, "Queen", "A Night at the Opera");
        Musica musicaImagine = new Musica("Imagine", 183, "John Lennon", "Imagine");
        Musica musicaBillieJean = new Musica("Billie Jean", 294, "Michael Jackson", "Thriller");
        Podcast podcastFlow = new Podcast("Flow Podcast", 7200, "Igor 3K", 100);
        Podcast podcastDev = new Podcast("Hipsters Ponto Tech", 3000, "Paulo Silveira", 250);

        plataforma.cadastrarConteudo(musicaBohemian);
        plataforma.cadastrarConteudo(musicaImagine);
        plataforma.cadastrarConteudo(musicaBillieJean);
        plataforma.cadastrarConteudo(podcastFlow);
        plataforma.cadastrarConteudo(podcastDev);

        Anuncio anuncioCafe = new Anuncio("Cafe da Serra", "Torrefacao Serrana", 30, 50.0);
        Anuncio anuncioCarro = new Anuncio("Novo Turbo 2026", "Auto Motors", 45, 80.0);
        plataforma.cadastrarAnuncio(anuncioCafe);
        plataforma.cadastrarAnuncio(anuncioCarro);

        Usuario usuarioPadrao = new Usuario("Ana Silva", "ana@email.com");
        Usuario usuarioPremium = new Usuario("Carlos Souza", "carlos@email.com");
        usuarioPremium.assinar(new PlanoIndividual(29.90));
        plataforma.cadastrarUsuario(usuarioPadrao);
        plataforma.cadastrarUsuario(usuarioPremium);
        usuarioLogado = usuarioPadrao;

        int opcao = -1;

        do {
            System.out.println("\n=================== SONORA ===================");
            System.out.println("Usuário atual: " + (usuarioLogado != null ? usuarioLogado.getNome() + " (Plano: " + usuarioLogado.getPlano().getNome() + ")" : "Nenhum"));
            System.out.println("----------------------------------------------");
            System.out.println(" 1 - Cadastrar música");
            System.out.println(" 2 - Cadastrar podcast");
            System.out.println(" 3 - Cadastrar anúncio");
            System.out.println(" 4 - Cadastrar usuário");
            System.out.println(" 5 - Selecionar usuário ativo");
            System.out.println(" 6 - Criar playlist e adicionar conteúdos");
            System.out.println(" 7 - Adicionar conteúdo a playlist existente");
            System.out.println(" 8 - Tocar playlist para usuário ativo");
            System.out.println(" 9 - Listar episódios de um apresentador");
            System.out.println("10 - Exibir receita mensal da plataforma");
            System.out.println("11 - Trocar plano do usuário ativo");
            System.out.println("12 - Exibir resumo do plano atual");
            System.out.println("13 - Seguir usuário");
            System.out.println("14 - Deixar de seguir usuário");
            System.out.println("15 - Listar usuários seguidos");
            System.out.println("16 - Listar todo o acervo");
            System.out.println("17 - Reproduzir um conteúdo avulso");
            System.out.println("18 - Executar demonstração completa dos requisitos");
            System.out.println(" 0 - Sair");
            System.out.println("==============================================");

            while (true) {
                System.out.print("Escolha uma opção: ");
                if (!scanner.hasNextLine()) {
                    opcao = 0;
                    break;
                }
                String linha = scanner.nextLine().trim();
                linha = linha.replace("\uFEFF", "").trim();
                if (linha.isEmpty()) {
                    continue;
                }
                try {
                    opcao = Integer.parseInt(linha);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Digite um número inteiro.");
                }
            }

            switch (opcao) {
                case 1: {
                    try {
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Artista: ");
                        String artista = scanner.nextLine();
                        System.out.print("Álbum: ");
                        String album = scanner.nextLine();
                        System.out.print("Duração em segundos: ");
                        int duracao = Integer.parseInt(scanner.nextLine().trim());

                        Musica m = new Musica(titulo, duracao, artista, album);
                        plataforma.cadastrarConteudo(m);
                        System.out.println("Música cadastrada com sucesso! ID: " + m.getId());
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: A duração deve ser um número inteiro.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao cadastrar música: " + e.getMessage());
                    }
                    break;
                }

                case 2: {
                    try {
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Apresentador: ");
                        String apresentador = scanner.nextLine();
                        System.out.print("Número do episódio: ");
                        int episodio = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Duração em segundos: ");
                        int duracao = Integer.parseInt(scanner.nextLine().trim());

                        Podcast p = new Podcast(titulo, duracao, apresentador, episodio);
                        plataforma.cadastrarConteudo(p);
                        System.out.println("Podcast cadastrado com sucesso! ID: " + p.getId());
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Episódio e duração devem ser números inteiros.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao cadastrar podcast: " + e.getMessage());
                    }
                    break;
                }

                case 3: {
                    try {
                        System.out.print("Título do anúncio: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Anunciante: ");
                        String anunciante = scanner.nextLine();
                        System.out.print("Duração em segundos: ");
                        int duracao = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Valor por mil impressões (R$): ");
                        double valor = Double.parseDouble(scanner.nextLine().trim());

                        Anuncio a = new Anuncio(titulo, anunciante, duracao, valor);
                        plataforma.cadastrarAnuncio(a);
                        System.out.println("Anúncio cadastrado com sucesso!");
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Duração deve ser inteiro e valor deve ser numérico.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao cadastrar anúncio: " + e.getMessage());
                    }
                    break;
                }

                case 4: {
                    try {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("E-mail: ");
                        String email = scanner.nextLine();

                        Usuario novo = new Usuario(nome, email);
                        plataforma.cadastrarUsuario(novo);
                        if (usuarioLogado == null) {
                            usuarioLogado = novo;
                        }
                        System.out.println("Usuário cadastrado com sucesso! ID: " + novo.getId());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
                    }
                    break;
                }

                case 5: {
                    try {
                        System.out.print("ID do usuário a selecionar: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        Usuario u = plataforma.buscarUsuarioPorId(id);
                        if (u != null) {
                            usuarioLogado = u;
                            System.out.println("Usuário ativo alterado para: " + u.getNome());
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido. Digite um número.");
                    }
                    break;
                }

                case 6: {
                    if (usuarioLogado == null) {
                        System.out.println("Cadastre ou selecione um usuário primeiro.");
                        break;
                    }

                    System.out.print("Nome da nova playlist: ");
                    String nomePlaylist = scanner.nextLine();

                    try {
                        Playlist playlist = new Playlist(nomePlaylist, usuarioLogado);
                        int idConteudo = -1;
                        do {
                            System.out.print("ID do conteúdo a adicionar (-1 para encerrar): ");
                            try {
                                idConteudo = Integer.parseInt(scanner.nextLine().trim());
                            } catch (NumberFormatException e) {
                                System.out.println("ID inválido. Digite um número.");
                                continue;
                            }

                            if (idConteudo != -1) {
                                Conteudo cont = plataforma.buscarConteudoPorId(idConteudo);
                                if (cont != null) {
                                    playlist.adicionar(cont);
                                    System.out.println("Adicionado: " + cont.getTitulo());
                                } else {
                                    System.out.println("Conteúdo com ID " + idConteudo + " não encontrado.");
                                }
                            }
                        } while (idConteudo != -1);

                        System.out.println("Playlist criada com " + playlist.getQuantidade() + " itens!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Não foi possível criar a playlist: " + e.getMessage());
                    }
                    break;
                }

                case 7: {
                    if (usuarioLogado == null) {
                        System.out.println("Selecione um usuário primeiro.");
                        break;
                    }
                    System.out.print("Nome da playlist: ");
                    String nomePl = scanner.nextLine();
                    Playlist playlist = new Playlist(nomePl, usuarioLogado);

                    System.out.print("ID do podcast ou música a adicionar: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        Conteudo cont = plataforma.buscarConteudoPorId(id);
                        if (cont != null) {
                            playlist.adicionar(cont);
                            System.out.println("Conteúdo " + cont.getTitulo() + " adicionado com sucesso!");
                        } else {
                            System.out.println("Conteúdo não encontrado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido.");
                    }
                    break;
                }

                case 8: {
                    if (usuarioLogado == null) {
                        System.out.println("Selecione um usuário primeiro.");
                        break;
                    }
                    if (plataforma.getTotalConteudos() == 0) {
                        System.out.println("Não há conteúdos no acervo.");
                        break;
                    }

                    Playlist tempPlaylist = new Playlist("Sessão Atual", usuarioLogado);
                    for (Conteudo item : plataforma.getAcervo()) {
                        tempPlaylist.adicionar(item);
                    }

                    System.out.println("\nTocando playlist para o usuário: " + usuarioLogado.getNome()
                            + " (Plano: " + usuarioLogado.getPlano().getNome()
                            + ", tem anúncios: " + usuarioLogado.getPlano().temAnuncios() + ")...");
                    plataforma.tocarPlaylist(usuarioLogado, tempPlaylist);
                    break;
                }

                case 9: {
                    System.out.print("Nome do apresentador: ");
                    String apresentador = scanner.nextLine();
                    System.out.println("Buscando episódios...");
                    plataforma.listarEpisodiosDe(apresentador);
                    break;
                }

                case 10: {
                    double receita = plataforma.calcularReceitaMensal();
                    System.out.printf("Receita mensal total da plataforma: R$ %.2f\n", receita);
                    break;
                }

                case 11: {
                    if (usuarioLogado == null) {
                        System.out.println("Selecione um usuário primeiro.");
                        break;
                    }

                    System.out.println("Planos disponíveis:");
                    System.out.println("1 - Gratuito (com anúncios, 1 dispositivo)");
                    System.out.println("2 - Individual (sem anúncios, R$ mensal)");
                    System.out.println("3 - Família (sem anúncios, até 6 membros)");
                    System.out.print("Escolha o novo plano: ");

                    try {
                        int escolhaPlano = Integer.parseInt(scanner.nextLine().trim());
                        Plano novoPlano = null;

                        if (escolhaPlano == 1) {
                            novoPlano = new PlanoGratuito();
                        } else if (escolhaPlano == 2) {
                            System.out.print("Preço mensal: ");
                            double preco = Double.parseDouble(scanner.nextLine().trim());
                            novoPlano = new PlanoIndividual(preco);
                        } else if (escolhaPlano == 3) {
                            System.out.print("Preço base mensal: ");
                            double preco = Double.parseDouble(scanner.nextLine().trim());
                            System.out.print("Quantidade de membros (1 a 6): ");
                            int membros = Integer.parseInt(scanner.nextLine().trim());
                            novoPlano = new PlanoFamilia(preco, membros);
                        } else {
                            System.out.println("Opção de plano inválida.");
                            break;
                        }

                        usuarioLogado.assinar(novoPlano);
                        System.out.println("Plano assinado com sucesso!");
                        System.out.println(usuarioLogado.getPlano().resumo());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada numérica inválida.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao alterar plano: " + e.getMessage());
                    }
                    break;
                }

                case 12: {
                    if (usuarioLogado == null) {
                        System.out.println("Selecione um usuário primeiro.");
                        break;
                    }
                    System.out.println("Resumo do plano de " + usuarioLogado.getNome() + ":");
                    System.out.println(usuarioLogado.getPlano().resumo());
                    break;
                }

                case 13: {
                    if (usuarioLogado == null) {
                        System.out.println("Selecione um usuário primeiro.");
                        break;
                    }
                    try {
                        System.out.print("ID do usuário a seguir: ");
                        int idSeguir = Integer.parseInt(scanner.nextLine().trim());
                        Usuario uSeguir = plataforma.buscarUsuarioPorId(idSeguir);
                        if (uSeguir == null) {
                            System.out.println("Usuário não encontrado.");
                            break;
                        }
                        usuarioLogado.seguir(uSeguir);
                        System.out.println("Agora você está seguindo " + uSeguir.getNome() + "!");
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Não foi possível seguir: " + e.getMessage());
                    }
                    break;
                }

                case 14: {
                    if (usuarioLogado == null) {
                        System.out.println("Selecione um usuário primeiro.");
                        break;
                    }
                    try {
                        System.out.print("ID do usuário a deixar de seguir: ");
                        int idDeixar = Integer.parseInt(scanner.nextLine().trim());
                        Usuario uDeixar = plataforma.buscarUsuarioPorId(idDeixar);
                        if (uDeixar == null) {
                            System.out.println("Usuário não encontrado.");
                            break;
                            }
                        usuarioLogado.deixarDeSeguir(uDeixar);
                        System.out.println("Deixou de seguir " + uDeixar.getNome() + ".");
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Não foi possível deixar de seguir: " + e.getMessage());
                    }
                    break;
                }

                case 15: {
                    if (usuarioLogado == null) {
                        System.out.println("Selecione um usuário primeiro.");
                        break;
                    }
                    if (usuarioLogado.getQuantidadeSeguindo() == 0) {
                        System.out.println("Você não segue nenhum usuário.");
                    } else {
                        System.out.println("Usuários seguidos por " + usuarioLogado.getNome() + ":");
                        for (Usuario u : usuarioLogado.getSeguindo()) {
                            System.out.println("- [" + u.getId() + "] " + u.getNome() + " (" + u.getEmail() + ")");
                        }
                    }
                    break;
                }

                case 16: {
                    if (plataforma.getTotalConteudos() == 0) {
                        System.out.println("Nenhum conteúdo cadastrado.");
                    } else {
                        System.out.println("=== Acervo da Plataforma ===");
                        for (Conteudo cont : plataforma.getAcervo()) {
                            System.out.println(cont);
                        }
                    }
                    break;
                }

                case 17: {
                    try {
                        System.out.print("ID do conteúdo a reproduzir: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        Conteudo cont = plataforma.buscarConteudoPorId(id);
                        if (cont != null) {
                            cont.reproduzir();
                            System.out.println("Total de reproduções deste conteúdo: " + cont.getReproducoes());
                        } else {
                            System.out.println("Conteúdo não encontrado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido.");
                    }
                    break;
                }

                case 18: {
                    System.out.println("\n========== DEMONSTRAÇÃO COMPLETA DOS REQUISITOS ==========");
                    System.out.printf("1. Receita mensal ANTES da reprodução com anúncios: R$ %.2f\n", plataforma.calcularReceitaMensal());

                    Playlist playlistMista = new Playlist("Mix Demonstracao", usuarioPadrao);
                    playlistMista.adicionar(musicaBohemian);
                    playlistMista.adicionar(podcastFlow);
                    playlistMista.adicionar(musicaImagine);
                    playlistMista.adicionar(podcastDev);

                    System.out.println("\n2. Tocando playlist mista para usuário Gratuito (espera-se anúncios intercalados a cada 2 itens):");
                    plataforma.tocarPlaylist(usuarioPadrao, playlistMista);

                    System.out.printf("\n3. Receita mensal DEPOIS da reprodução com anúncios: R$ %.2f\n", plataforma.calcularReceitaMensal());

                    System.out.println("\n4. Testando contadores de reproduções repetidas:");
                    musicaBohemian.reproduzir();
                    musicaBohemian.reproduzir();
                    podcastFlow.reproduzir();
                    System.out.println("Reproduções de " + musicaBohemian.getTitulo() + ": " + musicaBohemian.getReproducoes());
                    System.out.println("Reproduções de " + podcastFlow.getTitulo() + ": " + podcastFlow.getReproducoes());

                    System.out.println("\n5. Listando episódios de 'Paulo Silveira':");
                    plataforma.listarEpisodiosDe("Paulo Silveira");

                    System.out.println("\n6. Resumos dos planos dos usuários:");
                    System.out.println(usuarioPadrao.getNome() + ": " + usuarioPadrao.getPlano().resumo());
                    System.out.println(usuarioPremium.getNome() + ": " + usuarioPremium.getPlano().resumo());
                    System.out.println("==========================================================\n");
                    break;
                }

                case 0:
                    System.out.println("Saindo do Sonora. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Digite uma opção válida do menu.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}

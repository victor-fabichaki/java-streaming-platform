# Java Streaming Platform (Sonora)

[![Java](https://img.shields.io/badge/Java-21%2B-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

---

## 🌐 Idiomas / Languages
- [Português](#-português)
- [English](#-english)

---

# 🇧🇷 Português

O **Sonora** é uma plataforma de streaming de áudio desenvolvida em Java, que simula o funcionamento de serviços como Spotify ou Apple Music. O sistema gerencia conteúdos de áudio (músicas e podcasts), anúncios comerciais, perfis de usuários com diferentes modalidades de planos de assinatura, reprodução inteligente de playlists com inserção de anúncios e faturamento/receita mensal.

---

### 📋 Índice (Português)
1. [Funcionalidades Principais](#-funcionalidades-principais)
2. [Conceitos de POO Aplicados](#-conceitos-de-poo-aplicados)
3. [Estrutura do Projeto](#-estrutura-do-projeto)
4. [Como Compilar e Executar](#-como-compilar-e-executar)
5. [Demonstração e Guia de Uso](#-demonstração-e-guia-de-uso)

---

### 🚀 Funcionalidades Principais

- **Catálogo de Conteúdos:**
  - Cadastro e consulta de **Músicas** (com artista, álbum e duração).
  - Cadastro e consulta de **Podcasts** (com apresentador, número de episódio e duração).
  - Consulta de episódios por apresentador específico via busca polimórfica.
- **Modelos de Assinatura e Faturamento:**
  - **Plano Gratuito:** R$ 0,00/mês, 1 dispositivo, com exibição de anúncios.
  - **Plano Individual:** Valor fixo mensal, 1 dispositivo, sem anúncios.
  - **Plano Família:** Preço base + taxa de R$ 4,90 por membro adicional (até 6 membros), sem anúncios.
  - **Anúncios Publicitários:** Cobrança baseada em CPM (custo por mil impressões).
  - **Cálculo de Receita:** Integração em tempo real de assinaturas e veiculação de anúncios.
- **Playlists e Reprodução Inteligente:**
  - Criação de playlists mistas por usuários.
  - Intercalação automática de anúncios a cada 2 faixas para usuários de plano gratuito.
  - Usuários pagantes escutam a playlist sem interrupções publicitárias.
- **Rede Social Integrada:**
  - Sistema de seguir e deixar de seguir outros usuários.

---

### 🧠 Conceitos de POO Aplicados

O projeto foi estruturado seguindo rigorosamente as boas práticas da **Programação Orientada a Objetos**:

1. **Abstração (Classes Abstratas):**
   - `Conteudo`: Superclasse abstrata que padroniza identificação (`id`), `titulo`, `duracaoSegundos` e contador de `reproducoes`. Obriga as subclasses a implementarem `getCreditos()`.
   - `Plano` e `PlanoPago`: Definem a estrutura base de precificação e limite de dispositivos.
2. **Encapsulamento e Integridade:**
   - Atributos `private` com validações defensivas nos métodos modificadores (ex.: duração > 0, e-mail com `@`, títulos não vazios).
   - Uso de `Collections.unmodifiableList(...)` para proteger coleções internas contra mutações externas indevidas.
3. **Herança:**
   - Especialização de conteúdo: `Conteudo` $\rightarrow$ `Musica` e `Podcast`.
   - Especialização de planos: `Plano` $\rightarrow$ `PlanoGratuito` e `PlanoPago` $\rightarrow$ `PlanoIndividual` e `PlanoFamilia`.
4. **Polimorfismo e Interfaces:**
   - `Reproduzivel`: Implementada por `Conteudo` (e portanto `Musica` e `Podcast`) e por `Anuncio`. Permite que a fila de reprodução da playlist execute qualquer item chamando `reproduzir()`.
   - `Faturavel`: Implementada por `Plano` e `Anuncio`. O método `calcularReceitaMensal()` na `Plataforma` itera uniformemente sobre cobranças de planos e impressões de anúncios.
   - `Cronometravel`: Interface com método default `getDuracaoFormatada()`, convertendo segundos em formato `mm:ss`.
5. **Composição e Agregação:**
   - `Plataforma` agrega coleções de `Conteudo`, `Usuario` e `Anuncio`.
   - `Playlist` agrega itens de `Conteudo` e tem referência a um `Usuario` (dono).
   - `Usuario` é composto por um `Plano` e possui listas de referências para outros usuários que está seguindo.
6. **Membros Estáticos:**
   - Geradores de ID sequencial (`contador`) nas classes `Conteudo` e `Usuario`.

---

### 📂 Estrutura do Projeto

```text
java-streaming-platform/
├── README.md                 # Documentação bilíngue
├── .gitignore                # Arquivos ignorados pelo Git
└── src/                      # Código-fonte da aplicação
    ├── Anuncio.java          # Entidade de anúncio publicitário (Faturável e Reproduzível)
    ├── App.java              # Ponto de entrada (CLI interativo)
    ├── Conteudo.java         # Classe abstrata base para itens de áudio
    ├── Cronometravel.java    # Interface para itens com medição de tempo
    ├── Faturavel.java        # Interface de contrato financeiro
    ├── Musica.java           # Especialização de conteúdo musical
    ├── Plano.java            # Classe base de planos
    ├── PlanoFamilia.java     # Plano familiar com cálculo por membro
    ├── PlanoGratuito.java    # Plano gratuito com suporte a anúncios
    ├── PlanoIndividual.java  # Plano pago individual
    ├── PlanoPago.java        # Classe abstrata para planos tarifados
    ├── Plataforma.java       # Gerenciador central do sistema (acervo, usuários, receita)
    ├── Playlist.java         # Coleção personalizada de faixas
    ├── Podcast.java          # Especialização de conteúdo em formato podcast
    ├── Reproduzivel.java     # Interface para itens executáveis
    └── Usuario.java          # Entidade de usuário e relacionamentos
```

---

### 💻 Como Compilar e Executar

#### Pré-requisitos
- **Java JDK 17** ou superior instalado (recomendado Java 21).

#### Compilação

No terminal, a partir da raiz do repositório:

**Windows (PowerShell / Prompt de Comando):**
```powershell
javac -d bin -encoding UTF-8 (Get-ChildItem src/*.java)
```
*Ou usando o CMD tradicional:*
```cmd
javac -d bin -encoding UTF-8 src/*.java
```

**Linux / macOS (Bash):**
```bash
javac -d bin -encoding UTF-8 src/*.java
```

#### Execução

```bash
java -cp bin App
```

---

### 🎮 Demonstração e Guia de Uso

Ao iniciar a aplicação, um menu interativo com 18 opções será exibido no terminal:
- Use a **Opção 18** (`Executar demonstração completa dos requisitos`) para disparar uma demonstração automatizada de todas as regras de negócio:
  1. Cálculo de receita inicial.
  2. Execução de playlist mista com usuário gratuito (inserção de anúncios a cada 2 faixas).
  3. Atualização da receita baseada nas impressões veiculadas.
  4. Contadores de reproduções acumuladas.
  5. Filtro de episódios por apresentador.
  6. Resumos dos planos cadastrados.
- Pressione **0** a qualquer momento para sair.

---

# 🇺🇸 English

**Sonora** is a Java audio streaming platform simulation, designed to mirror core mechanics of modern streaming services like Spotify or Apple Music. The system manages multimedia content (music and podcasts), commercial advertisements, user accounts with diverse subscription tiers, intelligent playlist playback with ad insertion, and comprehensive monthly revenue calculation.

---

### 📋 Table of Contents (English)
1. [Core Features](#-core-features)
2. [OOP Concepts Applied](#-oop-concepts-applied)
3. [Project Structure](#-project-structure)
4. [Compilation and Execution](#-compilation-and-execution)
5. [Usage and Interactive Demo](#-usage-and-interactive-demo)

---

### 🚀 Core Features

- **Media Catalog Management:**
  - Register and retrieve **Music** (with artist, album, and length).
  - Register and retrieve **Podcasts** (with host name, episode number, and duration).
  - Filter podcast episodes by specific host names.
- **Subscription Plans & Monetization:**
  - **Free Plan:** \$0.00/month, single device, advertisement-supported.
  - **Individual Plan:** Fixed monthly fee, single device, ad-free.
  - **Family Plan:** Base price + \$4.90 per additional member (up to 6 members), ad-free.
  - **Advertisements:** Monetization tracked through CPM (cost per mille impressions).
  - **Platform Revenue Engine:** Real-time billing calculations aggregating subscriber fees and active ad impressions.
- **Smart Playlists & Audio Playback:**
  - Custom playlists containing mixed media types.
  - Automated ad insertion every 2 tracks for free-tier users.
  - Uninterrupted audio playback for premium subscribers.
- **Social Graph:**
  - Follow and unfollow other users on the platform.

---

### 🧠 OOP Concepts Applied

The platform is designed around fundamental **Object-Oriented Programming (OOP)** principles:

1. **Abstraction (Abstract Classes):**
   - `Conteudo`: Abstract root class standardizing `id`, `titulo`, `duracaoSegundos`, and `reproducoes`. Enforces downstream implementation of `getCreditos()`.
   - `Plano` and `PlanoPago`: Provide an extensible foundation for monetization rules and device limits.
2. **Encapsulation & Integrity:**
   - Private member fields with strict validation checks inside setter methods (e.g., duration > 0, valid email pattern, non-blank strings).
   - Immutable collection views (`Collections.unmodifiableList(...)`) prevent accidental external tampering with domain collections.
3. **Inheritance:**
   - Content domain: `Conteudo` $\rightarrow$ `Musica` and `Podcast`.
   - Subscription domain: `Plano` $\rightarrow$ `PlanoGratuito` and `PlanoPago` $\rightarrow$ `PlanoIndividual` and `PlanoFamilia`.
4. **Polymorphism & Interfaces:**
   - `Reproduzivel`: Implemented by `Conteudo` (and subclasses) as well as `Anuncio`. The playlist queue processes items polymorphically via `reproduzir()`.
   - `Faturavel`: Implemented by `Plano` and `Anuncio`. The platform calculates gross monthly revenue by treating subscriptions and ads under a common contract.
   - `Cronometravel`: Provides a default method `getDuracaoFormatada()` that formats time into `mm:ss` representation.
5. **Composition & Aggregation:**
   - `Plataforma` aggregates collections of `Conteudo`, `Usuario`, and `Anuncio`.
   - `Playlist` aggregates `Conteudo` entries and references an owning `Usuario`.
   - `Usuario` is composed with a `Plano` and tracks peer associations in a `seguindo` collection.
6. **Static Members:**
   - Thread-safe identifier sequence generators (`contador`) in `Conteudo` and `Usuario`.

---

### 📂 Project Structure

```text
java-streaming-platform/
├── README.md                 # Bilingual documentation
├── .gitignore                # Git ignore configuration
└── src/                      # Production application source code
    ├── Anuncio.java          # Ad entity (Billable & Playable)
    ├── App.java              # Main CLI application entrypoint
    ├── Conteudo.java         # Abstract base audio content class
    ├── Cronometravel.java    # Interface for timed entities
    ├── Faturavel.java        # Interface for billable entities
    ├── Musica.java           # Music specialization
    ├── Plano.java            # Abstract base subscription plan
    ├── PlanoFamilia.java     # Family plan with tiered pricing
    ├── PlanoGratuito.java    # Free tier with ad support
    ├── PlanoIndividual.java  # Individual paid plan
    ├── PlanoPago.java        # Abstract paid plan base class
    ├── Plataforma.java       # Central platform domain controller
    ├── Playlist.java         # Playlist aggregate
    ├── Podcast.java          # Podcast specialization
    ├── Reproduzivel.java     # Playable entity contract
    └── Usuario.java          # User profile and social relations
```

---

### 💻 Compilation and Execution

#### Prerequisites
- **Java JDK 17** or later (JDK 21 recommended).

#### Compile

Run the following command from the repository root:

**Windows (PowerShell):**
```powershell
javac -d bin -encoding UTF-8 (Get-ChildItem src/*.java)
```
*Or using standard Command Prompt (CMD):*
```cmd
javac -d bin -encoding UTF-8 src/*.java
```

**Linux / macOS (Bash):**
```bash
javac -d bin -encoding UTF-8 src/*.java
```

#### Run

```bash
java -cp bin App
```

---

### 🎮 Usage and Interactive Demo

When launched, the CLI provides an interactive menu with 18 options:
- Select **Option 18** (`Executar demonstração completa dos requisitos`) to run the automated end-to-end verification showcasing all business rules and mechanics:
  1. Base monthly revenue calculation.
  2. Free tier playlist playback with automated ad insertion every 2 songs.
  3. Incremental revenue calculation post-ad impression.
  4. Content playback hit counters.
  5. Host-based podcast filtering.
  6. Plan summaries.
- Enter **0** at any time to exit the program.

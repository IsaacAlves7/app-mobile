# 🐙 GitHub Stars — App Android Nativo

App Android nativo (Kotlin + Jetpack Compose) que consome a **API pública de
busca do GitHub** e mostra um ranking dos repositórios mais "impressionantes"
da plataforma — ordenados por número de estrelas (o "score" de popularidade).

Segue exatamente a trilha do seu print: **Android Studio → Gradle → Kotlin**.

## O que o app faz

- Busca repositórios com `stars:>1000`, ordenados por estrelas (desc), via
  `GET https://api.github.com/search/repositories`
- Mostra, para cada repositório: posição no ranking, avatar do dono, nome
  completo, descrição, ⭐ estrelas, 🍴 forks e linguagem principal
- Filtro por linguagem (ex.: digitar "kotlin" refina a busca para
  `stars:>1000 language:kotlin`)
- Toque no card abre o repositório no navegador
- Botão de atualizar no topo
- Estados de tela tratados de verdade: carregando, erro (sem internet, rate
  limit da API, erro genérico) e lista vazia

## Arquitetura

```
MVVM + Repository Pattern
UI (Compose) → ViewModel (StateFlow) → Repository → Retrofit → API do GitHub
```

```
app/src/main/java/com/androidexpress/githubstars/
├── MainActivity.kt
├── GitHubStarsApplication.kt
├── data/
│   ├── model/Repository.kt        # data classes (RepoItem, Owner, ...)
│   ├── remote/
│   │   ├── GitHubApiService.kt    # interface Retrofit
│   │   └── RetrofitInstance.kt    # cliente HTTP configurado
│   └── repository/GitHubRepository.kt
├── ui/
│   ├── viewmodel/
│   │   ├── RepoListViewModel.kt
│   │   └── RepoListUiState.kt     # Loading / Success / Error
│   ├── screens/RepoListScreen.kt  # tela principal
│   ├── components/RepoCard.kt     # card de cada repositório
│   └── theme/                     # Color.kt, Theme.kt, Type.kt
```

Sem framework de injeção de dependência (Hilt/Koin) de propósito — o projeto
usa singletons simples (`object`) para manter o código enxuto e fácil de ler.
Se o projeto crescer, dá pra migrar para Hilt sem grandes mudanças de
estrutura.

## Stack técnica

| Camada | Tecnologia |
|---|---|
| UI | Jetpack Compose + Material 3 |
| Rede | Retrofit 2 + OkHttp + Gson |
| Imagens | Coil (carrega o avatar do dono do repo) |
| Concorrência | Kotlin Coroutines + StateFlow |
| Linguagem | Kotlin 2.0 |
| Build | Gradle 8.7 / AGP 8.5.2 |

## Como abrir e rodar

1. Abra a pasta `GitHubStarsApp` no **Android Studio** (Koala ou mais recente).
2. Deixe o Gradle sincronizar (ele vai baixar as dependências do
   `app/build.gradle.kts` automaticamente).
3. Rode em um emulador ou dispositivo físico com **Android 8.0 (API 26)** ou superior.

Não precisa de nenhuma chave de API — o endpoint de busca do GitHub é público.
Sem autenticação, o rate limit é de **10 requisições/minuto por IP**; se você
ficar recarregando rápido demais, vai ver a mensagem de "limite atingido" no
app (é um erro tratado, não um crash).

### Quer aumentar o rate limit?

Gere um Personal Access Token em
`https://github.com/settings/tokens` (sem nenhuma permissão especial marcada)
e adicione o header de autenticação no interceptor do OkHttp, em
`RetrofitInstance.kt`:

```kotlin
.addHeader("Authorization", "Bearer SEU_TOKEN_AQUI")
```

⚠️ Nunca deixe um token hardcoded num app publicado — para produção, isso
deveria vir de um backend seu, não do cliente Android.

## Testes

Há um teste unitário de exemplo em `app/src/test/.../FormatCountTest.kt`
cobrindo a função que formata números grandes (1200 → "1.2k"). Rode com:

```bash
./gradlew test
```

## Possíveis evoluções

- Paginação (scroll infinito) usando o parâmetro `page` já exposto em `GitHubApiService`
- Cache local com Room para funcionar offline
- Tela de detalhes do repositório (README renderizado, contribuidores, issues abertas)
- Ordenar também por forks ou por atividade recente (`sort=updated`)
- Testes de UI com Compose Testing

# engenharia-de-testes Teste de Performance 3

## Tópicos

- [1 – Conceitos Fundamentais do Selenium](#1--conceitos-fundamentais-do-selenium)
- [2 - Configuração do Ambiente para Testes com Selenium WebDriver](#2--configuração-do-ambiente-para-testes-com-selenium-webdriver)
- [3 - Interação com Elementos Web](#3---interação-com-elementos-web)
- [4 - Navegação entre Páginas e Teste de Login](#4---navegação-entre-páginas-e-teste-de-login)
- [5 – Manipulação de Formulários e Componentes Web](#5--manipulação-de-formulários-e-componentes-web)
- [7 – Captura de Screenshots de Páginas e Elementos](#7--captura-de-screenshots-de-páginas-e-elementos)
- [8 – Validação de Carrinho de Compras e Checkout](#8--validação-de-carrinho-de-compras-e-checkout)
- [9 – Testes de Rolagem e Elementos Visíveis/Invisíveis](#9--testes-de-rolagem-e-elementos-visíveisinvisíveis)
---

# 1 – Conceitos Fundamentais do Selenium

O Selenium é uma das ferramentas mais utilizadas para automação de testes de aplicações web.
Ele é composto por diferentes módulos, cada um com um propósito específico dentro do ecossistema de testes automatizados.

Os três principais componentes do Selenium são: WebDriver, Grid e IDE.

### 1. Selenium WebDriver
O **Selenium WebDriver** é o componente principal do Selenium e é responsável por permitir a automação direta de navegadores reais.

Ele funciona como uma API que se comunica diretamente com o navegador (Chrome, Firefox, Edge, etc.), simulando as ações de um usuário real, como:

* Clicar em botões
* Preencher formulários
* Navegar entre páginas
* Validar textos, URLs e elementos visíveis
* Executar scripts JavaScript

O WebDriver é utilizado através de código, normalmente em linguagens como:

* Java
* Python
* JavaScript
* C#
* Ruby

**Principais características:**

* Interação real com o navegador
* Alto nível de controle sobre os testes
* Suporte a testes complexos e fluxos completos
* Integração com frameworks de teste (JUnit, TestNG, pytest, etc.)

### 2. Selenium Grid
O Selenium Grid é um componente utilizado para execução distribuída e paralela de testes.

Ele permite que os testes sejam executados:

* Em diferentes navegadores
* Em diferentes sistemas operacionais
* Em múltiplas máquinas ao mesmo tempo

O Grid funciona no modelo Hub + Nodes:

* Hub: recebe os testes e distribui para os nós disponíveis
* Nodes: máquinas ou containers que executam os testes nos navegadores configurados

Principais características:
 
* Execução paralela de testes
* Redução significativa do tempo total da suíte
* Ideal para testes de compatibilidade (cross-browser)
* Muito utilizado em pipelines de CI/CD

### 3. Selenium IDE

O Selenium IDE é uma ferramenta de automação mais simples, disponível como extensão para navegadores (Chrome e Firefox).

Ele permite gravar e reproduzir testes sem a necessidade de escrever código, utilizando uma interface gráfica.

Funcionalidades principais:

* Gravação de interações do usuário
* Reprodução dos testes gravados
* Edição básica dos passos
* Exportação dos testes para algumas linguagens e frameworks


### Vantagens e Limitações do Selenium IDE

**Vantagens**

* Fácil de usar, ideal para iniciantes
* Não exige conhecimento de programação
* Rápida criação de testes simples
* Útil para prototipação e aprendizado
* Pode servir como apoio para entender o fluxo da aplicação

**Limitações**

* Pouca flexibilidade para testes complexos
* Dificuldade de manutenção em aplicações dinâmicas
* Não é indicado para grandes suítes de testes
* Recursos limitados de lógica condicional e reutilização
* Menor integração com pipelines de CI/CD
* Testes mais frágeis em comparação ao WebDriver

---
# 2 – Configuração do Ambiente para Testes com Selenium WebDriver

Os principais passos para configurar o ambiente são:

### 1 - Instalação do Java Development Kit (JDK)
* Instalar o JDK (preferencialmente versão 11 ou superior)
* Configurar a variável de ambiente JAVA_HOME
* Verificar a instalação com o comando:

    ```java -version```

#### 2 - Escolha e Configuração da IDE
* Utilizar uma IDE como:
  * IntelliJ IDEA
  * Eclipse
  * VS Code
* Configurar a IDE para utilizar o JDK instalado

#### 3 - Criação do Projeto Java
* Criar um projeto Java ou um projeto Maven/Gradle
* Projetos com gerenciador de dependências são recomendados para facilitar a manutenção

#### 4 - Configuração do Gerenciador de Dependências
* Utilizar Maven ou Gradle
* Isso permite gerenciar versões do Selenium, drivers e bibliotecas de teste automaticamente

#### 5 - Adição das Dependências do Selenium
* Incluir a dependência do Selenium WebDriver no arquivo de configuração do projeto (`pom.xml` ou `build.gradle`)

#### 6 - Configuração do Navegador
* Instalar os navegadores que serão utilizados nos testes (Chrome, Firefox, Edge, etc.)
* Garantir compatibilidade entre navegador e driver

#### 7 - Criação de uma Classe de Teste
* Criar uma classe Java para inicializar o WebDriver
* Abrir o navegador
* Navegar para uma URL
* Encerrar o driver ao final do teste

### Importância do WebDriver Manager
O WebDriver Manager é uma biblioteca que automatiza o gerenciamento dos drivers dos navegadores utilizados pelo Selenium.

**Sem o WebDriver Manager, seria necessário:**
* Baixar manualmente o driver correto (ChromeDriver, GeckoDriver, etc.)
* Garantir compatibilidade entre driver e versão do navegador
* Configurar manualmente o caminho do driver no sistema

**Como o WebDriver Manager facilita a configuração**
* Detecta automaticamente a versão do navegador instalada
* Baixa a versão compatível do driver
* Gerencia atualizações automaticamente
* Elimina a necessidade de configurar variáveis de ambiente manualmente

Com o WebDriver Manager, a inicialização do driver se torna muito mais simples e confiável, reduzindo erros comuns de configuração.

### Passo a Passo Resumido para Instalar e Configurar o Selenium no Projeto
#### 1. Adicionar as Dependências no pom.xml do Selenium e do WebDriver Manager:
`````angular2html
<dependencies>
    <!-- Selenium WebDriver -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.x.x</version>
    </dependency>

    <!-- WebDriver Manager -->
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.x.x</version>
    </dependency>

    <!-- Framework de Teste (JUnit 5) -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.x.x</version>
        <scope>test</scope>
    </dependency>
</dependencies>
`````
#### 2. Criar a Classe Base de Testes
`````
WebDriverManager.chromedriver().setup();
WebDriver driver = new ChromeDriver();
driver.manage().window().maximize();
driver.get("https://example.com");
`````

#### 3. Executar um Teste Simples

Criar um teste simples para validar se o navegador abre corretamente e acessa uma página:

* Inicializar o WebDriver
* Navegar até uma URL
* Validar o título da página
* Encerrar o navegador com `driver.quit()`
---
# 3 - Interação com Elementos Web

O objetivo deste exercício foi implementar testes automatizados utilizando Selenium WebDriver,
explorando a interação com elementos comuns de aplicações web, como campos de texto, botões,
formulários, listas de produtos e fluxos de navegação.

Conforme proposto na atividade, foram escolhidos e implementados **dois cenários de teste**:

* **Register User** – Automação do cadastro de um novo usuário
* **Add Products in Cart** – Automação da adição de um produto ao carrinho em um e-commerce

Além da implementação dos testes, também foi realizada uma **análise** dos **principais desafios** encontrados durante
a interação com **elementos web**, como **sincronização**, **visibilidade** de elementos e **comportamento da interface**.

### Teste 1 – Registro de Usuário (Register User)

**Descrição**

Este teste automatiza o preenchimento e envio de um formulário de cadastro disponível no site DemoQA. 
O fluxo simula o comportamento de um usuário real ao inserir seus dados pessoais e submeter o formulário.

#### Etapas Automatizadas
* Acesso à página de formulário
* Preenchimento de campos de texto:
  * Primeiro nome
  * Último nome
  * Email
  * Telefone
  * Endereço
* Seleção de opções do tipo:
  * Radio button (gênero)
  * Checkbox (hobbies)
* Submissão do formulário

#### Destaques Técnicos

* Uso de métodos encadeados (fluent interface) na classe `FormPage`, tornando o teste mais legível
* Abstração das ações comuns (`preencherInput`, `setOption`, `submit`)
* Uso de `By` para localização explícita dos elementos
* Centralização da lógica de interação em uma única classe de página

### Teste 2 – Adição de Produto ao Carrinho (Add Products in Cart)

**Descrição**

**Este teste automatiza um fluxo completo de compra em um e-commerce de testes (SauceDemo), desde o login até a finalização do pedido.**
#### Etapas Automatizadas
1. Acesso à aplicação
2. Login com usuário válido
3. Validação da navegação para a página de produtos
4. Busca de um produto específico na lista
5. Validação de nome e preço do produto
6. Adição do produto ao carrinho
7. Navegação até o carrinho
8. Validação:
   * Nome do produto
   * Preço
   * Quantidade
9. Processo de checkout
10. Preenchimento dos dados do comprador
11. Finalização da compra
12. Validação da mensagem de sucesso do pedido

#### Destaques Técnicos
* Separação clara de responsabilidades:
  * `ProductsList` → lista e busca de produtos
  * `Product` → ações e dados de um produto individual
  * `Cart` e `CartItem` → informações e ações do carrinho
  * `Header` → navegação global
* Uso de assertivas para validar URL, dados do produto e mensagem final
* Reutilização da classe `FormPage` para o preenchimento de dados no checkout
* Código orientado ao “caminho feliz”, simulando um fluxo real de compra

#### Considerações sobre Quantidade de Produtos

A interface utilizada no teste não permite a alteração direta da quantidade do produto no carrinho.
No entanto, considerando um comportamento comum em sistemas de e-commerce, existem geralmente duas abordagens:

* Seleção da quantidade via `select`
* Múltiplos cliques no botão “Adicionar ao carrinho”

Nesse contexto, a lógica adotada segue o conceito de caminho feliz: cada clique adicional no botão de 
representa um incremento na quantidade do produto no carrinho. Em interfaces que oferecem um `select`, a automação poderia
ser estendida utilizando a classe `Select` do Selenium.
---
# 4 - Navegação entre Páginas e Teste de Login

### Objetivo da Atividade

O objetivo deste exercício foi automatizar cenários de login utilizando Selenium WebDriver, validando não apenas a interação com campos de formulário,
mas também a navegação entre páginas, o comportamento da aplicação após autenticação e o tratamento de erros em tentativas inválidas.

Para este exercício, foi escolhido o teste:
* **Login User with correct email and password**

Além disso, foram implementados testes adicionais para:
* **Login com usuário inválido**
* **Login com senha inválida**

Esses cenários permitem validar tanto o fluxo de sucesso quanto os fluxos de erro da aplicação.

### Cenários de Teste Implementados

#### 1. Login com Credenciais Válidas
Neste cenário, o teste simula o login de um usuário com credenciais corretas e valida se a autenticação ocorreu com sucesso.

**Validações realizadas:**
* Verificação da URL atual após o login, garantindo a navegação para a página de sucesso
* Validação do conteúdo da página:
  * Título principal (`h1`)
  * Texto de confirmação da autenticação
* Validação do **título da página (Page Title)**
* Verificação da presença e do texto do botão de **logout**
* Validação do link associado ao botão de logout

Essas verificações garantem que o login foi concluído corretamente tanto do ponto de vista de navegação quanto de conteúdo exibido ao usuário.

#### 2. Login com Usuário Inválido
Neste teste, o fluxo simula uma tentativa de login utilizando um **username incorreto**.

**Validações realizadas:**
* Preenchimento dos campos de usuário e senha
* Submissão do formulário
* Verificação da exibição da mensagem de erro
* Validação do texto do erro retornado pela aplicação
* Garantia de que os campos de input foram limpos após o erro
* Verificação de que a URL permaneceu na página de login

Esse cenário valida o comportamento esperado da aplicação em tentativas de autenticação inválidas.

#### 3. Login com Senha Inválida
De forma semelhante ao cenário anterior, este teste simula um login com **senha incorreta**.

**Validações realizadas**
* Preenchimento dos campos com usuário válido e senha inválida
* Submissão do formulário
* Verificação da mensagem de erro correspondente
* Garantia de limpeza dos campos de input
* Verificação da permanência na página de login através da URL

### Verificação de Navegação com Selenium

O Selenium permite verificar se o login ou logout ocorreu corretamente por meio da validação da URL da página atual.
Isso é feito utilizando o método:

````
driver.getCurrentUrl()
````

A URL retornada pode ser comparada com partes esperadas do caminho, garantindo que a navegação ocorreu conforme o fluxo da aplicação.
Esse tipo de validação é especialmente útil para confirmar:

* Redirecionamentos após login
* Retorno à página de login após logout
* Permanência na mesma página em casos de erro

Além da URL, também é possível validar o título da página por meio de:

````
driver.getTitle()
````

Essas duas verificações combinadas tornam o teste mais robusto, evitando falsos positivos.

### Uso de Cookies e Web Storage para Sessões
O Selenium oferece suporte ao gerenciamento de cookies, permitindo simular sessões autenticadas
sem a necessidade de realizar login repetidamente em todos os testes.

#### Cookies
Após um login bem-sucedido, é possível:
* Capturar os cookies da sessão:
````
driver.manage().getCookies();
````
* Armazená-los localmente
* Reutilizá-los em testes futuros:
````
driver.manage().addCookie(cookie);
````
Essa abordagem é útil para acelerar a execução da suíte de testes e reduzir dependências de fluxos repetitivos.

### Web Storage (LocalStorage e SessionStorage)
Além dos cookies, muitas aplicações modernas utilizam LocalStorage ou 
SessionStorage para armazenar informações de autenticação, como tokens JWT.

O Selenium pode interagir com o Web Storage utilizando JavaScriptExecutor, permitindo:

* Ler valores armazenados
* Inserir tokens manualmente
* Simular sessões autenticadas sem passar pela tela de login

Essa técnica é especialmente útil em testes de APIs integradas a frontends modernos **(SPA)**.

### Considerações sobre Qualidade e Melhorias Futuras
Embora os testes implementados cumpram corretamente os cenários propostos, ainda existem oportunidades claras de melhoria, tais como:

* Aplicação mais consistente do **Page Object Model (POM)**
* Extração de elementos e comportamentos específicos da página de login para uma classe dedicada
* Redução de código duplicado entre os cenários de teste
* Padronização das interações com formulários utilizando abstrações reutilizáveis

Essas melhorias aumentariam ainda mais a **manutenibilidade**, **escalabilidade** e **legibilidade** dos testes automatizados.

---
# 5 – Manipulação de Formulários e Componentes Web
**Objetivo da Atividade**

O objetivo deste exercício foi automatizar o preenchimento de um formulário web completo utilizando **Selenium WebDriver**, explorando a interação com
diferentes tipos de componentes comumente encontrados em aplicações web.

Para este exercício, foi escolhido um formulário disponível no site **Formy Project**, permitindo testar múltiplos tipos de campos em um único fluxo.

### Cenário de Teste Implementado
**Preenchimento de Formulário (Contact / Registration Form)**
O teste automatiza o preenchimento e envio de um formulário contendo:

* Campos de texto
* Botões de seleção (radio button e checkbox)
* Menu suspenso (dropdown)
* Campo de data (date picker)
* Submissão do formulário

Todo o fluxo é executado de forma encadeada, utilizando uma classe de abstração para centralizar a lógica de interação com os elementos.

### Estratégia de Automação Utilizada
Para manter o código organizado, reutilizável e de fácil manutenção, foi utilizada a classe 'FormPage', que encapsula os 
comuns de formulários. Essa abordagem reduz o acoplamento do teste com a estrutura HTML e melhora a legibilidade do código.

### Manipulação dos Diferentes Elementos Web
#### 1. Campos de Entrada de Texto
Para os campos de texto, foi criado o método 'preencherInput', responsável por:

* Localizar o elemento utilizando um 'By'
* Aguardar explicitamente até que o elemento esteja visível
* Limpar qualquer valor pré-existente
* Inserir o texto informado no teste

Essa abordagem garante que o elemento esteja disponível antes da interação, reduzindo falhas causadas por carregamento assíncrono da página.

### 2. Botões de Seleção (Radio Buttons e Checkboxes)
Para radio buttons e checkboxes, foi criado um método único ('setOption'), pois ambos compartilham a mesma lógica de interação:

* Localizar o elemento
* Verificar se ele já está selecionado
* Executar o clique apenas quando necessário

A decisão de reutilizar o mesmo método foi tomada porque, do ponto de vista do Selenium, a interação com esses componentes 
é idêntica, sendo feita através do método 'click()' do 'WebElement'.

Essa estratégia simplifica o código e evita duplicação desnecessária de lógica.

### 3. Dropdowns (Menus Suspensos)

Para os menus suspensos (`select`), foi utilizada a classe de suporte `Select`, disponibilizada pelo próprio Selenium.

**O processo consiste em:**

* Localizar o elemento `<select>`
* Instanciar a classe `Select` com a referência do elemento
* Utilizar o método `selectByVisibleText` para selecionar a opção desejada

Essa abordagem torna o teste mais legível e intuitivo, permitindo que a seleção seja 
feita com base no texto visível ao usuário, facilitando a escrita e manutenção dos testes.


### Sincronização e Estabilidade dos Testes

Em todas as interações, foi utilizado um mecanismo de espera explícita (`WebDriverWait`),
garantindo que os elementos estejam visíveis antes de qualquer ação.
Essa prática é essencial para evitar falhas intermitentes,
principalmente em páginas que carregam elementos de forma assíncrona.
---
# 7 – Captura de Screenshots de Páginas e Elementos

### Objetivo da Atividade

O objetivo deste exercício foi demonstrar como utilizar o Selenium WebDriver para realizar a captura de screenshots de páginas web,
com foco em validação de interface, apoio ao processo de debug e geração de evidências de testes automatizados.

Para este exercício, foi implementado um teste simples que realiza a captura da tela inteira
de um website, armazenando a imagem automaticamente no sistema de arquivos.

### Cenário de Teste Implementado

**Captura de Screenshot da Página Inteira**

O teste automatizado acessa um site público e realiza a captura da tela completa da página utilizando a interface `TakesScreenshot` fornecida pelo Selenium.

**O processo consiste em:**

* Acessar a página desejada
* Converter o `WebDriver` para `TakesScreenshot`
* Capturar a imagem da tela no formato de arquivo
* Salvar a imagem localmente com um nome único

Essa abordagem é útil para validar visualmente o estado da aplicação em um determinado momento do teste.

### Cenários em que a Captura de Screenshots é Útil
A captura de screenshots é extremamente útil em diversos contextos de automação, principalmente:

* Quando um teste falha de forma não esperada
* Para registrar o estado exato da interface no momento do erro
* Para facilitar a análise de falhas sem a necessidade de reproduzir manualmente o problema
* Como evidência de execução em ambientes de homologação ou entrega acadêmica

Ao registrar a imagem exatamente no momento da falha, o desenvolvedor consegue identificar rapidamente problemas de layout,
elementos ausentes, mensagens incorretas ou comportamentos inesperados da interface.

### Salvando Screenshots no Sistema de Arquivos
No teste implementado, as imagens são salvas automaticamente no sistema de arquivos utilizando a classe utilitária `FileUtils`.

**Estratégia Utilizada**

* As imagens são copiadas para uma pasta chamada `screenshots`
* A pasta fica localizada fora do diretório `src`, mantendo o código separado das evidências
* Caso a pasta não exista, ela é criada automaticamente
* O nome do arquivo utiliza `System.currentTimeMillis()` para:
  * Evitar sobrescrita de imagens
  * Facilitar a identificação do momento em que o screenshot foi capturado

### Captura de Screenshots para Relatórios de Teste
Além do armazenamento local, screenshots podem ser facilmente integrados a relatórios de testes **automatizados**, como:

* Relatórios gerados por frameworks de teste
* Ferramentas de CI/CD
* Relatórios personalizados em HTML

Normalmente, a captura pode ser configurada para ocorrer automaticamente em casos de falha, tornando o processo de análise mais rápido e confiável.

---
# 8 – Validação de Carrinho de Compras e Checkout

### Objetivo da Atividade

O objetivo deste exercício foi validar, por meio de automação com Selenium WebDriver, o correto 
funcionamento do carrinho de compras e do fluxo de checkout em um cenário de e-commerce.

Para este exercício, foi reutilizado o teste de Add Products in Cart implementado anteriormente, fazendo uso
de todas as classes responsáveis pelo carrinho, produtos e checkout, garantindo consistência e reaproveitamento de código.

#### Resumo do que Foi Implementado
O fluxo automatizado cobre todo o caminho principal de compra (“caminho feliz”), incluindo:

* Login do usuário no sistema
* Listagem de produtos disponíveis
* Busca de um produto específico pelo nome
* Validação das informações do produto (nome e preço)
* Adição do produto ao carrinho
* Navegação até o carrinho
* Validação dos dados exibidos no carrinho
* Processo de checkout
* Finalização do pedido
* Validação da mensagem de sucesso da compra

Todo o fluxo foi estruturado com classes específicas para cada responsabilidade, como produto,
lista de produtos, carrinho, item do carrinho e cabeçalho de navegação.

### Validação dos Produtos no Carrinho
A validação de que os produtos foram corretamente adicionados ao carrinho foi realizada por 
meio de verificações diretas dos dados exibidos na interface do usuário.

Os seguintes pontos foram validados:
* **Nome do produto:** conferindo se o item presente no carrinho corresponde ao produto selecionado anteriormente
* **Preço do produto:** garantindo que o valor exibido no carrinho seja o mesmo informado na listagem de produtos
* **Quantidade do produto:** verificando se a quantidade apresentada no carrinho está correta após a adição

Essas validações garantem que o carrinho esteja refletindo corretamente as ações realizadas pelo usuário durante a navegação no e-commerce.

### Validação da Quantidade de Produtos

A quantidade do produto no carrinho foi validada através da leitura direta do valor exibido no componente de quantidade.

Embora a interface utilizada no teste não permita a alteração direta da quantidade do produto, o comportamento observado segue
uma regra comum de e-commerce: cada clique no botão de “Adicionar ao carrinho” representa um incremento na quantidade do item.

Essa validação assegura que o sistema mantém a consistência entre a ação do usuário e a informação apresentada no carrinho.

### Validação do Checkout e Finalização da Compra
Após a validação do carrinho, o fluxo segue para o processo de checkout, onde:

* Os dados do comprador são preenchidos
* O pedido é finalizado
* A navegação para a página de confirmação é validada
* A mensagem de sucesso é conferida

Esses passos garantem que o pedido foi processado corretamente do início ao fim.

### Rolagem Automática da Página (Scroll)
Em cenários onde os elementos do carrinho ou do checkout não estão imediatamente visíveis na tela, o Selenium 
pode utilizar rolagem automática para permitir a interação com esses elementos.

Essa rolagem pode ser feita com o uso de JavaScriptExecutor, permitindo que o teste:

* Role a página até o elemento desejado
* Aguarde o elemento se tornar visível e clicável
* Execute a ação somente quando o elemento estiver realmente disponível

A utilização de scroll automático, em conjunto com esperas explícitas, aumenta
a estabilidade dos testes e reduz falhas causadas por elementos fora da área visível da tela.

---
# 9 – Testes de Rolagem e Elementos Visíveis/Invisíveis

### Objetivo da Atividade

O objetivo deste exercício foi demonstrar como utilizar o Selenium WebDriver para lidar com páginas que exigem rolagem (scroll)
para que determinados elementos se tornem visíveis e interativos.

Foi implementado um teste que localiza um elemento presente no DOM, realiza a rolagem automática da página até esse elemento e,
somente após ele estar visível e clicável, executa a ação de clique.

### Cenário de Teste Implementado

**Rolagem da Página e Interação com Elemento Inicialmente Invisível**

* O teste automatizado realiza as seguintes etapas:
* Acesso à página de cursos do site Practice Test Automation
* Localização de um link que inicialmente não está visível na tela
* Rolagem automática da página até o elemento desejado
* Espera explícita até que o elemento esteja visível e clicável
* Interação com o elemento (clique)

Esse fluxo simula um comportamento comum do usuário, que precisa rolar a página para acessar conteúdos posicionados fora da área visível inicial.

### Diferença entre Presença e Visibilidade de um Elemento

Em automação com Selenium, é fundamental compreender a diferença entre presença e visibilidade de um elemento:

* **Presença no DOM:**
Um elemento está presente quando ele existe na estrutura HTML da página, mesmo que não esteja visível para o usuário.

* **Visibilidade na tela:**
Um elemento é considerado visível quando está renderizado e dentro da área visível da página, podendo ser percebido e interagido pelo usuário.

No teste implementado, o elemento é inicialmente apenas presente no DOM, mas não visível. Por isso,
a automação primeiro aguarda sua presença e depois realiza a rolagem para torná-lo visível.

Essa distinção impacta diretamente os testes automatizados, pois tentar interagir com um elemento apenas
presente, mas não visível, pode resultar em falhas de execução.

### Uso de Rolagem Automática (Scroll) no Selenium

Para resolver o problema de interação com elementos fora da área visível da tela, foi utilizado o JavaScriptExecutor,
que permite executar scripts JavaScript diretamente no navegador.

A função `scrollIntoView` foi utilizada para:

* Rolar automaticamente a página até o elemento desejado
* Garantir que o elemento esteja visível antes da interação
* Tornar o teste mais estável e previsível

Após a rolagem, foi aplicada uma espera explícita para garantir que o elemento estivesse clicável, evitando problemas de sincronização.

---

# Estrutura e Arquitetura dos Testes

Durante o desenvolvimento, foi adotada a abordagem **Page Object Model (POM)**, buscando ao máximo: ()

- Definir responsabilidades únicas para cada classe
- Abstrair elementos e comportamentos da interface para classes específicas
- Reduzir acoplamento entre os testes e a estrutura HTML
- Facilitar manutenção, legibilidade e reutilização do código

Cada etapa do fluxo (formulários, cabeçalho, carrinho, lista de produtos, itens individuais) foi
representada por uma classe própria, tornando os testes mais expressivos e próximos da regra de negócio.

Link pra documentação do Selenium que fala justamente sobre **POM** com exemplos =)
(https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

---
# Desafios Encontrados na Automação

Durante o desenvolvimento dos testes, alguns desafios comuns em automação web foram identificados:

### 1. Sincronização e Tempo de Carregamento

Nem todos os elementos ficam disponíveis imediatamente após o carregamento da página. Para mitigar esse problema, foi utilizado:

* `WebDriverWait`
* `ExpectedConditions`

Foi observado que tempos muito baixos de espera (por exemplo, 10 segundos) podem ser insuficientes dependendo do site,
tornando necessário ajustar esse valor conforme o contexto.

### 2. Visibilidade e Interação com Elementos

Alguns elementos podem não estar visíveis na tela no momento da interação. Para esses casos, uma solução viável é:

* Utilizar `JavaScriptExecutor` para rolar a página
* Aguardar explicitamente até que o elemento esteja clicável
* Esse cuidado garante maior estabilidade e reduz falhas intermitentes nos testes.

### 3. Manutenção e Acoplamento

Para evitar testes frágeis e difíceis de manter, foi priorizada a criação de classes com responsabilidade única,
abstraindo os detalhes da interface para componentes reutilizáveis. Isso reduz o impacto de mudanças no HTML e melhora a organização do projeto.

---

# Conclusão

A implementação dos testes demonstrou, na prática, como o Selenium WebDriver pode ser utilizado para automatizar
fluxos reais de aplicações web, abrangendo desde interações simples com formulários até cenários mais completos,
como autenticação, navegação entre páginas, validação de carrinho de compras, checkout e manipulação de elementos
dinâmicos da interface.

Ao longo do desenvolvimento, desafios comuns da automação web, como sincronização, visibilidade de elementos,
rolagem de página e estabilidade dos testes, foram tratados por meio do uso de esperas explícitas, validações
de estado e execução de scripts JavaScript quando necessário.

A adoção do Page Object Model, aliada à definição clara de responsabilidades e à abstração dos componentes da
interface, contribuiu para a construção de testes mais legíveis, desacoplados e fáceis de manter, refletindo
boas práticas amplamente recomendadas na engenharia de testes automatizados.
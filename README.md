# engenharia-de-testes Teste de Performance 2

## Tópicos

- [1 - Exercício: Conceituação de testes baseados em propriedades](#1--conceituação-de-testes-baseados-em-propriedades)
- [2 - Exercício: Testes Baseados em Propriedades com Jqwik](#2--Testes-Baseados-em-Propriedades-com-Jqwik)
- [3 - Exercício: Isolamento de Classe com Objetos Simulados (Mocks)](#3--Isolament-de-Classe-com-Objetos-Simulados-Mocks)
- [4 - Exercício: Testes CRUD com Partição de Equivalência e Análise de Valor Limite](#4--Testes-CRUD-com-Partição-de-Equivalência-e-Análise-de-Valor-Limite)
---

# 1- Conceituação de testes baseados em propriedades

Testes baseados em propriedades (property-based testing) são uma abordagem de teste em que, em vez de escrever um pequeno conjunto de casos de entrada/saída concretos, você descreve propriedades gerais que a função/sistema deve satisfazer para todas (ou para muitas) entradas possíveis. O framework gera automaticamente muitas entradas (usando generators / arbitraries), executa a função e verifica se a propriedade se mantém. Se uma entrada falhar, o framework normalmente tenta reduzir (shrinking) o contraexemplo para um caso mínimo que ainda falha — isto ajuda muito no diagnóstico.
Principais vantagens

## Principais vantagens
- **Cobertura maior e automática** — gera centenas ou milhares de entradas variando tamanhos, negativos, zeros, strings vazias etc.

- **Encontra edge-cases sutis** — encontra combinações incomuns de parâmetros que você não pensou.

- **Shrinking** — apresenta um contraexemplo mínimo (mais fácil de debugar).

- **Menos especificidade de teste** — descreve comportamento geral em vez de muitos asserts por caso.

- **Ajuda a formular especificações** — forçar você a pensar em propriedades formais (invariantes) do código.

## Limitações
- Precisa definir boas propriedades, pois propriedades fracas geram testes fracos.

- Pode ser não-trivial gerar dados válidos (Ex: objetos com invariantes complexos), então precisa criar generators customizados.

- Resultados podem ser inicialmente não determinísticos, mas frameworks permitem fixar semente (seed) para reprodução.

- Para ações com efeitos colaterais I/O estado complexo, aplicar PBT exige cuidado.

---

# 2- Testes Baseados em Propriedades com Jqwik
Neste exercício, o objetivo foi aplicar testes baseados em **propriedades** (Property-Based-Testing), conhecido como PBT, utilizando o framework **Jqwik**, garantindo que a função **multiplyByTwo** da classe **MathFunctions** respeite características matemáticas fundamentais. Diferentemente de testes unitários tradicionais, que validam entradas específicas, aqui definimos propriedades gerais que devem ser verdadeiras para uma grande variedade de valores gerados automaticamente.


### Propriedades deste exercício:
- **O dobro de um número deve ser maior ou igual ao próprio número ``2n ≥ n``** 
  -  Esse teste foi implementado usando um gerador de inteiros dentro de um intervalo seguro **(MAX_RANGE_MULTIPLY_BY_TWO)** para evitar armazenar numeros **maiores** que a **capacidade** do tipo primitivo **inteiro**, pois o framework gera utiliza valores das **extremidades** para testar.
---
- **O dobro de um número par deve continuar sendo par**
  - A segunda propriedade verificou uma característica matemática essencial:
    se a **entrada** ``x`` é **par**, então ``multiplyByTwo(x)`` também deverá ser **par**.
    Para isso, foi criado um **gerador personalizado** chamado **gerarPares**, definido com a anotação ``@Provide``. Esse gerador
    produz números inteiros dentro de um intervalo seguro;
    transforma esses valores em números certamente pares ao multiplicá-los por 2.
    Assim, o teste recebe exclusivamente **números pares** e verifica se o resultado permanece par utilizando o método auxiliar ``isEven``.


**Anotações utilizadas nas propriedades:** ``@ForAll`` | ``@IntRange`` | ``@Provide``

---

# 3 - Isolamento de Classe com Objetos Simulados (Mocks)
Neste exercício, o objetivo foi testar a classe ``OrderService`` de forma **isolada**, garantindo que sua dependência externa (``PaymentProcessor``) não influenciasse na lógica de negócio durante os testes. Para isso, foram utilizados **JUnit 5** e **Mockito**, permitindo a criação de um mock que simula o comportamento do processador de pagamentos.

**O foco dos testes foi verificar**     
- se a classe ``OrderService`` reage corretamente aos diferentes cenários de pagamento
- se o método da dependência mockada é invocado corretamente
- se o retorno do método ``processOrder`` condiz com o cenário simulado.

---

### A classe OrderService deve:
1. chamar o método `processPayment` da dependência `PaymentProcessor`
2. retornar **true** quando o pagamento é **aprovado**
3. retornar **false** quando o pagamento é **recusado**

**Como PaymentProcessor é apenas uma interface, Mockito foi utilizado para criar um mock que simula suas respostas.**

### Cenários Implementados

1. **Pagamento aprovado**
   - o mock do `PaymentProcessor` é configurado para retornar **true** ao receber o valor **100.0**
   - espera-se que `OrderService.processOrder(100.0)` retorne **true**
   - verifica-se que o método `processPayment` foi chamado exatamente uma vez com o valor correto
   
2. **Pagamento recusado**
   - o mock é configurado para retornar false
   - o método `processOrder(100.0)` deve retornar **false**
   - novamente é verificado que o método do mock foi chamado corretamente

### Uso de Mockito no Teste
Para simular o comportamento do processador de pagamentos, foram utilizados:
- ``@Mock`` — para criar o objeto simulado da interface ``PaymentProcessor``
- ``@ExtendWith(MockitoExtension.class)`` — para ativar as funcionalidades do **Mockito** no **JUnit 5**
- ``when(...).thenReturn(...)`` — para definir o comportamento esperado do mock
-  ``verify(...)`` — para garantir que os **métodos** do mock foram chamados corretamente

---

# 4 - Testes CRUD com Partição de Equivalência e Análise de Valor Limit

Neste exercício, o objetivo foi validar as operações do serviço ``CustomerService`` aplicando as técnicas de **Partição de Equivalência** e **Análise de Valor Limite**, garantindo que todo o comportamento relacionado ao cadastro, atualização e exclusão de clientes esteja conforme as regras definidas.

A classe ``CustomerService`` implementa regras como:

- idade válida entre **18** e **99 anos**
- e-mail com formato válido
- apenas clientes **ativos** podem ser atualizados ou excluídos

Para isso, foram criados dois tipos de testes:

- **CustomerServiceParticaoTest** → focado em partição de equivalência
- **CustomerServiceLimiteTest** → focado em valores limites (boundary analysis)
---
### Partição de Equivalência
A técnica de **Partição de Equivalência** consiste em dividir o espaço de entradas em grupos (partições) que devem ter o mesmo comportamento esperado. Assim, testando apenas um elemento de cada partição, garantimos boa cobertura.

No contexto da classe ``CustomerService``, as partições principais são:

- **Idade válida (18–99)**
- **Idade inválida (<18 ou >99)**
- **Cliente ativo / inativo**
- **E-mail válido / inválido**
- **Operações CRUD permitidas / não permitidas**

Essas partições foram aplicadas nos testes a seguir.

---
### Análise de Valor Limite (Boundary Analysis)
Para o caso da idade, foram testados os limites imediatamente próximos ao intervalo permitido:
- Valores válidos: **18 e 99**
- Valores imediatamente inválidos: **17 e 100**

Essa técnica garante que falhas nos extremos da regra de negócio sejam detectadas.

---
### Implementação dos Testes

#### 1. **Testes de Cadastro com Idade Válida e Inválida** 
Foram criados testes que validam:
- idade **mínima** e **máxima inválidas** (partição de equivalência)
- valores **limites válidos** e **inválidos** (boundary analysis)

**Exemplos:**
- **10, 150** → inválidos (fora da partição válida)
- **17, 100** → inválidos (limites proibidos)
- **18, 99** → válidos (limites permitidos)
 
Os testes utilizam ``assertThrows`` para verificar se a exceção adequada é lançada.

---
### 2. Testes de Atualização de Cliente Ativo e Inativo

Para garantir o comportamento correto do método ``updateCustomer``, foram criados:

- um teste garantindo que cliente ativo pode ser atualizado
- um teste garantindo que cliente inativo **NÃO** pode ser atualizado, lançando ``ValidationException``
 
Esses testes confirmam que a partição **“ativo”** e **“inativo”** é tratada corretamente pela regra de negócio

---
### 3. Testes de Exclusão de Cliente Ativo e Inativo
Similar à atualização, foram testadas as partições:

- **cliente ativo** → operação permitida
- **cliente inativo** → operação proibida

O método ``deleteCustomer`` lança exceção quando o cliente está inativo, e os testes validam esse comportamento.

---

### 4. Testes de Validação de E-mail

Usando partição de equivalência, foram testados:

- **e-mail válido**
- **e-mail sem @**
- **e-mail sem domínio**

A implementação lança uma exceção específica ``EmailValidation`` para entradas inválidas, e os testes verificam essa lógica.

---

### 5. Teste Completo de Cadastro Válido

Foi criado um teste que garante que um cliente com todos os dados corretos é cadastrado com sucesso:

- **idade válida**
- **e-mail válido**
- **cliente ativo**
 
Esse teste verifica todo o fluxo de cadastro sem erros.

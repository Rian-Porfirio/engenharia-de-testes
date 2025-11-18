# engenharia-de-testes Teste de Performance 1

## Tópicos

#### Parte 1
- [Teste Exploratório](#1--teste-exploratório)
- [Identificação de Problemas](#2--identificação-de-problemas)
- [Especificação do Comportamento Esperado](#3--especificação-do-comportamento-esperado)
- [Casos de Teste por Partições Equivalentes](#4--casos-de-teste-por-partições-equivalentes)
- [Análise de Limites](#5--Análise-de-Limites)

#### Parte 2
- [O que são Testes Baseados em Propriedades](#1--o-que-são-testes-baseados-em-propriedades)
- [Criando Testes Baseados em Propriedades com Jqwik](#2--criando-testes-baseados-em-propriedades-com-jqwik)
- [Gerando Conjuntos Diversificados de Dados (Geradores Personalizados)](#3--gerando-conjuntos-diversificados-de-dados-geradores-personalizados)
- [Analisando Contraprovações Geradas pelo Jqwik](#4--analisando-contraprovações-geradas-pelo-jqwik)
- [Isolando Dependências com Mocks (Mockito)](#5--isolando-dependências-com-mocks-mockito)
- [Testes Baseados em Propriedades para Valores Específicos](#6--testes-baseados-em-propriedades-para-valores-específicos)
---

# 1- Teste Exploratório
Durante a execução do programa em cenários variados (valores normais, extremos e inválidos), foi possível observar que:

- As instruções apresentadas ao usuário são claras.
- A interação com o programa é intuitiva.
- O sistema não lida corretamente com entradas inválidas, o que afeta a experiência geral.

--- 

# 2- Identificação de problemas
Ao realizar os testes exploratórios, foram identificados os seguintes problemas:

#### Problemas Funcionais
- O sistema não trata o tipo da entrada, o que causa falhas quando o usuário insere valores não numéricos.
Prioridade: Altíssima.

- O sistema não trata a inserção de vírgulas como separador decimal, resultando em erro e interrupção do programa.
Prioridade: Altíssima.

- O sistema aceita valores negativos para peso e altura, causando comportamento incorreto ou falhas.
Prioridade: Altíssima.

#### Problemas de Usabilidade
- Há um erro de digitação relacionado ao campo de altura.
Prioridade: Baixa.

---

# 3- Especificação do comportamento esperado
O programa deveria operar da seguinte forma:

#### Entrada de dados
- O sistema deve aceitar apenas valores numéricos para peso e altura.
- O programa deve ser capaz de interpretar corretamente tanto ponto quanto vírgula como separador decimal, considerando o padrão brasileiro.
- Valores negativos não devem ser aceitos. Caso inseridos, o usuário deve ser informado claramente.

#### Saída e Tratamento de Erros
- Quando um valor inválido for informado (letras, símbolos, strings não numéricas), o sistema deve exibir uma mensagem de erro clara informando que o valor inserido não é um número válido.
- O programa não deve encerrar sua execução devido a erros de entrada. Em vez disso, deve solicitar novamente os valores corretos.
- Em caso de uso de vírgula como separador decimal, o sistema deve converter o valor para o formato adequado antes de realizar os cálculos.
- Em qualquer situação inválida (valor negativo, entrada não numérica, formato incorreto), o programa deve orientar o usuário sobre como corrigir o problema.

---

# 4- Casos de Teste por Partições Equivalentes

Os testes abaixo foram criados considerando três categorias de entradas:

- Entradas válidas
- Entradas inválidas
- Entradas limítrofes (também antecipando parte do exercício 5)

Cada método é explicado individualmente.

#### 4.1 Entradas válidas

##### ```testeEntradaValidaSaudavel()```
- Testa valores normais dentro dos padrões fisiológicos.
- Peso e altura estão dentro da faixa esperada e resultam em um IMC classificado como “Saudável”.
O objetivo é verificar se o cálculo e a classificação são corretos para entradas típicas.

##### ```testeEntradaValidaMagrezaLeve()```

- Testa uma altura negativa.
- Assim como o caso anterior, verifica se o sistema trata valores impossíveis.
Pertence à partição de entradas inválidas.

##### ```testeEntradaValidaSobrepeso()```

- Verifica a classificação “Sobrepeso”.
- O objetivo é garantir que, para valores de IMC entre 25.0 e 30.0, o método retorne a categoria correta.


#### 4.2 Entradas Inválidas

##### ```testePesoNegativo()```
- Testa um peso negativo, que deveria ser rejeitado.
- Como o código atual não faz validação, o teste espera uma exceção (IllegalArgumentException) caso a aplicação seja corrigida.
Essa entrada pertence à partição de valores proibidos.


##### ```testeAlturaNegativa()```
- Testa uma altura negativa.
- Assim como o caso anterior, verifica se o sistema trata valores impossíveis.
Pertence à partição de entradas inválidas.

##### ```testeEntradaComLetra()```

- Testa a conversão de string que contém letras em número.
- Espera-se NumberFormatException.
Este teste valida que entradas não numéricas não devem ser aceitas.

##### ```testeEntradaComCaracteresEspeciais()```

- Similar ao caso anterior, testando caracteres especiais.
- Também espera NumberFormatException, reforçando a necessidade de validação de entrada.

##### ```testeEntradaComVirgula()```
- Testa um caso comum no Brasil: números com vírgula.
- O código atual não trata esse caso e quebra.
- O teste verifica exatamente esse comportamento incorreto, preparando terreno para futura correção

# 5- Análise de Limites
A análise de limites foca nos valores extremos do domínio permitido para peso e altura.
Esses testes são fundamentais porque erros são comuns nas bordas das faixas de valores.

A seguir, cada teste é explicado conforme sua relação com limites inferiores ou superiores.

##### ```testePesoZero()```
- Avalia o comportamento do sistema quando o peso é igual a zero.
Esse é o limite inferior absoluto e deveria ser considerado inválido, mas o código atual aceita e retorna IMC = 0.
- O teste documenta esse comportamento, indicando necessidade de validação.

##### ```testeAlturaZero()```
- Testa o limite inferior da altura.
- Valor igual a zero causa divisão por zero, quebrando o programa.
- O teste confirma que uma exceção é lançada.
Trata-se do limite mais crítico no domínio das entradas.

##### ```testePesoExtremo()```
- Testa um peso muito elevado (300 kg).
- Esse valor está em um limite superior extremo, mas ainda possível fisiologicamente.
- O objetivo é verificar se o cálculo permanece estável, mesmo com valores altos

##### ```testeAlturaExtremamenteBaixa()```
- Testa uma altura extremamente baixa (0.50 m).
- Apesar de raro, o valor é válido e deve ser aceitável.
- O teste confirma se o cálculo funciona e gera um IMC coerente.
- É um limite inferior fisiológico, não matemático.

---

# Parte 2

#### 1- O que são Testes Baseados em Propriedades

Testes baseados em propriedades (Property-Based Testing — PBT) são uma abordagem em que não testamos apenas entradas e saídas específicas, como nos testes tradicionais.
Em vez disso, descrevemos propriedades gerais que o software SEMPRE deve cumprir, independente dos valores usados na entrada.

Ou seja:

No teste tradicional, escrevemos:
-> “Se peso = 80 e altura = 1.80, IMC deve ser X”.

No PBT, escrevemos:
-> “O IMC nunca pode ser negativo — para qualquer peso e altura positivos”.

O framework (ex: Jqwik) então gera AUTOMATICAMENTE dezenas ou centenas de combinações possíveis de valores para tentar violar essa propriedade.

#### Vantagens dos Testes Baseados em Propriedades

1. Geração automática de dados. Odesenvolvedor não precisa escrever diversos cenários manualmente. O Jqwik cria entradas aleatórias e até extremas (valores enormes, minúsculos, negativos etc.).

2. Identificação de casos inesperados. Como os testes exploram entradas que o desenvolvedor não imaginou, fica mais fácil encontrar bugs escondidos.

3. Maior cobertura com menos código. Um único teste pode validar centenas de casos diferentes.

4. Descoberta automática do menor contraexemplo

Quando uma propriedade falha, o framework reduz a entrada até achar o menor exemplo que causa erro, ajudando a entender rapidamente o problema.

#### Exemplo prático de propriedade no cálculo de IMC

Uma propriedade importante é:

O IMC nunca deve ser negativo, independentemente dos valores positivos de peso e altura.

Matematicamente,
IMC = peso / (altura²)
Se peso > 0 e altura > 0 → IMC sempre ≥ 0.

Portanto, essa propriedade pode ser testada facilmente com Jqwik.

# 2- Criando Testes Baseados em Propriedades com Jqwik

- ```@Property```: indica que é um teste baseado em propriedade

- ```@ForAll```: gera automaticamente valores aleatórios

- ```@Positive```: garante que os valores usados são > 0

```
class IMCTest {
    @Property
    void imcNuncaDeveSerNegativo(@ForAll @Positive double peso, @ForAll @Positive double altura) {
        double imc = peso / (altura * altura);
        assertThat(imc).isGreaterThanOrEqualTo(0);
    }
}
```

#### O que esse teste garante?
- Ele prova que para qualquer peso positivo e qualquer altura positiva,
o IMC não será negativo.
- O Jqwik vai testar dezenas de combinações automaticamente.

# 3- Gerando Conjuntos Diversificados de Dados (Geradores Personalizados)
Neste exercício você cria geradores próprios, permitindo testar cenários incomuns, extremos ou improváveis — por exemplo:

- Alturas muito baixas (0.5m)

- Alturas irrealisticamente altas (5m)

- Pesos extremamente grandes

#### Por que isso é útil?

Bug reais costumam aparecer em valores fora do comportamento normal.

Exemplo: 

```
class CustomGenerators {
    @Provide
    Arbitrary<Double> alturasExtremas() {
        return Arbitraries.of(0.5, 3.0, 5.0);  // Valores incomuns de altura
    }
}
```

Uso:

```
@Property
void testarIMCComAlturasExtremas(@ForAll("alturasExtremas") double altura, @ForAll @Positive double peso) {
    double imc = peso / (altura * altura);
    assertThat(imc).isGreaterThan(0);
}
```

# 4- Analisando Contraprovações Geradas pelo Jqwik
Aqui você roda um teste sem restrições, permitindo inclusive valores inválidos.
O objetivo é observar como o Jqwik encontra contraexemplos.

#### teste sem restrições

```
void testIMCComValoresAleatorios(@ForAll double peso, @ForAll double altura) {
    double imc = peso / (altura * altura);
    assertThat(imc).isGreaterThanOrEqualTo(0);
}
```

#### Possível falha gerada automaticamente:

Exemplo de contraexemplo encontrado:

peso = 10

altura = 0

#### Por que ocorre?

- Divisão por zero é inválida

- Altura negativa também geraria problemas
 
- Valores NaN violam a propriedade definida

#### O que se aprende?

- Esse teste ajuda a identificar:

- Falhas de validação

- Necessidade de regras de negócio

- Comportamentos inesperados

# 5- Isolando Dependências com Mocks (Mockito)
Agora o foco é evitar acessar banco de dados, APIs ou sistemas externos.
Mocks são objetos falsos criados para substituir dependências reais.

#### Quando usar mocks?

- Quando a classe depende de algo externo (DB, API, arquivo)
- Quando só queremos testar a lógica local
- Quando queremos simular respostas específicas

Exemplo: 

```
class IMCServiceTest {

    @Test
    void testCalculoIMCComMock() {
        IMCService imcService = mock(IMCService.class);
        when(imcService.calcularIMC(80, 1.80)).thenReturn(24.69);

        double imc = imcService.calcularIMC(80, 1.80);
        assertThat(imc).isEqualTo(24.69);
    }
}
```

# 6- Testes Baseados em Propriedades para Valores Específicos
Aqui você combina:

- Testes baseados em propriedades

- Exemplos específicos conhecidos.
A anotação ```@Example``` permite definir casos exatos dentro de um teste baseado em propriedade.

Exemplo:

```
@Property
void testIMCComCasosEspecificos(@Example double peso, @Example double altura) {
    double imc = peso / (altura * altura);
    assertThat(imc).isBetween(10.0, 50.0);
}
````

#### Por que escolher valores específicos?

- Validar casos de fronteira
- Testar situações clínicas comuns

- Validar comportamento realista:
  - IMC < 10 é incompatível com vida
  - IMC ≥ 50 é obesidade mórbida severa

Esses limites tornam o teste significativo e relacionado ao mundo real.
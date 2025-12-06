# engenharia-de-testes Teste de Performance 2

## Tópicos

- [Conceituação de testes baseados em propriedades](#1--Conceituação-de-testes-baseados-em-propriedades)
---

# 1- Conceituação de testes baseados em propriedades

Testes baseados em propriedades (property-based testing) são uma abordagem de teste em que, em vez de escrever um pequeno conjunto de casos de entrada/saída concretos, você descreve propriedades gerais que a função/sistema deve satisfazer para todas (ou para muitas) entradas possíveis. O framework gera automaticamente muitas entradas (usando generators / arbitraries), executa a função e verifica se a propriedade se mantém. Se uma entrada falhar, o framework normalmente tenta reduzir (shrinking) o contraexemplo para um caso mínimo que ainda falha — isto ajuda muito no diagnóstico.
Principais vantagens

## Principais vantagens
- Cobertura maior e automática — gera centenas ou milhares de entradas variando tamanhos, negativos, zeros, strings vazias etc.

- Encontra edge-cases sutis — encontra combinações incomuns de parâmetros que você não pensou.

- Shrinking — apresenta um contraexemplo mínimo (mais fácil de debugar).

- Menos especificidade de teste — descreve comportamento geral em vez de muitos asserts por caso.

- Ajuda a formular especificações — forçar você a pensar em propriedades formais (invariantes) do código.

## Limitações
- Precisa definir boas propriedades, pois propriedades fracas geram testes fracos.

- Pode ser não-trivial gerar dados válidos (Ex: objetos com invariantes complexos) — então precisa criar generators customizados.

- Resultados podem ser inicialmente não determinísticos — mas frameworks permitem fixar semente (seed) para reprodução.

- Para ações com efeitos colaterais I/O estado complexo, aplicar PBT exige cuidado.

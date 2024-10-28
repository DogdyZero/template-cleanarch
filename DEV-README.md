# Guia para Desenvolvedores - TechChallenge Restaurante

Este documento serve como um guia detalhado para desenvolvedores no projeto TechChallenge Restaurante, que é estruturado seguindo Domain-Driven Design (DDD) e arquitetura hexagonal.

## Estrutura de Diretórios e Responsabilidades

A estrutura do projeto é dividida em três módulos principais:

### Domain
Este módulo é o núcleo do sistema, contendo a lógica de negócios e as entidades.

#### Model
Contém entidades e objetos de valor que modelam o domínio do problema.

**Exemplo:** `Cliente.java`, `Pedido.java`
```java
package com.fiap.techchallenge.model;

public class Pedido {
    private Long id;
    private List<ItemPedido> itens;
    private Double total;
    // Métodos
}
```

#### Services
**Definição:**
Domain Services contêm lógica de negócios que não pertence naturalmente a nenhuma entidade ou valor objeto. Estes serviços ajudam a manter as classes do modelo de domínio (entidades e objetos de valor) focadas em suas responsabilidades primárias, aderindo assim ao princípio de responsabilidade única.

**Características:**
- Operam sobre entidades e objetos de valor do domínio.
- Implementam regras de negócios que são complexas e/ou envolvem múltiplas entidades.
- Não têm estado próprio (stateless).
- São isolados e não dependem de detalhes de infraestrutura ou de aplicação, como persistência de dados, UI, etc.

**Exemplo:**
Um `PedidoService` no domínio pode ser responsável por validar a composição de um pedido, verificando regras de negócios complexas, como disponibilidade de estoque e restrições de combinação de produtos.

```java
// Domain service example
package com.fiap.techchallenge.domain.services;

public class PedidoService {
    public void validarPedido(Pedido pedido) throws ValidationException {
        // Validações específicas do negócio
    }
}
```

#### Exceptions
Exceções personalizadas que fazem sentido dentro do contexto do domínio.

**Exemplo:** `PedidoNotFoundException.java`
```java
package com.fiap.techchallenge.exceptions;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException(String message) {
        super(message);
    }
}
```

### Application
Orquestra as operações de alto nível, contendo lógica de aplicação que coordena atividades do domínio.

#### Ports
Define interfaces para comunicação entre a camada de aplicação e o domínio ou serviços externos.

**Incoming (Ports de Entrada):**
Representam os métodos pelos quais os serviços externos ou a interface do usuário podem solicitar ações do sistema. Eles são a "porta de entrada" para o domínio, permitindo que operações sejam invocadas de fora.

```java
package com.fiap.techchallenge.ports.incoming;

public interface PedidoPort {
    void criarPedido(Pedido pedido);
}
```

**Outgoing (Ports de Saída):**
Representam as dependências que o domínio tem sobre serviços externos, como bases de dados ou outros sistemas. Eles abstraem a comunicação do domínio com o mundo externo, permitindo que o domínio permaneça independente de qualquer tecnologia específica.

```java
package com.fiap.techchallenge.ports.gateway;

public interface PedidoRepositoryPort {
	Pedido salvarPedido(Pedido pedido);
}
```

#### Services
**Definição:**
Application Services atuam como a orquestração de operações de alto nível, fazendo a ponte entre a interface de usuário e o domínio do modelo. Eles coordenam as ações que precisam ser tomadas em resposta a solicitações externas, delegando a execução das regras de negócios para os serviços ou entidades do domínio.

**Características:**
- Coordenam tarefas e delegam trabalho para os serviços de domínio ou diretamente para as entidades.
- Gerenciam o fluxo de dados de e para as entidades de domínio e garantem que as respostas adequadas sejam produzidas para a camada de apresentação ou para os sistemas externos.
- Podem gerenciar transações e garantir que todos os aspectos de uma operação sejam completados corretamente ou que a operação seja revertida se necessário.
- Frequentemente são o local onde são implementadas integrações com outros serviços (via ports e adapters).

**Exemplo:**
Um `PedidoApplicationService` pode ser responsável por processar uma solicitação de criação de pedido, garantindo que todas as etapas necessárias sejam completadas, como validação, persistência e notificação ao cliente.

```java
// Application service example
package com.fiap.techchallenge.application.services;

public class PedidoApplicationService implements PedidoPort {
    private final PedidoRepositoryPort pedidoGateway;
    private final PedidoService pedidoService;

    public PedidoApplicationService(PedidoRepositoryPort repo, PedidoService service) {
        this.pedidoGateway = repo;
        this.pedidoService = service;
    }

    public void criarPedido(Pedido pedido) {
        pedidoService.validarPedido(pedido);
        pedidoGateway.salvarPedido(pedido);
    }
}
```

### Infrastructure
Contém todos os detalhes técnicos e implementações específicas como comunicação com bancos de dados e interfaces web.

#### Adapters
Adaptadores para integrar com sistemas externos, como bancos de dados e APIs web.

**Web Adapter:**
```java
package com.fiap.techchallenge.adapters.web;

@RestController
public class PedidoController {
    private final PedidoPort pedidoPort;

    @PostMapping("/pedidos")
    public ResponseEntity<Void> criarPedido(@RequestBody Pedido pedido) {
        pedidoPort.criarPedido(pedido);
        return ResponseEntity.ok().build();
    }
}
```

#### Persistence
Implementações de repositórios que interagem com a base de dados.

**Exemplo:** `PedidoRepositoryImpl.java`
```java
package com.fiap.techchallenge.persistence.repositories;

public class PedidoRepositoryImpl implements PedidoRepositoryPort {
    private final PedidoRepository jpaRepository;

    public Pedido salvarPedido(Pedido pedido) {
        return jpaRepository.save(pedido);
    }
}
```


### Documentação da API com Swagger/Redoc

A documentação da API é uma parte essencial do desenvolvimento e manutenção de uma aplicação, fornecendo uma referência clara para os desenvolvedores sobre como interagir com os endpoints da API. No projeto TechChallenge Restaurante, utilizamos o Swagger (OpenAPI) para gerar e servir a documentação da API, complementada pelo Redoc para uma visualização mais amigável e interativa.

#### Configuração do Swagger

O Swagger é configurado automaticamente através da biblioteca Springdoc-OpenAPI, que integra as especificações do OpenAPI com aplicativos Spring Boot. Para habilitar e configurar o Swagger, adicionamos a seguinte dependência no `pom.xml`:

```xml
<!-- Swagger UI -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.4</version>
</dependency>
```

#### Acessando a Documentação Swagger

Após a configuração, a documentação da API gerada pelo Swagger está disponível por padrão em:

```
http://localhost:8080/swagger-ui.html
```

Esta interface fornece uma visão detalhada de todos os endpoints, modelos de dados e operações disponíveis na API.

#### Integrando com Redoc

Para uma apresentação mais elegante da documentação, integramos o Redoc, que utiliza o mesmo arquivo OpenAPI gerado pelo Swagger. A integração é feita adicionando uma página HTML que carrega o Redoc apontando para o arquivo OpenAPI.

1. **Criar um Arquivo HTML para o Redoc**:
   Crie o arquivo `redoc.html` dentro de `src/main/resources/static` com o seguinte conteúdo:

```html
<!DOCTYPE html>
<html>
<head>
    <title>API Documentation</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,700|Roboto:300,400,700" rel="stylesheet">
    <style>body { margin: 0; padding: 0; }</style>
</head>
<body>
    <redoc spec-url='http://localhost:8080/v3/api-docs'></redoc>
    <script src="https://cdn.redoc.ly/redoc/latest/bundles/redoc.standalone.js"></script>
</body>
</html>
```

2. **Acessar a Documentação pelo Redoc**:
   A documentação da API está agora disponível através do Redoc em:

```
http://localhost:8080/redoc.html
```

#### Documentando os Endpoints

Para garantir que cada endpoint seja adequadamente documentado, utilizamos anotações do OpenAPI em nossos controladores, especificando operações, parâmetros e respostas esperadas. Por exemplo:

```java
@GetMapping("/{id}")
@Operation(summary = "Busca um cliente por ID", description = "Retorna um cliente se encontrado pelo ID fornecido.")
@ApiResponse(responseCode = "200", description = "Cliente encontrado", 
             content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
@ApiResponse(responseCode = "404", description = "Cliente não encontrado")
public ResponseEntity<?> buscarCliente(@PathVariable("id") Long id) {
    // implementação do endpoint
}
```

### Conclusão

A documentação da API não apenas facilita o desenvolvimento e manutenção do projeto, mas também aprimora a experiência dos desenvolvedores e consumidores da API, fornecendo clareza e transparência sobre as funcionalidades disponíveis.


## Tecnologias Utilizadas
- **Java**
- **Maven**
- **Spring Boot**

## Como Rodar o Projeto
```bash
mvn clean install
java -jar <caminho-para-jar-do-projeto>/techchallenge-app.jar
```

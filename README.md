# API REST com Kotlin e Spring Boot

<p align="center">
  <img src="https://github.com/luizleme-tech/kotlin-spring/blob/main/kotlin-spring-forum-logo.png" align="center" style="border-radius: 50%; display: block; margin: auto;" width="35%" >
</p>

## Sobre o Projeto
Este repositório foi criado como parte do meu portfólio de desenvolvimento backend em Kotlin. O objetivo é construir uma API REST utilizando **Kotlin** e **Spring Boot**, baseada na API do fórum da Alura.

Durante a implementação, desenvolvemos endpoints REST, aplicamos boas práticas de estruturação de projetos e utilizamos ferramentas que otimizam o desenvolvimento ágil.

---

## Como Executar o Projeto

### Requisitos
Certifique-se de que os itens abaixo estão instalados no seu ambiente:

- **JDK 17+**
- **IntelliJ IDEA** (ou outra IDE de sua preferência)
- **Maven**

### Passo a Passo
1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   ```
2. Importe o projeto no IntelliJ IDEA.
3. Aguarde o download de todas as dependências do Maven.
4. Execute o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Acesse o navegador em: [http://localhost:8080/marco](http://localhost:8080/marco).

---

## Estrutura do Projeto
```plaintext
src/
├── main/
│   ├── kotlin/
│   │   └── com.luizlemetech.forum/
│   │       └── controller/
│   │           └── MarcoController.kt
├── resources/
│   └── application.properties
```

### Exemplo de Implementação
```kotlin
package com.luizlemetech.forum.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/marco")
class MarcoController {
    @GetMapping
    fun sayPolo(): String {
        return "Polo"
    }
}
```

---

## Autor
Desenvolvido por [Seu Nome](https://github.com/seu-usuario).

---

## Licença
Distribuído sob a licença MIT. Veja mais em [LICENSE](LICENSE).

---

## Contribuições
Contribuições são sempre bem-vindas! Para contribuir:

1. Realize um fork do repositório.
2. Crie uma branch para sua feature:
   ```bash
   git checkout -b minha-feature
   ```
3. Commit suas alterações:
   ```bash
   git commit -m 'Adicionando nova funcionalidade'
   ```
4. Envie para o repositório remoto:
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request para revisão.


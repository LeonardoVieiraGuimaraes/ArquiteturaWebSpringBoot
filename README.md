# Arquitetura Web com Spring Boot

Aplicação Java com Spring Boot containerizada e publicada através de **Cloudflare Tunnel**
— o caminho completo do código até a aplicação acessível pela internet.

## O que tem aqui

| Arquivo | Função |
| :--- | :--- |
| `Dockerfile.spring` | Imagem da aplicação Java |
| `docker-compose.yml` | Orquestração dos serviços |
| `cloudflare-ingress-config.yml` | Regras de roteamento do túnel |
| `.github/` | Automação de build e deploy |
| `data/` | Volume persistente |

## Cloudflare Tunnel, e por que ele resolve o problema

Publicar aplicação em máquina própria normalmente exige IP fixo, abrir porta no roteador e
gerenciar certificado TLS.

O túnel inverte a conexão: o servidor abre uma conexão **de saída** para a Cloudflare, e o
tráfego chega por ela. Consequências práticas:

- Nenhuma porta aberta para a internet
- Funciona atrás de CGNAT, sem IP fixo
- TLS terminado na borda, sem certificado para renovar
- A origem do serviço não fica exposta

Para estudo, laboratório ou projeto pessoal, é a forma mais direta de colocar algo no ar
sem infraestrutura paga.

## Como rodar

```bash
docker compose up -d --build
```

Para o túnel, ajuste `cloudflare-ingress-config.yml` com o seu domínio e o `tunnel ID`.

## Relacionados

- [`SpringBootDeploy`](https://github.com/LeonardoVieiraGuimaraes/SpringBootDeploy) — deploy com Docker Compose e MySQL
- [`monitoring-stack`](https://github.com/LeonardoVieiraGuimaraes/monitoring-stack) — Prometheus e Grafana monitorando este cenário

---

## Autor

**Leonardo Vieira Guimarães** — desenvolvedor backend e Product Owner no IMA.
Mestre em Modelagem Computacional e Sistemas (UNIMONTES), doutorando em Modelagem
Matemática e Computacional (CEFET-MG).

[![Portfólio](https://img.shields.io/badge/Portf%C3%B3lio-leoproti.com.br-0A0A0A?style=flat)](https://leoproti.com.br)
[![ORCID](https://img.shields.io/badge/ORCID-0009--0000--3118--4664-A6CE39?style=flat&logo=orcid&logoColor=white)](https://orcid.org/0009-0000-3118-4664)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-perfil-0A66C2?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/leonardo-vieira-guimaraes/)

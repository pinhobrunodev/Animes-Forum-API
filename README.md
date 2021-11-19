<h1 align="center"><a id="user-content---sobre-o-projeto-" class="anchor" aria-hidden="true" href="#--sobre-o-projeto-"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a> <g-emoji class="g-emoji" alias="computer" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f4bb.png">🎯</g-emoji> Sobre o Projeto </h1>
<div align="center"> 
    <a target="_blank" rel="noopener noreferrer" href="https://camo.githubusercontent.com/771cc18a712bf9edb0925a86164c34b0d803c4d9177dd4467eff7b777109c723/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a6176612d4544384230303f7374796c653d666f722d7468652d6261646765266c6f676f3d6a617661266c6f676f436f6c6f723d7768697465"><img src="https://camo.githubusercontent.com/771cc18a712bf9edb0925a86164c34b0d803c4d9177dd4467eff7b777109c723/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a6176612d4544384230303f7374796c653d666f722d7468652d6261646765266c6f676f3d6a617661266c6f676f436f6c6f723d7768697465" data-canonical-src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&amp;logo=java&amp;logoColor=white" style="max-width:100%;"></a>
    <a target="_blank" rel="noopener noreferrer" href="https://camo.githubusercontent.com/4bde567a4772f994f22418e4505a1ac8dc6e6219100251aa79b7279e02c8bb07/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f537072696e672d3644423333463f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67266c6f676f436f6c6f723d7768697465"><img src="https://camo.githubusercontent.com/4bde567a4772f994f22418e4505a1ac8dc6e6219100251aa79b7279e02c8bb07/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f537072696e672d3644423333463f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67266c6f676f436f6c6f723d7768697465" data-canonical-src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&amp;logo=spring&amp;logoColor=white" style="max-width:100%;"></a>

  <img src= "https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white"/>
  <img src="https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white"/>
  <img src="https://img.shields.io/badge/Grafana-F2F4F9?style=for-the-badge&logo=grafana&logoColor=orange&labelColor=F2F4F9"/>
  <img src="https://img.shields.io/badge/Prometheus-000000?style=for-the-badge&logo=prometheus&labelColor=000000"/>




</div>



### <i>Este projeto representa um sistema de Fórum onde os usuários podem criar tópicos e discutir sobre seus animes preferidos.
Animes esse que já estão pré-selecionados, entretanto , existe também a possibilidade dos **usuários enviarem solicitações de animes para serem colocados** !! =D </i>

#






## 🛠 Tecnologias utilizadas

### 🧱 Back end

- Java 11
- Spring Boot
- Spring Security
- Spring Validation
- OAuth2
- Maven
- H2 Database
- Docker
- Prometheus
- Grafana
- Swagger

#


### 🐳 Sobre o Docker

<a href="https://github.com/pinhobrunodev/Animes-Forum-API/wiki/Sobre-o-Docker-%F0%9F%90%B3--!!">Ir para Documentação do Docker</a>
  

#


### 📑 Modelagem do Sistema

<br>

Obs : Na entidade <code>Reply</code> ao invés de "int" é _"Instant"_.

![Modelagem Banco  - ANIMES FORUM](https://user-images.githubusercontent.com/60756219/142297282-e50f94b4-1cd3-4305-9be8-4c60c7fad529.png)


#



## Arquitetura das Camadas da Aplicação

![padrao_camadas](https://user-images.githubusercontent.com/60756219/138620073-f3a98830-1d88-445a-8036-2d4340441c6e.png)


#

## Monitoramento com Grafana e Prometheus(Em breve mais dados serão monitorados🥰)


- Quantidade de solicitações para inserir novos Animes.
- Quantidade de solicitações para inserir novos Animes que foram enviadas com algum erro. 
- Quantidade de solicitações de  Animes que foram aceitos.
- Quantidade de vezes que o Endpoint para aceitar uma solicitação de Anime deu problema. ( Id not found )
- Quantidade de solicitações de  Animes que foram recusados.
- Quantidade de vezes que o Endpoint para recusar uma solicitação de Anime deu problema. ( Id not found )

![moni](https://user-images.githubusercontent.com/60756219/139599038-37c9245d-94bd-4611-ae7a-e64f04db0237.png)


 #
      
<p align="center">Made with <g-emoji class="g-emoji" alias="green_heart" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f49a.png">💚</g-emoji> by Bruno Pinho</p>
      
 #


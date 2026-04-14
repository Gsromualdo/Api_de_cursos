O projeto foi estruturado seguindo o padrão de Camadas (Layered Architecture), garantindo uma separação clara de responsabilidades e facilitando a manutenção e testabilidade:

Controller 🌐: Camada de entrada que lida com as requisições HTTP e validações iniciais.

Service 🧠: Camada onde reside toda a Lógica de Negócio. É responsável por validar regras (ex: duplicidade de nomes) e coordenar as operações.

Repository 🗄️: Camada de persistência que utiliza Spring Data JPA para comunicação com o banco de dados.

Exception Handling 🛡️: Implementação de um manipulador global de exceções para garantir respostas HTTP semânticas e padronizadas (ex: 404 para recursos não encontrados).

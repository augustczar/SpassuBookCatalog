# SpassuBookCatalog em Desenvolvimento
## Evolução para arquitetura distribuida.

Objetivo:

Criar um projeto utilizando as boas práticas de mercado.

Projeto:

	O projeto consiste em um Catalogo de livros. 

Tecnologia:

	Java.
	Postgres.

Instruções:

Deve ser feito CRUD para Livro, Autor e Assunto conforme o modelo de dados.
A tela inicial pode ter um menu simples ou mesmo links direto para as telas construídas.
O modelo do banco deve ser seguido integralmente, salvo para ajustes de melhoria de performance.
A interface pode ser simples, mas precisa utilizar algum CSS que comande no mínimo a cor e tamanho dos componentes em tela (utilização do bootstrap será um diferencial).
	Os campos que pedem formatação devem possuir o mesmo (data, moeda, etc).
	Deve ser feito obrigatoriamente um relatório (utilizando o componente de relatórios de sua preferência(Crystal, ReportViewer, etc)) e a consulta desse relatório deve ser proveniente de uma view criada no banco de dados. Este relatório pode ser simples, mas permita o entendimento dos dados. O relatório deve trazer as informações das 3 tabelas principais agrupando os dados por autor (atenção pois um livro pode ter mais de autor).
	TDD (Test Driven Development) será considerados um diferencial.
	Tratamento de erros é essencial, evite try catchs genéricos em situações que permitam utilização de tratamentos específicos, como os possíveis erros de banco de dados.
	As mensagens emitidas pelo sistema, labels e etc ficam a seu critério.
	O modelo inicial não prevê, mas é necessário incluir um campo de valor (R$) para o livro.


<img src="/home/augustczar/Documents/SpassuTest/modeloDados.jpg" alt="modelo de dados">



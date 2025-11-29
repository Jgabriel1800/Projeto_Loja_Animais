#Projeto Loja de Animais
-----------------------------------------------------------------------------------
#Descrição
-----------------------------------------------------------------------------------

Este projeto é um sistema de gerenciamento para uma loja de animais, desenvolvido em Java com interface gráfica (Swing). Ele permite o cadastro e gerenciamento de clientes, produtos e vendas, além de gerar relatórios de desempenho da loja.

#Funcionalidades
-----------------------------------------------------------------------------------
Clientes

Cadastrar, listar e remover clientes.

Armazenamento persistente em arquivo .dat.

Produtos

Cadastrar, atualizar, listar e remover produtos.

Suporte a diferentes tipos de produtos: Alimento, Brinquedo e Higiene.

Controle de estoque e estoque mínimo.

Vendas

Registrar vendas associadas a clientes.

Adicionar itens às vendas.

Armazenamento persistente em arquivo .dat.

Relatórios

Produto mais vendido.

Produto menos vendido.

Total vendido no mês.

Melhor cliente (por valor total).

#Tecnologias
-----------------------------------------------------------------------------------

Java 11

Swing (GUI)

Serialização de objetos para persistência em arquivos .dat

#Estrutura de Pastas
-----------------------------------------------------------------------------------

Loja_De_Animais/

│

├── camada_apresentacao/   # Telas e interface gráfica

├── camada_servico/        # Lógica de negócio e serviços

├── camada_persistencia/   # DAOs e utilitários de arquivo

├── camada_negocio/        # Classes de negócio (Cliente, Produto, Venda, etc.)

├── excessoes/             # Tratamento de exceções personalizadas

└── database/              # Arquivos .dat com dados persistentes

#Como Rodar
-----------------------------------------------------------------------------------

Certifique-se de ter o Java 11 instalado.

Compile os arquivos .java.

Execute MainApp.java.

Utilize a interface gráfica para cadastrar clientes, produtos e registrar vendas.

Relatórios podem ser acessados pela tela de relatórios.

#Observações
-----------------------------------------------------------------------------------

Os dados são armazenados localmente em arquivos .dat dentro da pasta database.

Produtos, clientes e vendas possuem IDs gerados automaticamente para controle interno.

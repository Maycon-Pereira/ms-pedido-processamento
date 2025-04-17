# Microserviço de E-commerce com Spring Boot e RabbitMQ

Este repositório contém um mini projeto de microserviço para um sistema de e-commerce, dividido em duas APIs principais:

## Estrutura do Projeto

### 1. Producer
- **Função**: Cria e envia pedidos e itens para uma fila no RabbitMQ.
- **Responsabilidade**: 
  - Gera mensagens com informações de pedidos e itens.
  - Envia essas mensagens para a fila, garantindo que sejam processadas posteriormente.

### 2. Consumer
- **Função**: Consome as mensagens da fila do RabbitMQ.
- **Responsabilidade**: 
  - Escuta a fila em busca de novas mensagens.
  - Processa as mensagens recebidas, realizando operações como salvar dados no banco de dados.

## Comunicação
- As APIs se comunicam através do RabbitMQ, que atua como um intermediário para gerenciar as filas de mensagens.
- Essa abordagem permite:
  - **Desacoplamento**: O Producer e o Consumer operam de forma independente.
  - **Resiliência**: Mensagens são armazenadas na fila até serem processadas, evitando perda de dados.
  - **Escalabilidade**: Possibilidade de escalar o Producer e o Consumer de forma independente conforme a demanda.

## Conclusão
Este microserviço exemplifica um sistema de e-commerce, utilizando Spring Boot e RabbitMQ para gerenciar pedidos e itens.

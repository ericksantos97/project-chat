insert into pergunta (id, descricao, palavras_chave, resposta)
values (1, 'Qual é o número do pedido?', 'numero pedido', 'Número do seu último pedido: %s');

insert into pergunta (id, descricao, palavras_chave, resposta)
values (2, 'Qual é o número da nota fiscal?', 'numero nota fiscal', 'Número da nota fiscal do seu último pedido: %s');

insert into pergunta (id, descricao, palavras_chave, resposta)
values (3, 'Qual é o status do pedido?', 'status pedido', 'Status do seu último pedido: %s');

insert into produto (id, descricao, tipo)
values (1, 'Notebook', 'Informática');

insert into produto (id, descricao, tipo)
values (2, 'Mesa', 'Móveis');

insert into pedido (numero_pedido, cliente, data_compra, descricao, numero_nota_fiscal, status, id_produto)
values (1, 'Marcelo', '2020-04-20', 'descrição do pedido 1', '998536273', 'Despachado', 1);

insert into pedido (numero_pedido, cliente, data_compra, descricao, numero_nota_fiscal, status, id_produto)
values (2, 'Marcelo', '2020-04-22', 'descrição do pedido 2', '112233445', 'Aguardando Pagamento', 2);
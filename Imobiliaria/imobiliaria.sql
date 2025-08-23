-- Criação da tabela Imovel
CREATE TABLE Imovel (
    id INT PRIMARY KEY,
    endereco VARCHAR(255),
    tipo VARCHAR(50),
    quartos INT,
    banheiros INT,
    areaUtil DOUBLE,
    precoAluguel DOUBLE,
    disponivel BOOLEAN
);

-- Criação da tabela Cliente
CREATE TABLE Cliente (
    id INT PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(14)
);

-- Criação da tabela Contrato
CREATE TABLE Contrato (
    id INT PRIMARY KEY,
    imovel_id INT,
    cliente_id INT,
    valorAluguel DOUBLE,
    dataInicio DATE,
    dataFim DATE,
    ativo BOOLEAN,
    FOREIGN KEY (imovel_id) REFERENCES Imovel(id),
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);

-- Inserindo dados de exemplo na tabela Imovel
INSERT INTO Imovel (id, endereco, tipo, quartos, banheiros, areaUtil, precoAluguel, disponivel) VALUES
(1, 'Rua A, 123', 'Apartamento', 2, 1, 60.0, 1500.00, TRUE),
(2, 'Rua B, 456', 'Casa', 3, 2, 120.0, 2500.00, TRUE),
(3, 'Rua C, 789', 'Apartamento', 1, 1, 45.0, 1000.00, FALSE);

-- Inserindo dados de exemplo na tabela Cliente
INSERT INTO Cliente (id, nome, cpf) VALUES
(1, 'João Silva', '123.456.789-00'),
(2, 'Maria Oliveira', '987.654.321-00'),
(3, 'Pedro Costa', '111.222.333-44');

-- Inserindo dados de exemplo na tabela Contrato
INSERT INTO Contrato (id, imovel_id, cliente_id, valorAluguel, dataInicio, dataFim, ativo) VALUES
(1, 1, 1, 1500.00, '2023-01-01', '2024-01-01', TRUE),
(2, 3, 2, 1000.00, '2023-05-15', '2023-11-15', TRUE);

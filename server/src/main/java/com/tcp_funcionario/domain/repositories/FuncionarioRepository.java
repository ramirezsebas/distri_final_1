package com.tcp_funcionario.domain.repositories;

import java.util.List;

import com.tcp_funcionario.domain.entities.Funcionario;

public interface FuncionarioRepository {
    public Funcionario registrarFuncionario(Funcionario funcionario);

    public List<Funcionario> listarFuncionarios();

}
package com.tcp_funcionario.data.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tcp_funcionario.domain.entities.Funcionario;
import com.tcp_funcionario.domain.repositories.FuncionarioRepository;

public class MockFuncionarioRepository implements FuncionarioRepository {
    private final Map<String, Funcionario> bdLocal;

    public MockFuncionarioRepository(Map<String, Funcionario> bdLocal) {
        this.bdLocal = bdLocal;
    }

    private Funcionario getFuncionario(String cedula) {
        return bdLocal.get(cedula);
    }

    @Override
    public Funcionario registrarFuncionario(Funcionario funcionario) {
        Funcionario funcionarioNuevo = getFuncionario(funcionario.getCedula());
        if (funcionarioNuevo == null) {
            return null;
        }
        bdLocal.put(funcionarioNuevo.getCedula(), funcionarioNuevo);
        return funcionario;
    }

    @Override
    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> allFuncionarios = getAllFuncionarios();

        return allFuncionarios;
    }

    private List<Funcionario> getAllFuncionarios() {
        return new ArrayList<>(bdLocal.values());
    }

}

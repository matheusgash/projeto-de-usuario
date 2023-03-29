package com.projeto.testeprojeto.service;

import com.projeto.testeprojeto.entity.Cliente;
import com.projeto.testeprojeto.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {

        cliente.setCreatedAt(OffsetDateTime.now());

        return clienteRepository.save(cliente);

    }

    @Transactional
    public void deletar(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

    @Transactional
    public Cliente atualizar(Long clienteId, Cliente cliente) {

        Optional<Cliente> clienteAtualizado = clienteRepository.findById(clienteId);

        BeanUtils.copyProperties(cliente, clienteAtualizado.get(), "id", "createdAt");

        return clienteRepository.save(clienteAtualizado.get());
    }

    public Cliente buscar(Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        return cliente.get();
    }
}

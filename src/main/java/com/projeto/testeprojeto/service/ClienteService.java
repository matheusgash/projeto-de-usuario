package com.projeto.testeprojeto.service;

import com.projeto.testeprojeto.entity.Cliente;
import com.projeto.testeprojeto.input.ClienteInput;
import com.projeto.testeprojeto.mapper.ClienteMapper;
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


    public Cliente buscar(Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        return cliente.get();
    }

    @Transactional
    public Cliente salvar(ClienteInput clienteInput) {
        Cliente cliente = ClienteMapper.fromDtoToEntity(clienteInput);
        cliente.setCreatedAt(OffsetDateTime.now());

        return clienteRepository.save(cliente);

    }

    @Transactional
    public void deletar(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

    @Transactional
    public Cliente atualizar(Long clienteId, ClienteInput clienteInput) {
        clienteRepository.findById(clienteId);

        Cliente clienteAtualizado = ClienteMapper.fromDtoToEntity(clienteInput);
        clienteAtualizado.setId(clienteId);

        return clienteRepository.save(clienteAtualizado);
    }

}

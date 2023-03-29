package com.projeto.testeprojeto.controller;

import com.projeto.testeprojeto.entity.Cliente;
import com.projeto.testeprojeto.repository.ClienteRepository;
import com.projeto.testeprojeto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
        return clienteService.atualizar(clienteId, cliente);
    }

    @DeleteMapping("/{clienteId}")
    public void apagar(@PathVariable Long clienteId) {
        clienteService.deletar(clienteId);
    }
}
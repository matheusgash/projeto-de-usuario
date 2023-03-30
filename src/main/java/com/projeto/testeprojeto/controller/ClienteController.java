package com.projeto.testeprojeto.controller;

import com.projeto.testeprojeto.entity.Cliente;
import com.projeto.testeprojeto.input.ClienteInput;
import com.projeto.testeprojeto.mapper.ClienteMapper;
import com.projeto.testeprojeto.model.ClienteModel;
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
    public List<ClienteModel> listar() {
        List<Cliente> clientes = clienteRepository.findAll();

        return ClienteMapper.fromEntityToModelList(clientes);
    }

    @GetMapping("/por-nome")
    public List<String> listarPorNome() {
        return clienteRepository.findAllNome();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteModel> buscar(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ClienteModel clienteModel = ClienteMapper.fromEntityToModel(cliente.get());

        return ResponseEntity.ok(clienteModel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel adicionar(@RequestBody ClienteInput clienteInput) {

        return ClienteMapper.fromEntityToModel(clienteService.salvar(clienteInput));
    }

    @PutMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteModel atualizar(@PathVariable Long clienteId, @RequestBody ClienteInput clienteInput) {

        return ClienteMapper.fromEntityToModel(clienteService.atualizar(clienteId, clienteInput));
    }

    @DeleteMapping("/{clienteId}")
    public void apagar(@PathVariable Long clienteId) {
        clienteService.deletar(clienteId);
    }
}
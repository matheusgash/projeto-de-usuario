package com.projeto.testeprojeto.mapper;

import com.projeto.testeprojeto.entity.Cliente;
import com.projeto.testeprojeto.input.ClienteInput;
import com.projeto.testeprojeto.model.ClienteModel;

import java.util.ArrayList;
import java.util.List;

public class ClienteMapper {

    public static ClienteModel fromEntityToModel(Cliente cliente) {
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(cliente.getId());
        clienteModel.setNome(cliente.getNome());
        clienteModel.setEmail(cliente.getEmail());

        return clienteModel;
    }


    public static List<ClienteModel> fromEntityToModelList(List<Cliente> clientes){
        List<ClienteModel> clientesModels = new ArrayList<>();

        for(Cliente cliente : clientes){
            ClienteModel clienteModel = fromEntityToModel(cliente);
            clientesModels.add(clienteModel);
        }
        return clientesModels;
    }

    public static Cliente fromDtoToEntity(ClienteInput clienteInput){

        Cliente cliente = new Cliente();
        cliente.setNome(clienteInput.getNome());
        cliente.setEmail(clienteInput.getEmail());
        return cliente;
    }

}

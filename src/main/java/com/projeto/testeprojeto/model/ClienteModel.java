package com.projeto.testeprojeto.model;


import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ClienteModel {

    private Long id;
    private String nome;
    private String email;
}

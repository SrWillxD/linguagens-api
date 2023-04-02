package br.com.alura.linguagens.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface LinguagemRepository extends MongoRepository<Linguagem, String> {
    List<Linguagem> findByOrderByRanking();
}

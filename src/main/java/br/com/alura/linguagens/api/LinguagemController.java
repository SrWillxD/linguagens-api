package br.com.alura.linguagens.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LinguagemController {
    @Autowired
    private LinguagemRepository repositorio;


    @GetMapping("/linguagens")
    public List<Linguagem>obterLinguagens(){
        List<Linguagem> linguagens = repositorio.findByOrderByRanking();
        return linguagens;
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem linguagem){
        var liguagemSalva = repositorio.save(linguagem);
        return new ResponseEntity<>(liguagemSalva, HttpStatus.CREATED) ;
    }

    @GetMapping("/linguagens/{id}")
    public Linguagem obterLinguagemPorId(@PathVariable String id){
        return repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("linguagens/{id}")
    public Linguagem atualizaLinguagem(@PathVariable String id, @RequestBody Linguagem linguagem){
        if(!repositorio.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        linguagem.setId(id);
        Linguagem linguagemSalva =repositorio.save(linguagem);
        return linguagemSalva;
    }

    @DeleteMapping("/linguagens/{id}")
    public void excluirLinguagem(@PathVariable String id){
        repositorio.deleteById(id);
    }

}

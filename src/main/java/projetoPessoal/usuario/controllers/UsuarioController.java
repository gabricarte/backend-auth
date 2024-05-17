package projetoPessoal.usuario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoPessoal.usuario.LoginResponse;
import projetoPessoal.usuario.dto.LoginDto;
import projetoPessoal.usuario.dto.UsuarioDto;
import projetoPessoal.usuario.services.IUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final IUsuarioService servico;

    @Autowired
    public UsuarioController(IUsuarioService servico) {
        this.servico = servico;
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> criarUsuario(@RequestBody UsuarioDto usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.criarUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
        return ResponseEntity.ok(servico.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> obterUsuario(@PathVariable("id") int id) {
        return ResponseEntity.ok(servico.obterUsuario(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(
            @PathVariable("id") int id,
            @RequestBody UsuarioDto usuario
    ) {
        UsuarioDto usuarioAtualizado = servico.atualizarUsuario(id, usuario);
        if (usuarioAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") int id) {
        servico.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // login
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginDto loginDTO) {
        LoginResponse loginResponse = servico.loginUsuario(loginDTO);
        if (loginResponse.getStatus()) {
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
        }
    }

}

package projetoPessoal.usuario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetoPessoal.usuario.LoginResponse;
import projetoPessoal.usuario.dto.AdminDto;
import projetoPessoal.usuario.dto.LoginDto;
import projetoPessoal.usuario.dto.LojaDto;
import projetoPessoal.usuario.services.IAdminService;
import projetoPessoal.usuario.services.ILojaService;

@RestController
@RequestMapping
public class AuthController {
    private final ILojaService lojaService;
    private final IAdminService adminService;

    @Autowired
    public AuthController(ILojaService lojaService, IAdminService adminService) {
        this.lojaService = lojaService;
        this.adminService = adminService;
    }

    @PostMapping("/cadastro/loja")
    public ResponseEntity<LojaDto> cadastrarLoja(@RequestBody LojaDto lojaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lojaService.criarLoja(lojaDto));
    }

    @PostMapping(path = "/login/loja")
    public ResponseEntity<?> loginLoja(@RequestBody LoginDto loginDTO) {
        LoginResponse loginResponse = lojaService.loginLoja(loginDTO);
        if (loginResponse.getStatus()) {
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
        }
    }
    @PostMapping(path = "/login/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody AdminDto adminDto) {
        LoginResponse loginResponse = adminService.loginAdmin(adminDto);
        if (loginResponse.getStatus()) {
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
        }
    }





}

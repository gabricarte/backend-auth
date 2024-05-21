package projetoPessoal.usuario.services;

import projetoPessoal.usuario.LoginResponse;
import projetoPessoal.usuario.dto.LoginDto;
import projetoPessoal.usuario.dto.LojaDto;

import java.util.List;

public interface ILojaService {

    List<LojaDto> listarLojas();

    LojaDto obterLoja(int id);

    LojaDto criarLoja(LojaDto lojaDto);

    LojaDto atualizarLoja(int id, LojaDto lojaDto);

    void deletarLoja(int id);

    LoginResponse loginLoja(LoginDto loginDto);
}

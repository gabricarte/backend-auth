package projetoPessoal.usuario.services;

import projetoPessoal.usuario.LoginResponse;
import projetoPessoal.usuario.dto.AdminDto;

public interface IAdminService {
    LoginResponse loginAdmin(AdminDto adminDto);
}

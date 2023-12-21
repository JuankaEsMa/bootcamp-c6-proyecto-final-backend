package com.example.demo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Usuario;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.dto.UsuarioRecord;
import com.example.demo.dao.IUsuarioDAO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUserService {
	@Autowired
    private IUsuarioDAO usuarioDAO;
	@Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario add(Usuario usuario) {
        Optional<Usuario> theUser = usuarioDAO.findByEmail(usuario.getEmail());
        if (theUser.isPresent()){
            throw new UserAlreadyExistsException("A user with " +usuario.getEmail() +" already exists");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioDAO.save(usuario);
    }

    @Override
    public List<UsuarioRecord> getAllUsers() {
        return usuarioDAO.findAll()
                .stream()
                .map(user -> new UsuarioRecord(
                        user.getId(),
                        user.getNombre(),
                        user.getApellidos(),
                        user.getEmail(),
                        user.getFechaNacimiento())
                		).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(String email) {
        usuarioDAO.deleteByEmail(email);
    }

    @Override
    public Usuario getUser(String email) {
        return usuarioDAO.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public Usuario update(Usuario user) {
        return usuarioDAO.save(user);
    }
}
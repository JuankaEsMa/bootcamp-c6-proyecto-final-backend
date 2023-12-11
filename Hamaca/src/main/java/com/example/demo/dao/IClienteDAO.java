package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Cliente;
import com.example.demo.dto.Usuario;

public interface IClienteDAO extends JpaRepository<Cliente,Integer>{
	public Cliente findByUsuario(Usuario usuario);
}

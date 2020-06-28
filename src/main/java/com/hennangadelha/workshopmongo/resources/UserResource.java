package com.hennangadelha.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hennangadelha.workshopmongo.domain.User;
import com.hennangadelha.workshopmongo.dto.UserDto;
import com.hennangadelha.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	
	@Autowired
	private UserService service;
	
	
	
	@GetMapping
	public ResponseEntity <List<UserDto>> findAll(){
		
		List<User> usuarios = service.findAll();
		List<UserDto> usuariosDto = usuarios.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(usuariosDto);
	}
	
	
}

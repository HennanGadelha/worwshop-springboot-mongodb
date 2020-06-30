package com.hennangadelha.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@RequestMapping( value= "/{id}", method=RequestMethod.GET )
	public ResponseEntity <UserDto> findById(@PathVariable String id){
		
		User user = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDto(user));
		
	}
	
	
	@RequestMapping( value= "/{id}", method=RequestMethod.DELETE )
	public ResponseEntity <Void> delete(@PathVariable String id){
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	
	@RequestMapping( method=RequestMethod.POST )// request body usado para o endpoint reconhcer  o obj
	public ResponseEntity <Void> insert(@RequestBody UserDto userDto){
		
		User user = service.fromDto(userDto);
		user = service.insert(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).build(); 
		
	}
	
	
	@RequestMapping( value= "/{id}", method=RequestMethod.PUT )// request body usado para o endpoint reconhcer  o obj
	public ResponseEntity <Void> update(@RequestBody UserDto userDto, @PathVariable String id ){
		
		User user = service.fromDto(userDto);
		
		user.setId(id);
		
		//user = service.insert(user);
		
		user = service.update(user);
		
		return ResponseEntity.noContent().build();
		
	}
	
	
	
}

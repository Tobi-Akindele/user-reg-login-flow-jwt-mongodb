package com.ta.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.dto.responses.MessageResponse;
import com.ta.enums.RoleType;
import com.ta.models.Role;
import com.ta.repositories.RoleRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;

	@PostMapping("/role")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createRole(@Valid @RequestBody List<String> roles) {

		if (roles == null || roles.isEmpty()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Requestbody is missing!"));
		}
		List<String> roleTypes = Arrays.asList(RoleType.values()).stream().map(roleType -> roleType.name())
				.collect(Collectors.toList());
		List<Role> createdRoles = new ArrayList<>();
		roles.forEach(role -> {
			if (roleTypes.contains(role)) {
				if (!roleRepository.existsByName(RoleType.valueOf(role))) {
					createdRoles.add(roleRepository.insert(new Role(RoleType.valueOf(role))));
				}
			}
		});

		return ResponseEntity.ok(createdRoles);
	}
	
	@GetMapping("/roles")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getRole() {

		List<Role> roles = roleRepository.findAll();

		return ResponseEntity.ok(roles);
	}
}

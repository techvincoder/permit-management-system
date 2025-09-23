package com.tpms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tpms.dao.RoleRepository;
import com.tpms.entities.Role;
import com.tpms.exceptions.ResourceNotFoundException;

@Service
public class RoleService {

	private final RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	//Add Roles
	public Role addRole(Role role) {
		return this.roleRepository.save(role);
	}
	
	//Fetch All Roles
	public List<Role> getAllRole(){
		List<Role> list =  (List<Role>)this.roleRepository.findAll();
		return list;
	}
	
	//Fetch
	public Role getRoleById(long id) {
		return this.roleRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException ("there is no Role with ID: "+id));
	}
	
	//Update By ID
	public Role updateRoleById(long id,Role role) {
		Role rl = this.roleRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("There is No Such Role with ID: "+id));
		
		//Getters and Setters
		rl.setName(role.getName());
		//add more getters setters as needed
		return this.roleRepository.save(rl);
	}
	
	//Delete by Id
	public void deleteRoleById(long id) {
		if( !this.roleRepository.existsById(id)) {
			throw new ResourceNotFoundException("There is No Role with ID: "+id);
		}
		
		this.roleRepository.deleteById(id);
	}
}

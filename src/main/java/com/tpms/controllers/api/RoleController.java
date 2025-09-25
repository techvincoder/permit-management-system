package com.tpms.controllers.api;

import com.tpms.entities.Role;
import com.tpms.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Endpoint to create a new role.
     * Maps to: POST /api/roles/
     */
    @PostMapping("/")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role newRole = roleService.addRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    /**
     * Endpoint to get all roles.
     * Maps to: GET /api/roles/
     */
    @GetMapping("/")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRole();
        return ResponseEntity.ok(roles);
    }

    /**
     * Endpoint to get a single role by its ID.
     * Maps to: GET /api/roles/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    /**
     * Endpoint to update an existing role.
     * Maps to: PUT /api/roles/1
     */
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
        Role updatedRole = roleService.updateRoleById(id, roleDetails);
        return ResponseEntity.ok(updatedRole);
    }

    /**
     * Endpoint to delete a role by its ID.
     * Maps to: DELETE /api/roles/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.noContent().build();
    }
}


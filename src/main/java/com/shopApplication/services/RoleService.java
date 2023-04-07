package com.shopApplication.services;

import com.shopApplication.enums.MessageTypes;
import com.shopApplication.exceptions.MyMessageResponse;
import com.shopApplication.models.Role;
import com.shopApplication.payload.response.MessageResponse;
import com.shopApplication.repositories.RoleRepository;
import com.shopApplication.security.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // return all Roles

    public List<Role> list(){
        List<Role> roles = roleRepository.findAll();
        if(roles.isEmpty()) new MyMessageResponse("Error: No Roles listed", MessageTypes.WARN);
        return roles;
    }

    public List<Role> findAll(){
        List<Role> roles = roleRepository.findAll();
        if(roles.isEmpty()) new MyMessageResponse("Error: No Roles listed", MessageTypes.WARN);
        return roles;
    }

    // return Role by id

    public Role findByName( ERole name){
        Role role = roleRepository.findByName(name).orElse(new Role());
        if(role.getId()==null)
            new MyMessageResponse(String.format("Role name: %s not found", name), MessageTypes.ERROR);
        return role;
    }

    // edit/update a Role record - only if record with id exists

    public ResponseEntity<MessageResponse> update(Integer id, Role role){

        // check if exists first
        // then update

        if(roleRepository.existsById(id)) {
            roleRepository.save(role);
            return ResponseEntity.ok(new MyMessageResponse("Role record updated", MessageTypes.INFO));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Id does not exist [" + id + "] -> cannot update record", MessageTypes.WARN));
        }

    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public List<Role> findByNames(List<String> roles) {
        List<Role> roleList = new ArrayList<>();

        for (String s : roles) {
            roleList.add(roleRepository.findByName(ERole.valueOf(s)).orElse(new Role()));
        }
        return roleList;
    }
}

//package com.shopApplication.services;
//
//import com.shopApplication.enums.MessageTypes;
//import com.shopApplication.exceptions.MyMessageResponse;
//import com.shopApplication.models.Role;
//import com.shopApplication.models.UserRole;
//import com.shopApplication.models.UserRoleId;
//import com.shopApplication.payload.response.MessageResponse;
//import com.shopApplication.repositories.RoleRepository;
//import com.shopApplication.repositories.UserRoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class UserRoleService {
//
//    UserRoleRepository userRoleRepository;
//    RoleRepository roleRepository;
//
//    @Autowired
//    public UserRoleService(UserRoleRepository userRoleRepository, RoleRepository roleRepository) {
//        this.userRoleRepository = userRoleRepository;
//        this.roleRepository = roleRepository;
//    }
//
//    // return all Roles
//
//
//
//    // return Role by id
//
//    public List<Role> findByUserId(int id){
//        List<UserRole> userRoles = userRoleRepository.findByUserId(id).orElse(new ArrayList<>());
//
//        List<Integer> roleIds = userRoles.stream()
//                .map(UserRole::getId)
//                .map(UserRoleId::getRoleId)
//                .collect(Collectors.toList());
//
//        List<Role> roles = new ArrayList<>();
//        for(Integer i: roleIds){
//            roles.add(roleRepository.getById(i));
//        }
//
//        return roles;
//    }
//
//    // edit/update a Role record - only if record with id exists
//
//    public ResponseEntity<MessageResponse> update(Integer id, Role role){
//
//        // check if exists first
//        // then update
//
//        if(roleRepository.existsById(id)) {
//            roleRepository.save(role);
//            return ResponseEntity.ok(new MyMessageResponse("Role record updated", MessageTypes.INFO));
//        } else {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Id does not exist [" + id + "] -> cannot update record", MessageTypes.WARN));
//        }
//
//    }
//
//    public void save(Role role) {
//        roleRepository.save(role);
//    }
//
//}

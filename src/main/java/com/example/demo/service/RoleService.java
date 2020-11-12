package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role create(Role role){
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public Role deleteRoleId(Long id){
        Optional<Role> optionalRole = roleRepository.findById(id);
        if(optionalRole.isPresent()){
            Role role = optionalRole.get();
            roleRepository.deleteById(role.getId());
            return role;
        }else {
            return null;
        }
    }

    public Role update(Role role){
        return roleRepository.save(role);
    }
}

package com.example.carsharingservice.service;

import com.example.carsharingservice.model.Role;

public interface RoleService {
    Role findByName(Role.RoleName name);
}
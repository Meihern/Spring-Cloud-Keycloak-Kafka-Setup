package com.tpspringcloud.securityservice.services;

import com.tpspringcloud.securityservice.entities.AppRole;
import com.tpspringcloud.securityservice.entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewAppUser(AppUser appUser);
    AppRole addNewAppRole(AppRole appRole);
    void addRoleToUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> loadUsers();
}

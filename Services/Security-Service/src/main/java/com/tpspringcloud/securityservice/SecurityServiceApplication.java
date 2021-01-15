package com.tpspringcloud.securityservice;

import com.tpspringcloud.securityservice.entities.AppRole;
import com.tpspringcloud.securityservice.entities.AppUser;
import com.tpspringcloud.securityservice.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServiceApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(AccountService accountService){
        return args -> {
            // Ajout des rôles
            accountService.addNewAppRole(new AppRole(null, "USER"));
            accountService.addNewAppRole(new AppRole(null, "ADMIN"));
            accountService.addNewAppRole(new AppRole(null, "CUSTOMER-MANAGER"));
            accountService.addNewAppRole(new AppRole(null, "INVENTORY-MANAGER"));
            accountService.addNewAppRole(new AppRole(null, "BILLING-MANAGER"));

            // Ajout des utilisateurs
            accountService.addNewAppUser(new AppUser(null, "user1", "1234", new ArrayList<>()));
            accountService.addNewAppUser(new AppUser(null, "admin", "1234", new ArrayList<>()));
            accountService.addNewAppUser(new AppUser(null, "user2", "1234", new ArrayList<>()));
            accountService.addNewAppUser(new AppUser(null, "user3", "1234", new ArrayList<>()));
            accountService.addNewAppUser(new AppUser(null, "user4", "1234", new ArrayList<>()));
            accountService.addNewAppUser(new AppUser(null, "user5", "1234", new ArrayList<>()));

            // Ajout des rôles aux utilisateurs
            accountService.addRoleToUser("user1", "USER");
            accountService.addRoleToUser("admin", "USER");
            accountService.addRoleToUser("admin", "ADMIN");
            accountService.addRoleToUser("user2", "USER");
            accountService.addRoleToUser("user2", "CUSTOMER-MANAGER");
            accountService.addRoleToUser("user3", "USER");
            accountService.addRoleToUser("user3", "INVENTORY-MANAGER");
            accountService.addRoleToUser("user4", "USER");
            accountService.addRoleToUser("user4", "BILLING-MANAGER");
            accountService.addRoleToUser("user5", "USER");
        };
    }

}

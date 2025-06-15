package com.example.ArquiteturaWebSpringBoot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.ArquiteturaWebSpringBoot.model.Role;
import com.example.ArquiteturaWebSpringBoot.repository.RoleRepository;

@Configuration
public class RoleInitializer {

    @Bean
    public CommandLineRunner createDefaultRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
                roleRepository.save(new Role(null, "ROLE_ADMIN", "Administrador"));
            }
            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
                roleRepository.save(new Role(null, "ROLE_USER", "Usuário"));
            }
            if (roleRepository.findByName("ROLE_GERENTE").isEmpty()) {
                roleRepository.save(new Role(null, "ROLE_GERENTE", "Gerente"));
            }
        };
    }
}

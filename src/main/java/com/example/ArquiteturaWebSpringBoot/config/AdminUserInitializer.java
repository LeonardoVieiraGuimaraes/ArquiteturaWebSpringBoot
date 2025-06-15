package com.example.ArquiteturaWebSpringBoot.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.ArquiteturaWebSpringBoot.model.Role;
import com.example.ArquiteturaWebSpringBoot.model.User;
import com.example.ArquiteturaWebSpringBoot.repository.RoleRepository;
import com.example.ArquiteturaWebSpringBoot.repository.UserRepository;

@Configuration
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner createAdmin(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("431")); // senha padrão
                admin.setEnabled(true);
                // Busca a role ROLE_ADMIN no banco
                Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                        .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_ADMIN", "Administrador")));
                admin.setRoles(List.of(adminRole));
                admin.setNomeCompleto("Administrador do Sistema");
                admin.setCpf("00000000000");
                admin.setEmail("admin@admin.com");
                userRepository.save(admin);
            }
        };
    }
}

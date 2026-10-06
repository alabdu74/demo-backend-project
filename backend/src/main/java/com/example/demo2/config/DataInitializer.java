package com.example.demo2.config;

import com.example.demo2.model.entity.Product;
import com.example.demo2.model.entity.User;
import com.example.demo2.model.enums.ProductCategory;
import com.example.demo2.model.enums.Role;
import com.example.demo2.repository.ProductRepository;
import com.example.demo2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedUsers();
        seedProducts();
    }

    private void seedUsers() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            userRepository.save(User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build());
            log.info("✅ Default ADMIN created: admin / admin123");
        }

        if (userRepository.findByUsername("user").isEmpty()) {
            userRepository.save(User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user123"))
                    .role(Role.USER)
                    .build());
            log.info("✅ Default USER created: user / user123");
        }
    }

    private void seedProducts() {
        if (productRepository.count() > 0) {
            log.info("ℹ️ Products already exist, skipping seed");
            return;
        }

        productRepository.save(Product.builder()
                .name("Laptop")
                .description("14-inch ultrabook, 16GB RAM")
                .price(new BigDecimal("999.99"))
                .quantity(10)
                .category(ProductCategory.ELECTRONICS)
                .build());

        productRepository.save(Product.builder()
                .name("Wireless Mouse")
                .description("Ergonomic wireless mouse")
                .price(new BigDecimal("29.99"))
                .quantity(50)
                .category(ProductCategory.ELECTRONICS)
                .build());

        productRepository.save(Product.builder()
                .name("Coffee Mug")
                .description("Ceramic mug, 350ml")
                .price(new BigDecimal("9.99"))
                .quantity(100)
                .category(ProductCategory.FOOD)
                .build());

        productRepository.save(Product.builder()
                .name("T-Shirt")
                .description("Cotton t-shirt, size M")
                .price(new BigDecimal("19.99"))
                .quantity(30)
                .category(ProductCategory.CLOTHING)
                .build());

        productRepository.save(Product.builder()
                .name("Notebook")
                .description("A5 ruled notebook, 200 pages")
                .price(new BigDecimal("4.99"))
                .quantity(200)
                .category(ProductCategory.OTHER)
                .build());

        log.info("✅ Seeded 5 demo products");
    }
}
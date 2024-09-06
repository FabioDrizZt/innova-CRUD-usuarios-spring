package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private List<User> users = new ArrayList<>();

    public UserController() {
        // Inicialmente agregamos algunos usuarios al array
        users.add(new User(1L, "Juan", "juan@example.com"));
        users.add(new User(2L, "Maria", "maria@example.com"));
        users.add(new User(3L, "Carlos", "carlos@example.com"));
    }

    // GET - Obtener todos los usuarios
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // GET - Obtener un usuario por ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null); // Retorna null si no encuentra el usuario
    }

    // POST - Crear un nuevo usuario
    @PostMapping
    public User createUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    // PUT - Actualizar un usuario por ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                return user;
            }
        }
        return null; // Retorna null si no encuentra el usuario
    }

    // DELETE - Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.removeIf(user -> user.getId().equals(id));
        return "Usuario eliminado";
    }

}

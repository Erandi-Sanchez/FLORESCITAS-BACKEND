package org.perseflora.usuario.controller;

import jakarta.servlet.http.HttpSession;
import org.perseflora.usuario.model.Cliente;
import org.perseflora.usuario.repository.ClienteRepository;
import org.perseflora.usuario.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private ClienteRepository clienteRepository;


    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/loginToken")
    public String login(@RequestParam String nombreCliente){
        return jwtUtil.generateToken(nombreCliente);
    }

    @PostMapping("/perseflora/login")
    public ResponseEntity<?> login(@RequestBody Cliente loginCliente) {
        Cliente cliente = clienteRepository.findByEmail(loginCliente.getEmail());

        if (cliente == null) {
            return ResponseEntity.status(401).body("Correo no registrado");
        }

        if (!cliente.getPassword().equals(loginCliente.getPassword())) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }
        // Login exitoso
        return ResponseEntity.ok(cliente);
    }

}
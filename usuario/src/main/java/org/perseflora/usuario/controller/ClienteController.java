package org.perseflora.usuario.controller;

import org.perseflora.usuario.exception.ClienteNotFoundException;
import org.perseflora.usuario.model.Cliente;
import org.perseflora.usuario.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/perseflora/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired //inyección automatica de dependencias
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getAll() {
        return clienteService.getClientes();
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente newCliente) {
        if(clienteService.findByEmail(newCliente.getEmail()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.createCliente(newCliente));
    }

    @GetMapping("{idCliente}")
    public ResponseEntity<Cliente> getById(@PathVariable Long idCliente) {
        try {
            return ResponseEntity.ok(clienteService.findById(idCliente));
        } catch (ClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{emailCliente}")
    public ResponseEntity<Cliente> getByEmail(@PathVariable Long emailCliente) {
        try {
            return ResponseEntity.ok(clienteService.findByEmail(emailCliente));
        } catch (ClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-cliente/{idCliente}")
    public  ResponseEntity<Cliente> deleteCliente (@PathVariable Long idCliente) {
        try {
            clienteService.deleteCliente(idCliente);
            return ResponseEntity.noContent().build();
        }catch (ClienteNotFoundException e){
            return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-cliente/{idCliente}")
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente, @PathVariable Long idCliente){
        try{
            return ResponseEntity.ok(clienteService.updateCliente(cliente, idCliente));
        }catch (ClienteNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }



}
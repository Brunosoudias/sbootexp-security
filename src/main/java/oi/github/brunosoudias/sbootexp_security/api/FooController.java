package oi.github.brunosoudias.sbootexp_security.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute(){
        return ResponseEntity.ok("Public route ok!");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privatecRoute(Authentication authentication){
        System.out.println(authentication.getClass());
        return ResponseEntity.ok("Private route ok! Usuario conectado: " + authentication.getName());
    }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> admincRoute(Authentication authentication){
        System.out.println(authentication.getClass());
        return ResponseEntity.ok("Admin route ok! ");
    }
}

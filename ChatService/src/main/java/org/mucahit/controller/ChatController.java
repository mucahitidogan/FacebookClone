package org.mucahit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.mucahit.constants.EndPointList.*;

@RestController
@RequestMapping(CHAT)
@RequiredArgsConstructor
public class ChatController {

    @GetMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("Post atıldı");
    }
}

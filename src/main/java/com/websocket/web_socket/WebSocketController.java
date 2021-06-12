package com.websocket.web_socket;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class WebSocketController {

    private WebSocketService webSocketService;

    @GetMapping("/clock")
    public ResponseEntity clock_get() {

        List<Clock> clocks = webSocketService.retrieveClock();
        return new ResponseEntity(clocks,null, HttpStatus.OK);
    }

    @PostMapping("/clock")
    public ResponseEntity clock_post(@RequestBody Clock clock) {
        Clock c = webSocketService.createClock(clock);
        return new ResponseEntity(c,null, HttpStatus.OK);
    }

    @DeleteMapping("/clock")
    public ResponseEntity clock_delete(@RequestBody Clock clock) {

        Clock c = webSocketService.deleteClock(clock);
        return new ResponseEntity(c,null, HttpStatus.OK);
    }

    @GetMapping("/perfume")
    public ResponseEntity perfume_get() {

        List<Perfume> perfumes = webSocketService.retrievePerfume();
        return new ResponseEntity(perfumes,null, HttpStatus.OK);
    }

    @PostMapping("/perfume")
    public ResponseEntity perfume_post(@RequestBody Perfume perfume) {
        Perfume p = webSocketService.createPerfume(perfume);
        return new ResponseEntity(p,null, HttpStatus.OK);
    }

    @DeleteMapping("/perfume")
    public ResponseEntity perfume_delete(@RequestBody Perfume perfume) {

        Perfume p = webSocketService.deletePerfume(perfume);
        return new ResponseEntity(p,null, HttpStatus.OK);
    }
}

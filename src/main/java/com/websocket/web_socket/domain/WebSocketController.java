package com.websocket.web_socket.domain;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class WebSocketController {

    private WebSocketService webSocketService;
    private ClockUpdater clockUpdater;

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

    @DeleteMapping("/clock/{id}")
    public ResponseEntity clock_delete(@PathVariable Integer id) {

        Clock clock = webSocketService.deleteClock(id);
        clockUpdater.onClockDeleted(clock);

        return new ResponseEntity(clock,null, HttpStatus.OK);
    }

}

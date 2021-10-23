package com.websocket.web_socket.domain;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebSocketService {
    private List<Clock> clockRepository = new ArrayList<>();
    private int seq = 3;

    {
        clockRepository.add(
            Clock.builder()
                .id(1)
                .name("구찌백")
                .description("이거 명품입니다. ㄹㅇ")
                .price(1000000)
            .build()
        );
        clockRepository.add(
            Clock.builder()
                .id(2)
                .name("시계택")
                    .description("2거 명품입니다. real")
                    .price(2000000)
                .build()
        );

    }

    public List<Clock> retrieveClock() {
        return this.clockRepository;
    }

    public Clock createClock(Clock clock) {
        clock.setId(seq++);
        this.clockRepository.add(clock);

        return clock;
    }

    public Clock deleteClock(Integer id) {
        Clock clock = this.clockRepository.stream().filter(s -> s.getId().equals(id)).collect(Collectors.toList()).get(0);
        this.clockRepository = this.clockRepository.stream().filter(s -> !s.getId().equals(id)).collect(Collectors.toList());

        return clock;
    }

}

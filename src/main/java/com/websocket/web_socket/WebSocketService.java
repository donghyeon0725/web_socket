package com.websocket.web_socket;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class WebSocketService {
    private List<Clock> clockRepository = new ArrayList<>();
    private List<Perfume> perfumeRepository = new ArrayList<>();

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

        perfumeRepository.add(
            Perfume.builder()
                .id(1)
                .name("좋은수")
                .description("향기가 좋은데예?")
                .price(300000)
            .build()
        );
    }

    public List<Clock> retrieveClock() {
        return this.clockRepository;
    }

    public Clock createClock(Clock clock) {
        this.clockRepository.add(clock);

        return clock;
    }

    public Clock deleteClock(Clock clock) {
        this.clockRepository = this.clockRepository.stream().filter(s -> s.getId() != clock.getId()).collect(Collectors.toList());

        return clock;
    }

    public List<Perfume> retrievePerfume() {
        return this.perfumeRepository;
    }

    public Perfume createPerfume(Perfume perfume) {
        this.perfumeRepository.add(perfume);

        return perfume;
    }

    public Perfume deletePerfume(Perfume perfume) {
        this.perfumeRepository = this.perfumeRepository.stream().filter(s -> s.getId() != perfume.getId()).collect(Collectors.toList());

        return perfume;
    }

}

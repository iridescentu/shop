package com.sparta.preptest.controller;

import com.sparta.preptest.dto.PrepTestRequestDto;
import com.sparta.preptest.dto.PrepTestResponseDto;
import com.sparta.preptest.service.PrepTestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PrepTestController {

    private final PrepTestService prepTestService;

    public PrepTestController(PrepTestService prepTestService) {
        this.prepTestService = prepTestService;
    }

    @PostMapping("/post")
    public PrepTestResponseDto createPrepTest(@RequestBody PrepTestRequestDto requestDto) {
        return prepTestService.createPrepTest(requestDto);
    }

    @GetMapping("/post")
    public List<PrepTestResponseDto> getPrepTests() {
        return prepTestService.getPrepTests();
    }

    @PutMapping("/post/{id}")
    public PrepTestResponseDto updatePrepTest(@PathVariable Long id, @RequestBody PrepTestRequestDto requestDto) {
        return prepTestService.updatePrepTest(id, requestDto);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePrepTest(@PathVariable Long id) {
        prepTestService.deletePrepTest(id);
        return ResponseEntity.ok().body(Map.of("msg", "삭제 완료"));
    }
}

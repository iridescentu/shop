package com.sparta.preptest.service;

import com.sparta.preptest.dto.PrepTestRequestDto;
import com.sparta.preptest.dto.PrepTestResponseDto;
import com.sparta.preptest.entity.PrepTest;
import com.sparta.preptest.repository.PrepTestRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrepTestService {

    private final PrepTestRepository prepTestRepository;

    public PrepTestService(PrepTestRepository prepTestRepository) {
        this.prepTestRepository = prepTestRepository;
    }

    public PrepTestResponseDto createPrepTest(PrepTestRequestDto requestDto) {
        // RequestDto -> Entity
        PrepTest prepTest = new PrepTest(requestDto);

        // DB 저장
        PrepTest savePrepTest = prepTestRepository.save(prepTest);

        // Entity -> ResponseDto
        PrepTestResponseDto prepTestResponseDto = new PrepTestResponseDto(prepTest);

        return prepTestResponseDto;
    }

    public List<PrepTestResponseDto> getPrepTests() {
        // DB 조회
        return prepTestRepository.findAll();
    }

    public PrepTestResponseDto updatePrepTest(Long id, PrepTestRequestDto requestDto) {
        // 해당 PrepTest가 DB에 존재하는지 확인
        PrepTest prepTest = prepTestRepository.findById(id);
        if(prepTest != null) {
            // prepTest 내용 수정
            prepTestRepository.update(id, requestDto);
            // 업데이트된 PrepTest를 다시 조회
            PrepTest updatedPrepTest = prepTestRepository.findById(id);
            // 업데이트된 정보로 ResponseDto 생성
            return new PrepTestResponseDto(updatedPrepTest);
        } else {
            throw new IllegalArgumentException("선택한 준비 테스트는 존재하지 않습니다.");
        }
    }

    public Long deletePrepTest(Long id) {
        // 해당 PrepTest가 DB에 존재하는지 확인
        PrepTest prepTest = prepTestRepository.findById(id);
        if(prepTest != null) {
            // prepTest 삭제
            prepTestRepository.delete(id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 준비 테스트는 존재하지 않습니다.");
        }
    }
}

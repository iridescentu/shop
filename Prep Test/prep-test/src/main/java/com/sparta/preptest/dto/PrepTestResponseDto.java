package com.sparta.preptest.dto;

import com.sparta.preptest.entity.PrepTest;
import lombok.Getter;

@Getter
public class PrepTestResponseDto {
    private Long id;
    private String username;
    private String title;
    private String content;
    private int price;

    public PrepTestResponseDto(PrepTest prepTest) {
        this.id = prepTest.getId();
        this.username = prepTest.getUsername();
        this.title = prepTest.getTitle();
        this.content = prepTest.getContent();
        this.price = prepTest.getPrice();
    }

    public PrepTestResponseDto(Long id, String username, String title, String content, int price) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.price = price;
    }
}

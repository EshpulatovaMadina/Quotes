package com.example.quotes.DTO.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuotesResponseDto {
    private String author;
    private List<String> category;
    private String quote;
}

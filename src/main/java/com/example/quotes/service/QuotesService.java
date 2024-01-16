package com.example.quotes.service;

import com.example.quotes.DTO.respone.QuotesResponseDto;
import com.example.quotes.entity.QuotesEntity;
import com.example.quotes.repository.QuotesRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuotesService {
    private final QuotesRepo quotesRepo;
    private final ModelMapper modelMapper;

    public List<QuotesResponseDto> getRandom(int num) {
         return quotesRepo.findRandomSample(num).stream().map(this::parse).toList();
    }

    private QuotesResponseDto parse(QuotesEntity quotes) {
        return modelMapper.map(quotes, QuotesResponseDto.class);
    }

    public List<QuotesResponseDto> getByAuthor(String author, Pageable pageable) {
        return quotesRepo.findByAuthor(author, pageable).stream().map(this::parse).toList();
    }

    public List<QuotesResponseDto> getByCategory(String category,Pageable pageable) {
        return quotesRepo.findByCategory(category, pageable).stream().map(this::parse).toList();
    }
}

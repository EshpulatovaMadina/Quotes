package com.example.quotes.service;

import com.example.quotes.DTO.respone.Author;
import com.example.quotes.DTO.respone.QuotesResponseDto;
import com.example.quotes.entity.QuotesEntity;
import com.example.quotes.repository.QuotesRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
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

    public Page<QuotesResponseDto> getByAuthor(String author, Pageable pageable) {
        Page<QuotesEntity> repo = quotesRepo.findByAuthorContainingIgnoreCase(author, pageable);
        List<QuotesResponseDto> responseDtos = repo.getContent().stream().map(this::parse).toList();
        return new PageImpl<>(responseDtos, pageable, repo.getTotalElements());    }

    public Page<QuotesResponseDto> getByCategory(String category, Pageable pageable) {
        Page<QuotesEntity> repo = quotesRepo.findByCategoryContainingIgnoreCase(category, pageable);
        List<QuotesResponseDto> responseDtos = repo.getContent().stream().map(this::parse).toList();
        return new PageImpl<>(responseDtos, pageable, repo.getTotalElements());
    }


    // its stupidness 🤣
    public List<Author> getAllAuthors() {
        return quotesRepo.findDistinctAuthors();
    }

    public Slice<Author> getAuthorsByChar(Character character, Pageable pageable) {
        return quotesRepo.findAuthorsByFirstLetter(character.toString(), pageable);
    }
}


package com.example.quotes.controller;

import com.example.quotes.DTO.respone.Author;
import com.example.quotes.DTO.respone.QuotesResponseDto;
import com.example.quotes.entity.QuotesEntity;
import com.example.quotes.service.QuotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quotes")
public class QuotesController {
    private final QuotesService quotesService;
    @GetMapping("/random")
    public ResponseEntity<List<QuotesResponseDto>> random(@RequestParam(defaultValue = "1") Integer num){
        return ResponseEntity.ok(quotesService.getRandom(num));
    }

    @GetMapping("/author")
    public ResponseEntity<Page<QuotesResponseDto>> getByAuthor(
            @RequestParam(value = "page", defaultValue = "0")
            int page,
            @RequestParam(value = "size", defaultValue = "5")
            int size,
            @RequestParam String author
    ) {
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(quotesService.getByAuthor(author,pageable));
    }

    @GetMapping("/category")
    public ResponseEntity<Page<QuotesResponseDto>> getByCategory(
            @RequestParam(value = "page", defaultValue = "0")
            int page,
            @RequestParam(value = "size", defaultValue = "5")
            int size,
            @RequestParam String category
    ) {
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(quotesService.getByCategory(category,pageable));
    }

    @GetMapping("/authors")
    public ResponseEntity<Slice<Author>> getAllAuthors(
            @RequestParam(defaultValue = "a") Character character,
            @RequestParam(value = "page", defaultValue = "0")
            int page,
            @RequestParam(value = "size", defaultValue = "10")
            int size
    ) {
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(quotesService.getAuthorsByChar(character,pageable));
    }
}

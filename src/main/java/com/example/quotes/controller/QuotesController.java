package com.example.quotes.controller;

import com.example.quotes.entity.QuotesEntity;
import com.example.quotes.service.QuotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quotes")
public class QuotesController {
    private final QuotesService quotesService;
    @GetMapping("/get-all")
    public ResponseEntity<List<QuotesEntity>> getAll(){
        return ResponseEntity.ok(quotesService.getAll());
    }
}

package com.example.quotes.service;

import com.example.quotes.entity.QuotesEntity;
import com.example.quotes.repository.QuotesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuotesService {
    private final QuotesRepo quotesRepo;

    public List<QuotesEntity> getRandom(int num) {
        return quotesRepo.findRandomSample(num) ;
    }

}

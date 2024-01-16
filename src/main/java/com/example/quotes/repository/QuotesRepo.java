package com.example.quotes.repository;

import com.example.quotes.entity.QuotesEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuotesRepo extends MongoRepository<QuotesEntity, ObjectId> {
    @Aggregation(pipeline = { "{ $sample: { size: ?0 } }" })
    List<QuotesEntity> findRandomSample(int size);
    List<QuotesEntity> findByAuthor(String author, Pageable pageable);
    List<QuotesEntity> findByCategory(String category, Pageable pageable);
}

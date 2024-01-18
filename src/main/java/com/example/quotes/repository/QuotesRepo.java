package com.example.quotes.repository;

import com.example.quotes.DTO.respone.Author;
import com.example.quotes.entity.QuotesEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuotesRepo extends MongoRepository<QuotesEntity, ObjectId> {
    @Aggregation(pipeline = { "{ $sample: { size: ?0 } }" })
    List<QuotesEntity> findRandomSample(int size);
    Page<QuotesEntity> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    Page<QuotesEntity> findByCategoryContainingIgnoreCase(String category, Pageable pageable);
    @Aggregation(pipeline = {
            "{ $group: { _id: null, authors: { $addToSet: '$author' } } }",
            "{ $project: { _id: 0, authors: 1 } }",
            "{ $unwind: '$authors' }",
            "{ $replaceRoot: { newRoot: { name: '$authors' } } }"
    })
    List<Author> findDistinctAuthors();

    @Aggregation(pipeline = {
            "{ $group: { _id: null, authors: { $addToSet: '$author' } } }",
            "{ $project: { _id: 0, authors: 1 } }",
            "{ $unwind: '$authors' }",
            "{ $match: { 'authors': { $regex: '^" + "?0" + "', $options: 'i' } } }",
            "{ $replaceRoot: { newRoot: { name: '$authors' } } }"
    })
    Slice<Author> findAuthorsByFirstLetter(String firstLetter, Pageable pageable);

}
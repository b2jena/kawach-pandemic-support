package com.stackroute.resource.service;

import com.stackroute.resource.model.DBSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SequenceGeneratorService {

    private MongoOperations mongoOperations;
    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
    public int getSequenceNumber(String sequenceName){
        //get seqNo
        Query query = new Query(Criteria.where("id").is(sequenceName));
        //update seqNo
        Update update= new Update().inc("seq",1);
        //modify in document
        DBSequence counter = mongoOperations
                .findAndModify(query,update, FindAndModifyOptions.options().returnNew(true).upsert(true),DBSequence.class);

        return !Objects.isNull(counter)?counter.getSeq():1;

    }
}

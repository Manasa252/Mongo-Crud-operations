package com.codedecode.mongoCrud.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.codedecode.mongoCrud.model.DbSequence;

@Service
public class SequenceGeneratorService {

	@Autowired
	private MongoOperations mongoOperations;

	public int getSequenceNumber(String sequenceName) {
		Query query = new Query(Criteria.where("_id").is(sequenceName));
//		Update update=new Update().inc("seq", 1);
		DbSequence counter = mongoOperations.findAndModify(query, new Update().inc("seq", 1),
				options().returnNew(true).upsert(true), DbSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}
}

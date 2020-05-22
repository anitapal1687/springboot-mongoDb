package com.anita.springbootmongo.repository;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.anita.springbootmongo.model.DatabaseSequence;


@Service
public class SequenceRepository {
	
	  @Autowired
	  MongoTemplate mongoTemplate;
	  
	  public long generateSequence(String seqName) {
		  
		
		  FindAndModifyOptions options = new FindAndModifyOptions();
		  options.returnNew(true);
		  options.upsert(true);  
		  
		  Query query = new Query(Criteria.where("_id").is(seqName));
		  
		  
		  DatabaseSequence counter = mongoTemplate.findAndModify(query,
		      new Update().inc("seq",1), options, DatabaseSequence.class);
		    return !Objects.isNull(counter) ? counter.getSeq() : 1;
		 

	    }	  

}

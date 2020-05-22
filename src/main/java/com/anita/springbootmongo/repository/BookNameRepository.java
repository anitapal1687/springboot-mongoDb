package com.anita.springbootmongo.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;


import com.anita.springbootmongo.model.Book;
import com.anita.springbootmongo.model.Domain;
import com.mongodb.WriteResult;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

@Service
public class BookNameRepository  {

	  @Autowired
	    MongoTemplate mongoTemplate;
	
	public long updateBookName(String bookName, String authorName){
		
		   Query query = new Query(Criteria.where("bookName").is(bookName));
	        Update update = new Update();
	        update.set("authorName", authorName);

	        UpdateResult result = mongoTemplate.updateMulti(query, update, Book.class );
	        if(result!=null)
	            return result.getMatchedCount();
	        else
		return 0;
		
	}
	
	
	public long count (){
		Query query = new Query();
		
		return mongoTemplate.count(query, Book.class);
	}
	
	

	public long countByAuthorName (String authorName){
		Query query = new Query(Criteria.where("authorName").is(authorName));
		
		return mongoTemplate.count(query, Book.class);
	}
	
	public List<Domain> sumPriceByAuthorName (){
		
		
		Aggregation agg = newAggregation(
				match(Criteria.where("price").gt(50)),
				group("authorName").sum("price").as("sumofauthor"),
				project("sumofauthor").and("authorName").previousOperation(),
				sort(Sort.Direction.DESC, "sumofauthor")

			);
		
		//ArithmeticOperators op= new
		/*Aggregation agg1 = newAggregation(
				//match(Criteria.where("price").gt(50)),
				group().sum("price").as("sum")
			//	valueOf("price").sum("price").as("sumofauthor"),
				//project("sumofauthor").and("authorName").previousOperation(),
				//sort(Sort.Direction.DESC, "sum")

			);*/
		

		AggregationResults<Domain> result= mongoTemplate.aggregate(agg, Book.class, Domain.class);
		
	/*	AggregationResults<Domain> result1= mongoTemplate.aggregate(agg1, Book.class, Domain.class);
		*/
		List<Domain> sum= (List<Domain>) result.getMappedResults();
		
		/*org.bson.Document sum1= (org.bson.Document) result1.getRawResults();*/
		//LinkedHashMap o= (LinkedHashMap)sum1.getObjectId("sum"); 
		return sum;
		
	}
	
	
	
	
	
	/*@Query(value="{}", fields="{name : 1, _id : 0}")
	List<User> findNameAndExcludeId();*/
	
	

}

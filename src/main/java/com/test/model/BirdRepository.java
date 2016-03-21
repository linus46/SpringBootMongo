package com.test.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
//@RepositoryRestResource(collectionResourceRel = "birds", path = "/birds")
public interface BirdRepository extends MongoRepository<Bird, String> {

	public Bird getBirdById(@Param("id")String id);
	public List<Bird> findAll();
	public long deleteById(@Param("id")String id);
}

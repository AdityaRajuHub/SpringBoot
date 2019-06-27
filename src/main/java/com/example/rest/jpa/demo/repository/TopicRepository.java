package com.example.rest.jpa.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.rest.jpa.demo.model.Topic;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Integer>{
	
	public List<Topic> findByAuthorAndName(String author, String name);
	
	public List<Topic> findDistinctByAuthor(String author);
	
	public List<Topic> findDistinctByName(String name);
	
}

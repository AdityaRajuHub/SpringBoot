package com.example.rest.jpa.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.rest.jpa.demo.model.Topic;
import com.example.rest.jpa.demo.repository.TopicPagingRepository;
import com.example.rest.jpa.demo.repository.TopicRepository;

@Service
public class TopicService {

	@Autowired 
	TopicRepository topicRepository;
	
	@Autowired 
	TopicPagingRepository topicPagingRepository;
		
	
	public List<Topic> getAllTopics() {
		List topicList= new ArrayList<Topic>();
		topicRepository.findAll().forEach(t -> topicList.add(t));
		return topicList;
	}
	
	public Topic getTopic(int id) {
		return topicRepository.findById(id).get();
	}
	
	public List<Topic> getTopicsByPage(int pageNo) {
		List<Topic> list= new ArrayList<Topic>();
		topicPagingRepository.findAll(new PageRequest(pageNo, 20)).stream().forEach(t -> list.add(t));
		return list;
	}
	
	public void saveTopic(Topic topic) {
		topicRepository.save(topic);
	}
	
	public void saveTopics(List<Topic> topics) {
		topics.forEach(t -> topicRepository.save(t));	
	}
	
	public void updateTopic(Topic topic) {
		topicRepository.save(topic);
	}
	
	public void deleteTopic(int id) {
		topicRepository.deleteById(id);
	}
	
	public List<Topic> findByAuthorAndName(String author, String name){
		return topicRepository.findByAuthorAndName(author, name);
	}
	
	public List<Topic> getTopicByAuthor(String author) {
		return topicRepository.findDistinctByAuthor(author);
	}
	
	public List<Topic> getTopicByName(String name) {
		return topicRepository.findDistinctByName(name);
	}
}

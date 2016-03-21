package com.test;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Bird;
import com.test.model.BirdRepository;

@RestController
public class BirdController {

	@Autowired
	private BirdRepository birdRepo ;
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String testRest()
	{
		return "hello sunil";
	}
	@RequestMapping(value="/birds",method=RequestMethod.GET)
	public ResponseEntity<List<Bird>> getAllBirds()
	{
		return new ResponseEntity<List<Bird>>(birdRepo.findAll(),HttpStatus.OK);
	}
	@RequestMapping(value="/birds/{id}",method=RequestMethod.GET)
	public ResponseEntity<Bird> getBirdById(@PathVariable("id")String id)
	{
		Bird bird = birdRepo.getBirdById(id);
		if(bird!=null)
			return new ResponseEntity<Bird>(bird,HttpStatus.OK) ;
		else
			return new ResponseEntity<Bird>(HttpStatus.NOT_FOUND);
	}
	@RequestMapping(value="/birds/{id}",method=RequestMethod.DELETE)
	public void deleteBirdById(@PathVariable("id")String id)
	{
		long count = birdRepo.deleteById(id);
		if(count>0)
		{
			new ResponseEntity<>(HttpStatus.OK);
		}
		else
		{
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value="/birds",method=RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<Bird> saveBirds(@RequestBody Bird bird)
	{
		HttpStatus status = HttpStatus.OK ;
		try{
			return new ResponseEntity<Bird>(birdRepo.save(bird),status);	
		}
		catch(ConstraintViolationException e)
		{
			status = HttpStatus.BAD_REQUEST;
		}
		finally{
		}
		return new ResponseEntity<>(status);
	}
}

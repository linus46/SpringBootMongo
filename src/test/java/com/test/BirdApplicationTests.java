package com.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import com.test.model.Bird;
import com.test.model.BirdRepository;

@SpringApplicationConfiguration(classes = BirdApplication.class)
public class BirdApplicationTests  extends AbstractTestNGSpringContextTests {

	private String birdId ;
	@Autowired
	private BirdRepository repo ;
	@Test
	public void saveBirds() {
		Bird bird = new Bird();
		bird.setName("Parrot");
		bird.setFamily("I dont know");
		Set<String> continents = new HashSet<String>();
		continents.add("Asia");
		continents.add("Australia");
		bird.setContinents(continents);
		bird.setVisible(true);
		
		bird = repo.save(bird);
		birdId  = bird.getId();
		Assert.notNull(bird.getId(),"bird information is not saved into database");
		Assert.isTrue(bird.getId().isEmpty()==false,"Bird information is not saved into database");
	}
	@Test(expectedExceptions = ConstraintViolationException.class)
	public void saveBirdInvalidInput(){
		Bird bird = new Bird();
		bird.setFamily("I dont know");
		Set<String> continents = new HashSet<String>();
		continents.add("Asia");
		continents.add("Australia");
		bird.setContinents(continents);
		bird.setVisible(true);
		bird = repo.save(bird);
		Assert.isNull(bird.getId(),"Invalid bird inforation is stored");
		Assert.isTrue(bird.getId().isEmpty(),"Invalid bird information is stored");
	}
	@Test(dependsOnMethods = {"saveBirds"})
	public void getAllBirds(){
		List<Bird> allBirds = repo.findAll();
		Assert.notEmpty(allBirds);
	}
	@Test(dependsOnMethods = {"saveBirds"})
	public void getBirdWithId()
	{
		Bird bird = repo.getBirdById(birdId);
		Assert.notNull(bird,"Bird not found for given id : "+birdId);
	}
	@Test(dependsOnMethods={"saveBirds","getAllBirds","getBirdWithId"})
	public void deleteBirdById()
	{
		long count = repo.deleteById(birdId);
		Assert.isTrue(count>0,"failed to delete bird with id : "+birdId);
	}
}

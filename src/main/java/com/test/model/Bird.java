package com.test.model;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;

@Document
public class Bird {

	@Id
	@JsonProperty
	private String id ;
	@NotNull(message="name is mandatory")
	@NotEmpty(message="name is mandatory")
	@JsonProperty
	private String name ;
	@NotNull(message="family is mandatory")
	@NotEmpty(message="familly is mandatory")
	@JsonProperty
	private String family ;
	@NotNull(message="continents is mandatory")
	@Size(min=1)
	@JsonProperty
	private Set<String> continents;
	@JsonIgnore
	private Date added ;

	private boolean visible;
	public Bird(){
		this.added = new Date();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public Set<String> getContinents() {
		return continents;
	}
	public void setContinents(Set<String> continents) {
		this.continents = continents;
	}
	@JsonProperty
	@JsonDeserialize(using=DateDeserializer.class)
	public Date getAdded() {
		return added;
	}
	@JsonIgnore
	public void setAdded(Date added) {
		this.added = added;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}

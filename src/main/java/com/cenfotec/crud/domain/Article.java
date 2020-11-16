package com.cenfotec.crud.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Article {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	private String name;
	
	@ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
	private Antology anthology;

	public Article() {
	}

	public Article(long id, String name, Antology anthology) {
		this.id = id;
		this.name = name;
		this.anthology = anthology;
	}

	public Article(String name, Antology anthology) {
		this.name = name;
		this.anthology = anthology;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Antology getAnthology() {
		return anthology;
	}

	public void setAnthology(Antology anthology) {
		this.anthology = anthology;
	}
}

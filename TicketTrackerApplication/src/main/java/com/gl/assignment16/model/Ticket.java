package com.gl.assignment16.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String ticketTitle;
	private String description;
	private LocalDate createdOn;
	private String content;
	public Ticket(String ticketTitle, String description, LocalDate createdOn, String content) {
		super();
		this.ticketTitle = ticketTitle;
		this.description = description;
		this.createdOn = createdOn;
		this.content = content;
	}
	
	
	
}

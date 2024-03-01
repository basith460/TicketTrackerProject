package com.gl.assignment16.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;


@Service
public class TicketService {
	
	@Autowired
	TicketRepository tkrepo;
	
	public void add(Ticket ticket) {
		tkrepo.save(ticket);
	}
	
	public List<Ticket> getAll(){
		return tkrepo.findAll();
	}
	
	public Ticket getById(int id) {
		Optional<Ticket> tkoptional=tkrepo.findById(id);

		Ticket temp=null;
		if(tkoptional.get()!=null) {
			temp=tkoptional.get();
		}
		return temp;

	}
		
	public void delete(Ticket td) {
		tkrepo.delete(td);
	}
	
	public List<Ticket> filterByTitle(String searchKey){
		Ticket dummyTicket=new Ticket();
		dummyTicket.setTicketTitle(searchKey);
		
		ExampleMatcher  em=ExampleMatcher.matching().withMatcher("ticketTitle",ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("id","description","createdOn","content");
		
		Example<Ticket> example=Example.of(dummyTicket,em);
		
		return tkrepo.findAll(example);
	}
	
}

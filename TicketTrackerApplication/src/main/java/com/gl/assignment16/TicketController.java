package com.gl.assignment16;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.assignment16.model.Ticket;
import com.gl.assignment16.model.TicketService;

@Controller
public class TicketController {
	
	@Autowired
	TicketService tkService;
	
	@RequestMapping("/showtickets")
	public String Welcome(Model  model) {
		List<Ticket> tklist=tkService.getAll();
		model.addAttribute("ticket",tklist);
		return "home-page";
		
	}
	
	@RequestMapping("/addticket")
	public String add() {
		
		return "add-form";
	}
	
	@PostMapping("/addsave")
	public String addSave(@RequestParam String title,@RequestParam String description,@RequestParam String content,Model model) {
		
		Ticket tk=new Ticket(title,description,LocalDate.now(),content);
		
		tkService.add(tk);
		
		List<Ticket> tklist=tkService.getAll();
		model.addAttribute("ticket",tklist);
		return "home-page";
	}
	
	
	@GetMapping("/update-ticket")
	public String update(@RequestParam int id,Model model) {
		Ticket tkupdate=tkService.getById(id);
		if(tkupdate!=null) {
			model.addAttribute("ticket",tkupdate);
			return "update-ticket-form";
		}
		else {
			List<Ticket> tklist=tkService.getAll();
			model.addAttribute("ticket",tklist);
			return "home-page";
		}
	}
	
	@PostMapping("/updatesave")
	public String updateSave(@RequestParam int id,@RequestParam String title,@RequestParam String description,@RequestParam String content,Model model) {
		Ticket tk=new Ticket(id,title,description,LocalDate.now(),content);
		
		tkService.add(tk);
		
		List<Ticket> tklist=tkService.getAll();
		model.addAttribute("ticket",tklist);
		return "home-page";
	}
	
	
	@GetMapping("/delete-ticket")
	public String delete(@RequestParam int id,Model model) {
		Ticket tkdel=new Ticket(id,"","",null,"");
		
		tkService.delete(tkdel);
		
		List<Ticket> tklist=tkService.getAll();
		model.addAttribute("ticket",tklist);
		return "home-page";
		
	}
	
	@GetMapping("/view-ticket")
	public String view(@RequestParam int id,Model model) {
		Ticket tkview=tkService.getById(id);
		
		model.addAttribute("ticket",tkview);
		
		return "view-tickets";
		
	}
	
	@PostMapping("/serchvalue")
	public String Search(@RequestParam String title,Model model) {
		List<Ticket> tkfilteredlist=tkService.filterByTitle(title);
		model.addAttribute("ticket",tkfilteredlist);
		return "home-page";
	}
}

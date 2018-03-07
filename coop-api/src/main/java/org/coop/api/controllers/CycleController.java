package org.coop.api.controllers;

import java.util.List;
import java.util.Optional;

import org.coop.api.domain.cycle.Cycle;
import org.coop.api.domain.cycle.CycleRepository;
import org.coop.api.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coop/api")
@CrossOrigin("*")
public class CycleController {

	@Autowired
	private CycleRepository cycleRepository;

	@GetMapping("/cycle/list")
	public ResponseEntity<List<Cycle>> getCycleList() {

		return new ResponseEntity<>(cycleRepository.findAll(), HttpStatus.OK);

	}

	@GetMapping(value = "/cycle/{id}")
	public ResponseEntity<Cycle> getCycleById(@PathVariable("id") String id) {

		Optional<Cycle> cycle = cycleRepository.findById(id);

		if (cycle.isPresent()) {

			return new ResponseEntity<>(cycle.get(), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

	}

	@PostMapping("/cycle/add")
	public ResponseEntity<Cycle> addCycle(@RequestParam List<Member> members, Cycle cycle) {

		cycle.setContributors(members);

		return new ResponseEntity<>(cycleRepository.save(cycle), HttpStatus.OK);

	}

	@PutMapping(value = "/cycle/{id}")
	public ResponseEntity<Cycle> updateCycle(@PathVariable("id") String id, Cycle cycle) {

		Optional<Cycle> optional = cycleRepository.findById(id);

		if (optional.isPresent()) {

			Cycle data = optional.get();

			data.setYear(cycle.getYear());
			data.setStatus(cycle.getStatus());
			data.setStart(cycle.getStart());
			data.setEnd(cycle.getEnd());

			Cycle updated = cycleRepository.save(data);

			return new ResponseEntity<>(updated, HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

	@DeleteMapping(value = "/cycle/{id}")
	public void deleteCycle(@PathVariable("id") String id) {

		cycleRepository.deleteById(id);

	}

}

package org.coop.api.controllers;

import java.util.List;
import java.util.Optional;

import org.coop.api.domain.contribution.Contribution;
import org.coop.api.domain.contribution.ContributionRepository;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coop/api")
@CrossOrigin("*")
public class ContributionController {

	@Autowired
	private ContributionRepository contributionRepository;

	@GetMapping("/contribution/list")
	public ResponseEntity<List<Contribution>> getContributionList() {

		return new ResponseEntity<>(contributionRepository.findAll(), HttpStatus.OK);

	}

	@GetMapping(value = "/contribution/{id}")
	public ResponseEntity<Contribution> getContributionById(@PathVariable("id") String id) {

		Optional<Contribution> contribution = contributionRepository.findById(id);

		if (contribution.isPresent()) {

			return new ResponseEntity<>(contribution.get(), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

	}

	@PostMapping("/contribution/add")
	public ResponseEntity<Contribution> addContribution(Contribution contribution) {

		return new ResponseEntity<>(contributionRepository.save(contribution), HttpStatus.OK);

	}

	@PutMapping(value = "/contribution/{id}")
	public ResponseEntity<Contribution> updateContribution(@PathVariable("id") String id, Contribution contribution) {

		Optional<Contribution> optional = contributionRepository.findById(id);

		if (optional.isPresent()) {
			Contribution data = optional.get();

			data.setMember(contribution.getMember());
			data.setAmount(contribution.getAmount());
			data.setCutoff(contribution.getCutoff());
			data.setCycle(contribution.getCycle());
			data.setEntryDate(contribution.getEntryDate());

			Contribution updated = contributionRepository.save(data);

			return new ResponseEntity<>(updated, HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

	}

	@DeleteMapping(value = "/contribution/{id}")
	public void deleteContribution(@PathVariable("id") String id) {

		contributionRepository.deleteById(id);

	}

}

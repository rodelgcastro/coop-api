package org.coop.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.coop.api.domain.member.Member;
import org.coop.api.domain.member.MemberRepository;
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
public class MemberController {

	@Autowired
	private MemberRepository memberRepository;

	@GetMapping("/member/list")
	public ResponseEntity<List<Member>> getMemberList() {

		return new ResponseEntity<>(memberRepository.findAll(), HttpStatus.OK);

	}

	@GetMapping(value = "/member/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable("id") String id) {

		Optional<Member> member = memberRepository.findById(id);

		if (member.isPresent()) {

			return new ResponseEntity<>(member.get(), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

	}

	@PostMapping("/member/add")
	public ResponseEntity<Member> addMember(@Valid Member member) {

		return new ResponseEntity<>(memberRepository.save(member), HttpStatus.OK);

	}

	@PutMapping(value = "/member/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable("id") String id, @Valid Member member) {

		Optional<Member> optional = memberRepository.findById(id);

		if (optional.isPresent()) {

			Member data = optional.get();

			data.setFirstName(member.getFirstName());
			data.setMiddleName(member.getMiddleName());
			data.setLastName(member.getLastName());
			data.setBirthDate(member.getBirthDate());

			Member updated = memberRepository.save(data);

			return new ResponseEntity<>(updated, HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

	}

	@DeleteMapping(value = "/member/{id}")
	public void deleteMember(@PathVariable("id") String id) {

		memberRepository.deleteById(id);

	}

}

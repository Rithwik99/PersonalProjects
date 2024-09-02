package ttechlab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ttechlab.bo.MemberService;
import ttechlab.entity.Book;
import ttechlab.entity.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/get-member")
	public ResponseEntity<List<Member>> getAllTheMember(){
		List<Member>listfmember = memberService.getAllMembers();
			return new ResponseEntity<>(listfmember,HttpStatus.OK);
	}
	
	
	@PostMapping("/save-member")
	public ResponseEntity<Member>SaveMember(@RequestBody Member member) {
		Member savemember= memberService.saveMember(member);
		return new ResponseEntity<>(savemember,HttpStatus.OK);
		
	}
	
	@GetMapping("/get-member/{id}")
	public ResponseEntity<Member> getByid(@PathVariable Long id){
				Member memberbyid=memberService.getMemberById(id);
				if(memberbyid!=null) {
					return new ResponseEntity<>(memberbyid,HttpStatus.OK);
				}else {
				return new ResponseEntity<>(memberbyid,HttpStatus.NOT_FOUND);
			}
		}
	@DeleteMapping("/delete-member/{id}")
	public ResponseEntity<Member> deletebookById(@PathVariable Long id){
	Member deleteById=	memberService.getMemberById(id);
		if(deleteById !=null) {
			memberService.deleteMember(id);
			return new ResponseEntity<>(deleteById,HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(deleteById,HttpStatus.NOT_FOUND);
		}
	}
}

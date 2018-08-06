package com.techchefs.assignment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techchefs.assignment.entity.Bank;
import com.techchefs.assignment.entity.Transaction;

@RestController
@RequestMapping("/WebServices/api")
public class BankRestController {

	@Autowired
	BankRepository repository;

	public static final Logger logger = LoggerFactory.getLogger(BankRestController.class);

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> registerUser(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		Map<String, String> result = new HashMap<>();
		if (repository.registerUser(username, password)) {
			result.put("message", "User registerd successfully");
			result.put("registered", "YES");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.put("message", "user already exists");
			result.put("registered", "NO");
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> loginUser(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		Map<String, String> result = new HashMap<>();
		if (repository.isValidUser(username, password)) {
			result.put("message", "Valid user");
			result.put("registered", "YES");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.put("message", "Invalid user");
			result.put("registered", "NO");
			return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/getbanklist", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllBanksList() {
		List<Bank> list = repository.getAllBanksList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/getbankdata/{bankname}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getBankData(@PathVariable("bankname") String bankname) {
		Map<String, String> result = new HashMap<>();
		Bank bank = repository.getBankDetails(bankname);
		if (bank != null) {
			result.put("Username", "Yes");
			result.put("Password", "Yes");
			result.put("CorpId", "Yes");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.put("Username", "No");
			result.put("Password", "No");
			result.put("CorpId", "No");
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(value = "/logintobank/{bankname}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> loginToBank(@PathVariable("bankname") String bankname,@RequestParam("username") String username,
			@RequestParam("password") String password,@RequestParam("corpid") String corpid) {
		Map<String, String> result = new HashMap<>();
		
		boolean isValid = repository.isValidBankUser(bankname,username,password);
		if (isValid) {
			result.put("message", "Valid user");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.put("message", "You are not authorized to log into " + bankname);
			return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/gettransactiondata/{accountnumber}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getTransactionData(@PathVariable("accountnumber") String accountnumber) {
		List<Transaction> list = repository.getTransactionData(accountnumber);
		if (list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

}

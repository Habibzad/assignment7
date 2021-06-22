package com.meritamerica.assignment7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment7.exceptions.InvalidArgumentException;
import com.meritamerica.assignment7.exceptions.NegativeAmountException;
import com.meritamerica.assignment7.exceptions.NoResourceFoundException;
import com.meritamerica.assignment7.exceptions.NoSuchAccountException;
import com.meritamerica.assignment7.exceptions.ReachedAccountLimitException;
import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.CDAccount;
import com.meritamerica.assignment7.models.PersonalCheckingAccount;
import com.meritamerica.assignment7.models.SavingsAccount;
import com.meritamerica.assignment7.models.User;
import com.meritamerica.assignment7.security.util.JwtUtil;
import com.meritamerica.assignment7.service.AccountsService;
import com.meritamerica.assignment7.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserDashController {
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private AccountsService accountsService;


	@GetMapping("/Me")
	@PreAuthorize("hasRole('ROLE_USER')")
	public AccountHolder getAccountHolderById() throws NoResourceFoundException {
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		return user.getAccountHolder();
	}

	@PostMapping("/Me/personal-checking-account")
	@PreAuthorize("hasRole('ROLE_USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public PersonalCheckingAccount addCheckingAccount(@RequestBody PersonalCheckingAccount personalCheckingAccount)
			throws NoResourceFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException,
			NoSuchAccountException, InvalidArgumentException, ReachedAccountLimitException {
		if (personalCheckingAccount.getBalance() < 0) {
			throw new NegativeAmountException();
		}
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		AccountHolder accHolder = user.getAccountHolder();
		if (accHolder == null) {
			throw new NoResourceFoundException("Invalid id");
		}
		if (accHolder.getCombinedBalance() + personalCheckingAccount.getBalance() > 250000) {
			throw new ExceedsCombinedBalanceLimitException("exceeds limit of amount 250,000 max");
		}

		return accountsService.addPersonalCheckingAccount(accHolder.getId(), personalCheckingAccount);
	}

	@GetMapping("/Me/personal-checking-account")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<PersonalCheckingAccount> getPersonalCheckingAccount() throws NoResourceFoundException {
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		return user.getAccountHolder().getPersonalCheckingAccount();
	}

	@PostMapping("/Me/savingsaccounts")
	@PreAuthorize("hasRole('ROLE_USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSavingsAccount(@RequestBody SavingsAccount savingsAccount)
			throws NoResourceFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException,
			NoSuchAccountException, InvalidArgumentException, ReachedAccountLimitException {
		if (savingsAccount.getBalance() < 0) {
			throw new NegativeAmountException();
		}
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		AccountHolder accHolder = user.getAccountHolder();
		if (accHolder == null) {
			throw new NoResourceFoundException("Invalid id");
		}
		if (accHolder.getCombinedBalance() + savingsAccount.getBalance() > 250000) {
			throw new ExceedsCombinedBalanceLimitException("exceeds limit of amount 250,000 max");
		}
		return accountsService.addSavingsAccount(accHolder.getId(), savingsAccount);
	}

	@GetMapping("/Me/savingsaccounts")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<SavingsAccount> getSavingsAccount() throws NoResourceFoundException {
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		return user.getAccountHolder().getSavingsAccounts();
	}

	@PostMapping("/Me/cdaccounts")
	@PreAuthorize("hasRole('ROLE_USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount addCDAccount(@RequestBody CDAccount cdAccount)
			throws NoResourceFoundException, NegativeAmountException, NoSuchAccountException,
			ExceedsCombinedBalanceLimitException, InvalidArgumentException {
		if (cdAccount.getBalance() < 0) {
			throw new NegativeAmountException();
		}
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		AccountHolder accHolder = user.getAccountHolder();

		if (accHolder == null) {
			throw new NoResourceFoundException("Invalid id");
		}
		return accountsService.addCDAccount(accHolder.getId(), cdAccount);
	}

	@GetMapping("/Me/cdaccounts")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<CDAccount> getCDAccount() throws NoResourceFoundException {
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		return user.getAccountHolder().getCdAccounts();
	}

}

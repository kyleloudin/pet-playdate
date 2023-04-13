package com.techelevator.cardexample;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class petPlayDateController {
    private UserDao userDao;
    private PetDao petDao;
    private AccountDao accountDao;


    public TenmoController(UserDao userDao, TransferDao transferDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.transferDao = transferDao;
        this.accountDao = accountDao;
    }

    @RequestMapping(path = "/balance/{id}", method = RequestMethod.GET)
    public double getBalance(@PathVariable int id) {
        return userDao.getBalance(id);
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> listUsers() {
        return userDao.findAll();
    }

    @ResponseStatus (HttpStatus.CREATED)
    @RequestMapping(path = "/newtransfer", method= RequestMethod.POST)
    public Transfer addTransfer (@RequestBody Transfer transfer) {
        return transferDao.createTransfer(transfer);
    }

    @RequestMapping (path = "/transfer/{transferId}", method = RequestMethod.GET)
    public Transfer getTransfer(@PathVariable int transferId) {
        return transferDao.getTransferByTransferId(transferId);
    }

    @RequestMapping (path = "/transfers/{userId}", method = RequestMethod.GET)
    public List<Transfer> getTransferListByUserId (@PathVariable int userId) {
        return transferDao.getTransferListByUserId(userId);
    }

    @RequestMapping(path = "/accounts/{userId}", method = RequestMethod.GET)
    public List<Account> getAccounts(@PathVariable int userId) {
        return accountDao.listAll(userId);
    }

    @RequestMapping(path = "/account/userid/{userId}", method = RequestMethod.GET)
    public Account getAccountByUserId(@PathVariable int userId) {
        return accountDao.getAccountByUserId(userId);
    }

    @RequestMapping(path = "/account/accountid/{accountId}", method = RequestMethod.GET)
    public Account getAccountByAccountId(@PathVariable int accountId) {
        return accountDao.getAccountByAccountId(accountId);
    }

    //    @PreAuthorize("permitAll")
    @RequestMapping(path = "/account/balanceupdates", method = RequestMethod.PUT)
    public void updateBalance(@RequestBody Transfer transfer) {
        accountDao.updateBalances(transfer);
    }
}


}

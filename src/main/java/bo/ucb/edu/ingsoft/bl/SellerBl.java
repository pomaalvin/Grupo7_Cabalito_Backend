package bo.ucb.edu.ingsoft.bl;

import bo.ucb.edu.ingsoft.dao.PersonDao;
import bo.ucb.edu.ingsoft.dao.SellerDao;
import bo.ucb.edu.ingsoft.dao.TransactionDao;
import bo.ucb.edu.ingsoft.dao.UserDao;
import bo.ucb.edu.ingsoft.dto.SellerRequest;
import bo.ucb.edu.ingsoft.model.Person;
import bo.ucb.edu.ingsoft.model.Seller;
import bo.ucb.edu.ingsoft.model.Transaction;
import bo.ucb.edu.ingsoft.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerBl {
    private SellerDao sellerDao;
    private PersonDao personDao;
    private UserDao userDao;
    private TransactionDao transactionDao;

    private UserUtil userUtil;

    @Autowired
    public SellerBl(SellerDao sellerDao,UserDao userDao, PersonDao personDao, TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
        this.userDao=userDao;
        this.personDao=personDao;
        this.sellerDao = sellerDao;
        userUtil=new UserUtil();
    }

    public SellerRequest findSellerById(Integer sellerId) {
        return  sellerDao.findBySellerId(sellerId);
    }

    public SellerRequest create(SellerRequest sellerRequest, Transaction transaction){

        /*
        User user =new User();
        user.setPassword(sellerRequest.getPassword());
        user.setEmail(sellerRequest.getEmail());
        user.setTransaction(transaction);

        userDao.createUser(user);
        int userId=transactionDao.getLastInsertId();*/
        Seller seller = new Seller();
        Person person = new Person();
        person.setFirstName(sellerRequest.getFirstName());
        person.setLastName(sellerRequest.getLastName());
        person.setPhoneNumber(sellerRequest.getPhoneNumber());
        person.setTransaction(transaction);
        personDao.createPerson(person);
        int personId=transactionDao.getLastInsertId();
        seller.setImagePath(sellerRequest.getImagePath());
        seller.setIdPerson(personId);
        seller.setIdUser(userUtil.getIdUser());
        seller.setTransaction(transaction);
        sellerDao.createSeller(seller);
        int sellerId=transactionDao.getLastInsertId();
        sellerRequest.setIdSeller(sellerId);
        sellerRequest.setIdPerson(personId);
        seller.setIdUser(sellerId);
        return sellerRequest;
    }
}

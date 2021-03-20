package bo.ucb.edu.ingsoft.dao;

import bo.ucb.edu.ingsoft.dto.SellerRequest;
import bo.ucb.edu.ingsoft.dto.UserRequest;
import bo.ucb.edu.ingsoft.model.Seller;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerDao {
    public SellerRequest findBySellerId(Integer sellerId);
    public UserRequest findByUserId(Integer userId);
    public void createSeller(Seller seller);
}
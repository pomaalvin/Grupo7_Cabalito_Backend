package bo.ucb.edu.ingsoft.dao;

import bo.ucb.edu.ingsoft.dto.MechanicSellerRequest;
import bo.ucb.edu.ingsoft.dto.MechanicSimpleRequest;
import bo.ucb.edu.ingsoft.dto.QualifyMechanicRequest;
import bo.ucb.edu.ingsoft.model.Mechanic;
import bo.ucb.edu.ingsoft.model.Seller;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MechanicDao {
    public List<MechanicSimpleRequest> mechanics();
    public void qualifyMechanic(QualifyMechanicRequest qualifyMechanicRequest);

    public Mechanic stars (Integer idMechanic);

    public void starsValue(Mechanic mechanic);

    public void createMechanic(Mechanic mechanic);

    public void updateImage(Mechanic mechanic);

    public MechanicSellerRequest mechanicSeller(Integer idSeller);
}

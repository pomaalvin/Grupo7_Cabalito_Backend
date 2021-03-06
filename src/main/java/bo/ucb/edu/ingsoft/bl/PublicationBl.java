package bo.ucb.edu.ingsoft.bl;

import bo.ucb.edu.ingsoft.dao.*;
import bo.ucb.edu.ingsoft.dto.*;

import bo.ucb.edu.ingsoft.model.*;
import bo.ucb.edu.ingsoft.util.StorageUtil;
import bo.ucb.edu.ingsoft.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PublicationBl {
    private PublicationDao publicationDao;
    private TransactionDao transactionDao;

    @Autowired
    public PublicationBl(PublicationDao publicationDao,TransactionDao transactionDao) {
        this.publicationDao = publicationDao;
        this.transactionDao = transactionDao;
    }


    public List<PublicationSimpleRequest> publications(Integer idPublication, Integer i, Integer n, Integer idColor, Integer model, Integer idBrand, Integer doorNumber, Integer idCity, String title){
        PublicationRequest publicationRequest=new PublicationRequest();
        publicationRequest.setIdPublication(idPublication);
        publicationRequest.setIdColor(idColor);
        publicationRequest.setModel(model);
        publicationRequest.setIdBrand(idBrand);
        publicationRequest.setDoorNumber(doorNumber);
        publicationRequest.setIdCity(idCity);

        publicationRequest.setTitle(title);
        Integer idSeller=new UserUtil().getIdSeller();
        return publicationDao.publications(publicationRequest,i,n,idSeller);
    }

    public PublicationRequest create(PublicationRequest publicationRequest, Transaction transaction){

        Publication publication=new Publication();
        publication.setDescription(publicationRequest.getDescription());
        publication.setDoorNumber(publicationRequest.getDoorNumber());
        publication.setIdBrand(publicationRequest.getIdBrand());
        publication.setIdCity(publicationRequest.getIdCity());
        publication.setIdColor(publicationRequest.getIdColor());
        publication.setIdSeller(publicationRequest.getIdSeller());
        publication.setLicensePlate(publicationRequest.getLicensePlate().toUpperCase());
        publication.setTitle(publicationRequest.getTitle());
        publication.setModel(publicationRequest.getModel());
        publication.setMotor(publicationRequest.getMotor());
        publication.setModel(publicationRequest.getModel());
        publication.setPrice(publicationRequest.getPrice());
        publication.setStatus(publicationRequest.getStatus());
        publication.setTransaction(transaction);
        publication.setDatePublication(new Date());
        publicationDao.create(publication);
        Integer publicationId=transactionDao.getLastInsertId();
        publicationRequest.setIdPublication(publicationId);
        return publicationRequest;
    }

    public PublicationRequest update(PublicationRequest publicationRequest, Transaction transaction,Integer idSeller){

        Publication publication=new Publication();
        publication.setIdPublication(publicationRequest.getIdPublication());
        publication.setDescription(publicationRequest.getDescription());
        publication.setDoorNumber(publicationRequest.getDoorNumber());
        publication.setIdBrand(publicationRequest.getIdBrand());
        publication.setIdCity(publicationRequest.getIdCity());
        publication.setIdColor(publicationRequest.getIdColor());
        publication.setIdSeller(idSeller);
        publication.setLicensePlate(publicationRequest.getLicensePlate());
        publication.setTitle(publicationRequest.getTitle());
        publication.setModel(publicationRequest.getModel());
        publication.setMotor(publicationRequest.getMotor());
        publication.setModel(publicationRequest.getModel());
        publication.setPrice(publicationRequest.getPrice());
        publication.setStatus(publicationRequest.getStatus());
        publication.setTransaction(transaction);
        publication.setDatePublication(new Date());
        publicationDao.update(publication);
        return publicationRequest;
    }

    public boolean deleteImages(List<ImagePublicationRequest> imagesDelete, Transaction transaction){
        try{
            imagesDelete.forEach((image)->{
                ImagePublication imagePublication=new ImagePublication();
                imagePublication.setIdImagePublication(image.getIdImagePublication());
                imagePublication.setPath(image.getPath());
                imagePublication.setTransaction(transaction);
                System.out.println(imagePublication);
                publicationDao.deleteImagePublication(imagePublication);
            });
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public void uploadImages(MultipartFile[] images, Integer idPublication, Transaction transaction){
        List<ImagePublication> imagePublications=new ArrayList<>();
        Arrays.asList(images).stream().forEach(image -> {
            StorageUtil storageUtil= new StorageUtil();
            String nombre=storageUtil.upload(image,"imagePublication");
            ImagePublication imagePublication= new ImagePublication();
            imagePublication.setPath(nombre);
            imagePublication.setIdPublication(idPublication);
            imagePublication.setTransaction(transaction);
            imagePublications.add(imagePublication);
        });
        if(images.length>0){
            publicationDao.createImagePublication(imagePublications);}
    }
    public void delete(Integer idPublication, Transaction transaction){
        Publication publication= new Publication();
        publication.setStatus(false);
        publication.setIdPublication(idPublication);
        publication.setTransaction(transaction);
        publicationDao.delete(publication);
    }
    public List<Color> getColors(){
        return publicationDao.colors();
    }
    public List<Brand> getBrands(){
        return publicationDao.brands();
    }
    public List<City> getCities(){
        return publicationDao.cities();
    }



    public PublicationViewRequest publicationsView(Integer idPublication) {
        PublicationViewRequest publicationViewRequest=publicationDao.publicationView(idPublication);
        if(publicationViewRequest!=null)
            publicationViewRequest.setImages(publicationDao.publicationPaths(idPublication));
        return publicationViewRequest;
    }

}

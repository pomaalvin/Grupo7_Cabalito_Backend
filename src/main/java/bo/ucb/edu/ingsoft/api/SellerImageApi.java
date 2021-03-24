package bo.ucb.edu.ingsoft.api;

import bo.ucb.edu.ingsoft.bl.SellerBl;
import bo.ucb.edu.ingsoft.util.StorageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sellerImage")
public class SellerImageApi {
    //private static final Logger LOGGER = LoggerFactory.getLogger(MechanicApi.class);
    private SellerBl sellerBl;

    @Autowired
    public SellerImageApi(SellerBl sellerBl) {
        this.sellerBl = sellerBl;
    }

    @RequestMapping(path="/{path}/{name}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getImage(@PathVariable("path") String path, @PathVariable("name") String name){
        System.out.println(path);
        StorageUtil storageUtil=new StorageUtil();
        byte[] image=storageUtil.get(path,name);
        return image;
    }
}
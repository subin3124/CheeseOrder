package com.cheeseorder.cheeseorder.Service;

import com.cheeseorder.cheeseorder.DTO.MessageResponse;
import com.cheeseorder.cheeseorder.DTO.ObjectResponse;
import com.cheeseorder.cheeseorder.Entity.MenuEntity;
import com.cheeseorder.cheeseorder.Repository.MenuRepository;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public ObjectResponse getMenuList(long shopId) {
        try{
           List<MenuEntity> list = menuRepository.findMenuEntitiesByShopId(shopId);
           ObjectResponse objectResponse = new ObjectResponse(200,list);
           return objectResponse;
        }catch (Exception e) {
            return new ObjectResponse(400,e.getMessage());
        }
    }

    public MessageResponse uploadImage(MultipartFile image,String menuId) {
        try {
            String FILE_SERVER_PATH = "";
            LocalDateTime localDateTime = LocalDateTime.now();
            image.transferTo(new File(FILE_SERVER_PATH + "/image_" + menuId + "_" + localDateTime.toString() + ".png"));
            return new MessageResponse(200,FILE_SERVER_PATH + "/image_" + menuId + "_" + localDateTime.toString() + ".png");
        }catch (IOException e) {
            return new MessageResponse(400, e.getMessage());
        }
    }
}

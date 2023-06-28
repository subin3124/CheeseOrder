package com.cheeseorder.cheeseorder.Service;

import com.cheeseorder.cheeseorder.DTO.MessageResponse;
import com.cheeseorder.cheeseorder.Entity.QrEntity;
import com.cheeseorder.cheeseorder.Repository.QrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
public class QrService {

    @Autowired
    QrRepository qrRepository;

    public String StringReplace(String str){
        String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
        str = str.replaceAll(match, "");
        return str;
    }
   public MessageResponse CreateNewQrCode(String tableId) {
        try{
           String date =  "Date"+LocalDateTime.now().getYear()+LocalDateTime.now().getMonthValue()+LocalDateTime.now().getDayOfMonth()+LocalDateTime.now().getHour()+LocalDateTime.now().getMinute()+LocalDateTime.now().getSecond()+LocalDateTime.now().getNano();
            String qrId = tableId+date;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            String hashQrId = String.valueOf(messageDigest.digest(qrId.getBytes(StandardCharsets.UTF_8)));
            String qid = StringReplace(hashQrId);
            QrEntity entity = new QrEntity();
            entity.setQrId(qid);
            entity.setTableId(tableId);
            entity.setVaild(true);
            qrRepository.save(entity);
            return new MessageResponse(200,entity.getQrId());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }catch (DataAccessException e){
            return new MessageResponse(400,"Data Access Error : "+e.getMessage());
        }
    }
    public boolean isExistQrCode(String tableId) {
       try{
           return qrRepository.existsQrEntityByTableId(tableId);
       }catch (DataAccessException e) {
           throw new Error(e.getMessage());
       }
    }
    public MessageResponse getQrCode(String tableId) {
       try{
           QrEntity entity = qrRepository.findQrEntityByTableIdAndisVaild(tableId);
          return new MessageResponse(200, entity.getQrId());
       }catch (DataAccessException e) {
           return new MessageResponse(400,"DataAccessError : "+e.getMessage());
       }
    }
    public MessageResponse setUnVaildationQrCode(String tableId) {
       try{
           System.out.println("set invaild"+tableId);
           QrEntity entity = qrRepository.findQrEntityByTableIdAndisVaild(tableId);
           entity.setVaild(false);
           qrRepository.save(entity);
           return new MessageResponse(200,"success");
       }catch (DataAccessException e) {
           e.printStackTrace();
           return new MessageResponse(400,"DataAccessError : aaa"+e.getMessage());
       }
    }
    public MessageResponse getTableIdFromQrCode(String qrId) {
       try{
           QrEntity entity = qrRepository.findQrEntityByQrId(qrId);
          return new MessageResponse(200, entity.getTableId());
       }catch (DataAccessException e) {
           e.printStackTrace();
           return new MessageResponse(400,e.getMessage());
       }
    }
}

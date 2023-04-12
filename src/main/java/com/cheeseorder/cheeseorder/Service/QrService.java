package com.cheeseorder.cheeseorder.Service;

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

   public QrEntity CreateNewQrCode(String tableId) {
        try{
           String date =  "Date"+LocalDateTime.now().getYear()+LocalDateTime.now().getMonthValue()+LocalDateTime.now().getDayOfMonth()+LocalDateTime.now().getHour()+LocalDateTime.now().getMinute()+LocalDateTime.now().getSecond()+LocalDateTime.now().getNano();
            String qrId = tableId+date;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            String hashQrId = String.valueOf(messageDigest.digest(qrId.getBytes(StandardCharsets.UTF_8)));
            QrEntity entity = new QrEntity();
            entity.setQrId(hashQrId);
            entity.setTableId(tableId);
           return qrRepository.save(entity);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }catch (DataAccessException e){
            throw new Error("Data load Error : "+e.getMessage());
        }
    }
}

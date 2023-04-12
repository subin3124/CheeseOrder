package com.cheeseorder.cheeseorder.Controller;


import com.cheeseorder.cheeseorder.DTO.MessageResponse;
import com.cheeseorder.cheeseorder.Service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QrController {

    @Autowired
    QrService qrService;

    @PostMapping("/Admin/Qr/{tableId}")
    private MessageResponse CreateNewQrCode(@PathVariable("tableId") String tableId) {
        if (qrService.isExistQrCode(tableId)) {
            qrService.setUnVaildationQrCode(tableId);
        }
       return qrService.CreateNewQrCode(tableId);
    }

}

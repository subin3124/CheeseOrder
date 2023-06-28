package com.cheeseorder.cheeseorder.Controller;


import com.cheeseorder.cheeseorder.DTO.MessageResponse;
import com.cheeseorder.cheeseorder.Service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class QrController {

    @Autowired
    QrService qrService;

    @PostMapping("/Admin/Qr/{tableId}")
    private MessageResponse CreateNewQrCode(@PathVariable("tableId") String tableId) {
        if (qrService.isExistQrCode(tableId)) {
            System.out.println("setvaild");
            qrService.setUnVaildationQrCode(tableId);
        }
       return qrService.CreateNewQrCode(tableId);
    }
    @GetMapping("/Admin/Qr/{tableId}")
        private MessageResponse getVaildQrCode(@PathVariable("tableId") String tableId) {
        if(!qrService.isExistQrCode(tableId)){
            MessageResponse mesgr = qrService.CreateNewQrCode(tableId);
            if(mesgr.getCode()!=200)
                return mesgr;
        }
        return qrService.getQrCode(tableId);
        }


}

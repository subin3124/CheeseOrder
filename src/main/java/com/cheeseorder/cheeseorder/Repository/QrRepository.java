package com.cheeseorder.cheeseorder.Repository;

import com.cheeseorder.cheeseorder.Entity.QrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QrRepository extends JpaRepository<QrEntity,String> {
    public boolean existsQrEntityByTableId(String tableId);
    @Query(value = "update qrCode set isVaild=false where :tableId",nativeQuery = true)
    public void invalidationQrCode(@Param("tableId") String tableId);

    public QrEntity findQrEntityByQrId(String qrId);
}

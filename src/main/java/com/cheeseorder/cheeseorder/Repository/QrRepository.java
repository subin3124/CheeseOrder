package com.cheeseorder.cheeseorder.Repository;

import com.cheeseorder.cheeseorder.Entity.QrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QrRepository extends JpaRepository<QrEntity,String> {
    public boolean existsQrEntityByTableId(String tableId);
    @Query(value = "update qr_code set is_vaild=false where table_id=:tableId",nativeQuery = true)
    public void invalidationQrCode(@Param("tableId")String tableId);

    public QrEntity findQrEntityByQrId(String qrId);
    @Query(value = "select * from qr_code where table_id=?1 and is_vaild=true",nativeQuery = true)
    public QrEntity findQrEntityByTableIdAndisVaild(String tableId);
    @Query(value = "select * from qr_code where table_id=?1",nativeQuery = true)
    public QrEntity findQrEntityByTableId(String tableId);
}

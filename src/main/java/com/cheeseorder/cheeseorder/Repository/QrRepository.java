package com.cheeseorder.cheeseorder.Repository;

import com.cheeseorder.cheeseorder.DTO.QrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrRepository extends JpaRepository<QrEntity,String> {


}
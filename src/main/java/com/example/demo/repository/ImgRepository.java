package com.example.demo.repository;

import com.example.demo.model.ImgModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgRepository extends JpaRepository<ImgModel, Integer> {

}

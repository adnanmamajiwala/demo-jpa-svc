package com.example.demojpasvc.sample;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SampleRepository extends JpaRepository<Sample, Long> {

    @Query("select s from Sample s " +
            "where s.org like %:text% or s.name like %:text% or s.code like %:text% or s.description like %:text% " +
            " or s.batchName like %:text% or s.batchCode like %:text% or s.unit like %:text% or s.brand like %:text% " +
            " or s.qrCode like %:text% or concat(s.id,'') like %:text% or concat(s.price,'') like %:text% " +
            " or concat(s.tax,'') like %:text% or concat(s.start,'') like %:text% or concat(s.end,'') like %:text% " +
            " or concat(s.total,'') like %:text% or concat(s.quantity,'') like %:text% or concat(s.weight,'') like %:text% ")
    Page<Sample> searchByText(String text, Pageable pageable);

    List<Sample> findAllByBatchCodeIn(List<String> codes);

}

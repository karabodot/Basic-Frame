package com.example.surveyapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.surveyapp.Model.SurveyResponse;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {
}



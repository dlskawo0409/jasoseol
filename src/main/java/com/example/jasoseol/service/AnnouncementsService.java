package com.example.jasoseol.service;


import com.example.jasoseol.domain.*;
import com.example.jasoseol.dto.AddAnnouncementRequest;
import com.example.jasoseol.dto.AddAnnouncementDetailsRequest;
import com.example.jasoseol.dto.AddQuestionRequest;
import com.example.jasoseol.dto.CompanyNameAndTitleDTO;
import com.example.jasoseol.repository.*;

import jakarta.transaction.Transactional;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnouncementsService {


    private final CompanyRepository companyRepository;
    private final CompanyUserRepository companyUserRepository;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementDetailsRepository announcementDetailsRepository;
    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;

    public AnnouncementsService(AnnouncementRepository announcementRepository, CompanyRepository companyRepository, CompanyUserRepository companyUserRepository, AnnouncementDetailsRepository announcementDetailsRepository, CategoryRepository categoryRepository, QuestionRepository questionRepository){
        this.announcementRepository = announcementRepository;
        this.companyRepository = companyRepository;
        this.companyUserRepository = companyUserRepository;
        this.announcementDetailsRepository = announcementDetailsRepository;
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
    }
    @Transactional
    public boolean addAnnouncement(String email, AddAnnouncementRequest joinDTO){

        Long companyUserByEmail = companyUserRepository.findByEmail(email).getId();

        Optional<Company> companyOptional = companyRepository.findByCompanyName(joinDTO.getCompanyName());
        if (!companyOptional.isPresent() || companyUserByEmail == null) {
            return false;
        }
        Company company = companyOptional.get();

        Long companyUserByCompany = company.getCompanyUser().getId();
        if(companyUserByEmail != companyUserByCompany){ // 권한 없음
            return false;
        }

        announcementRepository.save(Announcement.builder()
                .company(company)
                .announcementTitle(joinDTO.getAnnouncementTitle())
                .startDay(joinDTO.getStartDay())
                .endDay(joinDTO.getEndDay())
                .clicked(0)
                .bookmarkCont(0)
                .visited(0)
                .build()
        );
        return true;
    }
    @Transactional
    public boolean addAnnouncementDetails(String email, AddAnnouncementDetailsRequest dto){
        CompanyUser companyUser = companyUserRepository.findByEmail(email);
        Company userCompany = companyUser.getCompany();

        Announcement announcement = announcementRepository.findByAnnouncementId(dto.getAnnouncementId());
        Company announcementCompany = announcement.getCompany();

        if(companyUser == null || announcement == null || userCompany == null || announcementCompany == null){
            return false;
        }

        if(!userCompany.equals(announcementCompany)){
            return false;
        }
        Category category = Category.builder()
                .bigCategory(dto.getBigCategory())
                .middleCategory(dto.getMiddleCategory())
                .smailCategory(dto.getSmallCategory())
                .build();
        categoryRepository.save(category);

        announcementDetailsRepository.save(AnnouncementDetails.builder()
                        .type(dto.getType())
                        .content(dto.getContent())
                        .announcement(announcement)
                        .writersCount(0)
                        .category(category)
                        .build()
        );

        return true;

    }

    @Transactional
    public boolean addQuestion(AddQuestionRequest dto){

        AnnouncementDetails announcementDetails = announcementDetailsRepository.findByDetailsId(dto.getAnnouncementDetailsId());
        if(announcementDetails == null){
            return false;
        }

        questionRepository.save(Question.builder()
                .questionContent(dto.getQuestion())
                .letter(dto.getLetter())
                .announcementDetails(announcementDetails)
                .build()
        );


        return true;
    }

//    @Transactional
//    public JSONArray getSearchTextOnToday( String title) throws JSONException { // 성능 향샹 여지가 있을 듯
//        List<Announcement> result = announcementRepository.findByDateAndText(LocalDateTime.now(), title);
//        JSONArray jsonArray = new JSONArray();
//
//        for(Announcement now : result){
//            JSONObject joByString = new JSONObject();
//            joByString.put(now.getCompany().getCompanyName(), now.getAnnouncementTitle());
//            jsonArray.put(joByString);
//
//        }
//
//
//        return jsonArray;
//    }
    @Transactional
    public List<CompanyNameAndTitleDTO> getSearchTextOnToday(String text) {
        String decodedText= URLDecoder.decode(text, StandardCharsets.UTF_8);
        List<Object[]> results = announcementRepository.findByDateAndText(LocalDateTime.now(), decodedText);

        return results.stream()
                .map(result -> new CompanyNameAndTitleDTO((String) result[0], (String) result[1]))
                .collect(Collectors.toList());
    }

}

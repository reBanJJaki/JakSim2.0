package com.twinkle.JakSim.controller.trainer;

import com.twinkle.JakSim.model.dto.timetable.TimetableInsertDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerInsertDto;
import com.twinkle.JakSim.model.dto.trainer.*;
import com.twinkle.JakSim.model.service.account.FileService;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import com.twinkle.JakSim.model.service.review.ReviewService;
import com.twinkle.JakSim.model.service.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class TrainerController {
    @Autowired
    TrainerService trainerService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    FileService fileService;
    @GetMapping("/trainer/trainerRegister")
    public String trainerSignUp(Model model,  @AuthenticationPrincipal User info) {

        if(info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }

        model.addAttribute("head_title", "트레이너 등록");
        model.addAttribute("userId", info);

        return "content/trainer/trainerRegister";
    }

    @PostMapping("/trainerRegister")
    public String trainerSignUp(TrainerInsertDto trainerDto, @AuthenticationPrincipal User info, Model model,
                                @RequestParam("certImage1") MultipartFile certImage,
                                @RequestParam("imagePath1") MultipartFile[] imagePath) throws IOException {
        model.addAttribute("head_title", "트레이너 등록");

        for (MultipartFile file : imagePath) {
            System.out.println("파일 이름: " + file.getOriginalFilename());
            System.out.println("파일 크기: " + file.getSize());
            // 파일 저장 등의 로직 수행
        }

        System.out.println("cert 파일 이름: " + certImage.getOriginalFilename());
        System.out.println("cert 파일 크기: " + certImage.getSize());
        trainerService.TrainerSignUp(trainerDto, info.getUsername(), certImage, imagePath);

        return "redirect:/";
    }

    @GetMapping("/trainer/trainerUpdate/{userId}")
    public String trainerUpdate(Model model, @PathVariable("userId") String userId, @AuthenticationPrincipal User info) {

        if(info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }

        model.addAttribute("head_title", "트레이너 정보수정");
        model.addAttribute("userId", info);
        model.addAttribute("trainer", trainerService.searchTrainer(userId));
        model.addAttribute("product", trainerService.getProduct(userId));
        model.addAttribute("cert", trainerService.getCert(userId));
        model.addAttribute("career", trainerService.getCareer(userId));
        model.addAttribute("imageList", trainerService.getTrainerImage(userId));
        model.addAttribute("name", trainerService.searchTrainerName(userId));

        return "content/trainer/trainerPage";
    }

    @PostMapping("/trainerUpdate")
    public String trainerUpdate(TrainerInsertDto trainerDto, @AuthenticationPrincipal User info, Model model,
                                @RequestParam("certImage1") MultipartFile certImage,
                                @RequestParam("imagePath1") MultipartFile[] imagePath) throws IOException {
        System.out.println("cert Multipart : " + certImage.getOriginalFilename());
        System.out.println("cert Multipart : " + certImage.getSize());

        for(MultipartFile file : imagePath) {
            System.out.println("image Multipart : " + file.getOriginalFilename());
            System.out.println("image Multipart : " + file.getSize());
            System.out.println("image Multipart : " + file.isEmpty());
            System.out.println("image Multipart : " + file.equals(""));
        }

        System.out.println("redirect update : " +trainerDto.toString());
        trainerService.updateTrainer(trainerDto, info.getUsername(), certImage, imagePath);

        return "redirect:/trainerUpdate/" + info.getUsername();
    }

    @PostMapping("/trainerDelete")
    public String trainerDelete(TrainerInsertDto trainerDto, @AuthenticationPrincipal User info) {
        //System.out.println("컨트롤러 del : "+ trainerDto.toString());
        trainerService.deleteTrainer(trainerDto ,info.getUsername());

        return "redirect:/";
    }

    @GetMapping("/trainer/{userId}")
    public String viewTrainer(@PathVariable("userId") String userId, @AuthenticationPrincipal User info, Model model) throws SQLException {
        if(info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }

        model.addAttribute("head_title", "트레이너 상세페이지");
        model.addAttribute("session", info);
        model.addAttribute("trainer", trainerService.searchTrainer(userId));
        model.addAttribute("review", reviewService.showReview(userId));
        model.addAttribute("product", trainerService.getProduct(userId));
        model.addAttribute("cert", trainerService.getCert(userId));
        model.addAttribute("career", trainerService.getCareer(userId));
        model.addAttribute("imageList", trainerService.getTrainerImage(userId));
        //model.addAttribute("starAvg", reviewService.getStarAvg(userId));

        return "content/trainer/trainerDetailPage";
    }


    @GetMapping("/trainer/trainerSearch")
    public String viewTrainerSearch(Model model, @AuthenticationPrincipal User info,
                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "pageSize", defaultValue = "3") int pageSize,
                                    @RequestParam(value = "filter", defaultValue = "-1") int filter ) {

        if (info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }
        model.addAttribute("head_title", "트레이너 찾기");
        model.addAttribute("userId", info);

        // 페이징을 위한 데이터 조회
        List<TrainerSearchDto> trainers = trainerService.searchAllTrainer(page, pageSize, filter);
        int totalTrainers = trainerService.getTrainerCnt(filter);

        model.addAttribute("trainers", trainers);
        model.addAttribute("currentPage", page);
        model.addAttribute("trainersPerPage", pageSize);
        model.addAttribute("filter", filter);

        int totalPages = (int) Math.ceil((double) totalTrainers / pageSize);

        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            pageNumbers.add(i);
        }
        model.addAttribute("pageNumbers", pageNumbers);

        // Pre-calculate previous and next page numbers
        int prevPage = (page > 1) ? page - 1 : 1;
        int nextPage = (page < totalPages) ? page + 1 : totalPages;

        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);

        return "content/trainer/trainerSearch";
    }

    public int getTotalPages(int totalItems, int pageSize) {
        return (int) Math.ceil((double) totalItems / pageSize);
    }



    @GetMapping("/trainer/trainerControl")
    public String trainerControl(Model model, @AuthenticationPrincipal User info){

        if(info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }

        model.addAttribute("head_title", "트레이너 관리페이지");
        model.addAttribute("userId", info);
        model.addAttribute("timetable", trainerService.getTimetable(info.getUsername()));
        model.addAttribute("name", trainerService.searchTrainerName(info.getUsername()));

        return "content/trainer/trainerPage2";
    }
    @PostMapping("/trainer/ptTimetableRegister")
    public String timetableRegister(Model model, @AuthenticationPrincipal User info, TimetableInsertDto timetable){
        model.addAttribute("userId", info);
        trainerService.registerTimetable(timetable, info.getUsername());

        return "redirect:/trainer/trainerControl";
    }
    @PostMapping("/trainer/ptTimetableUpdate")
    public String timetableDelete(@RequestParam("tIdx") int tIdx){
        trainerService.deleteTimetable(tIdx);

        return "redirect:/trainer/trainerControl";
    }

    @GetMapping("/trainer/ptUserInfo")
    public String ptUserInfo(Model model, @AuthenticationPrincipal User info,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "1") int pageSize,
                             @RequestParam(value = "ptUserName", defaultValue = "-") String ptUserName){

        if(info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }

        model.addAttribute("head_title", "트레이너 관리페이지");
        model.addAttribute("userId", info);
        model.addAttribute("ptUser", trainerService.getMyPtUserInfo(page, pageSize, info.getUsername(), ptUserName));
        model.addAttribute("name", trainerService.searchTrainerName(info.getUsername()));
        model.addAttribute("ptUserName", ptUserName);

        // 페이징을 위한 데이터 조회
        int totalPtUsers = trainerService.getPtUserCnt(info.getUsername(), ptUserName);

        model.addAttribute("currentPage", page);
        model.addAttribute("trainersPerPage", pageSize);

        int totalPages = (int) Math.ceil((double) totalPtUsers / pageSize);

        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            pageNumbers.add(i);
        }
        model.addAttribute("pageNumbers", pageNumbers);

        // Pre-calculate previous and next page numbers
        int prevPage = (page > 1) ? page - 1 : 1;
        int nextPage = (page < totalPages) ? page + 1 : totalPages;

        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);

        return "content/trainer/trainerPage3";
    }

    @GetMapping("/modalTest")
    public String modalTest(Model model) {
        model.addAttribute("head_title", "주소 검색");
        return "content/trainer/addressTest";
    }

    @GetMapping("/address-search")
    public String addressTest() {
        return "content/trainer/addressModal";
    }

}
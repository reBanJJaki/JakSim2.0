package com.twinkle.JakSim.controller.payment;

import com.twinkle.JakSim.model.dto.payment.response.PaymentDo;
import com.twinkle.JakSim.model.dto.trainer.ProductDto;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import com.twinkle.JakSim.model.dto.payment.response.ApproveResponse;
import com.twinkle.JakSim.model.service.payment.KakaoPayService;
import com.twinkle.JakSim.model.service.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final KakaoPayService kakaoPayService;
    private final PaymentService paymentService;
    private final TrainerService trainerService;


    private final String defaultPage = "/content/payment/";

    // 결제 진행 중 취소
    @GetMapping()
    public String scheduler() {
        return "content/payment/KakaoPay";
    }

    // 결제 진행 중 취소
    @GetMapping("/cancel")
    public String cancel() {
        return "content/payment/kakaoPay/Cancle";
    }

    // 결제 승인 (성공)
    @GetMapping("/success")
    public String afterPayRequest(@AuthenticationPrincipal User user, @RequestParam("pg_token") String pgToken, Model model) {
        ApproveResponse kakaoApprove = kakaoPayService.approveResponse(user.getUsername(), pgToken);

        if(kakaoApprove != null) {
            if(paymentService.savePaymentDetails(user.getUsername(), kakaoApprove)) {
                kakaoApprove.setApproved_at(kakaoApprove.getApproved_at().replace("T", " "));
                model.addAttribute("kakaoApprove", kakaoApprove);

                return "content/payment/kakaoPay/Success";
            }
        }

        // 일단은 에러처리 안함.
        return "content/payment/kakaoPay/Success";
    }
    
    ////////////////////////////////
    @GetMapping("/list")
    public String payListPage(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("head_title", "결제 확인 목록");
        model.addAttribute("total_page", paymentService.getTotalPage(user.getUsername()));

        return String.format(defaultPage + "pay_list");
    }

    @GetMapping("/detail/{idx}")
    public String payDetailPage(@AuthenticationPrincipal User user, @PathVariable("idx") String tid, Model model){
        model.addAttribute("head_title", "결제 내역 상세");
        paymentService.getPaymentByTid(tid).ifPresent(
                item -> {
                    model.addAttribute("payment", item);
                    ProductDto product = paymentService.getProductByIdx(item.getTp_idx());
                    model.addAttribute("product", product);
                    model.addAttribute("trainer", trainerService.searchByUsername(product.getUserId()));
                }
        );
        model.addAttribute("apiResponse", kakaoPayService.kakaoList(tid));
        return String.format(defaultPage + "pay_detail");
    }

    @GetMapping("/api/{page}")
    @ResponseBody
    public List<PaymentDo> payListItem(@AuthenticationPrincipal User user, @PathVariable("page") int page){
        return paymentService.getPageItem(user.getUsername(), page);
    }
    
}

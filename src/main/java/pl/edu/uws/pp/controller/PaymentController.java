package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.domain.dto.payment.*;
import pl.edu.uws.pp.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PreAuthorize("hasRole('RESIDENT')")
    @PostMapping
    public PaymentShortResponse createPayment(@RequestBody PaymentRequest request){
        return paymentService.createPayment(request);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<PaymentShortResponse> paymentList(){
        return paymentService.getPaymentsList();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public PaymentResponse paymentInfo(@PathVariable Long id){
        return paymentService.getPaymentInfo(id);
    }
}

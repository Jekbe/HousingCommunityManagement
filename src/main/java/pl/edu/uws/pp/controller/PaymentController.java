package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.payment.*;
import pl.edu.uws.pp.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PreAuthorize("hasRole('RESIDENT')")
    @PostMapping
    public PaymentShortResponse createPayment(@RequestBody PaymentRequest request,
                                              @AuthenticationPrincipal UserPrincipal user) {
        return paymentService.createPayment(request, user);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<PaymentShortResponse> paymentList(@AuthenticationPrincipal UserPrincipal user) {
        return paymentService.getPaymentsList(user);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public PaymentResponse paymentInfo(@PathVariable Long id,
                                       @AuthenticationPrincipal UserPrincipal user) {
        return paymentService.getPaymentInfo(id, user);
    }
}

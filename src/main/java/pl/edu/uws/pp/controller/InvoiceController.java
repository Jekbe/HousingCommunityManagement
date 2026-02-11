package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.invoice.*;
import pl.edu.uws.pp.service.InvoiceService;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PreAuthorize("hasRole('BUILDING_MANAGER')")
    @PostMapping
    public InvoiceShortResponse createInvoice(@RequestBody InvoiceRequest request,
                                              @AuthenticationPrincipal UserPrincipal user) {
        return invoiceService.createInvoice(request, user);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public InvoiceResponse invoiceInfo(@PathVariable Long id,
                                       @AuthenticationPrincipal UserPrincipal user) {
        return invoiceService.getInvoiceInfo(id, user);
    }

    @PreAuthorize("hasRole('BUILDING_MANAGER')")
    @PutMapping("/{id}")
    public InvoiceShortResponse editInvoice(@PathVariable Long id,
                                       @RequestBody InvoiceEditRequest request,
                                            @AuthenticationPrincipal UserPrincipal user) {
        return invoiceService.editInvoice(id, request, user);
    }

    @PreAuthorize("hasAnyRole('HOUSING_MANAGER', 'BUILDING_MANAGER')")
    @PatchMapping("/{id}")
    public InvoiceShortResponse changeInvoiceStatus(@PathVariable Long id,
                                               @RequestBody InvoiceChangeStatusRequest request,
                                                    @AuthenticationPrincipal UserPrincipal user) {
        return invoiceService.changeInvoiceStatus(id, request, user);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id){
        invoiceService.deleteInvoice(id);
    }
}

package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.domain.dto.invoice.*;
import pl.edu.uws.pp.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PreAuthorize("hasRole('BUILDING_MANAGER')")
    @PostMapping
    public InvoiceShortResponse createInvoice(@RequestBody InvoiceRequest request){
        return invoiceService.createInvoice(request);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public InvoiceResponse invoiceInfo(@PathVariable Long id){
        return invoiceService.getInvoiceInfo(id);
    }

    @PreAuthorize("hasRole('BUILDING_MANAGER')")
    @PutMapping("/{id}")
    public InvoiceShortResponse editInvoice(@PathVariable Long id,
                                       @RequestBody InvoiceEditRequest request){
        return invoiceService.editInvoice(id, request);
    }

    @PreAuthorize("hasAnyRole('HOUSING_MANAGER', 'BUILDING_MANAGER')")
    @PatchMapping("/{id}")
    public InvoiceShortResponse changeInvoiceStatus(@PathVariable Long id,
                                               @RequestBody InvoiceChangeStatusRequest request){
        return invoiceService.changeInvoiceStatus(id, request);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id){
        invoiceService.deleteInvoice(id);
    }
}

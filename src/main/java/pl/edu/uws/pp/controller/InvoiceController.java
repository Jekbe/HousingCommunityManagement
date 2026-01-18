package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceChangeStatusRequest;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceEditRequest;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceRequest;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceResponse;
import pl.edu.uws.pp.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    public InvoiceResponse createInvoice(@RequestBody InvoiceRequest request){
        return invoiceService.createInvoice(request);
    }

    @GetMapping("/{id}")
    public InvoiceResponse invoiceInfo(@PathVariable Long id){
        return invoiceService.getInvoiceInfo(id);
    }

    @PutMapping("/{id}")
    public InvoiceResponse editInvoice(@PathVariable Long id,
                                       @RequestBody InvoiceEditRequest request){
        return invoiceService.editInvoice(id, request);
    }

    @PatchMapping("/{id}")
    public InvoiceResponse changeInvoiceStatus(@PathVariable Long id,
                                               @RequestBody InvoiceChangeStatusRequest request){
        return invoiceService.changeInvoiceStatus(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id){
        invoiceService.deleteInvoice(id);
    }
}

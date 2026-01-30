package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Invoice;
import pl.edu.uws.pp.domain.entity.Manager;
import pl.edu.uws.pp.domain.entity.Resident;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByApartment_Building_Manager(Manager manager);
    List<Invoice> findAllByApartment_ResidentsContaining(Resident resident);
}

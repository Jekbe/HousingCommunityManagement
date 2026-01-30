package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Manager;
import pl.edu.uws.pp.domain.entity.Payment;
import pl.edu.uws.pp.domain.entity.Resident;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByInvoice_Apartment_Building_Manager(Manager manager);
    List<Payment> findAllByInvoice_Apartment_ResidentsContains(Resident resident);
    List<Payment> findAllByInvoice_Apartment_ResidentsContainsAndInvoice_Apartment_Building_Manager(Resident resident, Manager manager);
}

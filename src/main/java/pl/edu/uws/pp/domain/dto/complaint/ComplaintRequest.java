package pl.edu.uws.pp.domain.dto.complaint;

public record ComplaintRequest(
        Long managerId,
        String description
) {
}

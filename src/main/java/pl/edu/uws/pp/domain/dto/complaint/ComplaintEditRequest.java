package pl.edu.uws.pp.domain.dto.complaint;

public record ComplaintEditRequest(
        Long ComplaintId,
        String Description
) {
}

package nl.bsoft.core;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by bvpelt on 1/28/17.
 */
@Entity
public class Complaint {

    @Id
    private String complaintId;
    private String company;
    private String description;

    public Complaint(String complaintId, String company, String description) {
        this.complaintId = complaintId;
        this.company = company;
        this.description = description;
    }

    public Complaint() {
    }

    public String getComplaintId() {
        return complaintId;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }
}

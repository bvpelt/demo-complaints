package com.example;

/**
 * Created by bvpelt on 1/28/17.
 */
public class ComplaintFileEvent {
    private final String id;
    private final String company;
    private final String description;

    public ComplaintFileEvent(String id, String company, String description) {
        this.id = id;
        this.company = company;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }
}

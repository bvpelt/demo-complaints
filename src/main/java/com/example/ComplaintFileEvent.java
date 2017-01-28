package com.example;

/**
 * Created by bvpelt on 1/28/17.
 */
public class ComplaintFileEvent {
    private static String id;
    private static String company;
    private static String description;

    public ComplaintFileEvent(String id, String company, String description) {
        this.id = id;
        this.company = company;
        this.description = description;
    }

    public static String getId() {
        return id;
    }

    public static String getCompany() {
        return company;
    }

    public static String getDescription() {
        return description;
    }
}

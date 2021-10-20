package com.ericsson.projectmanager.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String title;
    private String desc;
    private String status;
    @Column(nullable = false, updatable = false)
    private String projectCode;

    public Project() {}

    public Project(String title, String desc, String status, String projectCode) {
        this.title = title;
        this.desc = desc;
        this.status = status;
        this.projectCode = projectCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id + "\'" +
                ", title=" + title + "\'" +
                ", desc=" + desc + "\'" +
                ", status=" + status + "\'" +
                "}";
    }
}



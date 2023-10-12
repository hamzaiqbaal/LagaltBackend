package com.example.lagaltcaseapplication.models;
import javax.persistence.*;

@Entity
@Table(name = "industries")
public class Industry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long industryId;

    @Column(name = "industry_name")
    private String industryname;

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public String getName() {
        return industryname;
    }

    public void setName(String name) {
        this.industryname = name;
    }
}

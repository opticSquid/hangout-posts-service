package com.hangout.core.hangoutpostsservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class HierarchyKeeper {
    @Id
    @GeneratedValue
    @Column(name = "keeperid")
    private Integer keeperId;
    @ManyToOne
    @JoinColumn(name = "parentcommentid")
    Comment parentComment;
    @ManyToOne
    @JoinColumn(name = "childcommentid")
    Comment childCommnet;
}

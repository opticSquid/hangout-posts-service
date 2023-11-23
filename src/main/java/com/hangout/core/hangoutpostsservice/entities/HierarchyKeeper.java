package com.hangout.core.hangoutpostsservice.entities;

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
    private Integer keeperId;
    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    Comment parentComment;
    @ManyToOne
    @JoinColumn(name = "child_comment_id")
    Comment childCommnet;
}

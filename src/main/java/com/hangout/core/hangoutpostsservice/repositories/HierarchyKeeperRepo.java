package com.hangout.core.hangoutpostsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hangout.core.hangoutpostsservice.entities.HierarchyKeeper;

public interface HierarchyKeeperRepo extends JpaRepository<HierarchyKeeper, Integer> {

}

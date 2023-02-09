package com.example.pwmanager.repos;

import com.example.pwmanager.models.EntryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryItemRepo extends JpaRepository<EntryItem, Long> {
}

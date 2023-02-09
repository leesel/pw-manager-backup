package com.example.pwmanager.services;

import com.example.pwmanager.models.EntryItem;
import com.example.pwmanager.repos.EntryItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class EntryItemService {
    @Autowired
    private EntryItemRepo entryItemRepo;

    public Iterable<EntryItem> getAll(){
        return entryItemRepo.findAll();
    }

    public Optional<EntryItem> getById(Long id){
        return entryItemRepo.findById(id);
    }

    public EntryItem save (EntryItem entryItem){
        if(entryItem.getId() == null){
            entryItem.setCreatedAt(Instant.now());
        }

        entryItem.setUpdatedAt(Instant.now());

        return entryItemRepo.save(entryItem);
    }

    public void delete (EntryItem entryItem){
        entryItemRepo.delete(entryItem);
    }

}

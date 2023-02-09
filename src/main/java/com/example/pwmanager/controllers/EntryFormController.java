package com.example.pwmanager.controllers;

import com.example.pwmanager.models.EntryItem;
import com.example.pwmanager.services.EntryItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EntryFormController {
    @Autowired
    private EntryItemService entryItemService;

    @GetMapping("/create-entry")
    public String showCreateForm(EntryItem entryItem) {
        return "new-entry-item";
    }

    @PostMapping("/entry")
    public String createEntryItem(@Valid EntryItem entryItem, BindingResult result, Model model) {
        EntryItem item = new EntryItem();
        item.setUrl(entryItem.getUrl());

        item.setUsername(entryItem.getUsername());
        item.setPassword(entryItem.getPassword());

        entryItemService.save(entryItem);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEntryItem(@PathVariable("id") Long id, Model model) {
        EntryItem entryItem = entryItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("EntryItem id: " + id + " not found"));
        entryItemService.delete(entryItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        EntryItem entryItem = entryItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("EntryItem id: " + id + " not found"));
        model.addAttribute("entry", entryItem);
        return "edit-entry-item";
    }

    @PostMapping("/entry/{id}")
    public String updateEntryItem(@PathVariable("id") Long id, @Valid EntryItem entryItem, BindingResult result, Model model) {
        EntryItem item = entryItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("EntryItem id: " + id + " not found"));

        item.setUrl(entryItem.getUrl());
        item.setUsername(entryItem.getUsername());
        item.setPassword(entryItem.getPassword());

        entryItemService.save(item);
        return "redirect:/";
    }
}

package com.tt.sfgpetclinic.service;

import com.tt.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PerService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}

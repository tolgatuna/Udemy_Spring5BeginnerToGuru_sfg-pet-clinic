package com.tt.sfgpetclinic.bootstrap;

import com.tt.sfgpetclinic.model.Owner;
import com.tt.sfgpetclinic.model.Pet;
import com.tt.sfgpetclinic.model.PetType;
import com.tt.sfgpetclinic.model.Vet;
import com.tt.sfgpetclinic.service.OwnerService;
import com.tt.sfgpetclinic.service.PetTypeService;
import com.tt.sfgpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dogPetType = new PetType();
        dogPetType.setName("Dog");
        petTypeService.save(dogPetType);

        PetType catPetType = new PetType();
        catPetType.setName("Cat");
        petTypeService.save(catPetType);

        Owner owner1 = new Owner();
        owner1.setFirstName("Micheal");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("12321321321312");

        Pet michealPet = new Pet();
        michealPet.setName("ZuuZuu");
        michealPet.setPetType(dogPetType);
        michealPet.setBirthDate(LocalDate.now());
        michealPet.setOwner(owner1);
        owner1.getPets().add(michealPet);

        ownerService.save(owner1);


        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Gleanne");
        owner2.setAddress("333 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("4556565666555");

        Pet fionaPet = new Pet();
        fionaPet.setName("Caty");
        fionaPet.setPetType(catPetType);
        fionaPet.setBirthDate(LocalDate.now());
        fionaPet.setOwner(owner1);
        owner2.getPets().add(fionaPet);

        ownerService.save(owner2);

        System.out.println("#### ----- Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("#### ----- Loaded Vets...");
    }
}

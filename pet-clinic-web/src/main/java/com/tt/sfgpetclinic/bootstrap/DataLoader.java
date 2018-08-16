package com.tt.sfgpetclinic.bootstrap;

import com.tt.sfgpetclinic.model.*;
import com.tt.sfgpetclinic.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0)
            loadData();
    }

    private void loadData() {
        PetType dogPetType = new PetType();
        dogPetType.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dogPetType);

        PetType catPetType = new PetType();
        catPetType.setName("Cat");
        PetType savedCatPetType = petTypeService.save(catPetType);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = Owner.builder()
                .firstName("Micheal")
                .lastName("Weston")
                .address("123 Brickerel")
                .city("Miami")
                .telephone("12321321321312")
                .build();

        Pet michealPet = Pet.builder()
                .name("ZuuZuu")
                .petType(savedDogPetType)
                .birthDate(LocalDate.now())
                .owner(owner1)
                .build();
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
        fionaPet.setPetType(savedCatPetType);
        fionaPet.setBirthDate(LocalDate.now());
        fionaPet.setOwner(owner1);
        owner2.getPets().add(fionaPet);

        ownerService.save(owner2);

        System.out.println("#### ----- Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedSurgery);
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedDentistry);

        vetService.save(vet2);

        System.out.println("#### ----- Loaded Vets...");


        Visit catVisit = new Visit();
        catVisit.setPet(fionaPet);
        catVisit.setDescription("Control");
        catVisit.setDate(LocalDate.now());

        visitService.save(catVisit);
        System.out.println("#### ----- Loaded Visits...");


    }
}

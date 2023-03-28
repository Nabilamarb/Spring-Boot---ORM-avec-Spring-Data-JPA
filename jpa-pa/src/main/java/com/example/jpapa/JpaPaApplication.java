package com.example.jpapa;

import com.example.jpapa.entities.Patient;
import com.example.jpapa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaPaApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {

        SpringApplication.run(JpaPaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //pour inserer plusieur lignne
        for (int i=0;i<100;i++){
            patientRepository.save(
                    new Patient(null,"nabila",new Date(),Math.random()>0.5?true:false,(int)(Math.random()*100)));
        }

        Page<Patient> patients= patientRepository.findAll(PageRequest.of(0,5));//donnez moi le 5 patients le premier 5 patients de page 0(pagination)
        System.out.println("total pages :"+patients.getTotalPages());
        System.out.println("total elements :"+patients.getTotalElements());
        System.out.println("Num pages :"+patients.getNumber());
        List<Patient> content = patients.getContent(); //la liste de patient de cette page
        Page<Patient> byMalade = patientRepository.findByMalade(true, PageRequest.of(0,4));//afficher la liste des patient qui sont malade
        List<Patient> patientList=patientRepository.chercherPatiens("%h%",40);
        byMalade.forEach(p->{
            System.out.println("=============================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade()); //parce que boolean
        });
        System.out.println("****************************");
        Patient patient=patientRepository.findById(1L).orElse(null); //si existe return nom et malade sinon return null
        if (patient!=null){
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }
        patient.setScore(870);//pour modifier score
        patientRepository.save(patient);
        patientRepository.deleteById(1L); //pour supprimer premier element
    }
}

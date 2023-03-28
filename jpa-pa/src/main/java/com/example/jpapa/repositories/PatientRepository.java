package com.example.jpapa.repositories;

import com.example.jpapa.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
     List<Patient> findByMalade(boolean m);//vous donnez est ce que malade ou pas
    Page<Patient> findByMalade(boolean m, Pageable pageable);//vous donnez est ce que malade ou pas et quelle numero de page et le size
    List<Patient> findByMaladeAndScoreLessThan(boolean m,int score);//donnez moi tous les patients qui sont malade ou pas et dans le score est inferieure
    List<Patient> findByMaladeIsTrueAndScoreLessThan(int score);//donnez moi tous les patients qui sont malade et dans le score est inferieure
    List<Patient> findByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Date d1, Date d2,String mc);//donnez moi date naissance entre d1 et d2
    @Query("select p from Patient p where p.nom like :x and p.score< :y")//pour accepte cette requete
    List<Patient> chercherPatiens(@Param("x") String nom,@Param("y") int scoreMin);//il ya deux facon soit (findByDateNaissanceBetweenAAndMaladeIsTrueOrNomLike) ou bien (chercherPatiens)



}

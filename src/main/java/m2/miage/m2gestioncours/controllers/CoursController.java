package m2.miage.m2gestioncours.controllers;

import m2.miage.m2gestioncours.entities.Cours;
import m2.miage.m2gestioncours.exception.GeneralErreurException;
import m2.miage.m2gestioncours.services.ICoursService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping( "/cours")
public class CoursController {
    private static final Logger logger = LoggerFactory.getLogger(CoursController.class);

    @Autowired
    ICoursService coursService;

    @GetMapping(value = "/allCours")
    private ResponseEntity<List<Cours>> getAllCours() throws GeneralErreurException {
        try{
            return ResponseEntity.ok(coursService.getListAllCours());
        }catch (Exception e){
            logger.error("Une erreur est survenue : ",e);
            throw new GeneralErreurException();
        }
    }

    @PostMapping(value = "/creerCours")
    private ResponseEntity<Cours> creerCours(@RequestBody Cours cours, @RequestParam boolean isEnseignant,
                                             @RequestParam boolean enseignantApte, @RequestParam int niveauExpEnseignant) throws GeneralErreurException {
        try{
            return new ResponseEntity<>(coursService.creerCours(cours, isEnseignant, enseignantApte, niveauExpEnseignant), HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("Une erreur est survenue : ",e);
            throw new GeneralErreurException();
        }
    }
}

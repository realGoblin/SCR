/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scr;
import  java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
/**
 *
 * @author Goblin
 */
public class menuLecturer {
    ArrayList<lecturer> lect;
    List<lecturer> result;  
    frameLecturer  menuLect   =   new    frameLecturer();
     public static   void    go(String   i){
        menuLecturer ff  =   new menuLecturer();
        ff.start(i);
    }
     public  void    start(String   idlect){
        lect    =   downloadAndSave.downloadL(); 
        result = lect.stream()
        .filter(a -> Objects.equals(a.ID, idlect))
        .collect(Collectors.toList());
        lecturer lectObject  =    (lecturer)    result.get(0);
        menuLect.addGroupAndDiscpInComboBox(lectObject);
        menuLect.setVisible(true);
     }
}

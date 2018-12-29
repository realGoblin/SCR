package scr;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
    
public  class   menuStudent{
    JFrameStudent  menuStud   =   new    JFrameStudent();
    ArrayList<ratings> rat;
    List<ratings> result;    
    public static   void    go(String   i){
        menuStudent ff  =   new menuStudent();
        ff.start(i);
    }
    public  void    start(String   idStudent){
        downloadAndSave.downloadR();
        
        result = rat.stream()
        .filter(a -> Objects.equals(a.stud, idStudent))
        .collect(Collectors.toList());
        
        for(int i   =0;i<result.size();i++){
            ratings ratObject  =    (ratings)    result.get(i);
            menuStud.addGroupInComboBox(ratObject.discp);        
        }               
        menuStud.setVisible(true);
    }
    public static   void    outRa(String   i){
        menuStudent ff  =   new menuStudent();
        ff.outR(i);
    }
    public  void    outR(String dis){
        List<ratings> r;
        r = result.stream()
        .filter(a -> Objects.equals(a.discp, dis))
        .collect(Collectors.toList());
        ratings ratOb   =   r.get(0);
        
    }
}
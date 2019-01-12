package scr;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import  java.awt.event.*;
    
public  class   menuStudent{
    JFrameStudent  menuStud   =   new    JFrameStudent();
    ArrayList<ratings> rat;
    List<ratings> result;    
    public static   void    go(String   i){
        menuStudent ff  =   new menuStudent();
        ff.start(i);
    }
    public  void    start(String   idStudent){
        rat =   downloadAndSave.downloadR();
        
        result = rat.stream()
        .filter(a -> Objects.equals(a.stud, idStudent))
        .collect(Collectors.toList());
        menuStud.jButtonVisiblRat.addActionListener(new  visibleRate());
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
class   visibleRate   implements  ActionListener{
            @Override
            public  void actionPerformed(ActionEvent    event){                
                List<ratings>   res = result.stream()
                .filter(a -> Objects.equals(a.discp, menuStud.JCombo.getSelectedItem()))
                .collect(Collectors.toList());
                ratings rateOb  =   res.get(0);
                menuStud.jLabel2.setText(String.valueOf(rateOb.R1));
                menuStud.jLabel3.setText(String.valueOf(rateOb.R2));
                menuStud.jLabel4.setText(String.valueOf(rateOb.gradPointAverage));
                menuStud.jLabel5.setText("Рэйтинг расчитывается по формуле R1*"+String.valueOf(rateOb.wR1)+"+R2*"+String.valueOf(rateOb.wR2));
            }                
        }
}
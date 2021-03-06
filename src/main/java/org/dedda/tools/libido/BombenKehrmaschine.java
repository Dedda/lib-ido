package org.dedda.tools.libido;

import javax.swing.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;

import static org.dedda.tools.libido.$.*;
import static org.dedda.tools.libido.EreignisseVerarbeiten.loggen;
import static org.dedda.tools.libido.Rechenoperationen.RechneSchnell;
import static org.dedda.tools.libido.Zahlen.Eins;

/**
 * Created by dedda on 10/5/15.
 *
 * @author dedda
 */
public final class BombenKehrmaschine extends JFrame{

    private BombenKehrmaschine $diese = this;

    public BombenKehrmaschine(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        if(!(__gz(_(BombenKehrmaschine.class, "knopfGröße"))> 0)){
            _(BombenKehrmaschine.class, "#knopfGröße", 50);
        }
        if(__gz(_(BombenKehrmaschine.class, "#höhe"))> 0){
            _($diese, "#höhe", __gz(_(BombenKehrmaschine.class, "#höhe")));
        }else{
            _($diese, "#höhe", 10);
        }
        if(__gz(_(BombenKehrmaschine.class, "#breite"))> 0){
            _($diese, "#breite", __gz(_(BombenKehrmaschine.class, "#breite")));
        }else{
            _($diese, "#breite", 20);
        }
        setResizable(false);
        setVisible(true);
        _($diese, "#fensterHöhe", RechneSchnell(_($diese, "#höhe")).mal(_(BombenKehrmaschine.class, "#knopfGröße")).plus(getInsets().top).plus(getInsets().bottom).istGleich());
        _($diese, "#fensterBreite", RechneSchnell(_($diese, "#breite")).mal(_(BombenKehrmaschine.class, "#knopfGröße")).plus(getInsets().left).plus(getInsets().right).istGleich());
        setSize((int)__gz(_($diese, "#fensterBreite")),(int)__gz(_($diese, "#fensterHöhe")));
        setLocationRelativeTo(null);
        $diese.getContentPane().setLayout(null);
        $diese.erstelleDasVirtuelleSpielfeld();
        $diese.erstelleDieKnöpfe();
    }

    private void erstelleDasVirtuelleSpielfeld(){
        _($diese, "#spielfeld", new DasVirtuelleSpielfeld((int)__gz(_($diese, "#höhe")),(int)__gz(_($diese, "#breite"))));
    }

    private void erstelleDieKnöpfe(){
        _($diese, "#yZähler", 0);
        while(__gz(_($diese, "#yZähler"))< __gz(_($diese, "#höhe"))){
            _($diese, "#xZähler", 0);
            while(__gz(_($diese, "#xZähler"))< __gz(_($diese, "#breite"))){
                $diese.getContentPane().add(new SpielfeldKnopf((int)__gz(_($diese, "#xZähler")),(int)__gz(_($diese, "#yZähler")),(DasVirtuelleSpielfeld)_($diese, "#spielfeld")));
                $diese.getContentPane().repaint();
                loggen(0, "Button " + _($diese, "#xZähler")+ ":" + _($diese, "#yZähler"));
                _$($diese, "#xZähler", $z -> RechneSchnell($z).plus(Eins()).istGleich());
            }
            _$($diese, "#yZähler", $z -> RechneSchnell($z).plus(Eins()).istGleich());
        }
    }

    private boolean alleBombenGefunden(){
        return(__gz(_($diese, "#bombenGesamt"))== __gz(_($diese, "#bombenAufgedeckt")));
    }

    private class SpielfeldKnopf extends JButton{

        private SpielfeldKnopf $dieser = this;

        private SpielfeldKnopf(final int $x, final int $y, final DasVirtuelleSpielfeld $spielfeld){
            _($dieser, "#x", $x);
            _($dieser, "#y", $y);
            $dieser.setSize((int)__gz(_(BombenKehrmaschine.class, "#knopfGröße")),(int)__gz(_(BombenKehrmaschine.class, "#knopfGröße")));
            _($dieser, "#xPunkt", __gz(RechneSchnell(_($dieser, "#x")).mal(_(BombenKehrmaschine.class, "#knopfGröße")).istGleich()));
            _($dieser, "#yPunkt", __gz(RechneSchnell(_($dieser, "#y")).mal(_(BombenKehrmaschine.class, "#knopfGröße")).istGleich()));
            $dieser.setLocation((int)__gz(_($dieser, "#xPunkt")),(int)__gz(_($dieser, "#yPunkt")));
            $dieser.addActionListener($ae ->{
                if(__eon(((DasVirtuelleSpielfeld)_($diese, "#spielfeld")).istEineBombe((int)__gz(_($dieser, "#x")),(int)__gz(_($dieser, "#y"))))){
                    $dieser.setText("X");
//                    _($diese, "#bombenAufgedeckt",(int)__gz(RechneSchnell(_($diese, "#bombenAufgedeckt")).plus(Eins()).istGleich()));
                    _$($diese, "#bombenAufgedeckt", $ba ->(int)__gz(RechneSchnell($ba).plus(Eins()).istGleich()));
                    if($diese.alleBombenGefunden()){
                        loggen(0, "Fertig!");
                        JOptionPane.showMessageDialog(null, "Du hast das Spiel gewonnen!");
                        $diese.dispose();
                        new BombenKehrmaschine();
                    }
                }else{
                    _($dieser, "#zahl", 0);
                    if(((DasVirtuelleSpielfeld)_($diese, "#spielfeld")).istEineBombe((int)__gz(RechneSchnell(_($dieser, "#x")).minus(Eins()).istGleich()),(int)__gz(RechneSchnell(_($dieser, "#y")).minus(Eins()).istGleich()))){
                        _($dieser, "#zahl", RechneSchnell(_($dieser, "#zahl")).plus(Eins()).istGleich());
                    }
                    if(((DasVirtuelleSpielfeld)_($diese, "#spielfeld")).istEineBombe((int)_($dieser, "#x"),(int)__gz(RechneSchnell(_($dieser, "#y")).minus(Eins()).istGleich()))){
                        _($dieser, "#zahl", RechneSchnell(_($dieser, "#zahl")).plus(Eins()).istGleich());
                    }
                    if(((DasVirtuelleSpielfeld)_($diese, "#spielfeld")).istEineBombe((int)__gz(RechneSchnell(_($dieser, "#x")).plus(Eins()).istGleich()),(int)__gz(RechneSchnell(_($dieser, "#y")).minus(Eins()).istGleich()))){
                        _($dieser, "#zahl", RechneSchnell(_($dieser, "#zahl")).plus(Eins()).istGleich());
                    }
                    if(((DasVirtuelleSpielfeld)_($diese, "#spielfeld")).istEineBombe((int)__gz(RechneSchnell(_($dieser, "#x")).minus(Eins()).istGleich()),(int)_($dieser, "#y"))){
                        _($dieser, "#zahl", RechneSchnell(_($dieser, "#zahl")).plus(Eins()).istGleich());
                    }
                    if(((DasVirtuelleSpielfeld)_($diese, "#spielfeld")).istEineBombe((int)__gz(RechneSchnell(_($dieser, "#x")).plus(Eins()).istGleich()),(int)_($dieser, "#y"))){
                        _($dieser, "#zahl", RechneSchnell(_($dieser, "#zahl")).plus(Eins()).istGleich());
                    }
                    if(((DasVirtuelleSpielfeld)_($diese, "#spielfeld")).istEineBombe((int)__gz(RechneSchnell(_($dieser, "#x")).minus(Eins()).istGleich()),(int)__gz(RechneSchnell(_($dieser, "#y")).plus(Eins()).istGleich()))){
                        _($dieser, "#zahl", RechneSchnell(_($dieser, "#zahl")).plus(Eins()).istGleich());
                    }
                    if(((DasVirtuelleSpielfeld)_($diese, "#spielfeld")).istEineBombe((int)_($dieser, "#x"),(int)__gz(RechneSchnell(_($dieser, "#y")).plus(Eins()).istGleich()))){
                        _($dieser, "#zahl", RechneSchnell(_($dieser, "#zahl")).plus(Eins()).istGleich());
                    }
                    if(((DasVirtuelleSpielfeld)_($diese, "#spielfeld")).istEineBombe((int)__gz(RechneSchnell(_($dieser, "#x")).plus(Eins()).istGleich()),(int)__gz(RechneSchnell(_($dieser, "#y")).plus(Eins()).istGleich()))){
                        _($dieser, "#zahl", RechneSchnell(_($dieser, "#zahl")).plus(Eins()).istGleich());
                    }
                    $dieser.setText(__t(__gz(_($dieser, "#zahl"))));
                }
                Arrays.stream($dieser.getActionListeners()).forEach($al -> $dieser.removeActionListener($al));
            });
        }

    }

    private class DasVirtuelleSpielfeld{

        private DasVirtuelleSpielfeld $dieses = this;

        private DasVirtuelleSpielfeld(final int $höhe, final int $breite){
            _($diese, "#bombenGesamt", 0);
            _($diese, "#bombenAufgedeckt", 0);
            _($dieses, "#höhe", $höhe);
            _($dieses, "#breite", $breite);
            _($dieses, "#reihen", new ArrayList<ArrayList<Boolean>>());
            _($dieses, "#xZähler", 0);
            while(__gz(_($dieses, "#xZähler"))< __gz(_($dieses, "#breite"))){
                _($dieses, "#yZähler", 0);
               ((ArrayList<ArrayList<Boolean>>)_($dieses, "#reihen")).add(new ArrayList<>());
                while(__gz(_($dieses, "#yZähler"))< __gz(_($dieses, "#höhe"))){
                   ((ArrayList<ArrayList<Boolean>>)_($dieses, "#reihen")).get((int)__gz(_($dieses, "#xZähler"))).add(__zeon());
                    if(istEineBombe((int)__gz(_($dieses, "#xZähler")),(int)__gz(_($dieses, "#yZähler")))){
                        _$($diese, "#bombenGesamt", $bg -> __gz(RechneSchnell($bg).plus(Eins()).istGleich()));
                    }
                    _$($dieses, "#yZähler", $z -> RechneSchnell($z).plus(Eins()).istGleich());
                }
                _$($dieses, "#xZähler", $z -> RechneSchnell($z).plus(Eins()).istGleich());
            }
        }

        private boolean istEineBombe(final int $x, final int $y){
            if($x < 0 || $y < 0){
                return false;
            }
            if($x >= __gz(_($dieses, "#breite"))|| $y >= __gz(_($dieses, "#höhe"))){
                return false;
            }
            return((ArrayList<ArrayList<Boolean>>)_($dieses, "#reihen")).get($x).get($y);
        }

    }
}

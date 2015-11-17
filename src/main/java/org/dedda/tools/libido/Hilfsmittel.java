package org.dedda.tools.libido;

import static org.dedda.tools.libido.$.__t;
import static org.dedda.tools.libido.EreignisseVerarbeiten.loggen;

/**
 * Created by dedda on 9/9/15.
 *
 * @author dedda
 */
public final class Hilfsmittel{

    public static Object[] feld(Object... $objekte){
        return $objekte;
    }

    public static int zähle(final Object[] $feld){
        return $feld.length;
    }

    public static boolean leer(final Object[] $feld){
        return $feld.length == 0;
    }

    public static void echo(final Object $objekt){
        System.out.println($objekt);
        loggen(0, __t($objekt));
    }

    private Hilfsmittel(){

    }

}

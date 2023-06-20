package org.tugas;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        System.out.println( "Hello World!" );
    }
}

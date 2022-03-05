package cz.muni.fi.pb162.project.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Alzbeta Strompova
 */
public class MyMouseListener implements MouseListener {

    private boolean firstClick = true;
    private Point piece;


    @Override
    public void mouseClicked(MouseEvent e) {
        if (firstClick) {
            var point = e.getPoint();
            piece = point;
            System.out.println(point.x);
            System.out.println(point.y);
        } else {
            var point = e.getPoint();
            System.out.println(point.x);
            System.out.println(point.y);
        }
        firstClick = !firstClick;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

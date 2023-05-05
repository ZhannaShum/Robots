package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Target {
    private volatile int m_targetPositionX = 150;
    private volatile int m_targetPositionY = 100;

    public void setTargetPosition(Point p) {
        m_targetPositionX = p.x;
        m_targetPositionY = p.y;
    }

    public int getX() {
        return m_targetPositionX;
    }

    public int getY() {
        return m_targetPositionY;
    }

    public void drawTarget(Graphics2D g) {
        int x = m_targetPositionX;
        int y = m_targetPositionY;
        g.setTransform(DrawingUtils.getResetTransform());
        DrawingUtils.drawFilledOval(g, x, y, 5, 5, Color.GREEN, Color.BLACK);
    }
}

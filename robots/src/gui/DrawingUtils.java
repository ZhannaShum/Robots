package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class DrawingUtils {
    public static double distance(double x1, double y1, double x2, double y2) {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    public static double angleTo(double fromX, double fromY, double toX, double toY) {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    public static double applyLimits(double value, double min, double max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    public static double asNormalizedRadians(double angle) {
        while (angle < 0) {
            angle += 2 * Math.PI;
        }
        while (angle >= 2 * Math.PI) {
            angle -= 2 * Math.PI;
        }
        return angle;
    }

    public static int round(double value) {
        return (int) (value + 0.5);
    }

    public static AffineTransform getRotatedTransform(double angle, int centerX, int centerY) {
        return AffineTransform.getRotateInstance(angle, centerX, centerY);
    }

    public static AffineTransform getResetTransform() {
        return AffineTransform.getRotateInstance(0, 0, 0);
    }

    public static void drawFilledOval(Graphics2D g, int centerX, int centerY, int diam1, int diam2, Color fillColor, Color borderColor) {
        g.setColor(fillColor);
        fillOval(g, centerX, centerY, diam1, diam2);
        g.setColor(borderColor);
        drawOval(g, centerX, centerY, diam1, diam2);
    }

    private static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }
}

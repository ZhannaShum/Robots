package gui;

import java.awt.Color;
import java.awt.Graphics2D;

public class Robot {
    private volatile double m_robotPositionX = 100;
    private volatile double m_robotPositionY = 100;
    private volatile double m_robotDirection = 0;

    public void updateRobotPosition(Target target) {
        double distance = DrawingUtils.distance(target.getX(), target.getY(),
                m_robotPositionX, m_robotPositionY);
        if (distance < 0.5) {
            return;
        }
        double velocity = Constants.MAX_VELOCITY;
        double angleToTarget = DrawingUtils.angleTo(m_robotPositionX, m_robotPositionY, target.getX(), target.getY());
        double angularVelocity = 0;
        if (angleToTarget > m_robotDirection) {
            angularVelocity = Constants.MAX_ANGULAR_VELOCITY;
        }
        if (angleToTarget < m_robotDirection) {
            angularVelocity = -Constants.MAX_ANGULAR_VELOCITY;
        }

        moveRobot(velocity, angularVelocity, 10);
    }

    private void moveRobot(double velocity, double angularVelocity, double duration) {
        velocity = DrawingUtils.applyLimits(velocity, 0, Constants.MAX_VELOCITY);
        angularVelocity = DrawingUtils.applyLimits(angularVelocity, -Constants.MAX_ANGULAR_VELOCITY, Constants.MAX_ANGULAR_VELOCITY);
        double newX = m_robotPositionX + velocity / angularVelocity *
                (Math.sin(m_robotDirection + angularVelocity * duration) -
                        Math.sin(m_robotDirection));
        if (!Double.isFinite(newX)) {
            newX = m_robotPositionX + velocity * duration * Math.cos(m_robotDirection);
        }
        double newY = m_robotPositionY - velocity / angularVelocity *
                (Math.cos(m_robotDirection + angularVelocity * duration) -
                        Math.cos(m_robotDirection));
        if (!Double.isFinite(newY)) {
            newY = m_robotPositionY + velocity * duration * Math.sin(m_robotDirection);
        }
        m_robotPositionX = newX;
        m_robotPositionY = newY;
        double newDirection = DrawingUtils.asNormalizedRadians(m_robotDirection + angularVelocity * duration);
        m_robotDirection = newDirection;
    }

    public void drawRobot(Graphics2D g) {
        int robotCenterX = DrawingUtils.round(m_robotPositionX);
        int robotCenterY = DrawingUtils.round(m_robotPositionY);
        g.setTransform(DrawingUtils.getRotatedTransform(m_robotDirection, robotCenterX, robotCenterY));
        DrawingUtils.drawFilledOval(g, robotCenterX, robotCenterY, 30, 10, Color.MAGENTA, Color.BLACK);
        DrawingUtils.drawFilledOval(g, robotCenterX + 10, robotCenterY, 5, 5, Color.WHITE, Color.BLACK);
    }
}

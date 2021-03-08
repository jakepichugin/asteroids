package com.company;

import java.util.List;

public enum GunType {

    PISTOL(10, new SingleBulletBehavior()),
    MACHINE_GUN(3, new SingleBulletBehavior()),
    SHOTGUN(15, new BurstBulletBehavior());

    private int frequency;
    private BulletBehavior bulletBehavior;

    GunType(int frequency, BulletBehavior bulletBehavior) {
        this.frequency = frequency;
        this.bulletBehavior = bulletBehavior;
    }

    public int getFrequency() {
        return frequency;
    }

    public BulletBehavior getBulletBehavior() {
        return bulletBehavior;
    }

    interface BulletBehavior {
        void fireBullet(List<Bullet> bulletsList, int x, int y, double angle);
    }

    static class SingleBulletBehavior implements BulletBehavior {
        @Override
        public void fireBullet(List<Bullet> bulletsList, int x, int y, double angle) {
            bulletsList.add(new Bullet(x, y, angle));

        }
    }

    static class BurstBulletBehavior implements BulletBehavior {
        @Override
        public void fireBullet(List<Bullet> bulletsList, int x, int y, double angle) {
            bulletsList.add(new Bullet(x, y, angle + 0.1));
            bulletsList.add(new Bullet(x, y, angle - 0.1));
            bulletsList.add(new Bullet(x, y, angle + 0.2));
            bulletsList.add(new Bullet(x, y, angle - 0.2));
            bulletsList.add(new Bullet(x, y, angle));
        }
    }

}

package at.zombi.shooter.game.util;

// Vector2D implementation of Gunvir Singh Ranu from https://gist.github.com/gunvirranu/6816d65c0231981787ebefd3bdb61f98 at 06.01.2025
public class Vector2D {

    public long x;
    public long y;

    public Vector2D() { }

    public Vector2D(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D v) {
        set(v);
    }

    public void set(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void setZero() {
        x = 0;
        y = 0;
    }

    public long[] getComponents() {
        return new long[]{x, y};
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public long getLengthSq() {
        return (x * x + y * y);
    }

    public long distanceSq(long vx, long vy) {
        vx -= x;
        vy -= y;
        return (vx * vx + vy * vy);
    }

    public long distanceSq(Vector2D v) {
        long vx = v.x - this.x;
        long vy = v.y - this.y;
        return (vx * vx + vy * vy);
    }

    public double distance(long vx, long vy) {
        vx -= x;
        vy -= y;
        return Math.sqrt(vx * vx + vy * vy);
    }

    public double distance(Vector2D v) {
        long vx = v.x - this.x;
        long vy = v.y - this.y;
        return Math.sqrt(vx * vx + vy * vy);
    }

    public double getAngle() {
        return Math.atan2(y, x);
    }

    public void normalize() {
        long magnitude = Math.round(getLength());
        x /= magnitude;
        y /= magnitude;
    }

    public Vector2D getNormalized() {
        long magnitude = Math.round(getLength());
        return new Vector2D(x / magnitude, y / magnitude);
    }

    public static Vector2D toCartesian(long magnitude, long angle) {
        return new Vector2D((long) (magnitude * Math.cos(angle)), (long) (magnitude * Math.sin(angle)));
    }

    public void add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void add(long vx, long vy) {
        this.x += vx;
        this.y += vy;
    }

    public static Vector2D add(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x + v2.x, v1.y + v2.y);
    }

    public Vector2D getAdded(Vector2D v) {
        return new Vector2D(this.x + v.x, this.y + v.y);
    }

    public void subtract(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void subtract(long vx, long vy) {
        this.x -= vx;
        this.y -= vy;
    }

    public static Vector2D subtract(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }

    public Vector2D getSubtracted(Vector2D v) {
        return new Vector2D(this.x - v.x, this.y - v.y);
    }

    public void multiply(long scalar) {
        x *= scalar;
        y *= scalar;
    }

    public Vector2D getMultiplied(long scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }

    public void divide(long scalar) {
        x /= scalar;
        y /= scalar;
    }

    public Vector2D getDivided(long scalar) {
        return new Vector2D(x / scalar, y / scalar);
    }

    public Vector2D getPerp() {
        return new Vector2D(-y, x);
    }

    public long dot(Vector2D v) {
        return (this.x * v.x + this.y * v.y);
    }

    public long dot(long vx, long vy) {
        return (this.x * vx + this.y * vy);
    }

    public static long dot(Vector2D v1, Vector2D v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public long cross(Vector2D v) {
        return (this.x * v.y - this.y * v.x);
    }

    public long cross(long vx, long vy) {
        return (this.x * vy - this.y * vx);
    }

    public static long cross(Vector2D v1, Vector2D v2) {
        return (v1.x * v2.y - v1.y * v2.x);
    }

    public double project(Vector2D v) {
        return (this.dot(v) / this.getLength());
    }

    public double project(long vx, long vy) {
        return (this.dot(vx, vy) / this.getLength());
    }

    public static double project(Vector2D v1, Vector2D v2) {
        return (dot(v1, v2) / v1.getLength());
    }

    public Vector2D getProjectedVector(Vector2D v) {
        return this.getNormalized().getMultiplied(Math.round(this.dot(v) / this.getLength()));
    }

    public Vector2D getProjectedVector(long vx, long vy) {
        return this.getNormalized().getMultiplied(Math.round(this.dot(vx, vy) / this.getLength()));
    }

    public static Vector2D getProjectedVector(Vector2D v1, Vector2D v2) {
        return v1.getNormalized().getMultiplied(Math.round(Vector2D.dot(v1, v2) / v1.getLength()));
    }

    public void rotateBy(long angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double rx = x * cos - y * sin;
        y = Math.round(x * sin + y * cos);
        x = Math.round(rx);
    }

    public Vector2D getRotatedBy(long angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vector2D(Math.round(x * cos - y * sin), Math.round(x * sin + y * cos));
    }

    public void rotateTo(long angle) {
        set(toCartesian(Math.round(getLength()), angle));
    }

    public Vector2D getRotatedTo(long angle) {
        return toCartesian(Math.round(getLength()), angle);
    }

    public void reverse() {
        x = -x;
        y = -y;
    }

    public Vector2D getReversed() {
        return new Vector2D(-x, -y);
    }

    @Override
    public Vector2D clone() {
        return new Vector2D(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Vector2D) {
            Vector2D v = (Vector2D) obj;
            return (x == v.x) && (y == v.y);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Vector2d[" + x + ", " + y + "]";
    }
}
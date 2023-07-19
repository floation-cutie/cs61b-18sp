
/**
 * @author floation_cutie
 * @param
 */
public class Planet {
    /* to reduce the chance of typos */
    public double xxPos; // : Its current x position
    public double yyPos; // : Its current y position
    public double xxVel; // : Its current velocity in the x direction
    public double yyVel; // : Its current velocity in the y direction
    public double mass; // : Its mass
    public String imgFileName;
    public final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
            double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /* About copy a object,there are two ways: swallow copy and deep copy */
    public Planet(Planet b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet b2) {
        double distance;
        double dx = b2.xxPos - this.xxPos;
        double dy = b2.yyPos - this.yyPos;
        distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }

    public double calcForceExertedBy(Planet b2) {
        double dis = this.calcDistance(b2);
        return (G * this.mass * b2.mass / (dis * dis));
    }

    public double calcForceExertedByX(Planet b2) {
        double dx = b2.xxPos - this.xxPos;
        double dis = this.calcDistance(b2);
        double Force = this.calcForceExertedBy(b2);
        return (Force * dx / dis);
    }

    public double calcForceExertedByY(Planet b2) {
        double dy = b2.yyPos - this.yyPos;
        double dis = this.calcDistance(b2);
        double Force = this.calcForceExertedBy(b2);
        return (Force * dy / dis);
    }

    public double calcNetForceExertedByX(Planet[] b_all) {
        double NetForceExertedByX = 0;
        for (Planet b_item : b_all) {
            if (!this.equals(b_item)) {
                NetForceExertedByX += this.calcForceExertedByX(b_item);
            }
        }
        return NetForceExertedByX;
    }

    public double calcNetForceExertedByY(Planet[] b_all) {
        double NetForceExertedByY = 0;
        for (Planet b_item : b_all) {
            if (!this.equals(b_item)) {
                NetForceExertedByY += this.calcForceExertedByY(b_item);
            }
        }
        return NetForceExertedByY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        this.xxVel += aX * dt;
        this.yyVel += aY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images\\" + this.imgFileName);
    }
}
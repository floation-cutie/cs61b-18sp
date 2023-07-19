public class NBody {

    private static final String Background = "images\\starfield.jpg";
    private static double T;
    private static double dt;
    private static String file;
    private static double universe_radius;

    public static double readRadius(String s) {
        In in = new In(s);
        in.readInt();
        double radius = in.readDouble();
        in.close();
        return radius;
    }

    public static Planet[] readPlanets(String s) {
        In in = new In(s);
        int num = in.readInt();
        Planet[] all_bodies = new Planet[num];
        in.readDouble();
        for (int i = 0; i < num; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            all_bodies[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        in.close();
        return all_bodies;
    }

    public static void main(String[] args) {

        /* Collecting All Needed Input */
        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        file = args[2];
        universe_radius = readRadius(file);
        Planet[] allBodies = readPlanets(file);

        /* Drawing the Background */
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-universe_radius, universe_radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, Background, 2 * universe_radius, 2 * universe_radius);
        StdDraw.show();

        /* dont let the background cover up the objects aka planets */

        for (Planet planet : allBodies) {
            planet.draw();
        }
        StdDraw.show();

        /* create an Animation */
        int num = allBodies.length;
        double time = 0;
        while (time < T) {
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for (int i = 0; i < num; i++) {
                xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
                yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);
            }
            for (int i = 0; i < num; i++) {
                allBodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, Background, 2 * universe_radius, 2 * universe_radius);
            for (Planet planet : allBodies) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", num);
        StdOut.printf("%.2e\n", universe_radius);
        for (int i = 0; i < num; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allBodies[i].xxPos, allBodies[i].yyPos, allBodies[i].xxVel,
                    allBodies[i].yyVel, allBodies[i].mass, allBodies[i].imgFileName);
        }
    }
}

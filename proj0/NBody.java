public class NBody{
    public static double readRadius(String filename){
        In in = new In(filename);
        int planetnumber = in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int planetnumber = in.readInt();
        double Radius = in.readDouble();
        Planet[] Planets = new Planet[planetnumber]; 
        for (int j = 0;j<planetnumber;j++){
            Planets[j] = new Planet(0,0,0,0,0,null);
            Planets[j].xxPos = in.readDouble();
            Planets[j].yyPos = in.readDouble();
            Planets[j].xxVel = in.readDouble();
            Planets[j].yyVel = in.readDouble();
            Planets[j].mass = in.readDouble();
            Planets[j].imgFileName = in.readString();
        }
        return Planets;
    }

    public static void main(String args[]){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.show();

        for(int i = 0;i < planets.length;i++){
            planets[i].draw();
        }

        StdDraw.enableDoubleBuffering();
        double[] xforces = new double[planets.length];
        double[] yforces = new double[planets.length];

        for (double time = 0 ; time < T;time += dt){
            for(int j = 0;j < planets.length;j++){
                xforces[j] = planets[j].calcNetForceExertedByX(planets);
                yforces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for(int j = 0;j < planets.length;j++){
                planets[j].update(dt,xforces[j],yforces[j]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(int i = 0;i < planets.length;i++){
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
    }
}
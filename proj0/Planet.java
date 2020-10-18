import java.lang.Math; 
    public class Planet{
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    private static double G = 6.67e-11;

    /** used for construct a Plnaet instance with an existing instance */
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /** used to construct a Planet instance with specific parameter */
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;  // does the reference type should be construct differently?
    }

    /** used to calculate the distance between two planet */
    public double calcDistance(Planet planet_y){
        double dx = planet_y.xxPos - this.xxPos;
        double dy = planet_y.yyPos - this.yyPos;
        double r_2 = dx * dx + dy *dy;
        double r = Math.sqrt(r_2);
        return r;
    }

    /** used to calculate the force between two planet  */
    public double calcForceExertedBy(Planet planet_y){
        double distance = this.calcDistance(planet_y);
        double force = G * this.mass * planet_y.mass/distance/distance;
        return force;
    }

    /**used to calculate the force on x axis */
    private double calcForceExertedByX(Planet[] planets){
        double forcesxx = 0;
        int lengthxx = planets.length;
        for (int i = 0;i < lengthxx; i++){
            double dx = planets[i].xxPos - this.xxPos;
    //        double dy = planets[i].yyPos - this.yyPos;
            forcesxx += this.calcForceExertedBy(planets[i])*(dx/this.calcDistance(planets[i]));
        }
        return forcesxx;
    }


    public double calcForceExertedByX(Planet planet){
        double forcesxx = 0;
        double dx = planet.xxPos - this.xxPos;
    //    double dy = planet.yyPos - this.yyPos;
        forcesxx += this.calcForceExertedBy(planet)*(dx/this.calcDistance(planet));
        return forcesxx;
    }
    

    /**used to calculate the force on y axis */
    private double calcForceExertedByY(Planet[] planets){
        double forcesyy = 0;
        int lengthyy = planets.length;
        for (int i = 0;i < lengthyy; i++){
    //        double dx = planets[i].xxPos - this.xxPos;
            double dy = planets[i].yyPos - this.yyPos;
            forcesyy += this.calcForceExertedBy(planets[i])*(dy/this.calcDistance(planets[i]));
        }
        return forcesyy;
    }

    public double calcForceExertedByY(Planet planet){
        double forcesyy = 0;
    //    double dx = planet.xxPos - this.xxPos;
        double dy = planet.yyPos - this.yyPos;
        forcesyy += this.calcForceExertedBy(planet)*(dy/this.calcDistance(planet));
        return forcesyy;
    }
    
    /**used to calculate the net force on x axis(dont include the original planet*/
    public double calcNetForceExertedByX(Planet[] planets){
        int length = planets.length;
        double netForcexx = 0;
        for (int i = 0;i < length;i++ ){
            if (this == planets[i]) continue;
            netForcexx += this. calcForceExertedByX(planets[i]);
        }
        return netForcexx;
    }

    /**used to calculate the net force on y axis(dont include the original planet*/
    public double calcNetForceExertedByY(Planet[] planets){
        int length = planets.length;
        double netForceyy = 0;
        for (int i = 0;i < length;i++ ){
            if (this == planets[i]) continue;
            netForceyy += this. calcForceExertedByY(planets[i]);
        }
        return netForceyy;
    }

    /** used to approximately update the position of planet in dt tiem with foce fX&fY on x&y axises */
    public void update(double dt,double fX,double fY){
        double ax = fX/this.mass;
        double ay = fY/this.mass;
        this.xxVel = this.xxVel + ax*dt;
        this.yyVel = this.yyVel + ay*dt;
        this.xxPos = this.xxPos + this.xxVel*dt;
        this.yyPos = this.yyPos + this.yyVel*dt;
    }

    /** used to draw the planet */
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}



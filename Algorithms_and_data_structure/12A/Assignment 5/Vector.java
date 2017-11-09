class Vector {
    public int sum;
    public int diff;
    public double mag;
    public double angle;
    public double x;
    public double y;

    public Vector(){
        int x = 0;
        int y = 0;
    }

    //set parameters
    public Vector(double i, double j){
        x=i;
        y=j;
    }

    //add brackets to the vector expression
    public String toString(){
        return("<" + this.x + "," + this.y +">");
    }

    //function calculating the sum of the vectors
    public Vector sum(Vector a){
        Vector s = new Vector(this.x + a.x, this.y + a.y);
        return s;
    }

    //fuction calculating the difference of the vectors
    public Vector diff(Vector a){
        Vector s = new Vector(this.x - a.x, this.y - a.y);
        return s;
    }

    //function calculating the magnitude of the vector
    public double mag(){
        double mag = Math.sqrt(this.x * this.x + this.y * this.y);
        return mag;

    }

    //function calculating the scalar product
    public Vector scalar(Vector a, int b){
        Vector s = new Vector(this.x * b, this.y * b);
        return s;
    }

    //function calculating the dot product
    public double dot(Vector a){
        double d = this.x * a.x + this.y * a.y;
        return d;
    }

    //function calculating the angle between two vectors
    public double angle(Vector a){
        double angle = Math.toDegrees(Math.acos( this.dot(a)/(this.mag()*a.mag()) ));
        return angle;
    }
}
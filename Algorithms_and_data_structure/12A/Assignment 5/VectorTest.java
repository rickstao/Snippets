/*  This program was done by myself (Ruikang Tao). 
    I spent about 3 hours working on this program. Thank you for taking your time
    to grade this assignment.
*/

/**
    *Assignment #5.
    *This program generates a class taht contains vector calculations.
    *Author: Ruikang Tao (rtao6@ucsc.edu)
*/
/**
 * Created by ruikangtao on 11/29/16.
 */
class VectorTest{
    public static void main(String[]args){
        Vector a = new Vector(1.1,2.8);
        Vector b = new Vector(8.2,6.3);
        int c =5;

        System.out.println("The sum of two vectors is " + a.sum(b));
        System.out.println("The difference of two vectors is " + a.diff(b));
        System.out.println("The magnitude is " + a.mag());
        System.out.println("The scalar product is " + a.scalar(b,c));
        System.out.println("The dot product is " + a.dot(b));
        System.out.println("The angle between two vectors is " + a.angle(b));
    }
}

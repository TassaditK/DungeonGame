package util;

public class Vector2D {

    public float x;
    public float y;

    public static float wordX;
    public static float wordY;

    public Vector2D() {
        x=0;
        y=0;
    }
    public Vector2D(Vector2D position) {
        new Vector2D(position.x,position.y);
    }
    public Vector2D(float x,float y){
        this.x=x;
        this.y=y;
    }

    public void addX(float i){x+=i;}
    public void addY(float i ){y+=i;}

    public void setX(float i){x=i;}
    public void setY(float i){y=i;}

    public void setVector(Vector2D vector){
        this.x=vector.x;
        this.y =vector.y;
    }

    public static void setWorldVar(float x,float y){
        wordX=x;
        wordY=y;
    }


    public Vector2D getWorldVar(){
        return new Vector2D(x-wordX,y-wordY);
    }

    public Vector2D getCamVar(){
        return new Vector2D(x+wordX,y+wordY);
    }

    @Override
    public String toString(){
        return "("+x+","+y+")" ;
    }

}

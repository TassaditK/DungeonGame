package util;

import Entity.Entity;

public class ColisionAABB {
    private  Vector2D position;
    private float xOffset=0;
    private float m_yOffset =0;
    private  float m_width;
    private  float m_height;
    private  float area;
    private  int size;
    public Entity entity;

    public ColisionAABB(Vector2D vector2D, int radiusCircle) {
        this.position=vector2D;
        this.area=radiusCircle;
    }

    public float getM_yOffset() {
        return m_yOffset;
    }

    public float getxOffset() {
        return xOffset;
    }

    public ColisionAABB(float m_yOffset) {
        this.m_yOffset = m_yOffset;
    }

    public ColisionAABB(Vector2D position, int width, int height) {
        this.position = position;
        this.m_width = width;
        this.m_height = height;
        size =Math.max(width,height);
    }
    public ColisionAABB(Vector2D position, int area, Entity entity){
        this.position=position;
        this.area=area;
        this.entity=entity;
        size=area;
    }

    public Vector2D getPosition(){return position;}
    public float getRadius(){return area;}
    public float getM_width(){return m_width;}
    public float getM_height(){return m_height;}


    public void setBox(Vector2D position,int width , int height){
        this.position=position;
        this.m_width =width;
        this.m_height =height;

        size=Math.max(width,height);
    }

    public void setCircle(Vector2D position ,int area){
        this.position=position;
        this.area=area;

        size=area;

    }

    public void setM_width(float width){m_width = m_width;}
    public void setM_height(float height){m_height = m_height;}

    public void setXOffset(float xOffsset){xOffset=xOffsset;}
    public void setYOffset(float yOffset){ m_yOffset=yOffset;}

    public boolean collides(ColisionAABB collideEntitybBox ){
        float ax=((position.getWorldVar().x+(xOffset))+(m_width /2));
        float ay=((position.getWorldVar().y)+(m_yOffset)+(m_height /2));

        float bx=((collideEntitybBox.getPosition().getWorldVar().x)+(collideEntitybBox.xOffset/2)+(m_width /2));
        float by=((collideEntitybBox.getPosition().getWorldVar().y)+(collideEntitybBox.m_yOffset /2)+(m_height /2));

        if(Math.abs(ax-bx)<(this.m_width /2) + (collideEntitybBox.m_width /2)){
            if(Math.abs(ay-by)<(this.m_height /2)+(collideEntitybBox.m_height /2)){
                return true;
            }
        }
        return false;
    }

    public float distance(Vector2D other) {
        float dx = position.x - other.x;
        float dy = position.y - other.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public boolean colCircleBox(ColisionAABB colisionAABB){
        float dx = Math.max(colisionAABB.getPosition().x+colisionAABB.getxOffset(),Math.min(position.x+(area/2),colisionAABB.getPosition().x+colisionAABB.getxOffset()+colisionAABB.getM_width()));
        float dy = Math.max(colisionAABB.getPosition().y+colisionAABB.getM_yOffset(),Math.min(position.y+(area/2),colisionAABB.getPosition().y+colisionAABB.getM_yOffset()+colisionAABB.getM_height()));

        dx= position.x+(area/2)-dx;
        dy= position.y+(area/2)-dy;

        if(Math.sqrt(dx*dx+dy*dy)<area/2){
            return true;
        }
        return false;



    }

    public boolean inside(double xp, double yp) {
        if(xp == -1 || yp == - 1) return false;

        int wTemp = (int) this.m_width;
        int hTemp = (int) this.m_height;
        int x = (int) this.position.x;
        int y = (int) this.position.y;

        if(xp < x || yp < y) {
            return false;
        }

        wTemp += x;
        hTemp += y;
        return ((wTemp < x || wTemp > xp) && (hTemp < y || hTemp > yp));
    }



}

import javafx.scene.paint.Color;


/**
 * This class holds information about players in the game
 */
public class Players {

    /**
     * Name of player
     */
    private String name;
    /**
     * Red Component of color
     */
    private double red;
    /**
     * Green Component of color
     */
    private double green;
    /**
     * Blue Component of color
     */
    private double blue;
    /**
     * Color of orb
     */
    private Color color;
    private boolean visited;

    public Players(String name1,Color color1){
        this.name = name1;
        this.red = color1.getRed();
        this.green = color1.getGreen();
        this.blue = color1.getBlue();
        this.color = color1;
        this.visited = false;
    }
    public Players(String name1,Color color1, double r, double g, double b){
        this.name = name1;
        this.red = r;
        this.green = g;
        this.blue = b;
        this.color = color1;
        this.visited = false;
    }

    public void setName(String name1) {
        this.name = name1;
    }

    public void setColor(Color color1){
        this.color = color1;
        this.red = color1.getRed();
        this.blue = color1.getBlue();
        this.green = color1.getGreen();
    }

    public Color getColor() {
        return color;
    }



    public String getName() {
        return name;
    }

//    public float getBlue() {
//        return blue;
//    }
//
//    public float getGreen() {
//        return green;
//    }
//
//    public float getRed() {
//        return red;
//    }
//
//    public void setBlue(float blue1) {
//        this.blue = blue1;
//    }
//
//    public void setGreen(float green1) {
//        this.green = green1;
//    }
//
//    public void setRed(float red1) {
//        this.red = red1;
//    }

}

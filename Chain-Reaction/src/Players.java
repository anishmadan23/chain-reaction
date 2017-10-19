public class Players {
    private String name;
    private float red;
    private float green;
    private float blue;

    public Players(String name1, float r, float g, float b){
        this.name = name1;
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public void setName(String name1) {
        this.name = name1;
    }

    public String getName() {
        return name;
    }

    public float getBlue() {
        return blue;
    }

    public float getGreen() {
        return green;
    }

    public float getRed() {
        return red;
    }

    public void setBlue(float blue1) {
        this.blue = blue1;
    }

    public void setGreen(float green1) {
        this.green = green1;
    }

    public void setRed(float red1) {
        this.red = red1;
    }

}

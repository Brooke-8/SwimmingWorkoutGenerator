package src;
import java.util.Random;

public class SetComponent {
    private int componentDistance;
    private int reps;
    private Stroke stroke;
    private int seconds;

    //Constructor
    public SetComponent(){
        componentDistance = 0;
        reps = 0;
        seconds = 0;
        stroke = Stroke.FREE;
    }
    public SetComponent(int reps, int distance, Stroke stroke){
        this.componentDistance = distance;
        this.stroke = stroke;
        this.reps = reps;
        this.seconds = this.calculateSeconds();

    }

    //Getters 
    public int getComponentDistance(){return this.componentDistance;}
    public int getReps(){return this.reps;}
    public Stroke getStroke(){return this.stroke;}
    public int getSeconds(){return this.seconds;}

    //Setters
    public void setComponentDistance(int componentDistance){this.componentDistance = componentDistance;}
    public void setReps(int reps){this.reps = reps;}
    public void setStroke(Stroke stroke){this.stroke = stroke;}
    public void setSeconds(int seconds){this.seconds=seconds;}

    public int randomDistance(int min,int max){
        Random r = new Random();
        int d = (int)Math.round((r.nextInt(max-min)+min)/25)*25;
        this.componentDistance = d;
        this.seconds = this.calculateSeconds();
        return d;
    }
    public Stroke randomStroke(){
        Stroke s = Stroke.randStroke();
        this.stroke = s;
        this.seconds = this.calculateSeconds();
        return s;
    }
    public Stroke randomStroke(Stroke[] include){
        Stroke s = Stroke.randStroke(include);
        this.stroke = s;
        this.seconds = this.calculateSeconds();
        return s;
    }
    public int randomReps(int min, int max){
        Random r= new Random();
        int reps = r.nextInt(max-min)+min;
        this.reps = reps;
        this.seconds = this.calculateSeconds();
        return reps;
    }

    private int calculateSeconds(){
        int pace = this.getStroke().pace;
        int distance = this.componentDistance;
        int seconds = 5 * (int) Math.round(((pace / 100.0) * distance) / 5);
        return seconds;
    } 
    public String secondsToString(){
        int t = this.seconds;
        int minutes = Math.floorDiv(t, 60);
        int s = t - (60*minutes);
        if (s <=9){
            return (minutes+ ":"+s+"0");
        }
        return (minutes+":"+s);
    }
      
}


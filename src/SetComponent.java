package src;
import java.util.Random;

public class SetComponent {
    private int componentDistance;
    private int reps;
    private Stroke stroke;

    public SetComponent(){}
    public SetComponent(int distance, Stroke stroke,int reps){
        this.componentDistance = distance;
        this.stroke = stroke;
        this.reps = reps;
    }
    public void randomDistance(int min,int max){
        Random r = new Random();
        int d = (int)Math.round((r.nextInt(max-min)+min)/25)*25;
        this.componentDistance = d;
    }
    public void randomStroke(){
        Stroke s = Stroke.randStroke();
        this.stroke = s;
    }
    public void randomReps(int min, int max){
        Random r= new Random();
        int reps = r.nextInt(max-min)+min;
        this.reps = reps;
    }
      
}


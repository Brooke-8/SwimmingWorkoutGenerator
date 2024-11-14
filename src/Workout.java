package src;
import java.util.ArrayList;

/*
 * @author Brooke MacQuarrie
 * Used to make a set with a given setType, number of components, and perferred distance
 */
public class Workout {
    private int distance;
    private String[][] format;
    private String title;
    private ArrayList<Set> sets;

    private Workout(WorkoutBuilder workout){
        this.distance = workout.distance;
        this.format = workout.format;
        this.title = workout.title;
        this.sets = workout.sets;

    }

    public static class WorkoutBuilder{
        protected int distance;
        protected int targetDistance;
        protected String[][] format;
        protected String title;
        protected ArrayList<Set> sets;

        public WorkoutBuilder(int targetDistance, String[][] format, String title){
            this.targetDistance = targetDistance;
            this.distance = 0;
            this.format = format;
            this.title = title;
            this.sets = new ArrayList<Set>(format.length);
        }
        public WorkoutBuilder addSet(Set set){
            this.sets.add(set);
            this.distance += set.getDistance();
            return this;
        }
        public WorkoutBuilder addFromFormat(){
            int distancePerSet = 25*Math.round((targetDistance/format.length)/25);
            for (int i = 0; i<format.length;i++){
                Set set = makeSet(format[i][0],Integer.parseInt(format[i][1]),distancePerSet);
                this.sets.add(set);
                this.distance += set.getDistance();
            }
            return this;
        }
        public Workout build(){
            return new Workout(this);
        }
    
    }

    public int getDistance() {return distance;}
    public String[][] getFormat() {return format;}
    public String getTitle() {return title;}
    public ArrayList<Set> getSets(){return sets;}

    
    public static Set makeSet(String setType,int numberOfComponents, int targetDistance){
        Set.SetBuilder setBuilder;
        switch(setType){
            case "RANDOMSET": setBuilder = new Set.RandomSetBuilder(targetDistance,1); break;
            case "COOLDOWN": setBuilder = new Set.CooldownSetBuilder(targetDistance,1); break;
            case "WARMUP": setBuilder = new Set.WarmupSetBuilder(targetDistance,1); break;
            default: setBuilder = new Set.RandomSetBuilder(targetDistance,1);
        }
        for (int j = 0; j <numberOfComponents; j++){
            setBuilder.addComponent();
        }
        return (setBuilder.build());
    }

}

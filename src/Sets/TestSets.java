package src.Sets;
import java.util.ArrayList;
import src.*;
public class TestSets {
    public static class RandomSet extends Set{
        public RandomSet(int distance, double multiplier){
            super(distance, multiplier);
            this.reps = 1;
            this.title = "Random";
        }
        public Set generate(){
            ArrayList<Integer> componentDistances = DistanceUtilities.separateTo25s(distance);
            for (int d : componentDistances){
                int componentDistance = DistanceUtilities.random25Factor(d);
                int componentReps = d/componentDistance;
                Stroke componentStroke = Stroke.randStroke();
                SetComponent component = new SetComponent(componentReps, componentDistance,componentStroke, multiplier);
                this.setComponents.add(component);
            }
            mergeSimilarComponents(setComponents);
            return this;
        }
    }

    public static class WarmupSet extends Set{
        public WarmupSet(int distance, double multiplier){
            super(distance, multiplier);
            this.reps = 1;
            this.title = "Warm Up";
        }
        public Set generate(){
            ArrayList<Integer> componentDistances = DistanceUtilities.separateTo25s(distance);
            for (int d : componentDistances){
                int componentDistance = DistanceUtilities.random25Factor(d);
                int componentReps = d/componentDistance;
                Stroke[] warmupStrokes = {Stroke.FREE,Stroke.BACK,Stroke.BREAST, Stroke.IM};
                Stroke componentStroke = Stroke.randStroke(warmupStrokes);
                SetComponent component = new SetComponent(componentReps, componentDistance,componentStroke, multiplier);
                this.setComponents.add(component);
            }
            mergeSimilarComponents(setComponents);
            return this;
        }
        public String toString(){
            StringBuilder str = new StringBuilder();
            for (SetComponent component : setComponents){
                str.append(component.getTotalDistance()+" "+ component.getStroke()+"\n");
            }
            return str.toString();
        }
        protected void mergeSimilarComponents(ArrayList<SetComponent> components){
            for (int i = 1; i < components.size(); i ++){
                SetComponent component1 = components.get(i-1);
                SetComponent component2 = components.get(i);
                if (component1.getStroke() == component2.getStroke()){
                    int newDistance = component1.getTotalDistance() + component2.getTotalDistance();
                    components.remove(i-1);
                    components.remove(i-1);
                    components.add(i-1,new SetComponent(1, newDistance, component1.getStroke(), component1.getMultiplier()));
                }
            }
        }
        
    }

    public static class CooldownSet extends Set{
        public CooldownSet(int distance, double multiplier){
            super(distance, multiplier);
            this.reps = 1;
            this.title = "Cooldown";
        }
        public Set generate(){
            ArrayList<Integer> componentDistances = DistanceUtilities.separateTo25s(distance);
            for (int d : componentDistances){
                int componentDistance = DistanceUtilities.random25Factor(d);
                int componentReps = d/componentDistance;
                Stroke[] slowStrokes = {Stroke.FREE,Stroke.BACK,Stroke.BREAST};
                Stroke componentStroke = Stroke.randStroke(slowStrokes);
                SetComponent component = new SetComponent(componentReps, componentDistance,componentStroke, multiplier);
                this.setComponents.add(component);
            }
            mergeSimilarComponents(setComponents);
            return this;
        }

        protected void mergeSimilarComponents(ArrayList<SetComponent> components){
            for (int i = 1; i < components.size(); i ++){
                SetComponent component1 = components.get(i-1);
                SetComponent component2 = components.get(i);
                if (component1.getStroke() == component2.getStroke()){
                    int newDistance = component1.getTotalDistance() + component2.getTotalDistance();
                    components.remove(i-1);
                    components.remove(i-1);
                    components.add(i-1,new SetComponent(1, newDistance, component1.getStroke(), component1.getMultiplier()));
                }
            }
        }
        public String toString(){
            StringBuilder str = new StringBuilder();
            for (SetComponent component : setComponents){
                str.append(component.getTotalDistance()+" "+ component.getStroke()+"\n");
            }
            return str.toString();
        }
    }
}

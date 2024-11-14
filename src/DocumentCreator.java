package src;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import src.Workout.WorkoutBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/*
 * @author Brooke MacQuarrie
 * DocumentCreator contains the main method that creates the document file, makes a given number of sets 
 * using SetCreator, then formats them into the document
 */

public class DocumentCreator{
    public static void main( String[] args){
        
        //If no argument given, use the default value
        if (args.length == 0){
            System.out.println("Length not given; using default value: "+Settings.DEFAULT_LENGTH);
            args = new String[1];
            args[0] = Settings.DEFAULT_LENGTH;
        }
        if (Integer.parseInt(args[0]) < 0){
            System.out.println("Length provided is invalid; using default value: "+Settings.DEFAULT_LENGTH);
            args[0] = Settings.DEFAULT_LENGTH;
        }

        //Make document, calculate goal set distances
        Document document = new Document();
        int targetDistance = Integer.parseInt(args[0]);
        try{
            //Create and Open document:
            PdfWriter.getInstance(document, new FileOutputStream(Settings.DOCUMENT_NAME));
            document.open();
            
            //Formate Title
            Paragraph title = new Paragraph(Settings.DOCUMENT_TITLE);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            //Creating and Formatting Sets
            WorkoutBuilder workoutBuilder = new WorkoutBuilder(targetDistance,Settings.FORMAT,Settings.DOCUMENT_TITLE);
            workoutBuilder.addFromFormat();
            Workout workout = workoutBuilder.build();

            for (Set set:workout.getSets()){
                Paragraph setDistance = new Paragraph(set.getTitle()+" - "+set.getDistance()+"m:");
                Paragraph setInfo = new Paragraph(set.toString());
                document.add(setDistance);
                document.add(setInfo);
                document.add(new Paragraph("\n"));
            }
            //Add total distance
            Paragraph total = new Paragraph("Total: " +workout.getDistance()+"m");
            total.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(total);
            
        } catch ( DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
        
}
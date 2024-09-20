package src;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
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

        //Make document, calculate goal set distances
        Document document = new Document();
        int goalDistance = Integer.parseInt(args[0]);
        int averageSetDistance = 25*Math.round((goalDistance/Settings.FORMAT.length)/25);

        try{
            //Create and Open document:
            PdfWriter.getInstance(document, new FileOutputStream(Settings.DOCUMENT_NAME));
            document.open();
            
            //Formate Title
            Paragraph title = new Paragraph(Settings.DOCUMENT_TITLE);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            //Creating and Formatting Sets
            SetCreator creator = new SetCreator();
            int totalDistance = 0;
            int countedSets = 0;
            for (int i = 0; i < Settings.FORMAT.length;i++){
                String setType = Settings.FORMAT[i][0];
                int setParts = Integer.parseInt(Settings.FORMAT[i][1]);

                Set set = creator.makeSet(setType, setParts, averageSetDistance);
                if (!(setType.equals("COOLDOWN") || setType.equals("WARMUP"))){countedSets++;}
                Paragraph s = new Paragraph(set.title(countedSets) + set.toString()+"\n");
                document.add(s);
                totalDistance += set.getSetDistance();

            }

            //Add total distance
            Paragraph total = new Paragraph("Total: " +totalDistance);
            total.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(total);
            
        } catch ( DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        
    }
}
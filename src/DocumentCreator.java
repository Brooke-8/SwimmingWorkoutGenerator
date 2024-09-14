package src;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class DocumentCreator{
    public static void main( String[] args){
        if (args.length == 0){
            
        }
        Document document = new Document();
        int goalDistance = Integer.parseInt(args[0]);
        int averageSetDistance = 25*Math.round((goalDistance/Settings.FORMAT.length)/25);

        try{
            //Create and open document:
            PdfWriter.getInstance(document, new FileOutputStream(Settings.DOCUMENT_NAME));
            document.open();
            
            //Formate Title
            Paragraph title = new Paragraph("Workout");
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            //Creating and Formatting Sets
            SetCreator creator = new SetCreator();
            int totalDistance = 0;
            for (int i = 0; i < Settings.FORMAT.length;i++){
                String setType = Settings.FORMAT[i][0];
                int setLength = Integer.parseInt(Settings.FORMAT[i][1]);

                Set set = creator.makeSet(setType, setLength, averageSetDistance);

                Paragraph s = new Paragraph(set.title()+" "+(i+1)+":\n" + set.toString()+"\n");
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
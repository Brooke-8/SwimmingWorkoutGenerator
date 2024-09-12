package src;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class WorkoutGenerator{
    public static void main( String[] args){
        Document document = new Document();

        try{
            PdfWriter.getInstance(document, new FileOutputStream("workout2.pdf"));
            document.open();
            Paragraph title = new Paragraph("Workout2");
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);
            
        } catch ( DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        
    }
}
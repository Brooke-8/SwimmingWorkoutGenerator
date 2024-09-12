package src;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class DocumentCreator{
    public static void main( String[] args){
        Document document = new Document();

        try{
            PdfWriter.getInstance(document, new FileOutputStream("Workout.pdf"));
            document.open();
            Paragraph title = new Paragraph("Workout");
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);
            Set set = new Set();
            for (int i = 0; i <10; i++){
                set.addRandomSetComponent(300);
            }
            Paragraph s = new Paragraph(set.toString());
            document.add(s);
            CoolDownSet cooldown = new CoolDownSet();
            for (int i = 0; i< 5; i++){
                cooldown.addCooldownComponent();
            }
            Paragraph cool = new Paragraph("\nCooldown: \n" + cooldown.toString());
            document.add(cool);
            Paragraph total = new Paragraph("Total: " +String.valueOf(set.getSetDistance()));
            total.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(total);
            
        } catch ( DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        
    }
}
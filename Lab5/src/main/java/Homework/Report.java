package Homework;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class Report {
    public void generateReport(Catalog catalog) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        Template template = velocityEngine.getTemplate("src/main/java/Homework/report.vm");

        VelocityContext context = new VelocityContext();
        context.put("catalog", catalog);

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        String html = writer.toString();

        try {
            FileWriter fileWriter = new FileWriter("template.html");
            fileWriter.write(html);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

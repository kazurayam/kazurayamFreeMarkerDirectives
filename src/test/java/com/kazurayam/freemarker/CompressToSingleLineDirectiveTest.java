package com.kazurayam.freemarker;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompressToSingleLineDirectiveTest extends TestBase {

    public CompressToSingleLineDirectiveTest() throws IOException {
        super();
    }

    @Test
    public void test_execute() throws IOException, TemplateException {
        /* set data into the model */
        List<String> segments = Arrays.asList(
                "   {\"cat\":",
                "\"Nikolai, Marcus and Ume\"",
                "},  \n",
                "   \"greeting\":",
                "\"Hello, world!\"",
                "}   \n");
        model.put("segments", segments);

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("compressToSingleLineDemo.ftlh");

        /* Merge data-model with template */
        Writer out = new StringWriter();
        temp.process(model, out);

        String output = out.toString();
        assertNotNull(output);

        System.out.println("---------------------");
        System.out.println(output);
        System.out.println("---------------------");

        assertTrue(output.startsWith("<span class="));
        assertTrue(output.contains("cat"));
        assertTrue(output.contains("Ume&quot;</span><span"));
        assertTrue(output.endsWith("</span>"));
    }

}

# FreeMarker Directives defined by kazurayam

This project is a collection of FreeMarker user-defined directives
developed by kazurayam.

It was tested with FreeMarker v2.3.31.

## `readAllLines`

The `readAllLines` directive reads a text file specified by the path parameter,
iterate over all lines in it, generate the templated body.
It will provide a loopVariable that contains the line.

Synopsis

    <@readAllLines path="path to the file" ; line>
      <p>${line}</p>
    </@readAllLines>

kazurayam developed `readAllLine` directive for the `materialstore` project.
See <https://github.com/kazurayam/materialstore/issues/191>

### Sample usage

#### Caller Java

**ReadAllLinesDirectivesTest.java**

    package com.kazurayam.freemarker;

    import freemarker.template.Template;
    import freemarker.template.TemplateException;
    import org.junit.jupiter.api.Test;

    import java.io.IOException;
    import java.io.StringWriter;
    import java.io.Writer;

    import static org.junit.jupiter.api.Assertions.assertNotNull;
    import static org.junit.jupiter.api.Assertions.assertTrue;

    public class ReadAllLinesDirectiveTest extends TestBase {

        public ReadAllLinesDirectiveTest() throws IOException {
            super();
        }

        @Test
        public void execute() throws IOException, TemplateException {
            /* Get the template (uses cache internally) */
            Template temp = cfg.getTemplate("readAllLinesDemo.ftl");

            /* Merge data-model with template */
            Writer out = new StringWriter();
            temp.process(model, out);

            String output = out.toString();
            assertNotNull(output);
            assertTrue(output.contains("<tr><td>0</td><td>publishedDate,uri,title,link,description,author</td></tr>"));
            System.out.println(output);
        }
    }

see also [TestBase.java](../src/test/java/com/kazurayam/freemarker/TestBase.java)

#### Template

**readAllLinesDemo.ftl**

    <#-- readAllLinesDemo.ftl -->
    <#-- custom directive name "readAllLines" is defined as a shared variable. See TestBase.java -->
    <#assign x = 0>
    <@readAllLines path="AmznPress/20220310_203757/objects/e96bd4c2e345301b567d70071dcec04fda699ce4.csv"; line>
        <tr><td>${x}</td><td>${line}</td></tr>
        <#assign x++>
    </@readAllLines>

#### Input

-   [sample CSV file](../src/test/fixture/store/AmznPress/20220310_203757/objects/e96bd4c2e345301b567d70071dcec04fda699ce4.csv)

#### Output

        <tr><td>0</td><td>publishedDate,uri,title,link,description,author</td></tr>
        <tr><td>1</td><td>Thu Mar 10 20:00:00 JST 2022,31596,"OOO Until TBD? Majority of Canadian Office Workers Want Remote Work to Stay ",https://press.aboutamazon.com/news-releases/news-release-details/ooo-until-tbd-majority-canadian-office-workers-want-remote-work,"Half of Canadian office workers say working mostly/entirely remote is their ideal scenario; only one-quarter prefer mostly/entirely in office Ability to work remotely and flexible work hours are now more important to office workers than workplace culture, development/growth opportunities and","Amazon.com, Inc. - Press Room News Releases"</td></tr>

    ... (trimmed)

## TestBase

The test classes here extends `TestBase.java` which prepares
the configuration of FreeMarker.
Please note that some sharedVariables are declared here,
which includes the name of directives (e.g, `readAllLines`).

**TestBase**

    package com.kazurayam.freemarker;

    import freemarker.template.Configuration;
    import freemarker.template.TemplateException;
    import freemarker.template.TemplateExceptionHandler;
    import freemarker.template.TemplateModelException;
    import org.junit.jupiter.api.extension.ExtensionContext;

    import java.io.IOException;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.HashMap;
    import java.util.Map;

    public class TestBase {

        protected Configuration cfg;
        protected Map<String, Object> model;

        public TestBase() throws IOException {

            Path projectDir = Paths.get(System.getProperty("user.dir"));

            /* ---------------------------------------------------------- */
            /* You should do this ONLY ONCE in the whole application lifecycle */

            /* Create and adjust the configuration singleton */
            cfg = new Configuration(Configuration.VERSION_2_3_31);

            Path templatesDir = projectDir.resolve("src/test/resources/freemarker_templates");
            cfg.setDirectoryForTemplateLoading(templatesDir.toFile());

            // Recommended settings for new projects:
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
            cfg.setBooleanFormat("c");
            cfg.setOutputEncoding("UTF-8");

            // add custom directives
            try {
                cfg.setSharedVariable("readAllLines", new com.kazurayam.freemarker.ReadAllLinesDirective());
                Path store = projectDir.resolve("src/test/fixture").resolve("store");
                cfg.setSharedVariable("store", store.normalize().toAbsolutePath().toString());
            } catch (TemplateModelException e) {
                throw new RuntimeException(e);
            }

            /* ---------------------------------------------------------- */
            /* You usually do these for MULTIPLE TIMES in the application life-cycle: */

            /* Create a data-model */
            model = new HashMap<>();
        }
    }

## Reference

1.  [FreeMarker Programmer’s Guide / The Data Model / Directives](https://freemarker.apache.org/docs/pgui_datamodel_directive.html)

package viewtest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Color;
import model.PhotoAlbum;
import model.Rectangle;
import model.Oval;
import model.Point;
import model.IShape;
import view.WebView;

public class WebViewTest {
  private PhotoAlbum album;
  private WebView webView;

  /**
   * Sets up the test fixture.
   */
  @BeforeEach
  public void setUp() {
    album = new PhotoAlbum();
    webView = new WebView(album);
  }

  /**
   * Tests the basic HTML structure.
   */
  @Test
  public void testBasicHtmlStructure() {
    album.takeSnapshot("Test Snapshot");
    String htmlOutput = webView.generateHtml();
    assertTrue(htmlOutput.startsWith("<!DOCTYPE html>"), "HTML should start with <!DOCTYPE>");
    assertTrue(htmlOutput.contains("<html"), "HTML should contain <html> tag");
    assertTrue(htmlOutput.contains("<head>"), "HTML should contain <head> tag");
    assertTrue(htmlOutput.contains("<body>"), "HTML should contain <body> tag");
    assertTrue(htmlOutput.contains("</html>"), "HTML should contain closing </html> tag");
  }

  /**
   * Tests the snapshot details.
   */
  @Test
  public void testSnapshotDetails() {
    album.takeSnapshot("Test Snapshot");
    String htmlOutput = webView.generateHtml();
    assertTrue(htmlOutput.contains("Test Snapshot"), "HTML should contain snapshot description");
  }

  /**
   * Tests the SVG graphics rendering.
   */
  @Test
  public void testSvgGraphicsRendering() {
    IShape rect = new Rectangle("rect", new Point(10, 20), 100.0, 200.0, new Color(255, 0, 0));
    IShape oval = new Oval("oval", new Point(30, 40), 50.0, 80.0, new Color(0, 255, 0));

    album.addShape(oval);
    album.addShape(rect);
    album.takeSnapshot("Another Snapshot with Shapes");

    String htmlOutput = webView.generateHtml();
    System.out.println(htmlOutput);
    assertTrue(htmlOutput.contains("<rect"), "HTML should include rectangle SVG element");
    assertTrue(htmlOutput.contains("width=\"100.000000\" height=\"200.000000\" fill=\"rgb(255,0,0)\""),
      "HTML should include correct attributes for rectangle");
    assertTrue(htmlOutput.contains("<ellipse"), "HTML should include ellipse SVG element");
    assertTrue(htmlOutput.contains("rx=\"50.000000\" ry=\"80.000000\" fill=\"rgb(0,255,0)\""),
      "HTML should include correct attributes for ellipse");
  }

  /**
   * Tests an empty model.
   */
  @Test
  public void testEmptyModel() {
    String htmlOutput = webView.generateHtml();
    assertTrue(htmlOutput.contains("<body>"), "HTML should still contain basic structure with empty model");
  }
}

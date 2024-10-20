package org.patricia.huanca;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;

public class GeneradorDirectorios {

    public static void crearEstructuraDesdeXML(String xmlFilePath, String rootDir) {
        try {
            File fXmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList plataformas = doc.getElementsByTagName("Plataforma");

            for (int i = 0; i < plataformas.getLength(); i++) {
                Node plataforma = plataformas.item(i);
                if (plataforma.getNodeType() == Node.ELEMENT_NODE) {
                    Element plataformaElement = (Element) plataforma;
                    String platformName = plataformaElement.getAttribute("nombre");

                    File plataformaDir = new File(rootDir + "/" + platformName);
                    plataformaDir.mkdirs();

                    FileWriter writer = new FileWriter(plataformaDir.getPath() + "/" + platformName + ".txt");

                    NodeList juegos = plataformaElement.getElementsByTagName("Juego");
                    for (int j = 0; j < juegos.getLength(); j++) {
                        Element juegoElement = (Element) juegos.item(j);
                        String nombre = juegoElement.getElementsByTagName("Nombre").item(0).getTextContent();
                        String developer = juegoElement.getElementsByTagName("Developer").item(0).getTextContent();

                        writer.write(nombre + " - " + developer + "\n");
                    }
                    writer.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


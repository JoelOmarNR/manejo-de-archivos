package org.patricia.huanca;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import java.util.*;
import java.io.*;

public class GeneradorXML {

    public static void generarXML(List<Juego> juegos, String filePath) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("Plataformas");
            doc.appendChild(rootElement);

            Map<String, List<Juego>> juegosPorPlataforma = new HashMap<>();
            for (Juego juego : juegos) {
                juegosPorPlataforma.computeIfAbsent(juego.getPlatform(), k -> new ArrayList<>()).add(juego);
            }

            for (Map.Entry<String, List<Juego>> entry : juegosPorPlataforma.entrySet()) {
                Element plataforma = doc.createElement("Plataforma");
                plataforma.setAttribute("nombre", entry.getKey());
                rootElement.appendChild(plataforma);

                for (Juego juego : entry.getValue()) {
                    Element juegoElement = doc.createElement("Juego");
                    Element nombre = doc.createElement("Nombre");
                    nombre.appendChild(doc.createTextNode(juego.getGame()));
                    Element developer = doc.createElement("Developer");
                    developer.appendChild(doc.createTextNode(juego.getDeveloper()));

                    juegoElement.appendChild(nombre);
                    juegoElement.appendChild(developer);
                    plataforma.appendChild(juegoElement);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


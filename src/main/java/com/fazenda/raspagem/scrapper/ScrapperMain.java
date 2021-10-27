package com.fazenda.raspagem.scrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazenda.raspagem.scrapper.entity.all;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ScrapperMain {
    public static void main(String[] args) throws IOException {

        // Site a ser acessado
        String url = "http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx";

        // Conectando e obtendo uma cópia do html inteiro da página
        Document doc = Jsoup.connect(url).get();

        Element tbody = doc.getElementsByClass("tabelaListagemDados").first();
        //System.out.println(tbody.text());

        //Criando uma lista de elementos
        List<Element> qbs = tbody.getElementsByClass("linhaImparCentralizada");

        //Lista de objetos
        List<all> qbsObjetos = new ArrayList<>();


        for (Element qb: qbs) {
                List<Element> atributos = qb.getElementsByTag("td");

                all allitens = new all(atributos.get(0).text(),atributos.get(1).html(),atributos.get(2).html(),
                        atributos.get(3).html(),atributos.get(4).html(),atributos.get(5).html(),atributos.get(6).html(),
                        atributos.get(7).html(),atributos.get(8).html());

                qbsObjetos.add(allitens);
        }
        for (all qb: qbsObjetos) {
            enviarQb(converterToJson(qb));
        }
    }
    //converter uma entidade que contam all itens em um texto json

        public static String converterToJson(all qb) throws JsonProcessingException {

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(qb);
        }


    public static void enviarQb(String  qbJson) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/viasoft");

        StringEntity entity = new StringEntity(qbJson);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        client.close();

    }

}

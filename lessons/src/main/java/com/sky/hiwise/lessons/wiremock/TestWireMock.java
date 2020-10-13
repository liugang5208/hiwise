package com.sky.hiwise.lessons.wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class TestWireMock {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8888);


    @Test
    public void exampleTest() {
        wireMockRule.stubFor(get(urlEqualTo("/my/resource"))
                //.withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse().withBody("test")
                        .withStatus(200)));
        wireMockRule.start();

    }

    public static void main(String[] args) {

        configureFor(9999);
        stubFor(get(urlEqualTo("/my/resource"))
                //.withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse().withBody("test")
                        .withStatus(200)));
    }
}

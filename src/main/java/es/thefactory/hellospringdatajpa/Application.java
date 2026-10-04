package es.thefactory.hellospringdatajpa;

import es.thefactory.hellospringdatajpa.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Pablo Lorenzo Manzano.
 */
public class Application {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}

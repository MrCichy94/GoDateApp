package pl.cichy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("place")
public class PlaceConfigurationProperties {

    private Template template;

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(final Template template) {
        this.template = template;
    }

    public static class Template {
        private boolean allowMultiplePlaces;

        public boolean isAllowMultiplePlaces() {
            return allowMultiplePlaces;
        }

        public void setAllowMultiplePlaces(final boolean allowMultiplePlaces) {
            this.allowMultiplePlaces = allowMultiplePlaces;
        }
    }
}

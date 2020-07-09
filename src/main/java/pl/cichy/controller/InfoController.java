package pl.cichy.controller;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cichy.PlaceConfigurationProperties;

@RestController
@RequestMapping(value = "/info")
public class InfoController {

    private DataSourceProperties dataSource;
    private PlaceConfigurationProperties myProp;

    public InfoController(final DataSourceProperties dataSource,
                          final PlaceConfigurationProperties myProp) {
        this.dataSource = dataSource;
        this.myProp = myProp;
    }

    @GetMapping("/url")
    String url(){ return dataSource.getUrl(); }

    @GetMapping("/prop")
    boolean myProp(){ return myProp.getTemplate().isAllowMultiplePlaces(); }

}

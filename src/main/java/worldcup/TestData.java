package worldcup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import worldcup.service.FootballerService;
import worldcup.service.RepresentationService;

@Component
public class TestData {
    FootballerService footballerService;
    RepresentationService representationService;

    @Autowired
    public TestData(FootballerService footballerService, RepresentationService representationService) {
        this.footballerService = footballerService;
        this.representationService = representationService;
    }

//    @PostConstruct
//    public void init() {
//        Representation representation1 = new Representation(new RepresentationDTO("National Team A", "NTA", 1990, 5));
//        Representation representation2 = new Representation(new RepresentationDTO("National Team B", "NTB", 1995, 0));
//
//        representationService.save(representation1);
//        representationService.save(representation2);
//
//        Footballer footballer1 = new Footballer("John Doe", 1990, 50, representation1);
//        Footballer footballer2 = new Footballer("Jane Smith", 1992, 30, representation1);
//        Footballer footballer3 = new Footballer("David Williams", 1988, 40, representation2);
//
//        footballerService.save(footballer1);
//        footballerService.save(footballer2);
//        footballerService.save(footballer3);
//    }
}

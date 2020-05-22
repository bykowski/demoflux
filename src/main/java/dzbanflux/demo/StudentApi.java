package dzbanflux.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StudentApi {

    Flux<Student> studentFlux;

    public StudentApi() {
        studentFlux = Flux.just(
                new Student("Janek", "Dzbanek"),
                new Student("Ania", "Łania"),
                new Student("Łukasz", "Kuchasz"),
                new Student("Justyna", "Chityna"));
    }

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Student> get() {
        return studentFlux;
    }

    @PostMapping
    public Flux<Student> create(@RequestBody Student student) {
        return studentFlux.mergeWith(Flux.just(student));
    }



}

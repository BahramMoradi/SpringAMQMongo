package dk.bam.demo.controller.mongocrud;

import com.mongodb.client.result.DeleteResult;
import dk.bam.demo.domain.MongoDocument;
import dk.bam.demo.services.crud.MongoCrudService;
import dk.bam.demo.services.validations.MongoDocumentValidator;
import dk.bam.demo.utils.models.MongoDocumentCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/documents")
public class MongoCrudController {
    private MongoCrudService crudService;
    private MongoDocumentValidator validator;

    @Autowired
    public void setCrudService(MongoCrudService crudService) {
        this.crudService = crudService;
    }

    @Autowired
    public void setValidator(MongoDocumentValidator validator) {
        this.validator = validator;
    }

    @GetMapping()
    public ResponseEntity<List<MongoDocument>> getAll() {
        log.info("Try to find all documents");
        return new ResponseEntity<>(crudService.all(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> add(@RequestBody MongoDocumentCommand cmd) {
        log.info("Try to add (save) document with information:" + cmd.toString());
        crudService.create(cmd);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable String id) {
        log.info("Try to retrieve a document with id: " + id);
        if (validator.isIdValid(id)) {
            MongoDocument document = crudService.get(id);
            if (document == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity(document, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity("id parameter is not valid", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> update(@RequestBody MongoDocumentCommand cmd) {
        log.info("Try to update document with info: " + cmd.toString());
        if (!validator.isMongoDocumentCommandIdValid(cmd)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MongoDocument document = crudService.update(cmd);
        if (document == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        log.info("Try to delete document with id: " + id);
        if (!validator.isIdValid(id)) {
            log.error("id is not valid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            DeleteResult result = crudService.delete(id);
            if (result.getDeletedCount() > 0) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
}

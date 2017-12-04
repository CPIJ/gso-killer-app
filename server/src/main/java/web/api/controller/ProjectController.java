package web.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import data.model.Project;
import web.ProjectManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<Project>>> all() {
        return new ResponseEntity<>(new HashMap<String, List<Project>>() {{
            put("Live Project", ProjectManager.getAll());
        }}, HttpStatus.OK);
    }
}

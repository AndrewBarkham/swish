package org.swish.svc.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@RestController
public class DatabaseController {

    @Autowired
    private H2DataAccessObject h2DataAccessObject;

    @RequestMapping("/")
    public String index() {
        return "<b>Springboot Database</b>" +
                "<p>Useage:</p>" +
                "<p>POST using rest client http://localhost:8080/insert/Andrew/Barkham</p>" +
                "<p>/view</p>";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view() {
        String results = "";
        try {
            results = h2DataAccessObject.getResults();
        } catch (SQLException se) {
            System.out.println(se);
        }

        return results;
    }

    @RequestMapping(value = "/insert/{firstName}/{surName}", method = RequestMethod.POST)
    public String insert(@PathVariable("firstName")String firstName, @PathVariable("surName")String surName) {
        try {
            h2DataAccessObject.insertIntoDatabase(firstName, surName);
        } catch (SQLException se) {
            System.out.println(se);
        }

        return "Record inserted";
    }

    @RequestMapping(value = "/update/{id}/{firstName}/{surName}", method = RequestMethod.PUT)
    public String update(@PathVariable("id")String id, @PathVariable("firstName")String firstName, @PathVariable("surName")String surName) {
        try {
            h2DataAccessObject.updateRecordInDatabase(Integer.parseInt(id), firstName, surName);
        } catch (SQLException se) {
            System.out.println(se);
        }

        return "Record updated";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id")String id) {
        try {
            h2DataAccessObject.deleteRecordFromDatabase(Integer.parseInt(id));
        } catch (SQLException se) {
            System.out.println(se);
        }

        return "Record deleted";
    }
}

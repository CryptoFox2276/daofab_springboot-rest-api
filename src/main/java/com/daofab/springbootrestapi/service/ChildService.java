package com.daofab.springbootrestapi.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.daofab.springbootrestapi.model.Child;
import com.daofab.springbootrestapi.model.ChildList;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChildService implements IChildService {
    private String _mFileName = "Child.json";

    @Override
    public List<Child> findAll() {
        
        ChildList childs = new ChildList();
        childs = json2Object(_mFileName, ChildList.class);

        return childs.getData();
    }

    @Override
    public Child findById(int id) {

        Child child = null;

        List<Child> childs = this.findAll();

        for( int index = 0 ; index < childs.size() ; index ++ ) {
            if(childs.get(index).getId() == id) {
                child = childs.get(index);
                break;
            }
        }

        return child;
    }
    
    public static <T>T json2Object(String fileName, Class<T> classType) {
        T t = null;

        File file = new File("src/main/resources/" + fileName);

        try {

            ObjectMapper mapper = new ObjectMapper();

            t = mapper.readValue(file, classType);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }
    
}

package com.example.demo4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo4.models.JavaStartExplorer;
import com.example.demo4.repository.JavaStartExplorerRepository;

@Service
public class JavaStartExplorerServicesImpl implements JavaStartExplorerServices{

    @Autowired
    JavaStartExplorerRepository javaStartExplorerRepository;

    @Override
    public void enviarDatos(JavaStartExplorer javaStartExplorer) {

      javaStartExplorerRepository.save(javaStartExplorer);

    }
}

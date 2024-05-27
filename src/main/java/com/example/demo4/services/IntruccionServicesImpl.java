package com.example.demo4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo4.models.Intruccion;
import com.example.demo4.repository.IntruccionRepository;

@Service
public class IntruccionServicesImpl implements IntruccionServices{
  @Autowired
    IntruccionRepository intruccionRepository;
    @Override
    public void enviarDatos(Intruccion intruccion) {

        intruccionRepository.save(intruccion);

    }

}

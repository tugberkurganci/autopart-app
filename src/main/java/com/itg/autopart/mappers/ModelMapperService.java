package com.itg.autopart.mappers;

import org.modelmapper.ModelMapper;


public interface ModelMapperService {

    ModelMapper forResponse();
    ModelMapper forRequest();
}

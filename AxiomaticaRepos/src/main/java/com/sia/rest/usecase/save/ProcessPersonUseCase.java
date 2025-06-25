package com.sia.rest.usecase.save;

import com.sia.rest.usecase.save.dto.ProcessPersonResponse;
import com.sia.rest.entity.Person;

public interface ProcessPersonUseCase {
    ProcessPersonResponse execute(Person person);
}
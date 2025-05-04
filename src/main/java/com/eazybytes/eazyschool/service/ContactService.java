package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.Contact;
import lombok.extern.slf4j.Slf4j;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.stereotype.Service;


/*
    @Slf4j is annotation provided by lombok ,which will provided us logger object(with name log)
     to log necessary information.
* */

@Service
@Slf4j
public class ContactService {

//  private Logger log= LoggerFactory.getLogger(ContactService.class);

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved=false;
        log.info("Contact details :"+contact );
        return true;

    }
}

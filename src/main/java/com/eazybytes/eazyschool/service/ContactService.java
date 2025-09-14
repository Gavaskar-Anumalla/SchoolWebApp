package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


/*
    @Slf4j is annotation provided by lombok ,which will provided us logger object(with name log)
     to log necessary information.
* */

@Service
@Slf4j
public class ContactService {

//  private Logger log= LoggerFactory.getLogger(ContactService.class);

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ClientHttpRequestFactorySettings clientHttpRequestFactorySettings;

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        log.info("Contact details :" + contact);
        contact.setStatus(EazySchoolConstants.OPEN);
        contact.setCreatedAt(LocalDateTime.now());
        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        int result = contactRepository.saveContact(contact);
        if (result > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contactMsgs = contactRepository.findMsgsWithOpenStatus(EazySchoolConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId, String name) {
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId, EazySchoolConstants.CLOSE, name);
        if (result > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}

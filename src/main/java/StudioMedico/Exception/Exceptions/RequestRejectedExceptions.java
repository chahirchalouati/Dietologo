/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudioMedico.Exception.Exceptions;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.security.web.firewall.RequestRejectedException;

/**
 *
 * @author Chahir Chalouati
 */
@Slf4j

public class RequestRejectedExceptions extends RequestRejectedException {

    public RequestRejectedExceptions(String message) {
        super(message);
        log.info("Simple log statement with inputs {}, {} and {}", 1, 2, 3);
    }

}

/*

 Licensed to the Apache Software Foundation (ASF) under one or more
 contributor license agreements.  See the NOTICE file distributed with
 this work for additional information regarding copyright ownership.
 The ASF licenses this file to You under the Apache License, Version 2.0
 (the "License"); you may not use this file except in compliance with
 the License.  You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package org.superbiz.struts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.Transactional;
import java.util.Properties;

@Component
public class FindUser {

    Logger logger=LoggerFactory.getLogger(getClass());

    private final UserService service;

    public FindUser(UserService service) {
        this.service = service;
    }

    private long id;
    private String errorMessage;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String execute() {

        try {

            logger.debug("inside Finduser {}", id);

            this.user = service.find(id);

            logger.debug("Result", this.user.getFirstName());
        } catch (Exception e) {
            this.errorMessage = e.getMessage();
            return "failure";
        }

        return "success";
    }
}

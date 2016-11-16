/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cs5200.geppetto.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cs5200.geppetto.dao.*;
import cs5200.geppetto.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.apache.log4j.Logger;


@Controller
public class WelcomeController {

	static Logger log = Logger.getLogger(WelcomeController.class.getName());

	@Autowired
	private LobbyIndustryDao lobbyIndustryDao;

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		log.info("===========");
        log.info("Hello World");
		log.info("===========");
//		List<LobbyIndustry> exampleLobbyIndustries= lobbyIndustryDao.getByClient("1-800 Contacts");
//		log.info(exampleLobbyIndustries);
//		model.put("exampleList", exampleLobbyIndustries);
		model.put("time", new Date());
		model.put("message", this.message);

		return "welcome";
	}
}
